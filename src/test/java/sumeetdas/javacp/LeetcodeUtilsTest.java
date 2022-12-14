package sumeetdas.javacp;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class LeetcodeUtilsTest {
    @Test
    public void testGetLevelFirstTreeRepresentation() {
        // var util = new LeetcodeUtils();
    }

    @Test
    public void testGetRootNodeFromLevelFirstArray() {
        var root = LeetcodeUtils.getRootNodeFromLevelFirstArray(new Integer[] {4,2,7,1,3,6,9});
        var list = LeetcodeUtils.getLevelFirstTreeRepresentation(root);
        Assert.assertEquals("level first tree representation should be [4,2,7,1,3,6,9]", 
        Arrays.asList(4,2,7,1,3,6,9), list);
    }
}
