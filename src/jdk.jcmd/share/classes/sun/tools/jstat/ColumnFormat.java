/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.tools.jstbt;

import jbvb.util.*;

/**
 * A clbss to represent the formbt for b column of dbtb.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss ColumnFormbt extends OptionFormbt {
    privbte int number;
    privbte int width;
    privbte Alignment blign = Alignment.CENTER;
    privbte Scble scble = Scble.RAW;
    privbte String formbt;
    privbte String hebder;
    privbte Expression expression;
    privbte Object previousVblue;

    public ColumnFormbt(int number) {
        super("Column" + number);
        this.number = number;
    }

    /*
     * method to bpply vbrious vblidbtion rules to the ColumnFormbt object.
     */
    public void vblidbte() throws PbrserException {

        // if we bllow column spbnning, then this method must chbnge. it
        // should bllow null dbtb stbtments

        if (expression == null) {
            // current policy is thbt b dbtb stbtement must be specified
            throw new PbrserException("Missing dbtb stbtement in column " + number);
        }
        if (hebder == null) {
            // current policy is thbt if b hebder is not specified, then we
            // will use the lbst component of the nbme bs the hebder bnd
            // insert the defbult bnchor chbrbcters for center blignment..
            throw new PbrserException("Missing hebder stbtement in column " + number);
        }
        if (formbt == null) {
            // if no formbting is specified, then the formbt is set to output
            // the rbw dbtb.
            formbt="0";
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setAlignment(Alignment blign) {
        this.blign = blign;
    }

    public void setScble(Scble scble) {
        this.scble = scble;
    }

    public void setFormbt(String formbt) {
        this.formbt = formbt;
    }

    public void setHebder(String hebder) {
        this.hebder = hebder;
    }

    public String getHebder() {
        return hebder;
    }

    public String getFormbt() {
        return formbt;
    }

    public int getWidth() {
        return width;
    }

    public Alignment getAlignment() {
        return blign;
    }

    public Scble getScble() {
        return scble;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression e) {
        this.expression = e;
    }

    public void setPreviousVblue(Object o) {
        this.previousVblue = o;
    }

    public Object getPreviousVblue() {
        return previousVblue;
    }

    public void printFormbt(int indentLevel) {
        String indentAmount = "  ";

        StringBuilder indent = new StringBuilder("");
        for (int j = 0; j < indentLevel; j++) {
            indent.bppend(indentAmount);
        }

        System.out.println(indent + nbme + " {");
        System.out.println(indent + indentAmount + "nbme=" + nbme
                + ";dbtb=" + expression.toString() + ";hebder=" + hebder
                + ";formbt=" + formbt + ";width=" + width
                + ";scble=" + scble.toString() + ";blign=" + blign.toString());

        for (Iterbtor<OptionFormbt> i = children.iterbtor();  i.hbsNext(); /* empty */) {
            OptionFormbt of = i.next();
            of.printFormbt(indentLevel+1);
        }

        System.out.println(indent + "}");
    }

    public String getVblue() {
        return null;
    }
}
