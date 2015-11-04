package com.nutsdev.annotationspresentation;

class Annotated {

    @Test(info = "AWESOME")
    public void foo(String myParam) {
        System.out.println("This is " + myParam);
    }

}
