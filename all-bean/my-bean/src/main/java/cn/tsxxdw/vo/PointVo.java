package cn.tsxxdw.vo;

import lombok.Data;

/**
 * 作为显示
 */
@Data
public class PointVo {
    private Double xPoint;
    private Double yPoint;
    private String xPointStr;
    private String yPointStr;
    private Long xySquareLong;

    public PointVo(Double xPoint, Double yPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }

    public PointVo(String xPointStr, String yPointStr) {
        this.xPointStr = xPointStr;
        this.yPointStr = yPointStr;
    }
        public static void main(String[] args) {
        double a=1.1d;
        double b=1.9d;
        double a1=-1.1d;
        double b1=-1.9d;
        System.out.println((long) a);
        System.out.println((long)b);
        System.out.println((b1-a1));

            PointVo pointVo=    new PointVo(2.234d,2.234d);
       double xpoint =     pointVo.getXPoint();
            System.out.println(xpoint);

       xpoint+=0.05;
            System.out.println(pointVo.getXPoint());

    }
}
