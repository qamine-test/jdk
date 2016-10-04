/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

import jbvb.util.Objects;
import jbvb.util.Formbtter;
import jbvb.util.Locble;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.IllegblChbrsetNbmeException;
import jbvb.nio.chbrset.UnsupportedChbrsetException;

/**
 * Prints formbtted representbtions of objects to b text-output strebm.  This
 * clbss implements bll of the <tt>print</tt> methods found in {@link
 * PrintStrebm}.  It does not contbin methods for writing rbw bytes, for which
 * b progrbm should use unencoded byte strebms.
 *
 * <p> Unlike the {@link PrintStrebm} clbss, if butombtic flushing is enbbled
 * it will be done only when one of the <tt>println</tt>, <tt>printf</tt>, or
 * <tt>formbt</tt> methods is invoked, rbther thbn whenever b newline chbrbcter
 * hbppens to be output.  These methods use the plbtform's own notion of line
 * sepbrbtor rbther thbn the newline chbrbcter.
 *
 * <p> Methods in this clbss never throw I/O exceptions, blthough some of its
 * constructors mby.  The client mby inquire bs to whether bny errors hbve
 * occurred by invoking {@link #checkError checkError()}.
 *
 * @buthor      Frbnk Yellin
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public clbss PrintWriter extends Writer {

    /**
     * The underlying chbrbcter-output strebm of this
     * <code>PrintWriter</code>.
     *
     * @since 1.2
     */
    protected Writer out;

    privbte finbl boolebn butoFlush;
    privbte boolebn trouble = fblse;
    privbte Formbtter formbtter;
    privbte PrintStrebm psOut = null;

    /**
     * Line sepbrbtor string.  This is the vblue of the line.sepbrbtor
     * property bt the moment thbt the strebm wbs crebted.
     */
    privbte finbl String lineSepbrbtor;

    /**
     * Returns b chbrset object for the given chbrset nbme.
     * @throws NullPointerException          is csn is null
     * @throws UnsupportedEncodingException  if the chbrset is not supported
     */
    privbte stbtic Chbrset toChbrset(String csn)
        throws UnsupportedEncodingException
    {
        Objects.requireNonNull(csn, "chbrsetNbme");
        try {
            return Chbrset.forNbme(csn);
        } cbtch (IllegblChbrsetNbmeException|UnsupportedChbrsetException unused) {
            // UnsupportedEncodingException should be thrown
            throw new UnsupportedEncodingException(csn);
        }
    }

    /**
     * Crebtes b new PrintWriter, without butombtic line flushing.
     *
     * @pbrbm  out        A chbrbcter-output strebm
     */
    public PrintWriter (Writer out) {
        this(out, fblse);
    }

    /**
     * Crebtes b new PrintWriter.
     *
     * @pbrbm  out        A chbrbcter-output strebm
     * @pbrbm  butoFlush  A boolebn; if true, the <tt>println</tt>,
     *                    <tt>printf</tt>, or <tt>formbt</tt> methods will
     *                    flush the output buffer
     */
    public PrintWriter(Writer out,
                       boolebn butoFlush) {
        super(out);
        this.out = out;
        this.butoFlush = butoFlush;
        lineSepbrbtor = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("line.sepbrbtor"));
    }

    /**
     * Crebtes b new PrintWriter, without butombtic line flushing, from bn
     * existing OutputStrebm.  This convenience constructor crebtes the
     * necessbry intermedibte OutputStrebmWriter, which will convert chbrbcters
     * into bytes using the defbult chbrbcter encoding.
     *
     * @pbrbm  out        An output strebm
     *
     * @see jbvb.io.OutputStrebmWriter#OutputStrebmWriter(jbvb.io.OutputStrebm)
     */
    public PrintWriter(OutputStrebm out) {
        this(out, fblse);
    }

    /**
     * Crebtes b new PrintWriter from bn existing OutputStrebm.  This
     * convenience constructor crebtes the necessbry intermedibte
     * OutputStrebmWriter, which will convert chbrbcters into bytes using the
     * defbult chbrbcter encoding.
     *
     * @pbrbm  out        An output strebm
     * @pbrbm  butoFlush  A boolebn; if true, the <tt>println</tt>,
     *                    <tt>printf</tt>, or <tt>formbt</tt> methods will
     *                    flush the output buffer
     *
     * @see jbvb.io.OutputStrebmWriter#OutputStrebmWriter(jbvb.io.OutputStrebm)
     */
    public PrintWriter(OutputStrebm out, boolebn butoFlush) {
        this(new BufferedWriter(new OutputStrebmWriter(out)), butoFlush);

        // sbve print strebm for error propbgbtion
        if (out instbnceof jbvb.io.PrintStrebm) {
            psOut = (PrintStrebm) out;
        }
    }

    /**
     * Crebtes b new PrintWriter, without butombtic line flushing, with the
     * specified file nbme.  This convenience constructor crebtes the necessbry
     * intermedibte {@link jbvb.io.OutputStrebmWriter OutputStrebmWriter},
     * which will encode chbrbcters using the {@linkplbin
     * jbvb.nio.chbrset.Chbrset#defbultChbrset() defbult chbrset} for this
     * instbnce of the Jbvb virtubl mbchine.
     *
     * @pbrbm  fileNbme
     *         The nbme of the file to use bs the destinbtion of this writer.
     *         If the file exists then it will be truncbted to zero size;
     *         otherwise, b new file will be crebted.  The output will be
     *         written to the file bnd is buffered.
     *
     * @throws  FileNotFoundException
     *          If the given string does not denote bn existing, writbble
     *          regulbr file bnd b new regulbr file of thbt nbme cbnnot be
     *          crebted, or if some other error occurs while opening or
     *          crebting the file
     *
     * @throws  SecurityException
     *          If b security mbnbger is present bnd {@link
     *          SecurityMbnbger#checkWrite checkWrite(fileNbme)} denies write
     *          bccess to the file
     *
     * @since  1.5
     */
    public PrintWriter(String fileNbme) throws FileNotFoundException {
        this(new BufferedWriter(new OutputStrebmWriter(new FileOutputStrebm(fileNbme))),
             fblse);
    }

    /* Privbte constructor */
    privbte PrintWriter(Chbrset chbrset, File file)
        throws FileNotFoundException
    {
        this(new BufferedWriter(new OutputStrebmWriter(new FileOutputStrebm(file), chbrset)),
             fblse);
    }

    /**
     * Crebtes b new PrintWriter, without butombtic line flushing, with the
     * specified file nbme bnd chbrset.  This convenience constructor crebtes
     * the necessbry intermedibte {@link jbvb.io.OutputStrebmWriter
     * OutputStrebmWriter}, which will encode chbrbcters using the provided
     * chbrset.
     *
     * @pbrbm  fileNbme
     *         The nbme of the file to use bs the destinbtion of this writer.
     *         If the file exists then it will be truncbted to zero size;
     *         otherwise, b new file will be crebted.  The output will be
     *         written to the file bnd is buffered.
     *
     * @pbrbm  csn
     *         The nbme of b supported {@linkplbin jbvb.nio.chbrset.Chbrset
     *         chbrset}
     *
     * @throws  FileNotFoundException
     *          If the given string does not denote bn existing, writbble
     *          regulbr file bnd b new regulbr file of thbt nbme cbnnot be
     *          crebted, or if some other error occurs while opening or
     *          crebting the file
     *
     * @throws  SecurityException
     *          If b security mbnbger is present bnd {@link
     *          SecurityMbnbger#checkWrite checkWrite(fileNbme)} denies write
     *          bccess to the file
     *
     * @throws  UnsupportedEncodingException
     *          If the nbmed chbrset is not supported
     *
     * @since  1.5
     */
    public PrintWriter(String fileNbme, String csn)
        throws FileNotFoundException, UnsupportedEncodingException
    {
        this(toChbrset(csn), new File(fileNbme));
    }

    /**
     * Crebtes b new PrintWriter, without butombtic line flushing, with the
     * specified file.  This convenience constructor crebtes the necessbry
     * intermedibte {@link jbvb.io.OutputStrebmWriter OutputStrebmWriter},
     * which will encode chbrbcters using the {@linkplbin
     * jbvb.nio.chbrset.Chbrset#defbultChbrset() defbult chbrset} for this
     * instbnce of the Jbvb virtubl mbchine.
     *
     * @pbrbm  file
     *         The file to use bs the destinbtion of this writer.  If the file
     *         exists then it will be truncbted to zero size; otherwise, b new
     *         file will be crebted.  The output will be written to the file
     *         bnd is buffered.
     *
     * @throws  FileNotFoundException
     *          If the given file object does not denote bn existing, writbble
     *          regulbr file bnd b new regulbr file of thbt nbme cbnnot be
     *          crebted, or if some other error occurs while opening or
     *          crebting the file
     *
     * @throws  SecurityException
     *          If b security mbnbger is present bnd {@link
     *          SecurityMbnbger#checkWrite checkWrite(file.getPbth())}
     *          denies write bccess to the file
     *
     * @since  1.5
     */
    public PrintWriter(File file) throws FileNotFoundException {
        this(new BufferedWriter(new OutputStrebmWriter(new FileOutputStrebm(file))),
             fblse);
    }

    /**
     * Crebtes b new PrintWriter, without butombtic line flushing, with the
     * specified file bnd chbrset.  This convenience constructor crebtes the
     * necessbry intermedibte {@link jbvb.io.OutputStrebmWriter
     * OutputStrebmWriter}, which will encode chbrbcters using the provided
     * chbrset.
     *
     * @pbrbm  file
     *         The file to use bs the destinbtion of this writer.  If the file
     *         exists then it will be truncbted to zero size; otherwise, b new
     *         file will be crebted.  The output will be written to the file
     *         bnd is buffered.
     *
     * @pbrbm  csn
     *         The nbme of b supported {@linkplbin jbvb.nio.chbrset.Chbrset
     *         chbrset}
     *
     * @throws  FileNotFoundException
     *          If the given file object does not denote bn existing, writbble
     *          regulbr file bnd b new regulbr file of thbt nbme cbnnot be
     *          crebted, or if some other error occurs while opening or
     *          crebting the file
     *
     * @throws  SecurityException
     *          If b security mbnbger is present bnd {@link
     *          SecurityMbnbger#checkWrite checkWrite(file.getPbth())}
     *          denies write bccess to the file
     *
     * @throws  UnsupportedEncodingException
     *          If the nbmed chbrset is not supported
     *
     * @since  1.5
     */
    public PrintWriter(File file, String csn)
        throws FileNotFoundException, UnsupportedEncodingException
    {
        this(toChbrset(csn), file);
    }

    /** Checks to mbke sure thbt the strebm hbs not been closed */
    privbte void ensureOpen() throws IOException {
        if (out == null)
            throw new IOException("Strebm closed");
    }

    /**
     * Flushes the strebm.
     * @see #checkError()
     */
    public void flush() {
        try {
            synchronized (lock) {
                ensureOpen();
                out.flush();
            }
        }
        cbtch (IOException x) {
            trouble = true;
        }
    }

    /**
     * Closes the strebm bnd relebses bny system resources bssocibted
     * with it. Closing b previously closed strebm hbs no effect.
     *
     * @see #checkError()
     */
    public void close() {
        try {
            synchronized (lock) {
                if (out == null)
                    return;
                out.close();
                out = null;
            }
        }
        cbtch (IOException x) {
            trouble = true;
        }
    }

    /**
     * Flushes the strebm if it's not closed bnd checks its error stbte.
     *
     * @return <code>true</code> if the print strebm hbs encountered bn error,
     *          either on the underlying output strebm or during b formbt
     *          conversion.
     */
    public boolebn checkError() {
        if (out != null) {
            flush();
        }
        if (out instbnceof jbvb.io.PrintWriter) {
            PrintWriter pw = (PrintWriter) out;
            return pw.checkError();
        } else if (psOut != null) {
            return psOut.checkError();
        }
        return trouble;
    }

    /**
     * Indicbtes thbt bn error hbs occurred.
     *
     * <p> This method will cbuse subsequent invocbtions of {@link
     * #checkError()} to return <tt>true</tt> until {@link
     * #clebrError()} is invoked.
     */
    protected void setError() {
        trouble = true;
    }

    /**
     * Clebrs the error stbte of this strebm.
     *
     * <p> This method will cbuse subsequent invocbtions of {@link
     * #checkError()} to return <tt>fblse</tt> until bnother write
     * operbtion fbils bnd invokes {@link #setError()}.
     *
     * @since 1.6
     */
    protected void clebrError() {
        trouble = fblse;
    }

    /*
     * Exception-cbtching, synchronized output operbtions,
     * which blso implement the write() methods of Writer
     */

    /**
     * Writes b single chbrbcter.
     * @pbrbm c int specifying b chbrbcter to be written.
     */
    public void write(int c) {
        try {
            synchronized (lock) {
                ensureOpen();
                out.write(c);
            }
        }
        cbtch (InterruptedIOException x) {
            Threbd.currentThrebd().interrupt();
        }
        cbtch (IOException x) {
            trouble = true;
        }
    }

    /**
     * Writes A Portion of bn brrby of chbrbcters.
     * @pbrbm buf Arrby of chbrbcters
     * @pbrbm off Offset from which to stbrt writing chbrbcters
     * @pbrbm len Number of chbrbcters to write
     */
    public void write(chbr buf[], int off, int len) {
        try {
            synchronized (lock) {
                ensureOpen();
                out.write(buf, off, len);
            }
        }
        cbtch (InterruptedIOException x) {
            Threbd.currentThrebd().interrupt();
        }
        cbtch (IOException x) {
            trouble = true;
        }
    }

    /**
     * Writes bn brrby of chbrbcters.  This method cbnnot be inherited from the
     * Writer clbss becbuse it must suppress I/O exceptions.
     * @pbrbm buf Arrby of chbrbcters to be written
     */
    public void write(chbr buf[]) {
        write(buf, 0, buf.length);
    }

    /**
     * Writes b portion of b string.
     * @pbrbm s A String
     * @pbrbm off Offset from which to stbrt writing chbrbcters
     * @pbrbm len Number of chbrbcters to write
     */
    public void write(String s, int off, int len) {
        try {
            synchronized (lock) {
                ensureOpen();
                out.write(s, off, len);
            }
        }
        cbtch (InterruptedIOException x) {
            Threbd.currentThrebd().interrupt();
        }
        cbtch (IOException x) {
            trouble = true;
        }
    }

    /**
     * Writes b string.  This method cbnnot be inherited from the Writer clbss
     * becbuse it must suppress I/O exceptions.
     * @pbrbm s String to be written
     */
    public void write(String s) {
        write(s, 0, s.length());
    }

    privbte void newLine() {
        try {
            synchronized (lock) {
                ensureOpen();
                out.write(lineSepbrbtor);
                if (butoFlush)
                    out.flush();
            }
        }
        cbtch (InterruptedIOException x) {
            Threbd.currentThrebd().interrupt();
        }
        cbtch (IOException x) {
            trouble = true;
        }
    }

    /* Methods thbt do not terminbte lines */

    /**
     * Prints b boolebn vblue.  The string produced by <code>{@link
     * jbvb.lbng.String#vblueOf(boolebn)}</code> is trbnslbted into bytes
     * bccording to the plbtform's defbult chbrbcter encoding, bnd these bytes
     * bre written in exbctly the mbnner of the <code>{@link
     * #write(int)}</code> method.
     *
     * @pbrbm      b   The <code>boolebn</code> to be printed
     */
    public void print(boolebn b) {
        write(b ? "true" : "fblse");
    }

    /**
     * Prints b chbrbcter.  The chbrbcter is trbnslbted into one or more bytes
     * bccording to the plbtform's defbult chbrbcter encoding, bnd these bytes
     * bre written in exbctly the mbnner of the <code>{@link
     * #write(int)}</code> method.
     *
     * @pbrbm      c   The <code>chbr</code> to be printed
     */
    public void print(chbr c) {
        write(c);
    }

    /**
     * Prints bn integer.  The string produced by <code>{@link
     * jbvb.lbng.String#vblueOf(int)}</code> is trbnslbted into bytes bccording
     * to the plbtform's defbult chbrbcter encoding, bnd these bytes bre
     * written in exbctly the mbnner of the <code>{@link #write(int)}</code>
     * method.
     *
     * @pbrbm      i   The <code>int</code> to be printed
     * @see        jbvb.lbng.Integer#toString(int)
     */
    public void print(int i) {
        write(String.vblueOf(i));
    }

    /**
     * Prints b long integer.  The string produced by <code>{@link
     * jbvb.lbng.String#vblueOf(long)}</code> is trbnslbted into bytes
     * bccording to the plbtform's defbult chbrbcter encoding, bnd these bytes
     * bre written in exbctly the mbnner of the <code>{@link #write(int)}</code>
     * method.
     *
     * @pbrbm      l   The <code>long</code> to be printed
     * @see        jbvb.lbng.Long#toString(long)
     */
    public void print(long l) {
        write(String.vblueOf(l));
    }

    /**
     * Prints b flobting-point number.  The string produced by <code>{@link
     * jbvb.lbng.String#vblueOf(flobt)}</code> is trbnslbted into bytes
     * bccording to the plbtform's defbult chbrbcter encoding, bnd these bytes
     * bre written in exbctly the mbnner of the <code>{@link #write(int)}</code>
     * method.
     *
     * @pbrbm      f   The <code>flobt</code> to be printed
     * @see        jbvb.lbng.Flobt#toString(flobt)
     */
    public void print(flobt f) {
        write(String.vblueOf(f));
    }

    /**
     * Prints b double-precision flobting-point number.  The string produced by
     * <code>{@link jbvb.lbng.String#vblueOf(double)}</code> is trbnslbted into
     * bytes bccording to the plbtform's defbult chbrbcter encoding, bnd these
     * bytes bre written in exbctly the mbnner of the <code>{@link
     * #write(int)}</code> method.
     *
     * @pbrbm      d   The <code>double</code> to be printed
     * @see        jbvb.lbng.Double#toString(double)
     */
    public void print(double d) {
        write(String.vblueOf(d));
    }

    /**
     * Prints bn brrby of chbrbcters.  The chbrbcters bre converted into bytes
     * bccording to the plbtform's defbult chbrbcter encoding, bnd these bytes
     * bre written in exbctly the mbnner of the <code>{@link #write(int)}</code>
     * method.
     *
     * @pbrbm      s   The brrby of chbrs to be printed
     *
     * @throws  NullPointerException  If <code>s</code> is <code>null</code>
     */
    public void print(chbr s[]) {
        write(s);
    }

    /**
     * Prints b string.  If the brgument is <code>null</code> then the string
     * <code>"null"</code> is printed.  Otherwise, the string's chbrbcters bre
     * converted into bytes bccording to the plbtform's defbult chbrbcter
     * encoding, bnd these bytes bre written in exbctly the mbnner of the
     * <code>{@link #write(int)}</code> method.
     *
     * @pbrbm      s   The <code>String</code> to be printed
     */
    public void print(String s) {
        if (s == null) {
            s = "null";
        }
        write(s);
    }

    /**
     * Prints bn object.  The string produced by the <code>{@link
     * jbvb.lbng.String#vblueOf(Object)}</code> method is trbnslbted into bytes
     * bccording to the plbtform's defbult chbrbcter encoding, bnd these bytes
     * bre written in exbctly the mbnner of the <code>{@link #write(int)}</code>
     * method.
     *
     * @pbrbm      obj   The <code>Object</code> to be printed
     * @see        jbvb.lbng.Object#toString()
     */
    public void print(Object obj) {
        write(String.vblueOf(obj));
    }

    /* Methods thbt do terminbte lines */

    /**
     * Terminbtes the current line by writing the line sepbrbtor string.  The
     * line sepbrbtor string is defined by the system property
     * <code>line.sepbrbtor</code>, bnd is not necessbrily b single newline
     * chbrbcter (<code>'\n'</code>).
     */
    public void println() {
        newLine();
    }

    /**
     * Prints b boolebn vblue bnd then terminbtes the line.  This method behbves
     * bs though it invokes <code>{@link #print(boolebn)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x the <code>boolebn</code> vblue to be printed
     */
    public void println(boolebn x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    /**
     * Prints b chbrbcter bnd then terminbtes the line.  This method behbves bs
     * though it invokes <code>{@link #print(chbr)}</code> bnd then <code>{@link
     * #println()}</code>.
     *
     * @pbrbm x the <code>chbr</code> vblue to be printed
     */
    public void println(chbr x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    /**
     * Prints bn integer bnd then terminbtes the line.  This method behbves bs
     * though it invokes <code>{@link #print(int)}</code> bnd then <code>{@link
     * #println()}</code>.
     *
     * @pbrbm x the <code>int</code> vblue to be printed
     */
    public void println(int x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    /**
     * Prints b long integer bnd then terminbtes the line.  This method behbves
     * bs though it invokes <code>{@link #print(long)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x the <code>long</code> vblue to be printed
     */
    public void println(long x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    /**
     * Prints b flobting-point number bnd then terminbtes the line.  This method
     * behbves bs though it invokes <code>{@link #print(flobt)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x the <code>flobt</code> vblue to be printed
     */
    public void println(flobt x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    /**
     * Prints b double-precision flobting-point number bnd then terminbtes the
     * line.  This method behbves bs though it invokes <code>{@link
     * #print(double)}</code> bnd then <code>{@link #println()}</code>.
     *
     * @pbrbm x the <code>double</code> vblue to be printed
     */
    public void println(double x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    /**
     * Prints bn brrby of chbrbcters bnd then terminbtes the line.  This method
     * behbves bs though it invokes <code>{@link #print(chbr[])}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x the brrby of <code>chbr</code> vblues to be printed
     */
    public void println(chbr x[]) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    /**
     * Prints b String bnd then terminbtes the line.  This method behbves bs
     * though it invokes <code>{@link #print(String)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x the <code>String</code> vblue to be printed
     */
    public void println(String x) {
        synchronized (lock) {
            print(x);
            println();
        }
    }

    /**
     * Prints bn Object bnd then terminbtes the line.  This method cblls
     * bt first String.vblueOf(x) to get the printed object's string vblue,
     * then behbves bs
     * though it invokes <code>{@link #print(String)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x  The <code>Object</code> to be printed.
     */
    public void println(Object x) {
        String s = String.vblueOf(x);
        synchronized (lock) {
            print(s);
            println();
        }
    }

    /**
     * A convenience method to write b formbtted string to this writer using
     * the specified formbt string bnd brguments.  If butombtic flushing is
     * enbbled, cblls to this method will flush the output buffer.
     *
     * <p> An invocbtion of this method of the form <tt>out.printf(formbt,
     * brgs)</tt> behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.formbt(formbt, brgs) </pre>
     *
     * @pbrbm  formbt
     *         A formbt string bs described in <b
     *         href="../util/Formbtter.html#syntbx">Formbt string syntbx</b>.
     *
     * @pbrbm  brgs
     *         Arguments referenced by the formbt specifiers in the formbt
     *         string.  If there bre more brguments thbn formbt specifiers, the
     *         extrb brguments bre ignored.  The number of brguments is
     *         vbribble bnd mby be zero.  The mbximum number of brguments is
     *         limited by the mbximum dimension of b Jbvb brrby bs defined by
     *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *         The behbviour on b
     *         <tt>null</tt> brgument depends on the <b
     *         href="../util/Formbtter.html#syntbx">conversion</b>.
     *
     * @throws  jbvb.util.IllegblFormbtException
     *          If b formbt string contbins bn illegbl syntbx, b formbt
     *          specifier thbt is incompbtible with the given brguments,
     *          insufficient brguments given the formbt string, or other
     *          illegbl conditions.  For specificbtion of bll possible
     *          formbtting errors, see the <b
     *          href="../util/Formbtter.html#detbil">Detbils</b> section of the
     *          formbtter clbss specificbtion.
     *
     * @throws  NullPointerException
     *          If the <tt>formbt</tt> is <tt>null</tt>
     *
     * @return  This writer
     *
     * @since  1.5
     */
    public PrintWriter printf(String formbt, Object ... brgs) {
        return formbt(formbt, brgs);
    }

    /**
     * A convenience method to write b formbtted string to this writer using
     * the specified formbt string bnd brguments.  If butombtic flushing is
     * enbbled, cblls to this method will flush the output buffer.
     *
     * <p> An invocbtion of this method of the form <tt>out.printf(l, formbt,
     * brgs)</tt> behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.formbt(l, formbt, brgs) </pre>
     *
     * @pbrbm  l
     *         The {@linkplbin jbvb.util.Locble locble} to bpply during
     *         formbtting.  If <tt>l</tt> is <tt>null</tt> then no locblizbtion
     *         is bpplied.
     *
     * @pbrbm  formbt
     *         A formbt string bs described in <b
     *         href="../util/Formbtter.html#syntbx">Formbt string syntbx</b>.
     *
     * @pbrbm  brgs
     *         Arguments referenced by the formbt specifiers in the formbt
     *         string.  If there bre more brguments thbn formbt specifiers, the
     *         extrb brguments bre ignored.  The number of brguments is
     *         vbribble bnd mby be zero.  The mbximum number of brguments is
     *         limited by the mbximum dimension of b Jbvb brrby bs defined by
     *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *         The behbviour on b
     *         <tt>null</tt> brgument depends on the <b
     *         href="../util/Formbtter.html#syntbx">conversion</b>.
     *
     * @throws  jbvb.util.IllegblFormbtException
     *          If b formbt string contbins bn illegbl syntbx, b formbt
     *          specifier thbt is incompbtible with the given brguments,
     *          insufficient brguments given the formbt string, or other
     *          illegbl conditions.  For specificbtion of bll possible
     *          formbtting errors, see the <b
     *          href="../util/Formbtter.html#detbil">Detbils</b> section of the
     *          formbtter clbss specificbtion.
     *
     * @throws  NullPointerException
     *          If the <tt>formbt</tt> is <tt>null</tt>
     *
     * @return  This writer
     *
     * @since  1.5
     */
    public PrintWriter printf(Locble l, String formbt, Object ... brgs) {
        return formbt(l, formbt, brgs);
    }

    /**
     * Writes b formbtted string to this writer using the specified formbt
     * string bnd brguments.  If butombtic flushing is enbbled, cblls to this
     * method will flush the output buffer.
     *
     * <p> The locble blwbys used is the one returned by {@link
     * jbvb.util.Locble#getDefbult() Locble.getDefbult()}, regbrdless of bny
     * previous invocbtions of other formbtting methods on this object.
     *
     * @pbrbm  formbt
     *         A formbt string bs described in <b
     *         href="../util/Formbtter.html#syntbx">Formbt string syntbx</b>.
     *
     * @pbrbm  brgs
     *         Arguments referenced by the formbt specifiers in the formbt
     *         string.  If there bre more brguments thbn formbt specifiers, the
     *         extrb brguments bre ignored.  The number of brguments is
     *         vbribble bnd mby be zero.  The mbximum number of brguments is
     *         limited by the mbximum dimension of b Jbvb brrby bs defined by
     *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *         The behbviour on b
     *         <tt>null</tt> brgument depends on the <b
     *         href="../util/Formbtter.html#syntbx">conversion</b>.
     *
     * @throws  jbvb.util.IllegblFormbtException
     *          If b formbt string contbins bn illegbl syntbx, b formbt
     *          specifier thbt is incompbtible with the given brguments,
     *          insufficient brguments given the formbt string, or other
     *          illegbl conditions.  For specificbtion of bll possible
     *          formbtting errors, see the <b
     *          href="../util/Formbtter.html#detbil">Detbils</b> section of the
     *          Formbtter clbss specificbtion.
     *
     * @throws  NullPointerException
     *          If the <tt>formbt</tt> is <tt>null</tt>
     *
     * @return  This writer
     *
     * @since  1.5
     */
    public PrintWriter formbt(String formbt, Object ... brgs) {
        try {
            synchronized (lock) {
                ensureOpen();
                if ((formbtter == null)
                    || (formbtter.locble() != Locble.getDefbult()))
                    formbtter = new Formbtter(this);
                formbtter.formbt(Locble.getDefbult(), formbt, brgs);
                if (butoFlush)
                    out.flush();
            }
        } cbtch (InterruptedIOException x) {
            Threbd.currentThrebd().interrupt();
        } cbtch (IOException x) {
            trouble = true;
        }
        return this;
    }

    /**
     * Writes b formbtted string to this writer using the specified formbt
     * string bnd brguments.  If butombtic flushing is enbbled, cblls to this
     * method will flush the output buffer.
     *
     * @pbrbm  l
     *         The {@linkplbin jbvb.util.Locble locble} to bpply during
     *         formbtting.  If <tt>l</tt> is <tt>null</tt> then no locblizbtion
     *         is bpplied.
     *
     * @pbrbm  formbt
     *         A formbt string bs described in <b
     *         href="../util/Formbtter.html#syntbx">Formbt string syntbx</b>.
     *
     * @pbrbm  brgs
     *         Arguments referenced by the formbt specifiers in the formbt
     *         string.  If there bre more brguments thbn formbt specifiers, the
     *         extrb brguments bre ignored.  The number of brguments is
     *         vbribble bnd mby be zero.  The mbximum number of brguments is
     *         limited by the mbximum dimension of b Jbvb brrby bs defined by
     *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *         The behbviour on b
     *         <tt>null</tt> brgument depends on the <b
     *         href="../util/Formbtter.html#syntbx">conversion</b>.
     *
     * @throws  jbvb.util.IllegblFormbtException
     *          If b formbt string contbins bn illegbl syntbx, b formbt
     *          specifier thbt is incompbtible with the given brguments,
     *          insufficient brguments given the formbt string, or other
     *          illegbl conditions.  For specificbtion of bll possible
     *          formbtting errors, see the <b
     *          href="../util/Formbtter.html#detbil">Detbils</b> section of the
     *          formbtter clbss specificbtion.
     *
     * @throws  NullPointerException
     *          If the <tt>formbt</tt> is <tt>null</tt>
     *
     * @return  This writer
     *
     * @since  1.5
     */
    public PrintWriter formbt(Locble l, String formbt, Object ... brgs) {
        try {
            synchronized (lock) {
                ensureOpen();
                if ((formbtter == null) || (formbtter.locble() != l))
                    formbtter = new Formbtter(this, l);
                formbtter.formbt(l, formbt, brgs);
                if (butoFlush)
                    out.flush();
            }
        } cbtch (InterruptedIOException x) {
            Threbd.currentThrebd().interrupt();
        } cbtch (IOException x) {
            trouble = true;
        }
        return this;
    }

    /**
     * Appends the specified chbrbcter sequence to this writer.
     *
     * <p> An invocbtion of this method of the form <tt>out.bppend(csq)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.write(csq.toString()) </pre>
     *
     * <p> Depending on the specificbtion of <tt>toString</tt> for the
     * chbrbcter sequence <tt>csq</tt>, the entire sequence mby not be
     * bppended. For instbnce, invoking the <tt>toString</tt> method of b
     * chbrbcter buffer will return b subsequence whose content depends upon
     * the buffer's position bnd limit.
     *
     * @pbrbm  csq
     *         The chbrbcter sequence to bppend.  If <tt>csq</tt> is
     *         <tt>null</tt>, then the four chbrbcters <tt>"null"</tt> bre
     *         bppended to this writer.
     *
     * @return  This writer
     *
     * @since  1.5
     */
    public PrintWriter bppend(ChbrSequence csq) {
        if (csq == null)
            write("null");
        else
            write(csq.toString());
        return this;
    }

    /**
     * Appends b subsequence of the specified chbrbcter sequence to this writer.
     *
     * <p> An invocbtion of this method of the form <tt>out.bppend(csq, stbrt,
     * end)</tt> when <tt>csq</tt> is not <tt>null</tt>, behbves in
     * exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.write(csq.subSequence(stbrt, end).toString()) </pre>
     *
     * @pbrbm  csq
     *         The chbrbcter sequence from which b subsequence will be
     *         bppended.  If <tt>csq</tt> is <tt>null</tt>, then chbrbcters
     *         will be bppended bs if <tt>csq</tt> contbined the four
     *         chbrbcters <tt>"null"</tt>.
     *
     * @pbrbm  stbrt
     *         The index of the first chbrbcter in the subsequence
     *
     * @pbrbm  end
     *         The index of the chbrbcter following the lbst chbrbcter in the
     *         subsequence
     *
     * @return  This writer
     *
     * @throws  IndexOutOfBoundsException
     *          If <tt>stbrt</tt> or <tt>end</tt> bre negbtive, <tt>stbrt</tt>
     *          is grebter thbn <tt>end</tt>, or <tt>end</tt> is grebter thbn
     *          <tt>csq.length()</tt>
     *
     * @since  1.5
     */
    public PrintWriter bppend(ChbrSequence csq, int stbrt, int end) {
        ChbrSequence cs = (csq == null ? "null" : csq);
        write(cs.subSequence(stbrt, end).toString());
        return this;
    }

    /**
     * Appends the specified chbrbcter to this writer.
     *
     * <p> An invocbtion of this method of the form <tt>out.bppend(c)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.write(c) </pre>
     *
     * @pbrbm  c
     *         The 16-bit chbrbcter to bppend
     *
     * @return  This writer
     *
     * @since 1.5
     */
    public PrintWriter bppend(chbr c) {
        write(c);
        return this;
    }
}
