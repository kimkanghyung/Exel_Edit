import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

class Info {
	
	public int seq_num;
	public int gubun;
	public String coluom_name;
	
	public Info(int seq_num,int gubun,String coluom_name){
		this.seq_num = seq_num;
		this.gubun = gubun;
		this.coluom_name = coluom_name;
	}
	
	
}

class InfoTableModel extends AbstractTableModel{
    private static final long serialVersionUID = 7932826462497464190L;
    public ArrayList<Info> pages;
 
    public InfoTableModel(){
        pages = new ArrayList<Info>();
    }
    public int getColumnCount() {
        return 2;
    }
    public int getRowCount() {
        return pages.size();
    }
    public void addInfo(Info page){
        int idx = pages.size();
        pages.add(page);
        fireTableRowsInserted(idx, idx); // 반드시 호출해야한다.
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        Info info = pages.get(rowIndex);
        switch (columnIndex) {
        case 0 :
            return info.seq_num;
        case 1 :
            return info.gubun;
        case 2 :
            return info.coluom_name;
        default :
                return "invalid";
        }
    }
}

public class Exel_edit extends JFrame
{
	
	Container cp;
	////////////원본파일//////////////////
	JTextField jptopta; //원본파일열기 파일경로
	JButton jptopbt1;  //원본파일 선택하는 버튼
	JTable jt_top;
	Object rowData1[][] = {{"1","1 번",false}};
	public ArrayList<Info> page1;
	
	////////////첨부파일/////////////////
	JTextField jpbottomta; //첨부파일열기 파일경로
	JButton jpbottombt1; //첨부파일 선택하는 버튼
	JTable jt_bottom;
	Object rowData2[][]= {{"1","1 번",false},{"2","2 번",true} };
	
	/////////////join////////////////
	JButton jp_direct_right ;
	JButton jp_direct_left ;
	Object rowData3[][] ;
	
	/////////////결과출력////////////////
	JTextArea result_textarea;
	///////////전역변수//////////////
	int display_cnt = 0;

	public Exel_edit(){
		
		page1 = new ArrayList<Info>();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setSize(1000,700);
		cp = super.getContentPane();
		this.setDesign();
		//rowData1 = null;

        setVisible(true);
		
	}
	
	
	public void setDesign() {
		
		JPanel jmain = new JPanel(); //메인 페널
		
		GridBagLayout gbl = new GridBagLayout();
		jmain.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		
		
		///////////////////원본패널/////////////////////////
		JPanel jptop = new JPanel();  //원본파일 패널
		JPanel s_jptop = new JPanel(); // 원본파일 안 패널
		//jptop_cell = new JTextField();
		JScrollPane jsp1 = new JScrollPane();
		String org_header[] = {"번호","컬럼명","chk"};
		//rowData1 = {{"1","1 번",false},{"2","2 번",true} };
		jsp1 = make_table(org_header,rowData1);
		//make_table(String header[] ,Object list[][] )
		
		JLabel jptoplb = new JLabel("원본파일"); //원본파일 라벨
		jptopbt1 = new JButton("파일열기");
		jptopta = new JTextField(10);
	    jptopta.setEditable(false);
		jptop.setBorder(new TitledBorder("원복 엑셀파일"));
		jptop.setLayout(new BorderLayout());
		
		s_jptop.add(jptoplb);
		s_jptop.add(jptopta);
		s_jptop.add(jptopbt1);
		
		jptop.add("North",s_jptop);
		jptop.add("Center",jsp1);
		
	
		
		///////////////////첨부파일////////////////////
		
		JPanel jpbottom = new JPanel();
		JPanel s_jpbottom = new JPanel(); // 원본파일 안 패널
		//jpbottom_cell = new JTextField();
		JScrollPane jsp2 = new JScrollPane();
		String add_header[] = {"번호","컬럼명","chk"};
		//rowData2 = null;
		//rowData2 = 	{{"1","1 번",false},{"2","2 번",true} };
		jsp2 = make_table(add_header,rowData2);
		
		
		JLabel jpbottomlb = new JLabel("첨부파일"); //원본파일 라벨
		jpbottombt1 = new JButton("파일열기");
		jpbottomta = new JTextField(10);
		jpbottomta.setEditable(false);
		jpbottom.setBorder(new TitledBorder("첨부 엑셀파일"));	
		jpbottom.setLayout(new BorderLayout());
		
		s_jpbottom.add(jpbottomlb);
		s_jpbottom.add(jpbottomta);
		s_jpbottom.add(jpbottombt1);
		
		jpbottom.add("North",s_jpbottom);
		jpbottom.add("Center",jsp2);
		
		//////////////////////join 패널////////////////////////
		JPanel jpjoin = new JPanel();
		JPanel s_jpradio = new JPanel();
		JPanel s_jpjoin = new JPanel();
		ButtonGroup bg=new ButtonGroup();
		JRadioButton s_jradiobt = new JRadioButton("join");
		JRadioButton s_display = new JRadioButton("출력");
		
		bg.add(s_jradiobt);
		bg.add(s_display);
		
		jpjoin.setLayout(new BorderLayout());
		jpjoin.setBorder(new TitledBorder("Join/출력조건"));
		
		s_jpradio.add(s_jradiobt);
		s_jpradio.add(s_display);
		
		
		
		//jpjoin.setLayout(new BorderLayout());
		// s_jpjoin.setBorder(new TitledBorder("Join조건"));
		JScrollPane jsp_join = new JScrollPane();
		String join_header[] = {"원본번호","원본컬럼","첨부번호","첨부컬럼","chk"};
		rowData3 = null;
		jsp_join = make_table(join_header,rowData3);
		s_jpjoin.add(jsp_join);
		// s_jpradio.add(jsp_join);
		
		jpjoin.add("North",s_jpradio);
		jpjoin.add("Center",s_jpjoin);
		
		
		///////////////////////화살표//////////////////////////
		JPanel jp_direct = new  JPanel();
		jp_direct.setBorder(new TitledBorder(""));
		jp_direct_right = new JButton(">>");
		jp_direct_left = new JButton("<<");
		Box box = Box.createVerticalBox(); 
		box.add(Box.createVerticalStrut(130));
		box.add(jp_direct_right);
		box.add(Box.createVerticalStrut(10));
		box.add(jp_direct_left);
		box.add(Box.createVerticalStrut(100));

		jp_direct.add(box);
		//jp_direct.add( jp_direct_left);
		//버튼 이벤트 처리
		jp_direct_right.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(rowData3 !=null){
				for(int i =0; i < rowData1.length; i++){
					
					
					for(int j =0; j < rowData3.length; j++){
						if(rowData1[i][0].equals(rowData3[j][0])){
							
						}
						else{
							
							rowData3[display_cnt][0] = rowData1[i][0]; 	
							display_cnt++;
							
						}
					}
				  } //for end 
				}//if(rowData3 !=null)
				else {
					for(int t =0; t < rowData1.length; t++){
						System.out.println(rowData1[t]);
						rowData3[display_cnt] = rowData1[t];
						display_cnt++;
						//System.out.println(t);
						//System.out.println(t);
					}
				}
			} 
	    });
		
		jp_direct_left.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		////////////////////cp처리//////////////////////////
		cp.setLayout(new GridLayout(2,1));
		
		
		addGrid(gbl, gbc, jptop     , 0, 0, 1, 1, 10, 1,jmain);
		addGrid(gbl, gbc, jp_direct , 1, 0, 1, 2, 1, 1,jmain);
		addGrid(gbl, gbc, jpjoin    , 2, 0, 1, 2, 15, 1,jmain);
		addGrid(gbl, gbc, jpbottom  , 0, 1, 1, 1, 10, 1,jmain);
		

		//////////////////////////////////////
		JPanel jresult = new JPanel(); // 결과 페널 맨하단
		result_textarea = new JTextArea();
		jresult.setBorder(new TitledBorder("결과출력"));
		jresult.add(result_textarea);
		
		
		cp.add(jmain);
		cp.add(jresult);
		
	}
	
/*	public void actionPerformed(ActionEvent e)
	{
		//액션 리스너 재정의
		if (e.getSource().equals(jp_direct_right))
		{
			System.out.println("jp_direct_right");
		}
		else if(e.getSource().equals(jp_direct_left))
		{
			System.out.println("jp_direct_left");
		}
	}	
*/	
	
	public JScrollPane make_table(String header[] ,Object list[][] ){
		
		//JTable jt = new JTable(list,header);		
		JCheckBox checkBox = new JCheckBox();
		DefaultTableModel dtm = new DefaultTableModel(list, header);
		JTable jt = new JTable(dtm);
		//System.out.println();
		if(list == null) {
			
		}
		else {
		jt.getColumn("chk").setCellRenderer(dcr);
		}
		jt.getColumn("chk").setCellEditor(new DefaultCellEditor(checkBox));
		checkBox.setHorizontalAlignment(JLabel.CENTER);
		JScrollPane jsp = new JScrollPane(jt);
		return jsp;	
		
	}
	
	DefaultTableCellRenderer dcr = new DefaultTableCellRenderer()
	 {
	  public Component getTableCellRendererComponent  // 셀렌더러
	   (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	  {
	   JCheckBox box= new JCheckBox();
	   box.setSelected(((Boolean)value).booleanValue());  
	   box.setHorizontalAlignment(JLabel.CENTER);
	   return box;
	  }
	};
	
	private void addGrid(GridBagLayout gbl, GridBagConstraints gbc, Component c,  
            int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty,JPanel jp) {
      gbc.gridx = gridx;
      gbc.gridy = gridy;
      gbc.gridwidth = gridwidth;
      gbc.gridheight = gridheight;
      gbc.weightx = weightx;
      gbc.weighty = weighty;
      gbl.setConstraints(c, gbc);
      jp.add(c);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Exel_edit();
		
	}


	

}