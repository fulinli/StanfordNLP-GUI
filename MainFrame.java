//登录页面
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.stanford.nlp.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
//登录页面
public class MainFrame extends JFrame implements ActionListener{
	Font f1, f2, f3;
	JButton b1, b2, b3;
	
	TextField tf;
	TextArea ta;
    String props="StanfordCoreNLP-chinese.properties";
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation document;    
	public MainFrame() {
		// 创建窗体对象
        super("自然语言处理系统_by_fulinli");
        setLayout(null);
        // 设置窗体属性和布局
        f1=new Font("宋体",Font.BOLD,20);
        f2=new Font("宋体",Font.PLAIN,20);
        f3=new Font("宋体",Font.PLAIN,15);
        this.setBounds(400, 200, 1000, 800);
        //this.setLayout(null);

        JLabel l1 = new JLabel("请输入待解析语句：");
        l1.setFont(f1);
        l1.setBounds(10, 10, 800, 40);
        
        JLabel l2 = new JLabel("解析结果：");
        l2.setFont(f1);
        l2.setBounds(10, 160, 200, 20);
        // 创建文本框
        tf = new TextField(20);
        tf.setBounds(10, 50, 950, 65);
        tf.setFont(f2);
        // 创建按钮
        b1 = new JButton("解析(Parse)");
        b1.setFont(f3);
        b1.setBounds(850,120,125,40);
        b2 = new JButton("词性标注(POS)");
        b2.setFont(f3);
        b2.setBounds(700,120,125,40);
        b3 = new JButton("依赖分析(DP)");
        b3.setFont(f3);
        b3.setBounds(550,120,125,40);
        // 创建文本域
        
        ta = new TextArea(10, 40);
        ta.setBounds(10,180,950,550);
        ta.setEditable(false);
        ta.setBackground(Color.WHITE);
        ta.setFont(f2);
        // 把组件添加到窗体
        this.add(l1);
        this.add(l2);
        this.add(tf);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(ta);        
        // 设置窗体关闭
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // 对按钮添加事件
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        // 设置窗体显示
        this.setVisible(true);
	}	
	public void actionPerformed(ActionEvent e) {
		String input;
		String output;
		Object source = e.getSource();
		if(source == b1) {
			ta.setText("");
//			ta.setText("word\t\tpos\t\tlemma\t\tner\n");
			input = tf.getText().trim();
	        //if  data from file
	        //annotation = new Annotation(IOUtils.slurpFileNoExceptions(file));
	        //annotation = new Annotation("110kv的屋外带电构件与非带电构件的净空不应小于1000MM。");
	        document = new Annotation(input);
	        pipeline.annotate(document);	        
//	        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
//	        for(CoreMap sentence: sentences) {
//	             // traversing the words in the current sentence
//	             // a CoreLabel is a CoreMap with additional token-specific methods
//	            for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
//	                // this is the text of the token
//	                String word = token.get(TextAnnotation.class);
//	                // this is the POS tag of the token
//	                String pos = token.get(PartOfSpeechAnnotation.class);
//	                // this is the NER label of the token
//	                String ne = token.get(NamedEntityTagAnnotation.class);
//	                String lemma = token.get(LemmaAnnotation.class);
//	                ta.append(word+"\t\t"+pos+"\t\t"+lemma+"\t\t"+ne+"\n");
//	                //System.out.println(word+"\t"+pos+"\t"+lemma+"\t"+ne);
//	            }
//	            // this is the parse tree of the current sentence
//	            Tree tree = sentence.get(TreeAnnotation.class);
//	            // this is the Stanford dependency graph of the current sentence
//	            SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
//	        }
//          结果重定向到文本
//	        try {
//				System.setOut(new PrintStream(new FileOutputStream("system_out.txt")));
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	        
//	        //GUIPrintStream guiPrintStream = new GUIPrintStream(System.out, jTextArea);
//	        pipeline.prettyPrint(document, System.out);
//	        File file = new File("system_out.txt");
//	        try {
//				FileReader filereader = new FileReader(file);
//				BufferedReader bufferreader = new BufferedReader(filereader);
//				String aline;
//				try {
//					while ((aline = bufferreader.readLine()) != null)
//						//按行读取文本，显示在TEXTAREA中
//						ta.append(aline + "\r\n");
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				try {
//					filereader.close();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				try {
//					bufferreader.close();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				System.out.println(e1);
//			}
	        System.setOut(new GUIPrintStream(System.out, ta));
	        pipeline.prettyPrint(document, System.out);
	        //PrintStream printStream = new PrintStream(new CustomOutputStream(ta));
	        //System.setOut(new GUIPrintStream(System.out, ta));
	        //System.setOut(printStream);
	        //pipeline.prettyPrint(annotation, System.out);
	        //GUIPrintStream guiPrintStream = new GUIPrintStream(System.out, TextArea); 
		}else if (source == b2) {
			ta.setText("");
			ta.setText("单词\t\t词性\t\t命名实体\n");
			input = tf.getText().trim();
			document = new Annotation(input);
	        pipeline.annotate(document);
	        String nn = "";
	        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
	        for(CoreMap sentence: sentences) {
//	              	traversing the words in the current sentence
//	              	a CoreLabel is a CoreMap with additional token-specific methods
	            for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
	                // this is the text of the token
	                String word = token.get(TextAnnotation.class);
	                // this is the POS tag of the token
	                String pos = token.get(PartOfSpeechAnnotation.class);
	                // this is the NER label of the token
	                String ne = token.get(NamedEntityTagAnnotation.class);
	                if(pos.equals("NN")) {
	                	nn += word+"  ";
	                }
	                ta.append(word+"\t\t"+pos+"\t\t"+ne+"\n");	               
	            }
	            ta.append("\n名词元组抽取：(  "+ nn + ")");
	        }
		}else if (source == b3) {
			ta.setText("");
			input = tf.getText();
			String ans = "";
	        document = new Annotation(input);
	        pipeline.annotate(document);
	        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
	        for(CoreMap sentence: sentences) {
	            SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
	    	    ans = ans + dependencies.toString() + "\n";
	        }
			ta.setText(ans);
		}
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
    }

}