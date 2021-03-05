/***
 * 
 * Trie 树的结点结构
Trie 树是一个有根的树，其结点具有以下字段：。

最多  R 个指向子结点的链接，其中每个链接对应字母表数据集中的一个字母。
本文中假定  R 为 26，小写拉丁字母的数量。
布尔字段，以指定节点是对应键的结尾还是只是键前缀。

 * @author 26442
 *
 */
public class MyTrie {
	
	//Trie树结点结构
	class TrieNode{
		private TrieNode[] links;//节点，用于指向子结点的链接
		
		private final int R = 26;
		
		private boolean isEnd;
		
		public TrieNode() {//创建新的Trie树节点
			links = new TrieNode[R];
		}
		
		public boolean containsKey(char ch) {//判断当前节点是否包含ch
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
		 * 插入操作
		 * 
		 * 我们通过搜索 Trie 树来插入一个键。我们从根开始搜索它对应于第一个键字符的链接。有两种情况：
		1.链接存在。沿着链接移动到树的下一个子层。算法继续搜索下一个键字符。
		2.链接不存在。创建一个新的节点，并将它与父节点的链接相连，该链接与当前的键字符相匹配。
		重复以上步骤，直到到达键的最后一个字符，然后将当前节点标记为结束节点，算法完成。

		 * ***/
		private TrieNode root;
		
		public void insert (String word) {
			TrieNode node = root;
			
			for(int i = 0; i < word.length(); i++) {
				char currentChar = word.charAt(i);
				if(!node.containsKey(currentChar))
					node.put(currentChar, new TrieNode());
				node = node.get(currentChar);//沿着链接移动到树的下一个子层
			}
			
			node.setEnd();//重复以上步骤，直到到达键的最后一个字符，然后将当前节点标记为结束节点，算法完成。
			
		}
		
		/***
		 * 在Trie树中查找
		 * 
		 * 每个键在 trie 中表示为从根到内部节点或叶的路径。我们用第一个键字符从根开始，。检查当前节点中与键字符对应的链接。有两种情况：
		1.存在链接。我们移动到该链接后面路径中的下一个节点，并继续搜索下一个键字符。
		2.不存在链接。若已无键字符，且当前结点标记为 isEnd，则返回 true。  !!!否则有两种可能，均返回 false :
			i.还有键字符剩余，但无法跟随 Trie 树的键路径，找不到键。
			ii.没有键字符剩余，但当前结点没有标记为 isEnd。也就是说，待查找键只是Trie树中另一个键的前缀。
		 * 
		 */
		//在trie中搜索前缀或整个键，并返回搜索结束的节点
		
		private TrieNode searchPrefix(String word) {
			TrieNode node = root;
			for(int i = 0; i <word.length(); i++) {
				char curLetter = word.charAt(i);
				if(node.containsKey(curLetter)) {
					node = node.get(curLetter);//进入下一层继续搜索
				}else {
					return null;
				}
			}
			return node;
		}
		
		public boolean search(String word) {
			TrieNode node = searchPrefix(word);//若已无键字符，且当前结点标记为 isEnd，则返回 true
			return node != null && node.isEnd();
		}
		
		
		/***
		 * 查找 Trie 树中的键前缀
		 * 
		 * 该方法与在 Trie 树中搜索键时使用的方法非常相似。我们从根遍历 Trie 树，直到键前缀中没有字符，或者无法用当前的键字符继续 Trie 中的路径。
		 * 与上面提到的“搜索键”算法唯一的区别是，
		 * 到达键前缀的末尾时，总是返回 true。我们不需要考虑当前 Trie 节点是否用 “isend” 标记，因为我们搜索的是键的前缀，而不是整个键。
		 */
		public boolean startWith(String word) {//查找 Trie 树中的键前缀
			TrieNode node = searchPrefix(word);//若已无键字符，且当前结点标记为 isEnd，则返回 true
			return node != null;
		}
		
		
		
		
		
		
	}
}
