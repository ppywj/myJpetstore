package servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author pp
 * 生成图形验证码
 */
@WebServlet("/ValidateCodeServlet")
public class ValidateCodeServlet extends HttpServlet {
    public static final String CHECK_CODE_KEY = "CHECK_CODE_KEY";
    private static final long serialVersionUID = 1L;

    //设置验证图片的宽度，高度，验证码个数
    private int width = 152;
    private int height = 35;
    private int codeCount = 4;
    //验证码字体高度
    private int fontHeight = height - 2;
    //验证码中的单个字符基线
    private int codeX = width / (codeCount + 2);
    private int codeY = height - 4;
    //验证码由哪些字符组成
    char[] codeSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz23456789".toCharArray();

    //初始化验证码图形属性
    public void init() {
        fontHeight = height - 2;
        codeX = width / (codeCount + 2);
        codeY = height - 4;
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedImage buffImage = null;
        buffImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

// 在buffImage中创建一个2D的图像
        Graphics2D graphics = null;
        graphics = buffImage.createGraphics();

// 设置背景颜色并绘制
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);

        Font font = null;
        font = new Font("", Font.BOLD, fontHeight);
        graphics.setFont(font);

// 绘制验证码背景的矩形边框
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, width - 1, height - 1);

        Random random = new Random();

// 生成并绘制随机的干扰线
        graphics.setColor(Color.GREEN);
        for (int i = 0; i < 15; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(20);
            int yl = random.nextInt(20);
            graphics.drawLine(x, y, x + xl, y + yl);
        }

// 在矩形区域内绘制随机颜色的噪点
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));  // 随机颜色
            graphics.fillRect(x, y, 2, 2);  // 绘制2x2的噪点
        }

// 生成验证码
        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
            // 每个字符使用随机颜色进行绘制
            graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            graphics.drawString(strRand, (i + 1) * codeX+4, codeY);
            randomCode.append(strRand);
        }

// 将生成的验证码存储到session中，以便后续验证
        req.getSession().setAttribute(CHECK_CODE_KEY, randomCode.toString());

// 设置响应头，禁止图像缓存
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

// 将图像写入到输出流
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImage, "jpeg", sos);
        sos.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}