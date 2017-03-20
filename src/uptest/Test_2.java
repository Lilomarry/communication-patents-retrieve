package uptest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Test_2 {

	public void method1(String file) {
		FileWriter fw = null;
		try {
			// ����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
			File f = new File(file);
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		// pw.println("");
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void method2(String file, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(conent + "\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void method3(String fileName, String content) {
		try {
			// ��һ����������ļ���������д��ʽ
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// �ļ����ȣ��ֽ���
			long fileLength = randomFile.length();
			// ��д�ļ�ָ���Ƶ��ļ�β��
			randomFile.seek(fileLength);
			randomFile.writeBytes(content + "\r\n");
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws UnsupportedEncodingException {
		Element element = null;
		// ����ʹ�þ���·��

		String string = new String("201480001710NEW");
		String str = "E:\\ͨѶר���ı�\\" + string + ".xml";
		File file = new File(URLDecoder.decode(str, "UTF-8"));
		Test_2 a = new Test_2();
		// documentBuilderΪ������ֱ��ʵ��������XML�ļ�ת��ΪDOM�ļ���
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		// IgnoreDtd ignore = new IgnoreDtd();
		// DocumentBuilderFactory.setValidating(false);
		// dbf.setValidating(false);
		try {
			// ����documentBuilderFactory����
			dbf = DocumentBuilderFactory.newInstance();
			// dbf.setValidating(false);
			// ����db������documentBuilderFactory�����÷���documentBuilder����
			db = dbf.newDocumentBuilder();
			// �õ�һ��DOM�����ظ�document����
			Document dt = db.parse(file);
			// �õ�һ��element��Ԫ��
			element = dt.getDocumentElement();
			// ��ø��ڵ�
			System.out.println("��Ԫ��" + ".." + element.getNodeType());
			// ��ø�Ԫ���µ��ӽڵ�
			NodeList childNodes = element.getChildNodes();
			// ������Щ�ӽڵ�
			for (int i = 0; i < childNodes.getLength(); i++) {
				// ���ÿ����Ӧλ��i�Ľ��
				Node node1 = childNodes.item(i);
				if ("cn-bibliographic-data".equals(node1.getNodeName())) {
					// �����������Ϊ��cn-patent-document��������������ӽڵ�
					// ���<cn-patent-document>�µĽڵ�
					NodeList nodeDetail = node1.getChildNodes();
					// ����<cn-patent-document>�µĽڵ�
					for (int j = 0; j < nodeDetail.getLength(); j++) {
						// ���<cn-bibliographic-data>Ԫ��ÿһ���ڵ�
						Node detail1 = nodeDetail.item(j);
						if ("cn-publication-reference".equals(detail1.getNodeName()))
						// �ӽڵ�Ϊcn-publication-reference����������
						// ���<cn-publication-reference>�µĽ��
						{
							NodeList nodeDetail1_2 = detail1.getChildNodes();
							// ����<cn-publication-reference>�µĽ��
							for (int k = 0; k < nodeDetail1_2.getLength(); k++) {
								// ���<cn-publication-reference>Ԫ��ÿһ�����
								Node detail1_2_3 = nodeDetail1_2.item(k);
								if ("document-id".equals(detail1_2_3.getNodeName())) {
									// �ӽڵ�Ϊdocument-id����������
									// ���document-id�µĽ��
									NodeList nodeDetail1_2_3 = detail1_2_3.getChildNodes();
									// ����document-id�µĽ��
									for (int m = 0; m < nodeDetail1_2_3.getLength(); m++) {
										// ���document-idԪ��ÿһ���
										Node detail1_2_3_4 = nodeDetail1_2_3.item(m);
										if ("country".equals(detail1_2_3_4.getNodeName())) {
											// �ӽڵ�Ϊcountry�����
											System.out.println("country:  " + detail1_2_3_4.getTextContent());
										} else if ("doc-number".equals(detail1_2_3_4.getNodeName())) {
											System.out.println("doc-number: " + detail1_2_3_4.getTextContent());
										} else if ("kind".equals(detail1_2_3_4.getNodeName())) {
											System.out.println("kind: " + detail1_2_3_4.getTextContent());
										} else if ("date".equals(detail1_2_3_4.getNodeName())) {
											System.out.println("date: " + detail1_2_3_4.getTextContent());
										}
									}

								} else if ("gazette-reference".equals(detail1_2_3.getNodeName()))
								// �ӽڵ�Ϊgazette-reference����������
								// ���gazette-reference�µĽ��
								{
									NodeList nodeDetail1_2_3 = detail1_2_3.getChildNodes();
									// ����document-id�µĽ��
									for (int m = 0; m < nodeDetail1_2_3.getLength(); m++) {
										// ���document-idԪ��ÿһ���
										Node detail1_2_3_4 = nodeDetail1_2_3.item(m);
										if ("gazette-num".equals(detail1_2_3_4.getNodeName())) {
											// �ӽڵ�Ϊcountry�����
											System.out.println("gazette-num:  " + detail1_2_3_4.getTextContent());
										} else if ("date".equals(detail1_2_3_4.getNodeName())) {
											System.out.println("date: " + detail1_2_3_4.getTextContent());
										}
									}
								}

							}
						} else if ("application-reference".equals(detail1.getNodeName())) {
							System.out.println("application-reference: "
									+ detail1.getAttributes().getNamedItem("appl-type").getNodeValue() + ". ");
							NodeList nodeDetail1_2 = detail1.getChildNodes();
							// ����<application-reference>�µĽ��
							for (int k = 0; k < nodeDetail1_2.getLength(); k++) {
								// ���<application-reference>Ԫ��ÿһ�����
								Node detail1_2_3 = nodeDetail1_2.item(k);
								if ("document-id".equals(detail1_2_3.getNodeName())) {
									NodeList nodeDetail1_2_3 = detail1_2_3.getChildNodes();
									// ����document-id�µĽ��
									System.out.println("document-id");
									for (int m = 0; m < nodeDetail1_2_3.getLength(); m++) {
										// ���document-idԪ��ÿһ���
										Node detail1_2_3_4 = nodeDetail1_2_3.item(m);
										if ("country".equals(detail1_2_3_4.getNodeName())) {
											// �ӽڵ�Ϊcountry�����
											System.out.println("country:  " + detail1_2_3_4.getTextContent());
										} else if ("doc-number".equals(detail1_2_3_4.getNodeName())) {
											System.out.println("doc-number: " + detail1_2_3_4.getTextContent());
										} else if ("kind".equals(detail1_2_3_4.getNodeName())) {
											System.out.println("kind: " + detail1_2_3_4.getTextContent());
										} else if ("date".equals(detail1_2_3_4.getNodeName())) {
											System.out.println("date: " + detail1_2_3_4.getTextContent());
										}
									}
								}

							}

						} else if ("priority-claims".equals(detail1.getNodeName())) {

							NodeList nodeDetail1_2 = detail1.getChildNodes();
							// ����<priority-claims>�µĽ��
							for (int k = 0; k < nodeDetail1_2.getLength(); k++) {
								// ���<priority-claims>Ԫ��ÿһ�����
								Node detail1_2_3 = nodeDetail1_2.item(k);
								if ("priority-claim".equals(detail1_2_3.getNodeName())) {
									System.out.println("priority-claim");
									NodeList nodeDetail1_2_3 = detail1_2_3.getChildNodes();
									// ����priority-claim�µĽ��
									for (int m = 0; m < nodeDetail1_2_3.getLength(); m++) {
										// ���priority-claimԪ��ÿһ���
										Node detail1_2_3_4 = nodeDetail1_2_3.item(m);
										if ("country".equals(detail1_2_3_4.getNodeName())) {
											// �ӽڵ�Ϊcountry�����
											System.out.println("country:  " + detail1_2_3_4.getTextContent());
										} else if ("doc-number".equals(detail1_2_3_4.getNodeName())) {
											System.out.println("doc-number: " + detail1_2_3_4.getTextContent());
										} else if ("date".equals(detail1_2_3_4.getNodeName())) {
											System.out.println("date: " + detail1_2_3_4.getTextContent());
										}
									}
								}

							}

						} else if ("classifications-ipcr".equals(detail1.getNodeName())) {
							System.out.println("classifications-ipcr");
							NodeList nodeDetail1_2 = detail1.getChildNodes();
							// ����<classifications-ipcr>�µĽ��
							for (int k = 0; k < nodeDetail1_2.getLength(); k++) {
								// ���<classifications-ipcr>Ԫ��ÿһ�����
								Node detail1_2_3 = nodeDetail1_2.item(k);
								if ("classification-ipcr".equals(detail1_2_3.getNodeName())) {
									NodeList nodeDetail1_2_3 = detail1_2_3.getChildNodes();
									// ����priority-claim�µĽ��
									for (int m = 0; m < nodeDetail1_2_3.getLength(); m++) {
										// ���priority-claimԪ��ÿһ���
										Node detail1_2_3_4 = nodeDetail1_2_3.item(m);
										if ("text".equals(detail1_2_3_4.getNodeName())) {
											// �ӽڵ�Ϊcountry�����
											System.out.println("country:  " + detail1_2_3_4.getTextContent());
										} else if ("doc-number".equals(detail1_2_3_4.getNodeName())) {
											System.out.println("doc-number: " + detail1_2_3_4.getTextContent());
										} else if ("date".equals(detail1_2_3_4.getNodeName())) {
											System.out.println("date: " + detail1_2_3_4.getTextContent());
										}
									}
								}

							}

						} else if ("invention-title".equals(detail1.getNodeName())) {
							System.out.println("invention-title: " + detail1.getTextContent());
							a.method1("E:\\ͨѶר���ı�\\ͨѶר���ı�_txt\\" + string + ".txt");
							a.method2("E:\\ͨѶר���ı�\\ͨѶר���ı�_txt\\" + string + ".txt", detail1.getTextContent());
							NodeList nodeDetail1_2 = detail1.getChildNodes();

						} else if ("abstract".equals(detail1.getNodeName())) {
							System.out.println("abstract: " + detail1.getTextContent());
							String temp = detail1.getTextContent().replaceAll("\r", "");
							temp = temp.replaceAll("\\s*", "");
							a.method2("E:\\ͨѶר���ı�\\ͨѶר���ı�_txt\\" + string + ".txt", "ժҪ\r\n" + temp);
							NodeList nodeDetail1_2 = detail1.getChildNodes();

						}
					}
				} else if ("application-body".equals(node1.getNodeName())) {
					System.out.println("application-body");
					NodeList nodeDetail1_2 = node1.getChildNodes();
					// ����<classifications-ipcr>�µĽ��
					for (int k = 0; k < nodeDetail1_2.getLength(); k++) {
						// ���<classifications-ipcr>Ԫ��ÿһ�����
						Node detail1_2_3 = nodeDetail1_2.item(k);
						if ("description".equals(detail1_2_3.getNodeName())) {
							System.out.println("description :");
							NodeList nodeDetail1_2_3 = detail1_2_3.getChildNodes();

							// ����priority-claim�µĽ��
							for (int m = 0; m < nodeDetail1_2_3.getLength(); m++) {
								// ���priority-claimԪ��ÿһ���

								Node detail1_2_3_4 = nodeDetail1_2_3.item(m);
								if ("invention-title".equals(detail1_2_3_4.getNodeName())) {
									// �ӽڵ�Ϊcn-applicant�����
									System.out.println("invention-title:  ");
								} else if ("p".equals(detail1_2_3_4.getNodeName())) {
									System.out.println("" + detail1_2_3_4.getTextContent());
									String temp = detail1_2_3_4.getTextContent().replaceAll("\r", "");
									temp = temp.replaceAll("\\s*", "");
									a.method2("E:\\ͨѶר���ı�\\ͨѶר���ı�_txt\\" + string + ".txt", temp);
								}
							}
						} else if ("claims".equals(detail1_2_3.getNodeName())) {
							System.out.println("claims :");
							NodeList nodeDetail1_2_3 = detail1_2_3.getChildNodes();
							String temp = detail1_2_3.getTextContent().replaceAll("\r", "");
							temp = temp.replaceAll("\\s*", "");
							a.method2("E:\\ͨѶר���ı�\\ͨѶר���ı�_txt\\" + string + ".txt", temp);

							// ����priority-claim�µĽ��
							for (int m = 0; m < nodeDetail1_2_3.getLength(); m++) {
								// ���priority-claimԪ��ÿһ���

								Node detail1_2_3_4 = nodeDetail1_2_3.item(m);
								if ("claim".equals(detail1_2_3_4.getNodeName())) {
									// �ӽڵ�Ϊcn-applicant�����
									System.out.println("" + detail1_2_3_4.getTextContent());
								}
							}
						}

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
