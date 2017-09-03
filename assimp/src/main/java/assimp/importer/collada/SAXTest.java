package assimp.importer.collada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

final class SAXTest {
	
	public static void main(String[] args) {
		
		Myhandler hanlder = null;
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			parser.parse("test_res/saxtest.xml", hanlder = new Myhandler("stu"));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(hanlder.getList());
	}

	private static class Myhandler extends DefaultHandler{
		Map<String, String> map = null;
		List<Map<String, String>> list = null;
		String currentTag = null;
		String currentValue = null;
		String nodeName = null;
		
		Attributes values;
		
		Myhandler(String nodeName){
			this.nodeName = nodeName;
		}
		
		public List<Map<String, String>> getList() { return list;}
		
		//��ʼ�����ĵ�������ʼ����XML��Ԫ��ʱ���ø÷���
		@Override
		public void startDocument() throws SAXException {
			System.out.println("--startDocument()--");
			//��ʼ��Map
			list = new ArrayList<>();
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) {
			if(values == null){
				values = attributes;
			}else {
				System.out.println("values == attributes ? " + (values == attributes));
			}
			
			//�ж����ڽ�����Ԫ���ǲ��ǿ�ʼ������Ԫ��
			System.out.println("--startElement()--"+qName);
			if(qName.equals(nodeName)){
				map = new HashMap<>();
				
				System.out.println("id = " + attributes.getValue("id"));
			}
			
			//�ж����ڽ�����Ԫ���Ƿ�������ֵ,���������ȫ��ȡ�������浽map�����У���:<person id="00001"></person>
			if(attributes != null && map != null){
				for(int i = 0; i < attributes.getLength(); i++){
					map.put(attributes.getQName(i), attributes.getValue(i));
				}
			}
			
			currentTag = qName;  // ���ڽ�����Ԫ��
		}
		
		@Override
		public void characters(char[] ch, int start, int length){
			System.out.println("--characters()--");
			if(currentTag != null && map != null){
				currentValue = new String(ch, start, length);
				//������ݲ�Ϊ�պͿո�Ҳ���ǻ��з��򽫸�Ԫ������ֵ�ʹ���map��
				if(currentValue!=null&&!currentValue.trim().equals("")&&!currentValue.trim().equals("\n")){
					map.put(currentTag, currentValue);
					 System.out.println("-----"+currentTag+" "+currentValue);
				}
				
				//��ǰ��Ԫ���ѽ������������ÿ�������һ��Ԫ�صĽ���
				currentTag=null;
				currentValue = null;
			}
		}
		
		//ÿ��Ԫ�ؽ�����ʱ�򶼻���ø÷���
		@Override
		public void endElement(String uri, String localName, String qName) {
			System.out.println("--endElement()--"+qName);
			//�ж��Ƿ�Ϊһ���ڵ������Ԫ�ر�ǩ
			if(qName.equals(nodeName)){
				list.add(map);
				map = null;
			}
		}
		
		//���������ĵ�����������Ԫ�ؽ�����ǩʱ���ø÷���
		@Override
		public void endDocument(){
			System.out.println("--endDocument()--");
		}
	}
}
