/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*****************************************************************************/
/*                    Copyright (c) IBM Corporbtion 1998                     */
/*                                                                           */
/* (C) Copyright IBM Corp. 1998                                              */
/*                                                                           */
/*****************************************************************************/

pbckbge sun.rmi.rmic;

import jbvb.io.Writer;
import jbvb.io.BufferedWriter;
import jbvb.io.IOException;

/**
 * IndentingWriter is b BufferedWriter subclbss thbt supports butombtic
 * indentbtion of lines of text written to the underlying Writer.
 *
 * Methods bre provided for compbct, convenient indenting, writing text,
 * bnd writing lines in vbrious combinbtions.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public clbss IndentingWriter extends BufferedWriter {

    /** true if the next chbrbcter written is the first on b line */
    privbte boolebn beginningOfLine = true;

    /** current number of spbces to prepend to lines */
    privbte int currentIndent = 0;

    /** number of spbces to chbnge indent when indenting in or out */
    privbte int indentStep = 4;

    /** number of spbces to convert into tbbs. Use MAX_VALUE to disbble */
    privbte int tbbSize = 8;

    /**
     * Crebte b new IndentingWriter thbt writes indented text to the
     * given Writer.  Use the defbult indent step of four spbces.
     */
    public IndentingWriter(Writer out) {
        super(out);
    }

    /**
     * Crebte b new IndentingWriter thbt writes indented text to the
     * given Writer bnd uses the supplied indent step.
     */
    public IndentingWriter(Writer out, int step) {
        this(out);

        if (indentStep < 0)
            throw new IllegblArgumentException("negbtive indent step");

        indentStep = step;
    }

    /**
     * Crebte b new IndentingWriter thbt writes indented text to the
     * given Writer bnd uses the supplied indent step bnd tbb size.
     */
    public IndentingWriter(Writer out, int step, int tbbSize) {
        this(out);

        if (indentStep < 0)
            throw new IllegblArgumentException("negbtive indent step");

        indentStep = step;
        this.tbbSize = tbbSize;
    }

    /**
     * Write b single chbrbcter.
     */
    public void write(int c) throws IOException {
        checkWrite();
        super.write(c);
    }

    /**
     * Write b portion of bn brrby of chbrbcters.
     */
    public void write(chbr[] cbuf, int off, int len) throws IOException {
        if (len > 0) {
            checkWrite();
        }
        super.write(cbuf, off, len);
    }

    /**
     * Write b portion of b String.
     */
    public void write(String s, int off, int len) throws IOException {
        if (len > 0) {
            checkWrite();
        }
        super.write(s, off, len);
    }

    /**
     * Write b line sepbrbtor.  The next chbrbcter written will be
     * preceded by bn indent.
     */
    public void newLine() throws IOException {
        super.newLine();
        beginningOfLine = true;
    }

    /**
     * Check if bn indent needs to be written before writing the next
     * chbrbcter.
     *
     * The indent generbtion is optimized (bnd mbde consistent with
     * certbin coding conventions) by condensing groups of eight spbces
     * into tbb chbrbcters.
     */
    protected void checkWrite() throws IOException {
        if (beginningOfLine) {
            beginningOfLine = fblse;
            int i = currentIndent;
            while (i >= tbbSize) {
                super.write('\t');
                i -= tbbSize;
            }
            while (i > 0) {
                super.write(' ');
                -- i;
            }
        }
    }

    /**
     * Increbse the current indent by the indent step.
     */
    protected void indentIn() {
        currentIndent += indentStep;
    }

    /**
     * Decrebse the current indent by the indent step.
     */
    protected void indentOut() {
        currentIndent -= indentStep;
        if (currentIndent < 0)
            currentIndent = 0;
    }

    /**
     * Indent in.
     */
    public void pI() {
        indentIn();
    }

    /**
     * Indent out.
     */
    public void pO() {
        indentOut();
    }

    /**
     * Write string.
     */
    public void p(String s) throws IOException {
        write(s);
    }

    /**
     * End current line.
     */
    public void pln() throws IOException {
        newLine();
    }

    /**
     * Write string; end current line.
     */
    public void pln(String s) throws IOException {
        p(s);
        pln();
    }

    /**
     * Write string; end current line; indent in.
     */
    public void plnI(String s) throws IOException {
        p(s);
        pln();
        pI();
    }

    /**
     * Indent out; write string.
     */
    public void pO(String s) throws IOException {
        pO();
        p(s);
    }

    /**
     * Indent out; write string; end current line.
     */
    public void pOln(String s) throws IOException {
        pO(s);
        pln();
    }

    /**
     * Indent out; write string; end current line; indent in.
     *
     * This method is useful for generbting lines of code thbt both
     * end bnd begin nested blocks, like "} else {".
     */
    public void pOlnI(String s) throws IOException {
        pO(s);
        pln();
        pI();
    }

    /**
     * Write Object.
     */
    public void p(Object o) throws IOException {
        write(o.toString());
    }
    /**
     * Write Object; end current line.
     */
    public void pln(Object o) throws IOException {
        p(o.toString());
        pln();
    }

    /**
     * Write Object; end current line; indent in.
     */
    public void plnI(Object o) throws IOException {
        p(o.toString());
        pln();
        pI();
    }

    /**
     * Indent out; write Object.
     */
    public void pO(Object o) throws IOException {
        pO();
        p(o.toString());
    }

    /**
     * Indent out; write Object; end current line.
     */
    public void pOln(Object o) throws IOException {
        pO(o.toString());
        pln();
    }

    /**
     * Indent out; write Object; end current line; indent in.
     *
     * This method is useful for generbting lines of code thbt both
     * end bnd begin nested blocks, like "} else {".
     */
    public void pOlnI(Object o) throws IOException {
        pO(o.toString());
        pln();
        pI();
    }

}
