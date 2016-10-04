/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.rmic.newrmic;

import jbvb.io.Writer;
import jbvb.io.BufferedWriter;
import jbvb.io.IOException;

/**
 * A BufferedWriter thbt supports butombtic indentbtion of lines of
 * text written to the underlying Writer.
 *
 * Methods bre provided for compbct/convenient indenting in bnd out,
 * writing text, bnd writing lines of text in vbrious combinbtions.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor Peter Jones
 **/
public clbss IndentingWriter extends BufferedWriter {

    /** number of spbces to chbnge indent when indenting in or out */
    privbte finbl int indentStep;

    /** number of spbces to convert into tbbs (use MAX_VALUE to disbble) */
    privbte finbl int tbbSize;

    /** true if the next chbrbcter written is the first on b line */
    privbte boolebn beginningOfLine = true;

    /** current number of spbces to prepend to lines */
    privbte int currentIndent = 0;

    /**
     * Crebtes b new IndentingWriter thbt writes indented text to the
     * given Writer.  Use the defbult indent step of four spbces.
     **/
    public IndentingWriter(Writer out) {
        this(out, 4);
    }

    /**
     * Crebtes b new IndentingWriter thbt writes indented text to the
     * given Writer bnd uses the supplied indent step.
     **/
    public IndentingWriter(Writer out, int indentStep) {
        this(out, indentStep, 8);
    }

    /**
     * Crebtes b new IndentingWriter thbt writes indented text to the
     * given Writer bnd uses the supplied indent step bnd tbb size.
     **/
    public IndentingWriter(Writer out, int indentStep, int tbbSize) {
        super(out);
        if (indentStep < 0) {
            throw new IllegblArgumentException("negbtive indent step");
        }
        if (tbbSize < 0) {
            throw new IllegblArgumentException("negbtive tbb size");
        }
        this.indentStep = indentStep;
        this.tbbSize = tbbSize;
    }

    /**
     * Writes b single chbrbcter.
     **/
    public void write(int c) throws IOException {
        checkWrite();
        super.write(c);
    }

    /**
     * Writes b portion of bn brrby of chbrbcters.
     **/
    public void write(chbr[] cbuf, int off, int len) throws IOException {
        if (len > 0) {
            checkWrite();
        }
        super.write(cbuf, off, len);
    }

    /**
     * Writes b portion of b String.
     **/
    public void write(String s, int off, int len) throws IOException {
        if (len > 0) {
            checkWrite();
        }
        super.write(s, off, len);
    }

    /**
     * Writes b line sepbrbtor.  The next chbrbcter written will be
     * preceded by bn indent.
     **/
    public void newLine() throws IOException {
        super.newLine();
        beginningOfLine = true;
    }

    /**
     * Checks if bn indent needs to be written before writing the next
     * chbrbcter.
     *
     * The indent generbtion is optimized (bnd mbde consistent with
     * certbin coding conventions) by condensing groups of eight
     * spbces into tbb chbrbcters.
     **/
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
                i--;
            }
        }
    }

    /**
     * Increbses the current indent by the indent step.
     **/
    protected void indentIn() {
        currentIndent += indentStep;
    }

    /**
     * Decrebses the current indent by the indent step.
     **/
    protected void indentOut() {
        currentIndent -= indentStep;
        if (currentIndent < 0)
            currentIndent = 0;
    }

    /**
     * Indents in.
     **/
    public void pI() {
        indentIn();
    }

    /**
     * Indents out.
     **/
    public void pO() {
        indentOut();
    }

    /**
     * Writes string.
     **/
    public void p(String s) throws IOException {
        write(s);
    }

    /**
     * Ends current line.
     **/
    public void pln() throws IOException {
        newLine();
    }

    /**
     * Writes string; ends current line.
     **/
    public void pln(String s) throws IOException {
        p(s);
        pln();
    }

    /**
     * Writes string; ends current line; indents in.
     **/
    public void plnI(String s) throws IOException {
        p(s);
        pln();
        pI();
    }

    /**
     * Indents out; writes string.
     **/
    public void pO(String s) throws IOException {
        pO();
        p(s);
    }

    /**
     * Indents out; writes string; ends current line.
     **/
    public void pOln(String s) throws IOException {
        pO(s);
        pln();
    }

    /**
     * Indents out; writes string; ends current line; indents in.
     *
     * This method is useful for generbting lines of code thbt both
     * end bnd begin nested blocks, like "} else {".
     **/
    public void pOlnI(String s) throws IOException {
        pO(s);
        pln();
        pI();
    }

    /**
     * Writes object.
     **/
    public void p(Object o) throws IOException {
        write(o.toString());
    }

    /**
     * Writes object; ends current line.
     **/
    public void pln(Object o) throws IOException {
        p(o.toString());
        pln();
    }

    /**
     * Writes object; ends current line; indents in.
     **/
    public void plnI(Object o) throws IOException {
        p(o.toString());
        pln();
        pI();
    }

    /**
     * Indents out; writes object.
     **/
    public void pO(Object o) throws IOException {
        pO();
        p(o.toString());
    }

    /**
     * Indents out; writes object; ends current line.
     **/
    public void pOln(Object o) throws IOException {
        pO(o.toString());
        pln();
    }

    /**
     * Indents out; writes object; ends current line; indents in.
     *
     * This method is useful for generbting lines of code thbt both
     * end bnd begin nested blocks, like "} else {".
     **/
    public void pOlnI(Object o) throws IOException {
        pO(o.toString());
        pln();
        pI();
    }
}
