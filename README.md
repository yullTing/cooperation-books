### 看这里
1、main 是默认的分支，无法删除。

2、1/2的图书管理系统已经提交到 main 分支上了，原 master 分支已经删除。

3、<b>下载 main 分支上的数据，一共三步（此处使用 SSH 协议）</b>：

- 3.1、在本地电脑上，新建并重命名一个文件夹，选中该文件夹右击，选择 Git Bash Here 

- 3.2、在弹出窗口输入<code> git clone git@github.com:yullTing/cooperation-books.git </code>，然后回车

- 3.3、该命令是下载默认分支中的项目。下载需要时间，完成后请在文件夹中查看。

4、如果要下载指定分支（如分支 master ）的项目，可以输入如下指令：<code>git clone -b master git@github.com:yullTing/cooperation-books.git</code>




<details>
  <summary>如果要上传文件夹到自己的 Github</summary>
  <pre>
  #### 另可参考教程：https://felixwang.site/archives/gitsynchronize
  <h6>

  第一步，新建并重命名文件夹（如newFile）。

  第二步，将需要上传的文件夹（或者文件）放在新建的文件夹（newFile）中。

  第三步，右击该文件夹并选择 Git Bash Here 。

  第四步，在弹出的窗口输入<code> git init </code>并回车，将该文件夹变成 Git 可管理的仓库。

  第五步，输入<code> git remote add test git@github.com:xxxx/javaCore.git </code>并回车，这条指令用来将刚刚新建的文件夹和远程仓库相关联，其中 test 是给远程仓库的命名，而git@github.com:xxxx/javaCore.git 是远程仓库的url地址。

  第六步，接着输入<code> git add . </code>并回车（注意 add 后面是空格+ .），该指令目的是把新建文件夹下（newFile）的所有文件暂存到仓库。

  第七步，输入<code> git commit -m "日志" </code>并回车，双引号中的“日志”可以随意更改，比如“第一次提交”等。

  第八步，输入<code> git push -u test master </code>，将暂存到仓库的所有文件提交到远程仓库（前面命名为test）中的 master 分支上。（如果不是第一次提交，可以不用输入 -u）上传的时间可能稍长，请耐心等待。
  </h6>
  ![image](https://user-images.githubusercontent.com/96578240/156734439-e6344425-bedb-4e27-8ed9-b485f78afd64.png)
  ![image](https://user-images.githubusercontent.com/96578240/156734789-17291267-37f6-4e93-8ffe-bc69a93c17b1.png)
  </pre>
</details>
