### 介绍
本项目主要是通过Java实现了大文件的多线程下载，以及突破云盘限速
原作者:[silently9527](https://gitee.com/silently9527)   
原项目链接[FastDownload](https://gitee.com/silently9527/fast-download)

### 使用方式

#### 1. 小文件下载 
对应小文件下载，可以先下载到内存，然后在输出到文件中

```
FileDownloader fileDownloader = new FileDownloader();
String fileURL = "http://img.doutula.com/production/uploads/image/2017/10/19/20171019627498_uQtkcl.jpg";
fileDownloader.downloadToMemory(fileURL, "/Users/huaan9527/Desktop/71019627498_uQtkcl.jpg");
```

#### 2. 大文件单线程下载 

```
FileDownloader fileDownloader = new FileDownloader();
String fileURL = "http://img.doutula.com/production/uploads/image/2017/10/19/20171019627498_uQtkcl.jpg";
fileDownloader.downloadFile(fileURL, "/Users/huaan9527/Desktop/71019627498_uQtkcl.jpg");
```

#### 3. 大文件多线程下载 

```
FileDownloader fileDownloader = new FileDownloader();
String fileURL = "http://img.doutula.com/production/uploads/image/2017/10/19/20171019627498_uQtkcl.jpg";
fileDownloader.multiThreadDownload(fileURL, "/Users/huaan9527/Desktop/71019627498_uQtkcl.jpg");
```


### 测试结果

测试百度云下载的文件 46M，自己本地最大下载速度 2M

#### 1. 单线程下载
![](https://gitee.com/silently9527/fast-download/raw/master/imgs/%E5%8D%95%E7%BA%BF%E7%A8%8B%E4%B8%8B%E8%BD%BD%E9%80%9F%E5%BA%A6.png)

总耗时: 603s


#### 2. 多线程下载

![](https://gitee.com/silently9527/fast-download/raw/master/imgs/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E4%B8%8B%E8%BD%BD%E8%80%97%E6%97%B6.png)

云盘单线程下载限速100kb，为了充分的压榨网速，所以做了不同线程的测试速度

| 线程数 | 下载总耗时 |
|-----|-------|
| 10  | 60s   |
| 20  | 30s   |
| 30  | 21s   |
| 40  | 15s   |
| 50  | 13s   |

由于自己的最大下载速度是 2M，所以线程数设置在30个下载速度比较合适

> 注意：从浏览器中获取的云盘下载地址需要使用 URLDecode 解码，并且下载链接有时效性
>
> 本项目纯粹用于学习

---

基于此项目开发了IDEA插件
1. Github地址：[https://github.com/silently9527/FastDownloadIdeaPlugin](https://github.com/silently9527/FastDownloadIdeaPlugin)
2. Gitee地址：[https://gitee.com/silently9527/FastDownloadIdeaPlugin](https://gitee.com/silently9527/FastDownloadIdeaPlugin)

> 喜欢的朋友不要忘记star哟



#### 微信公众号

<img width="200" src="https://raw.githubusercontent.com/silently9527/JavaCore/master/imgs/gonzhonghao.png" alt="公众号">


## 我的技术博客
[https://silently9527.cn/](https://silently9527.cn/)

## 捐赠研发
如果您认为此项目帮到了您的开发工作,您可以捐赠我一杯可乐(相信这比打赏主播更有意义)。

<img width="200" src="https://tva1.sinaimg.cn/large/008eGmZEgy1gn63yahvn4j30ia0igjsw.jpg" >
