/***
 * 
 * Trie ���Ľ��ṹ
Trie ����һ���и�������������������ֶΣ���

���  R ��ָ���ӽ������ӣ�����ÿ�����Ӷ�Ӧ��ĸ�����ݼ��е�һ����ĸ��
�����мٶ�  R Ϊ 26��Сд������ĸ��������
�����ֶΣ���ָ���ڵ��Ƕ�Ӧ���Ľ�β����ֻ�Ǽ�ǰ׺��

 * @author 26442
 *
 */
public class MyTrie {
	
	//Trie�����ṹ
	class TrieNode{
		private TrieNode[] links;//�ڵ㣬����ָ���ӽ�������
		
		private final int R = 26;
		
		private boolean isEnd;
		
		public TrieNode() {//�����µ�Trie���ڵ�
			links = new TrieNode[R];
		}
		
		public boolean containsKey(char ch) {//�жϵ�ǰ�ڵ��Ƿ����ch
			return links[ch - 'a'] != null;
		}
		
		public TrieNode get(char ch) {//get
			return links[ch - 'a'];
		}
		
		public void put(char ch, TrieNode node) {
			links[ch - 'a'] = node;
		}
		
		public void setEnd() {
			isEnd = true;
		}
		
		public boolean isEnd() {
			return isEnd;
		}
		
		
		
		/***
		 * �������
		 * 
		 * ����ͨ������ Trie ��������һ���������ǴӸ���ʼ��������Ӧ�ڵ�һ�����ַ������ӡ������������
		1.���Ӵ��ڡ����������ƶ���������һ���Ӳ㡣�㷨����������һ�����ַ���
		2.���Ӳ����ڡ�����һ���µĽڵ㣬�������븸�ڵ�������������������뵱ǰ�ļ��ַ���ƥ�䡣
		�ظ����ϲ��裬ֱ������������һ���ַ���Ȼ�󽫵�ǰ�ڵ���Ϊ�����ڵ㣬�㷨��ɡ�

		 * ***/
		private TrieNode root;
		
		public void insert (String word) {
			TrieNode node = root;
			
			for(int i = 0; i < word.length(); i++) {
				char currentChar = word.charAt(i);
				if(!node.containsKey(currentChar))
					node.put(currentChar, new TrieNode());
				node = node.get(currentChar);//���������ƶ���������һ���Ӳ�
			}
			
			node.setEnd();//�ظ����ϲ��裬ֱ������������һ���ַ���Ȼ�󽫵�ǰ�ڵ���Ϊ�����ڵ㣬�㷨��ɡ�
			
		}
		
		/***
		 * ��Trie���в���
		 * 
		 * ÿ������ trie �б�ʾΪ�Ӹ����ڲ��ڵ��Ҷ��·���������õ�һ�����ַ��Ӹ���ʼ������鵱ǰ�ڵ�������ַ���Ӧ�����ӡ������������
		1.�������ӡ������ƶ��������Ӻ���·���е���һ���ڵ㣬������������һ�����ַ���
		2.���������ӡ������޼��ַ����ҵ�ǰ�����Ϊ isEnd���򷵻� true��  !!!���������ֿ��ܣ������� false :
			i.���м��ַ�ʣ�࣬���޷����� Trie ���ļ�·�����Ҳ�������
			ii.û�м��ַ�ʣ�࣬����ǰ���û�б��Ϊ isEnd��Ҳ����˵�������Ҽ�ֻ��Trie������һ������ǰ׺��
		 * 
		 */
		//��trie������ǰ׺�������������������������Ľڵ�
		
		private TrieNode searchPrefix(String word) {
			TrieNode node = root;
			for(int i = 0; i <word.length(); i++) {
				char curLetter = word.charAt(i);
				if(node.containsKey(curLetter)) {
					node = node.get(curLetter);//������һ���������
				}else {
					return null;
				}
			}
			return node;
		}
		
		public boolean search(String word) {
			TrieNode node = searchPrefix(word);//�����޼��ַ����ҵ�ǰ�����Ϊ isEnd���򷵻� true
			return node != null && node.isEnd();
		}
		
		
		/***
		 * ���� Trie ���еļ�ǰ׺
		 * 
		 * �÷������� Trie ����������ʱʹ�õķ����ǳ����ơ����ǴӸ����� Trie ����ֱ����ǰ׺��û���ַ��������޷��õ�ǰ�ļ��ַ����� Trie �е�·����
		 * �������ᵽ�ġ����������㷨Ψһ�������ǣ�
		 * �����ǰ׺��ĩβʱ�����Ƿ��� true�����ǲ���Ҫ���ǵ�ǰ Trie �ڵ��Ƿ��� ��isend�� ��ǣ���Ϊ�����������Ǽ���ǰ׺����������������
		 */
		public boolean startWith(String word) {//���� Trie ���еļ�ǰ׺
			TrieNode node = searchPrefix(word);//�����޼��ַ����ҵ�ǰ�����Ϊ isEnd���򷵻� true
			return node != null;
		}
		
		
		
		
		
		
	}
}
