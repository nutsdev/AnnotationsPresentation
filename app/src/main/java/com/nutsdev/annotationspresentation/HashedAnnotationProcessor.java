package com.nutsdev.annotationspresentation;

@SupportedAnnotationTypes(value = {"com.nutsdev.annotationspresentation.Hashed"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class HashedAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}

/* так выглядит process с конкретной реализацией
public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if ( annotations == null || annotations.isEmpty()) {
            return false;
        }
        for (TypeElement annotation : annotations) {
            // Выбираем все элементы, у которых стоит наша аннотация
            final Set<? extends Element> fields = roundEnv.getElementsAnnotatedWith(annotation);
            JavacElements utils = javacProcessingEnv.getElementUtils();
            for (final Element field : fields) {
                //Получаем аннотацию, потом возьмём из неё метод хеширования.
                Hashed hashed = field.getAnnotation(Hashed.class);
                //преобразовываем аннотированный элемент в дерево
                JCTree blockNode = utils.getTree(field);
                if (blockNode instanceof JCTree.JCVariableDecl) {
                    //Помним, что поле может оказаться не только строковым.
                    JCTree.JCVariableDecl var = (JCTree.JCVariableDecl) blockNode;
                    //получаем инициализатор (то что после знака = )
                    JCTree.JCExpression initializer = var.getInitializer();
                    //Проверка отсечёт поля с инициализацией в конструкторе, а так же конструкции вида:
                    // "" + 1
                    // new String("new string")
                    if ((initializer != null) && (initializer  instanceof JCTree.JCLiteral)) {
                        JCTree.JCLiteral lit = (JCTree.JCLiteral) initializer;
                        //получаем строку
                        String value = lit.getValue().toString();
                        try {
                            MessageDigest md = MessageDigest.getInstance(hashed.method());
                            //Для однообразия на разных платформах задаём локаль.
                            md.update(value.getBytes("UTF-8"));
                            byte[] hash = md.digest();
                            StringBuilder str = new StringBuilder(hash.length * 2);
                            for (byte val : hash) {
                                str.append(String.format("%02X", val & 0xFF));
                            }
                            value = str.toString();
                            lit = maker.Literal(value);
                            var.init = lit;
                        } catch (NoSuchAlgorithmException e) {
                            //ошибка компиляции: неверный алгоритм хеширования
                        } catch (UnsupportedEncodingException e) {
                            //ошибка компиляции: такое вообще возможно??
                        }
                    } else {
                        //Ошибка компиляции: неверное применение аннотации.
                    }
                }
            }
        }
    }
*/
