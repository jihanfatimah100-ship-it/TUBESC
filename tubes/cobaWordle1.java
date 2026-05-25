package tubes;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;


public class cobaWordle1 implements ActionListener{

    public List<String> getWordList() {
    List<String> wordList = new ArrayList<>();

    wordList.addAll(Arrays.asList(
        "APPLE","ANGEL","ALBUM","ALIEN","ADULT","ABOVE","ACTOR","ACORN","ALONE","AMBER",
        "BREAD","BRAVE","BLACK","BLINK","BRICK","BRAIN","BEACH","BERRY","BIRTH","BROWN",
        "CHAIR","CLOUD","CANDY","CLOCK","CLEAN","CROWN","CRANE","CHEST","CYCLE","COVER",
        "DREAM","DRIVE","DANCE","DOUBT","DRINK","DEPTH","DOZEN","DELTA","DEVIL","DIRTY",
        "EARTH","ENJOY","EMPTY","ELDER","EVENT","EAGLE","ENTRY","ERROR","EXACT","ELITE",
        "FLAME","FRUIT","FRESH","FRAME","FIELD","FIBER","FLOOD","FORCE","FUNNY","FAITH",
        "GRAPE","GIANT","GHOST","GLASS","GREEN","GRASS","GUIDE","GROUP","GLOVE","GOING",
        "HOUSE","HEART","HAPPY","HONEY","HORSE","HUMAN","HOTEL","HABIT","HEAVY","HUMOR",
        "INDEX","IMAGE","IVORY","IDEAL","INNER","ISSUE","INPUT","IRONY","ICING","IGLOO",
        "JUICE","JELLY","JOKER","JUDGE","JUMBO","JAPAN","JEWEL","JOINT","JUNKO","JOLLY",
        "KNIFE","KOALA","KNOCK","KNOWN","KNEEL","KARMA","KITTY","KIOSK","KAYAK","KIDDO",
        "LIGHT","LEMON","LUCKY","LEVEL","LEARN","LUNCH","LASER","LOCAL","LIVER","LOTUS",
        "MANGO","MUSIC","MOUSE","MOTOR","MAGIC","METAL","MARCH","MONEY","MODEL","MOUTH",
        "NURSE","NIGHT","NOBLE","NORTH","NEVER","NOVEL","NOISE","NAKED","NINJA","NERVE",
        "OCEAN","OLIVE","ORDER","OPERA","ORBIT","OFFER","ORGAN","OUTER","OWNER","OXIDE",
        "PIZZA","PLANT","POWER","PEACH","PHONE","PARTY","PILOT","PRIDE","PRESS","POINT",
        "QUEEN","QUICK","QUIET","QUART","QUERY","QUILT","QUOTE","QUEST","QUACK","QURAN",
        "RIVER","ROBOT","RADIO","ROUTE","ROUND","RANCH","RHYME","RULER","ROUGH","ROYAL",
        "SNAKE","SUGAR","SMART","SMILE","STONE","SHEEP","SOUND","SHINE","SCORE","SUPER",
        "TABLE","TIGER","TRAIN","TRUCK","TEACH","THEME","THUMB","TITLE","TOAST","TOWER",
        "UNITY","ULTRA","UNCLE","UNDER","UPPER","URBAN","USAGE","UTTER","UPSET","UNION",
        "VIDEO","VISIT","VOICE","VIRUS","VALUE","VIVID","VOTER","VENOM","VAPOR","VAULT",
        "WATER","WORLD","WOMAN","WATCH","WHITE","WHEEL","WHALE","WORTH","WRONG","WOUND",
        "XENON","XYLEM","XEROX","XYSTI","XENIA",
        "YOUNG","YEAST","YACHT","YUMMY","YOURS","YIELD","YOGUR","YAHOO","YOKEL","YOUNI",
        "ZEBRA","ZONAL","ZESTY","ZIPPY","ZEBEC","ZORRO","ZILCH","ZAPPY","ZONED","ZYGOT"));

    return wordList;
}
    
    class WordPanel extends JPanel{
        JLabel[]wordColumns = new JLabel[5];
        
        public WordPanel(){
            this.setLayout(new GridLayout(1,5));
            Border blackBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
            for (int i = 0; i < 5; i++) {
                wordColumns[i] = new JLabel();
                wordColumns[i].setHorizontalAlignment(JLabel.CENTER);
                wordColumns[i].setOpaque(true);
                wordColumns[i].setBorder(blackBorder);
                this.add(wordColumns[i]);
            }
        }
        
        public void clearWordPanel(){
            for (int i = 0; i < 5; i++) {
                wordColumns[i].setText("");
            }
        }
        
        public void setPanelText(String charValue, int position, Color color){
            this.wordColumns[position].setText(charValue);
            this.wordColumns[position].setBackground(color);
        }
    }
    
    class UserPanel extends JPanel{
        private JTextField userInput;
        private JButton okButton;
        
        public UserPanel(){
            this.setLayout(new GridLayout(1,2));
            userInput = new JTextField();
            okButton = new JButton("OK");
            
            this.add(userInput);
            this.add(okButton);
        }
        public JTextField getUserInput(){
            return userInput;
        }
        public JButton getOkButton(){
            return okButton;
        }
        
    }
    
    private JFrame gameFrame;
    private WordPanel[] wordPanelArray = new WordPanel[6];
    private UserPanel userPanel;
    private String wordleString;
    private int count=0;
    
    public cobaWordle1(){
        this.wordleString = getWordleString(); 
        System.out.println("Kata kunci rahasia: " + this.wordleString);
        
        gameFrame = new JFrame("Coba Wordle Game");
        gameFrame.setSize(500,500);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setLayout(new GridLayout(7,1));
        gameFrame.setVisible(true);
        gameFrame.setLocationRelativeTo(null);
        
        for (int i = 0; i < 6; i++) {
            wordPanelArray[i] = new WordPanel();
            gameFrame.add(wordPanelArray[i]);
        }
        userPanel = new UserPanel();
        userPanel.getOkButton().addActionListener(this);
        gameFrame.add(userPanel);
        gameFrame.revalidate();
        
        wordleString = getWordleString();
        System.out.println("Word for the day : "+wordleString);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String userWord = this.userPanel.getUserInput().getText();
        
        if(userWord.length()>4){
            if(isWordleWordEqualTo(userWord.trim().toUpperCase())){
                clearAllPanels();
                JOptionPane.showMessageDialog(null, "!!!You Win!!!", "Congrats", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(count>5){
                JOptionPane.showMessageDialog(null, "You Lost! Better Luck Next Time", "Better Luck Next Time", JOptionPane.INFORMATION_MESSAGE);
                gameFrame.dispose();
                return;
            }
            count++;
        }
    }
    
    private void clearAllPanels(){
        for (int i = 0; i < count; i++) {
            wordPanelArray[i].clearWordPanel();
        }
    }
    
    private boolean isWordleWordEqualTo(String userWord) {
        List<String> wordleWordsList = Arrays.asList(wordleString.split(""));
        String[] userWordsArray = userWord.split("");
        List<Boolean> wordMatchList = new ArrayList<>();
        
        for (int i = 0; i < userWordsArray.length; i++) {
            if(wordleWordsList.contains(userWordsArray[i])){
                if(wordleWordsList.get(i).equals(userWordsArray[i])){
                    getActivePanel().setPanelText(userWordsArray[i],i,Color.GREEN);
                    wordMatchList.add(true);
                } else {
                    getActivePanel().setPanelText(userWordsArray[i],i,Color.YELLOW);
                    wordMatchList.add(false);
                }
            } else {
                getActivePanel().setPanelText(userWordsArray[i],i,Color.GRAY);
                wordMatchList.add(false);
            }
        }
        return !wordMatchList.contains(false);
    }
    
    public WordPanel getActivePanel(){
        return this.wordPanelArray[count];
    }
    
    public String getWordleString(){
        List<String> wordList = getWordList();
        Random random = new Random();
        int position = random.nextInt(wordList.size());

        return wordList.get(position).trim().toUpperCase();
    }
    
    public static void main(String[] args) {
        new cobaWordle1();
    }
    
}



