### 看这里
1、main 是默认的分支，无法删除。

2、1/2的图书管理系统已经提交到 main 分支上了，原 master 分支已经删除。

3、下载 main 分支上的数据，一共三步（此处使用 SSH 协议）：

  3.1、在本地电脑上，新建并重命名一个文件夹，选中该文件夹右击，选择 Git Bash Here 

  3.2、在弹出窗口输入 git clone git@github.com:yullTing/cooperation-books.git ，然后回车

  3.3、该命令是下载默认分支中的项目。下载需要时间，完成后请在文件夹中查看。

4、如果要下载指定分支（如分支 master ）的项目，可以输入如下指令：git clone -b master git@github.com:yullTing/cooperation-books.git
