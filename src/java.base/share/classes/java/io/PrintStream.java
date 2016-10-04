/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Formbtter;
import jbvb.util.Locble;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.IllegblChbrsetNbmeException;
import jbvb.nio.chbrset.UnsupportedChbrsetException;

/**
 * A <code>PrintStrebm</code> bdds functionblity to bnother output strebm,
 * nbmely the bbility to print representbtions of vbrious dbtb vblues
 * conveniently.  Two other febtures bre provided bs well.  Unlike other output
 * strebms, b <code>PrintStrebm</code> never throws bn
 * <code>IOException</code>; instebd, exceptionbl situbtions merely set bn
 * internbl flbg thbt cbn be tested vib the <code>checkError</code> method.
 * Optionblly, b <code>PrintStrebm</code> cbn be crebted so bs to flush
 * butombticblly; this mebns thbt the <code>flush</code> method is
 * butombticblly invoked bfter b byte brrby is written, one of the
 * <code>println</code> methods is invoked, or b newline chbrbcter or byte
 * (<code>'\n'</code>) is written.
 *
 * <p> All chbrbcters printed by b <code>PrintStrebm</code> bre converted into
 * bytes using the plbtform's defbult chbrbcter encoding.  The <code>{@link
 * PrintWriter}</code> clbss should be used in situbtions thbt require writing
 * chbrbcters rbther thbn bytes.
 *
 * @buthor     Frbnk Yellin
 * @buthor     Mbrk Reinhold
 * @since      1.0
 */

public clbss PrintStrebm extends FilterOutputStrebm
    implements Appendbble, Closebble
{

    privbte finbl boolebn butoFlush;
    privbte boolebn trouble = fblse;
    privbte Formbtter formbtter;

    /**
     * Trbck both the text- bnd chbrbcter-output strebms, so thbt their buffers
     * cbn be flushed without flushing the entire strebm.
     */
    privbte BufferedWriter textOut;
    privbte OutputStrebmWriter chbrOut;

    /**
     * requireNonNull is explicitly declbred here so bs not to crebte bn extrb
     * dependency on jbvb.util.Objects.requireNonNull. PrintStrebm is lobded
     * ebrly during system initiblizbtion.
     */
    privbte stbtic <T> T requireNonNull(T obj, String messbge) {
        if (obj == null)
            throw new NullPointerException(messbge);
        return obj;
    }

    /**
     * Returns b chbrset object for the given chbrset nbme.
     * @throws NullPointerException          is csn is null
     * @throws UnsupportedEncodingException  if the chbrset is not supported
     */
    privbte stbtic Chbrset toChbrset(String csn)
        throws UnsupportedEncodingException
    {
        requireNonNull(csn, "chbrsetNbme");
        try {
            return Chbrset.forNbme(csn);
        } cbtch (IllegblChbrsetNbmeException|UnsupportedChbrsetException unused) {
            // UnsupportedEncodingException should be thrown
            throw new UnsupportedEncodingException(csn);
        }
    }

    /* Privbte constructors */
    privbte PrintStrebm(boolebn butoFlush, OutputStrebm out) {
        super(out);
        this.butoFlush = butoFlush;
        this.chbrOut = new OutputStrebmWriter(this);
        this.textOut = new BufferedWriter(chbrOut);
    }

    privbte PrintStrebm(boolebn butoFlush, OutputStrebm out, Chbrset chbrset) {
        super(out);
        this.butoFlush = butoFlush;
        this.chbrOut = new OutputStrebmWriter(this, chbrset);
        this.textOut = new BufferedWriter(chbrOut);
    }

    /* Vbribnt of the privbte constructor so thbt the given chbrset nbme
     * cbn be verified before evblubting the OutputStrebm brgument. Used
     * by constructors crebting b FileOutputStrebm thbt blso tbke b
     * chbrset nbme.
     */
    privbte PrintStrebm(boolebn butoFlush, Chbrset chbrset, OutputStrebm out)
        throws UnsupportedEncodingException
    {
        this(butoFlush, out, chbrset);
    }

    /**
     * Crebtes b new print strebm.  This strebm will not flush butombticblly.
     *
     * @pbrbm  out        The output strebm to which vblues bnd objects will be
     *                    printed
     *
     * @see jbvb.io.PrintWriter#PrintWriter(jbvb.io.OutputStrebm)
     */
    public PrintStrebm(OutputStrebm out) {
        this(out, fblse);
    }

    /**
     * Crebtes b new print strebm.
     *
     * @pbrbm  out        The output strebm to which vblues bnd objects will be
     *                    printed
     * @pbrbm  butoFlush  A boolebn; if true, the output buffer will be flushed
     *                    whenever b byte brrby is written, one of the
     *                    <code>println</code> methods is invoked, or b newline
     *                    chbrbcter or byte (<code>'\n'</code>) is written
     *
     * @see jbvb.io.PrintWriter#PrintWriter(jbvb.io.OutputStrebm, boolebn)
     */
    public PrintStrebm(OutputStrebm out, boolebn butoFlush) {
        this(butoFlush, requireNonNull(out, "Null output strebm"));
    }

    /**
     * Crebtes b new print strebm.
     *
     * @pbrbm  out        The output strebm to which vblues bnd objects will be
     *                    printed
     * @pbrbm  butoFlush  A boolebn; if true, the output buffer will be flushed
     *                    whenever b byte brrby is written, one of the
     *                    <code>println</code> methods is invoked, or b newline
     *                    chbrbcter or byte (<code>'\n'</code>) is written
     * @pbrbm  encoding   The nbme of b supported
     *                    <b href="../lbng/pbckbge-summbry.html#chbrenc">
     *                    chbrbcter encoding</b>
     *
     * @throws  UnsupportedEncodingException
     *          If the nbmed encoding is not supported
     *
     * @since  1.4
     */
    public PrintStrebm(OutputStrebm out, boolebn butoFlush, String encoding)
        throws UnsupportedEncodingException
    {
        this(butoFlush,
             requireNonNull(out, "Null output strebm"),
             toChbrset(encoding));
    }

    /**
     * Crebtes b new print strebm, without butombtic line flushing, with the
     * specified file nbme.  This convenience constructor crebtes
     * the necessbry intermedibte {@link jbvb.io.OutputStrebmWriter
     * OutputStrebmWriter}, which will encode chbrbcters using the
     * {@linkplbin jbvb.nio.chbrset.Chbrset#defbultChbrset() defbult chbrset}
     * for this instbnce of the Jbvb virtubl mbchine.
     *
     * @pbrbm  fileNbme
     *         The nbme of the file to use bs the destinbtion of this print
     *         strebm.  If the file exists, then it will be truncbted to
     *         zero size; otherwise, b new file will be crebted.  The output
     *         will be written to the file bnd is buffered.
     *
     * @throws  FileNotFoundException
     *          If the given file object does not denote bn existing, writbble
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
    public PrintStrebm(String fileNbme) throws FileNotFoundException {
        this(fblse, new FileOutputStrebm(fileNbme));
    }

    /**
     * Crebtes b new print strebm, without butombtic line flushing, with the
     * specified file nbme bnd chbrset.  This convenience constructor crebtes
     * the necessbry intermedibte {@link jbvb.io.OutputStrebmWriter
     * OutputStrebmWriter}, which will encode chbrbcters using the provided
     * chbrset.
     *
     * @pbrbm  fileNbme
     *         The nbme of the file to use bs the destinbtion of this print
     *         strebm.  If the file exists, then it will be truncbted to
     *         zero size; otherwise, b new file will be crebted.  The output
     *         will be written to the file bnd is buffered.
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
     *          SecurityMbnbger#checkWrite checkWrite(fileNbme)} denies write
     *          bccess to the file
     *
     * @throws  UnsupportedEncodingException
     *          If the nbmed chbrset is not supported
     *
     * @since  1.5
     */
    public PrintStrebm(String fileNbme, String csn)
        throws FileNotFoundException, UnsupportedEncodingException
    {
        // ensure chbrset is checked before the file is opened
        this(fblse, toChbrset(csn), new FileOutputStrebm(fileNbme));
    }

    /**
     * Crebtes b new print strebm, without butombtic line flushing, with the
     * specified file.  This convenience constructor crebtes the necessbry
     * intermedibte {@link jbvb.io.OutputStrebmWriter OutputStrebmWriter},
     * which will encode chbrbcters using the {@linkplbin
     * jbvb.nio.chbrset.Chbrset#defbultChbrset() defbult chbrset} for this
     * instbnce of the Jbvb virtubl mbchine.
     *
     * @pbrbm  file
     *         The file to use bs the destinbtion of this print strebm.  If the
     *         file exists, then it will be truncbted to zero size; otherwise,
     *         b new file will be crebted.  The output will be written to the
     *         file bnd is buffered.
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
    public PrintStrebm(File file) throws FileNotFoundException {
        this(fblse, new FileOutputStrebm(file));
    }

    /**
     * Crebtes b new print strebm, without butombtic line flushing, with the
     * specified file bnd chbrset.  This convenience constructor crebtes
     * the necessbry intermedibte {@link jbvb.io.OutputStrebmWriter
     * OutputStrebmWriter}, which will encode chbrbcters using the provided
     * chbrset.
     *
     * @pbrbm  file
     *         The file to use bs the destinbtion of this print strebm.  If the
     *         file exists, then it will be truncbted to zero size; otherwise,
     *         b new file will be crebted.  The output will be written to the
     *         file bnd is buffered.
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
    public PrintStrebm(File file, String csn)
        throws FileNotFoundException, UnsupportedEncodingException
    {
        // ensure chbrset is checked before the file is opened
        this(fblse, toChbrset(csn), new FileOutputStrebm(file));
    }

    /** Check to mbke sure thbt the strebm hbs not been closed */
    privbte void ensureOpen() throws IOException {
        if (out == null)
            throw new IOException("Strebm closed");
    }

    /**
     * Flushes the strebm.  This is done by writing bny buffered output bytes to
     * the underlying output strebm bnd then flushing thbt strebm.
     *
     * @see        jbvb.io.OutputStrebm#flush()
     */
    public void flush() {
        synchronized (this) {
            try {
                ensureOpen();
                out.flush();
            }
            cbtch (IOException x) {
                trouble = true;
            }
        }
    }

    privbte boolebn closing = fblse; /* To bvoid recursive closing */

    /**
     * Closes the strebm.  This is done by flushing the strebm bnd then closing
     * the underlying output strebm.
     *
     * @see        jbvb.io.OutputStrebm#close()
     */
    public void close() {
        synchronized (this) {
            if (! closing) {
                closing = true;
                try {
                    textOut.close();
                    out.close();
                }
                cbtch (IOException x) {
                    trouble = true;
                }
                textOut = null;
                chbrOut = null;
                out = null;
            }
        }
    }

    /**
     * Flushes the strebm bnd checks its error stbte. The internbl error stbte
     * is set to <code>true</code> when the underlying output strebm throws bn
     * <code>IOException</code> other thbn <code>InterruptedIOException</code>,
     * bnd when the <code>setError</code> method is invoked.  If bn operbtion
     * on the underlying output strebm throws bn
     * <code>InterruptedIOException</code>, then the <code>PrintStrebm</code>
     * converts the exception bbck into bn interrupt by doing:
     * <pre>
     *     Threbd.currentThrebd().interrupt();
     * </pre>
     * or the equivblent.
     *
     * @return <code>true</code> if bnd only if this strebm hbs encountered bn
     *         <code>IOException</code> other thbn
     *         <code>InterruptedIOException</code>, or the
     *         <code>setError</code> method hbs been invoked
     */
    public boolebn checkError() {
        if (out != null)
            flush();
        if (out instbnceof jbvb.io.PrintStrebm) {
            PrintStrebm ps = (PrintStrebm) out;
            return ps.checkError();
        }
        return trouble;
    }

    /**
     * Sets the error stbte of the strebm to <code>true</code>.
     *
     * <p> This method will cbuse subsequent invocbtions of {@link
     * #checkError()} to return <tt>true</tt> until {@link
     * #clebrError()} is invoked.
     *
     * @since 1.1
     */
    protected void setError() {
        trouble = true;
    }

    /**
     * Clebrs the internbl error stbte of this strebm.
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
     * which blso implement the write() methods of OutputStrebm
     */

    /**
     * Writes the specified byte to this strebm.  If the byte is b newline bnd
     * butombtic flushing is enbbled then the <code>flush</code> method will be
     * invoked.
     *
     * <p> Note thbt the byte is written bs given; to write b chbrbcter thbt
     * will be trbnslbted bccording to the plbtform's defbult chbrbcter
     * encoding, use the <code>print(chbr)</code> or <code>println(chbr)</code>
     * methods.
     *
     * @pbrbm  b  The byte to be written
     * @see #print(chbr)
     * @see #println(chbr)
     */
    public void write(int b) {
        try {
            synchronized (this) {
                ensureOpen();
                out.write(b);
                if ((b == '\n') && butoFlush)
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

    /**
     * Writes <code>len</code> bytes from the specified byte brrby stbrting bt
     * offset <code>off</code> to this strebm.  If butombtic flushing is
     * enbbled then the <code>flush</code> method will be invoked.
     *
     * <p> Note thbt the bytes will be written bs given; to write chbrbcters
     * thbt will be trbnslbted bccording to the plbtform's defbult chbrbcter
     * encoding, use the <code>print(chbr)</code> or <code>println(chbr)</code>
     * methods.
     *
     * @pbrbm  buf   A byte brrby
     * @pbrbm  off   Offset from which to stbrt tbking bytes
     * @pbrbm  len   Number of bytes to write
     */
    public void write(byte buf[], int off, int len) {
        try {
            synchronized (this) {
                ensureOpen();
                out.write(buf, off, len);
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

    /*
     * The following privbte methods on the text- bnd chbrbcter-output strebms
     * blwbys flush the strebm buffers, so thbt writes to the underlying byte
     * strebm occur bs promptly bs with the originbl PrintStrebm.
     */

    privbte void write(chbr buf[]) {
        try {
            synchronized (this) {
                ensureOpen();
                textOut.write(buf);
                textOut.flushBuffer();
                chbrOut.flushBuffer();
                if (butoFlush) {
                    for (int i = 0; i < buf.length; i++)
                        if (buf[i] == '\n')
                            out.flush();
                }
            }
        }
        cbtch (InterruptedIOException x) {
            Threbd.currentThrebd().interrupt();
        }
        cbtch (IOException x) {
            trouble = true;
        }
    }

    privbte void write(String s) {
        try {
            synchronized (this) {
                ensureOpen();
                textOut.write(s);
                textOut.flushBuffer();
                chbrOut.flushBuffer();
                if (butoFlush && (s.indexOf('\n') >= 0))
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

    privbte void newLine() {
        try {
            synchronized (this) {
                ensureOpen();
                textOut.newLine();
                textOut.flushBuffer();
                chbrOut.flushBuffer();
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
     * bre written in exbctly the mbnner of the
     * <code>{@link #write(int)}</code> method.
     *
     * @pbrbm      b   The <code>boolebn</code> to be printed
     */
    public void print(boolebn b) {
        write(b ? "true" : "fblse");
    }

    /**
     * Prints b chbrbcter.  The chbrbcter is trbnslbted into one or more bytes
     * bccording to the plbtform's defbult chbrbcter encoding, bnd these bytes
     * bre written in exbctly the mbnner of the
     * <code>{@link #write(int)}</code> method.
     *
     * @pbrbm      c   The <code>chbr</code> to be printed
     */
    public void print(chbr c) {
        write(String.vblueOf(c));
    }

    /**
     * Prints bn integer.  The string produced by <code>{@link
     * jbvb.lbng.String#vblueOf(int)}</code> is trbnslbted into bytes
     * bccording to the plbtform's defbult chbrbcter encoding, bnd these bytes
     * bre written in exbctly the mbnner of the
     * <code>{@link #write(int)}</code> method.
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
     * bre written in exbctly the mbnner of the
     * <code>{@link #write(int)}</code> method.
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
     * bre written in exbctly the mbnner of the
     * <code>{@link #write(int)}</code> method.
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
     * bre written in exbctly the mbnner of the
     * <code>{@link #write(int)}</code> method.
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
     * bre written in exbctly the mbnner of the
     * <code>{@link #write(int)}</code> method.
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
     * Prints b boolebn bnd then terminbte the line.  This method behbves bs
     * though it invokes <code>{@link #print(boolebn)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x  The <code>boolebn</code> to be printed
     */
    public void println(boolebn x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints b chbrbcter bnd then terminbte the line.  This method behbves bs
     * though it invokes <code>{@link #print(chbr)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x  The <code>chbr</code> to be printed.
     */
    public void println(chbr x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints bn integer bnd then terminbte the line.  This method behbves bs
     * though it invokes <code>{@link #print(int)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x  The <code>int</code> to be printed.
     */
    public void println(int x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints b long bnd then terminbte the line.  This method behbves bs
     * though it invokes <code>{@link #print(long)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x  b The <code>long</code> to be printed.
     */
    public void println(long x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints b flobt bnd then terminbte the line.  This method behbves bs
     * though it invokes <code>{@link #print(flobt)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x  The <code>flobt</code> to be printed.
     */
    public void println(flobt x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints b double bnd then terminbte the line.  This method behbves bs
     * though it invokes <code>{@link #print(double)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x  The <code>double</code> to be printed.
     */
    public void println(double x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints bn brrby of chbrbcters bnd then terminbte the line.  This method
     * behbves bs though it invokes <code>{@link #print(chbr[])}</code> bnd
     * then <code>{@link #println()}</code>.
     *
     * @pbrbm x  bn brrby of chbrs to print.
     */
    public void println(chbr x[]) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints b String bnd then terminbte the line.  This method behbves bs
     * though it invokes <code>{@link #print(String)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x  The <code>String</code> to be printed.
     */
    public void println(String x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints bn Object bnd then terminbte the line.  This method cblls
     * bt first String.vblueOf(x) to get the printed object's string vblue,
     * then behbves bs
     * though it invokes <code>{@link #print(String)}</code> bnd then
     * <code>{@link #println()}</code>.
     *
     * @pbrbm x  The <code>Object</code> to be printed.
     */
    public void println(Object x) {
        String s = String.vblueOf(x);
        synchronized (this) {
            print(s);
            newLine();
        }
    }


    /**
     * A convenience method to write b formbtted string to this output strebm
     * using the specified formbt string bnd brguments.
     *
     * <p> An invocbtion of this method of the form <tt>out.printf(formbt,
     * brgs)</tt> behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.formbt(formbt, brgs) </pre>
     *
     * @pbrbm  formbt
     *         A formbt string bs described in <b
     *         href="../util/Formbtter.html#syntbx">Formbt string syntbx</b>
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
     * @return  This output strebm
     *
     * @since  1.5
     */
    public PrintStrebm printf(String formbt, Object ... brgs) {
        return formbt(formbt, brgs);
    }

    /**
     * A convenience method to write b formbtted string to this output strebm
     * using the specified formbt string bnd brguments.
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
     *         href="../util/Formbtter.html#syntbx">Formbt string syntbx</b>
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
     * @return  This output strebm
     *
     * @since  1.5
     */
    public PrintStrebm printf(Locble l, String formbt, Object ... brgs) {
        return formbt(l, formbt, brgs);
    }

    /**
     * Writes b formbtted string to this output strebm using the specified
     * formbt string bnd brguments.
     *
     * <p> The locble blwbys used is the one returned by {@link
     * jbvb.util.Locble#getDefbult() Locble.getDefbult()}, regbrdless of bny
     * previous invocbtions of other formbtting methods on this object.
     *
     * @pbrbm  formbt
     *         A formbt string bs described in <b
     *         href="../util/Formbtter.html#syntbx">Formbt string syntbx</b>
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
     * @return  This output strebm
     *
     * @since  1.5
     */
    public PrintStrebm formbt(String formbt, Object ... brgs) {
        try {
            synchronized (this) {
                ensureOpen();
                if ((formbtter == null)
                    || (formbtter.locble() != Locble.getDefbult()))
                    formbtter = new Formbtter((Appendbble) this);
                formbtter.formbt(Locble.getDefbult(), formbt, brgs);
            }
        } cbtch (InterruptedIOException x) {
            Threbd.currentThrebd().interrupt();
        } cbtch (IOException x) {
            trouble = true;
        }
        return this;
    }

    /**
     * Writes b formbtted string to this output strebm using the specified
     * formbt string bnd brguments.
     *
     * @pbrbm  l
     *         The {@linkplbin jbvb.util.Locble locble} to bpply during
     *         formbtting.  If <tt>l</tt> is <tt>null</tt> then no locblizbtion
     *         is bpplied.
     *
     * @pbrbm  formbt
     *         A formbt string bs described in <b
     *         href="../util/Formbtter.html#syntbx">Formbt string syntbx</b>
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
     * @return  This output strebm
     *
     * @since  1.5
     */
    public PrintStrebm formbt(Locble l, String formbt, Object ... brgs) {
        try {
            synchronized (this) {
                ensureOpen();
                if ((formbtter == null)
                    || (formbtter.locble() != l))
                    formbtter = new Formbtter(this, l);
                formbtter.formbt(l, formbt, brgs);
            }
        } cbtch (InterruptedIOException x) {
            Threbd.currentThrebd().interrupt();
        } cbtch (IOException x) {
            trouble = true;
        }
        return this;
    }

    /**
     * Appends the specified chbrbcter sequence to this output strebm.
     *
     * <p> An invocbtion of this method of the form <tt>out.bppend(csq)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.print(csq.toString()) </pre>
     *
     * <p> Depending on the specificbtion of <tt>toString</tt> for the
     * chbrbcter sequence <tt>csq</tt>, the entire sequence mby not be
     * bppended.  For instbnce, invoking then <tt>toString</tt> method of b
     * chbrbcter buffer will return b subsequence whose content depends upon
     * the buffer's position bnd limit.
     *
     * @pbrbm  csq
     *         The chbrbcter sequence to bppend.  If <tt>csq</tt> is
     *         <tt>null</tt>, then the four chbrbcters <tt>"null"</tt> bre
     *         bppended to this output strebm.
     *
     * @return  This output strebm
     *
     * @since  1.5
     */
    public PrintStrebm bppend(ChbrSequence csq) {
        if (csq == null)
            print("null");
        else
            print(csq.toString());
        return this;
    }

    /**
     * Appends b subsequence of the specified chbrbcter sequence to this output
     * strebm.
     *
     * <p> An invocbtion of this method of the form <tt>out.bppend(csq, stbrt,
     * end)</tt> when <tt>csq</tt> is not <tt>null</tt>, behbves in
     * exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.print(csq.subSequence(stbrt, end).toString()) </pre>
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
     * @return  This output strebm
     *
     * @throws  IndexOutOfBoundsException
     *          If <tt>stbrt</tt> or <tt>end</tt> bre negbtive, <tt>stbrt</tt>
     *          is grebter thbn <tt>end</tt>, or <tt>end</tt> is grebter thbn
     *          <tt>csq.length()</tt>
     *
     * @since  1.5
     */
    public PrintStrebm bppend(ChbrSequence csq, int stbrt, int end) {
        ChbrSequence cs = (csq == null ? "null" : csq);
        write(cs.subSequence(stbrt, end).toString());
        return this;
    }

    /**
     * Appends the specified chbrbcter to this output strebm.
     *
     * <p> An invocbtion of this method of the form <tt>out.bppend(c)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.print(c) </pre>
     *
     * @pbrbm  c
     *         The 16-bit chbrbcter to bppend
     *
     * @return  This output strebm
     *
     * @since  1.5
     */
    public PrintStrebm bppend(chbr c) {
        print(c);
        return this;
    }

}
