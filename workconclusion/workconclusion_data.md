```sql
/*
Navicat MySQL Data Transfer

Source Server         : remote-100.106.43.30
Source Server Version : 50518
Source Host           : 100.106.43.30:3306
Source Database       : WorkConclusion

Target Server Type    : MYSQL
Target Server Version : 50518
File Encoding         : 65001

Date: 2017-01-16 22:32:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for knowledge
-- ----------------------------
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
  `id` int(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `link` varchar(200) NOT NULL,
  `createDate` date NOT NULL,
  `updateDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of knowledge
-- ----------------------------

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `solution` text NOT NULL,
  `questionStatus` int(1) NOT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '2017-01-16 22:22:45', '版本对接中的配置。', '', '0', null);
INSERT INTO `question` VALUES ('2', '2017-01-04 22:20:55', '1，jenkins 中是否有显示cvs 格式文件的插件。:2,tomcat 服务器优化。', '', '0', null);

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `recordDate` date NOT NULL,
  `workContent` text NOT NULL,
  `workStatus` int(1) NOT NULL,
  `nextPlan` text NOT NULL,
  `conclusion` text NOT NULL,
  `question_id` int(20) DEFAULT NULL,
  `knowledge_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `know_work_id` (`knowledge_id`),
  KEY `ques_work_id` (`question_id`),
  CONSTRAINT `ques_work_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `know_work_id` FOREIGN KEY (`knowledge_id`) REFERENCES `knowledge` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES ('1', '2017-01-03', '1,C30 中所有编译环境的jdk 和mvn统一<br />2,C30-2.0公共环境磁盘满了，需清理。（挂载磁盘，以及移动共用目录）', '0', '性能自动化CI计划对齐。', '1,接到的任务，性能自动化CI 搭建，没能及时地对齐。', null, null);
INSERT INTO `work` VALUES ('2', '2017-01-04', '1,C30 中，性能自动化CI目标对齐，环境部署。<br />', '0', '性能自动化CI的git库地址，编译分发脚本落实。', '1,该如何提高自己的效率', null, null);
INSERT INTO `work` VALUES ('3', '2017-01-05', '1,CI 中机器的整理。现在整理出来的机器，空闲的有51台。一个是6+1的，一个是之前15个子工程的。<br />2：给王如慧那边搭建一个测试的跑UT 的工程。', '0', '', '1,why you can not stay calm to read somethig.', null, null);
INSERT INTO `work` VALUES ('4', '2017-01-06', '1,tomcat 性能优化，参考着一些博客，进行配置。<br />', '0', '', '1，无日报总结，每天花个10分钟到20分钟进行总结。', null, null);
INSERT INTO `work` VALUES ('5', '2017-01-09', '1,CI 机器的整理。整理出的机器，6+1工程，用到的机器有110台，剩余机器74台，C301.5机器45台，则，后面干掉C301.5基线的工程的话，有119台，刚好可以搭建另一套的工程。<br />2，6+1 工程问题梳理。', '0', '1，版本构建和MR 构建冲突方案。：2，工程对接看板。：3，6+1 Carbon 编译所处位置有误。需整改。', '1，无日报总结，每天花个10分钟到20分钟进行总结。', null, null);
INSERT INTO `work` VALUES ('6', '2017-01-10', '1,版本构建和MR 工程共用机器，相互影响问题，方案已经有。<br />2,Carbon 6+1 工程中所处的位置的整改，完成。', '0', '', '', null, null);
INSERT INTO `work` VALUES ('7', '2017-01-11', '1,Hive_ODBC 工程，window 机器编译时，用mvn 编译zkcode 的时候报错，报错的是跑zkcode 的test 的时候。 网上说的是内存不足，以及某些jar 包调用了System.exit(). 参照着网上说的进行了修改，但是解决不了问题。<br />', '0', '', '', null, null);
INSERT INTO `work` VALUES ('8', '2017-01-12', '1,6+1 工程中，针对随机失败的SparkUT，可以尝试，重跑失败的UT，以及没跑的UT，方案正在整理中。', '0', '', '', null, null);
INSERT INTO `work` VALUES ('9', '2017-01-13', '1,杜军令的提的MR，工程提示编译错误，韩献的提MR工程会卡着。 分析了一下，没有找到原因.。<br/>2,与姜松对齐CI 性能测试的流层。现在编译已近可以跑通，后面需要把tar 包分发到各个测试的机器上。跑测试，然后把测试结果返回jenkins。<br /> 3,c20 Ci UT的streaming ut，失败，环境问题引起两次，随机失败一次，环境问题是，jenkins 清理工作目录的插件bug 没能清理本地的文件，导致构建失败。已经解决。<br /> 4,给2.0基线的mr工程添加机器，5台，正在测试中。<br />5，给李凯填请假的电子流。<br />', '0', '', '', null, null);

```
