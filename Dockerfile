FROM harbor.baocloud.cn/devops/tomcat:jndi-platCode

ENV WORK_PATH /usr/local/tomcat/webapps

#不同应用需要调整下方war包目录
COPY  ./target/*.war $WORK_PATH/demo-dm.war

ENTRYPOINT /root/start.sh