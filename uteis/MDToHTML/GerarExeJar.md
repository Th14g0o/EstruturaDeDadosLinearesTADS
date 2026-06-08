> Na raiz do repossitorio

### crie manifest.mf

```
Main-Class: uteis.MDToHTML.ProgramaSwing
```

> deixe uma linha em branco no final do arquivo

```bash
javac -d bin uteis/MDToHTML/*.java
```

```bash
jar cfm ConversorMD.jar manifest.mf -C bin .
```

> Verificar se ta tudo empacotado

```bash
jar tf ConversorMD.jar
```

> Para testar

```bash
java -jar ConversorMD.jar
```

> Seja pra onde for o executavel levar pasta fontes e base.html