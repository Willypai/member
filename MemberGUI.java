import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// 定義性別的列舉型態
enum Sex {
    MALE, FEMALE
}

// 定義血型的列舉型態
enum BloodType {
    A, B, O, AB
}

// 會員類別
class Member {
    private String username; // 會員名稱
    private Sex sex; // 會員性別
    private BloodType bloodType; // 會員血型

    // 會員建構子
    public Member(String username, Sex sex, BloodType bloodType) {
        this.username = username;
        this.sex = sex;
        this.bloodType = bloodType;
    }

    // 取得會員名稱
    public String getUsername() {
        return username;
    }

    // 取得會員性別
    public Sex getSex() {
        return sex;
    }

    // 取得會員血型
    public BloodType getBloodType() {
        return bloodType;
    }

    // 覆寫toString方法，用於顯示會員資訊
    @Override
    public String toString() {
        return "Member: " + username + ", Sex: " + sex + ", BloodType: " + bloodType;
    }
}

// 會員資料管理的GUI介面
public class MemberGUI {
    private List<Member> members = new ArrayList<>(); // 會員資料串列
    private DefaultListModel<String> memberListModel = new DefaultListModel<>(); // 用於JList的資料模型
    private JFrame frame; // 主視窗
    private JTextField usernameField; // 輸入會員名稱的文字方塊
    private JRadioButton maleRadio, femaleRadio; // 性別選擇的Radio按鈕
    private JComboBox<BloodType> bloodTypeComboBox; // 血型選擇的下拉式選單
    private JList<String> memberList; // 顯示會員資料的JList

    // 會員GUI介面的建構子
    public MemberGUI() {
        frame = new JFrame("Users SingUp"); // 設定視窗標題
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定關閉視窗時的動作
        frame.setLayout(new BorderLayout()); // 使用邊界佈局管理器

        // 建立輸入介面
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        inputPanel.add(new JLabel("Sex:"));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(maleRadio);
        sexGroup.add(femaleRadio);
        JPanel sexPanel = new JPanel();
        sexPanel.add(maleRadio);
        sexPanel.add(femaleRadio);
        inputPanel.add(sexPanel);

        inputPanel.add(new JLabel("BloodType:"));
        BloodType[] bloodTypes = BloodType.values();
        bloodTypeComboBox = new JComboBox<>(bloodTypes);
        inputPanel.add(bloodTypeComboBox);

        JButton addButton = new JButton("新增"); // 新增按鈕
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText(); // 取得會員名稱
                Sex sex = maleRadio.isSelected() ? Sex.MALE : Sex.FEMALE; // 取得性別
                BloodType bloodType = (BloodType) bloodTypeComboBox.getSelectedItem(); // 取得血型

                Member member = new Member(username, sex, bloodType); // 建立會員物件
                members.add(member); // 加入會員串列
                memberListModel.addElement(member.toString()); // 將會員資料加入JList的資料模型
            }
        });

        inputPanel.add(addButton); // 新增按鈕加入輸入介面

        frame.add(inputPanel, BorderLayout.NORTH); // 將輸入介面加入視窗的上方

        memberList = new JList<>(memberListModel); // 建立JList並使用資料模型
        frame.add(new JScrollPane(memberList), BorderLayout.CENTER); // 將JList加入視窗的中央，並加入捲軸

        frame.pack(); // 自動調整視窗大小
        frame.setVisible(true); // 顯示視窗
    }

    // 主程式，用於啟動GUI介面
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MemberGUI(); // 建立會員GUI介面
            }
        });
    }
}
