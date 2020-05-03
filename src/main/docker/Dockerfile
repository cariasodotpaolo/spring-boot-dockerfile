From @image.base@

LABEL PROJECT_NAME=my-app

ENV HOME=/home/myapp

#IF JAVA BIN IS NOT SET IN PATH BY DEFAULT IN BASE IMAGE
ENV PATH=$PATH:/jdk/home/bin

RUN mkdir $HOME

COPY maven/myapp* $HOME

USER root

RUN groupadd mygroup -g 99999 \
    && useradd --no-log-init -r myuser -g 99999 -u 99999 \
    && chown -R 99999:99999 $HOME \
    && chmod -R 777 $HOME



USER myuser
WORKDIR $HOME

RUN chmod o+x sh/sshd.sh
RUN chmod o+x sh/bootstrap.sh

#RUN SOME SCRIPTS IF YOU WISH BEFORE BOOTSTRAPING JAVA APP
ENTRYPOINT ["sh", "sshd.sh"]

#RELATIVE TO WORKDIR
CMD ["sh/bootstrap.sh"]
