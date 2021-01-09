import NuitrackJavaWrapper.Nuitrack;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Nuitrack.init("");
        System.out.println("version: " + Nuitrack.getVersion());
        System.out.println("DepthProvider: " + Nuitrack.getConfigValue("DefaultModules.DepthProvider"));
        Nuitrack.setConfigValue("DefaultModules.DepthProvider", "RealsenseDepthProvider");
        System.out.println("DepthProvider: " + Nuitrack.getConfigValue("DefaultModules.DepthProvider"));

    }
}