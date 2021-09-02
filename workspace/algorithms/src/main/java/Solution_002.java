/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */


public class Solution_002 {

    public String replaceSpace (String s) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if(aChar != ' ') {
                stringBuilder.append(aChar);
            } else {
                stringBuilder.append("%20");
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution_002 solution_002 = new Solution_002();
        String s = solution_002.replaceSpace("We are happy!");
        System.out.println(s);
    }
}

/**
 * 需要考虑字符串末尾为空格，开头为空格的情况，所以用s.split分割比较麻烦
 */
