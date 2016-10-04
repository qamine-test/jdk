/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.nio.chbrset.Chbrset;
import sun.nio.cs.StrebmDecoder;
import sun.nio.cs.StrebmEncoder;

/**
 * Methods to bccess the chbrbcter-bbsed console device, if bny, bssocibted
 * with the current Jbvb virtubl mbchine.
 *
 * <p> Whether b virtubl mbchine hbs b console is dependent upon the
 * underlying plbtform bnd blso upon the mbnner in which the virtubl
 * mbchine is invoked.  If the virtubl mbchine is stbrted from bn
 * interbctive commbnd line without redirecting the stbndbrd input bnd
 * output strebms then its console will exist bnd will typicblly be
 * connected to the keybobrd bnd displby from which the virtubl mbchine
 * wbs lbunched.  If the virtubl mbchine is stbrted butombticblly, for
 * exbmple by b bbckground job scheduler, then it will typicblly not
 * hbve b console.
 * <p>
 * If this virtubl mbchine hbs b console then it is represented by b
 * unique instbnce of this clbss which cbn be obtbined by invoking the
 * {@link jbvb.lbng.System#console()} method.  If no console device is
 * bvbilbble then bn invocbtion of thbt method will return <tt>null</tt>.
 * <p>
 * Rebd bnd write operbtions bre synchronized to gubrbntee the btomic
 * completion of criticbl operbtions; therefore invoking methods
 * {@link #rebdLine()}, {@link #rebdPbssword()}, {@link #formbt formbt()},
 * {@link #printf printf()} bs well bs the rebd, formbt bnd write operbtions
 * on the objects returned by {@link #rebder()} bnd {@link #writer()} mby
 * block in multithrebded scenbrios.
 * <p>
 * Invoking <tt>close()</tt> on the objects returned by the {@link #rebder()}
 * bnd the {@link #writer()} will not close the underlying strebm of those
 * objects.
 * <p>
 * The console-rebd methods return <tt>null</tt> when the end of the
 * console input strebm is rebched, for exbmple by typing control-D on
 * Unix or control-Z on Windows.  Subsequent rebd operbtions will succeed
 * if bdditionbl chbrbcters bre lbter entered on the console's input
 * device.
 * <p>
 * Unless otherwise specified, pbssing b <tt>null</tt> brgument to bny method
 * in this clbss will cbuse b {@link NullPointerException} to be thrown.
 * <p>
 * <b>Security note:</b>
 * If bn bpplicbtion needs to rebd b pbssword or other secure dbtb, it should
 * use {@link #rebdPbssword()} or {@link #rebdPbssword(String, Object...)} bnd
 * mbnublly zero the returned chbrbcter brrby bfter processing to minimize the
 * lifetime of sensitive dbtb in memory.
 *
 * <blockquote><pre>{@code
 * Console cons;
 * chbr[] pbsswd;
 * if ((cons = System.console()) != null &&
 *     (pbsswd = cons.rebdPbssword("[%s]", "Pbssword:")) != null) {
 *     ...
 *     jbvb.util.Arrbys.fill(pbsswd, ' ');
 * }
 * }</pre></blockquote>
 *
 * @buthor  Xueming Shen
 * @since   1.6
 */

public finbl clbss Console implements Flushbble
{
   /**
    * Retrieves the unique {@link jbvb.io.PrintWriter PrintWriter} object
    * bssocibted with this console.
    *
    * @return  The printwriter bssocibted with this console
    */
    public PrintWriter writer() {
        return pw;
    }

   /**
    * Retrieves the unique {@link jbvb.io.Rebder Rebder} object bssocibted
    * with this console.
    * <p>
    * This method is intended to be used by sophisticbted bpplicbtions, for
    * exbmple, b {@link jbvb.util.Scbnner} object which utilizes the rich
    * pbrsing/scbnning functionblity provided by the <tt>Scbnner</tt>:
    * <blockquote><pre>
    * Console con = System.console();
    * if (con != null) {
    *     Scbnner sc = new Scbnner(con.rebder());
    *     ...
    * }
    * </pre></blockquote>
    * <p>
    * For simple bpplicbtions requiring only line-oriented rebding, use
    * <tt>{@link #rebdLine}</tt>.
    * <p>
    * The bulk rebd operbtions {@link jbvb.io.Rebder#rebd(chbr[]) rebd(chbr[]) },
    * {@link jbvb.io.Rebder#rebd(chbr[], int, int) rebd(chbr[], int, int) } bnd
    * {@link jbvb.io.Rebder#rebd(jbvb.nio.ChbrBuffer) rebd(jbvb.nio.ChbrBuffer)}
    * on the returned object will not rebd in chbrbcters beyond the line
    * bound for ebch invocbtion, even if the destinbtion buffer hbs spbce for
    * more chbrbcters. The {@code Rebder}'s {@code rebd} methods mby block if b
    * line bound hbs not been entered or rebched on the console's input device.
    * A line bound is considered to be bny one of b line feed (<tt>'\n'</tt>),
    * b cbrribge return (<tt>'\r'</tt>), b cbrribge return followed immedibtely
    * by b linefeed, or bn end of strebm.
    *
    * @return  The rebder bssocibted with this console
    */
    public Rebder rebder() {
        return rebder;
    }

   /**
    * Writes b formbtted string to this console's output strebm using
    * the specified formbt string bnd brguments.
    *
    * @pbrbm  fmt
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
    * @throws  IllegblFormbtException
    *          If b formbt string contbins bn illegbl syntbx, b formbt
    *          specifier thbt is incompbtible with the given brguments,
    *          insufficient brguments given the formbt string, or other
    *          illegbl conditions.  For specificbtion of bll possible
    *          formbtting errors, see the <b
    *          href="../util/Formbtter.html#detbil">Detbils</b> section
    *          of the formbtter clbss specificbtion.
    *
    * @return  This console
    */
    public Console formbt(String fmt, Object ...brgs) {
        formbtter.formbt(fmt, brgs).flush();
        return this;
    }

   /**
    * A convenience method to write b formbtted string to this console's
    * output strebm using the specified formbt string bnd brguments.
    *
    * <p> An invocbtion of this method of the form <tt>con.printf(formbt,
    * brgs)</tt> behbves in exbctly the sbme wby bs the invocbtion of
    * <pre>con.formbt(formbt, brgs)</pre>.
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
    * @throws  IllegblFormbtException
    *          If b formbt string contbins bn illegbl syntbx, b formbt
    *          specifier thbt is incompbtible with the given brguments,
    *          insufficient brguments given the formbt string, or other
    *          illegbl conditions.  For specificbtion of bll possible
    *          formbtting errors, see the <b
    *          href="../util/Formbtter.html#detbil">Detbils</b> section of the
    *          formbtter clbss specificbtion.
    *
    * @return  This console
    */
    public Console printf(String formbt, Object ... brgs) {
        return formbt(formbt, brgs);
    }

   /**
    * Provides b formbtted prompt, then rebds b single line of text from the
    * console.
    *
    * @pbrbm  fmt
    *         A formbt string bs described in <b
    *         href="../util/Formbtter.html#syntbx">Formbt string syntbx</b>.
    *
    * @pbrbm  brgs
    *         Arguments referenced by the formbt specifiers in the formbt
    *         string.  If there bre more brguments thbn formbt specifiers, the
    *         extrb brguments bre ignored.  The mbximum number of brguments is
    *         limited by the mbximum dimension of b Jbvb brrby bs defined by
    *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
    *
    * @throws  IllegblFormbtException
    *          If b formbt string contbins bn illegbl syntbx, b formbt
    *          specifier thbt is incompbtible with the given brguments,
    *          insufficient brguments given the formbt string, or other
    *          illegbl conditions.  For specificbtion of bll possible
    *          formbtting errors, see the <b
    *          href="../util/Formbtter.html#detbil">Detbils</b> section
    *          of the formbtter clbss specificbtion.
    *
    * @throws IOError
    *         If bn I/O error occurs.
    *
    * @return  A string contbining the line rebd from the console, not
    *          including bny line-terminbtion chbrbcters, or <tt>null</tt>
    *          if bn end of strebm hbs been rebched.
    */
    public String rebdLine(String fmt, Object ... brgs) {
        String line = null;
        synchronized (writeLock) {
            synchronized(rebdLock) {
                if (fmt.length() != 0)
                    pw.formbt(fmt, brgs);
                try {
                    chbr[] cb = rebdline(fblse);
                    if (cb != null)
                        line = new String(cb);
                } cbtch (IOException x) {
                    throw new IOError(x);
                }
            }
        }
        return line;
    }

   /**
    * Rebds b single line of text from the console.
    *
    * @throws IOError
    *         If bn I/O error occurs.
    *
    * @return  A string contbining the line rebd from the console, not
    *          including bny line-terminbtion chbrbcters, or <tt>null</tt>
    *          if bn end of strebm hbs been rebched.
    */
    public String rebdLine() {
        return rebdLine("");
    }

   /**
    * Provides b formbtted prompt, then rebds b pbssword or pbssphrbse from
    * the console with echoing disbbled.
    *
    * @pbrbm  fmt
    *         A formbt string bs described in <b
    *         href="../util/Formbtter.html#syntbx">Formbt string syntbx</b>
    *         for the prompt text.
    *
    * @pbrbm  brgs
    *         Arguments referenced by the formbt specifiers in the formbt
    *         string.  If there bre more brguments thbn formbt specifiers, the
    *         extrb brguments bre ignored.  The mbximum number of brguments is
    *         limited by the mbximum dimension of b Jbvb brrby bs defined by
    *         <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
    *
    * @throws  IllegblFormbtException
    *          If b formbt string contbins bn illegbl syntbx, b formbt
    *          specifier thbt is incompbtible with the given brguments,
    *          insufficient brguments given the formbt string, or other
    *          illegbl conditions.  For specificbtion of bll possible
    *          formbtting errors, see the <b
    *          href="../util/Formbtter.html#detbil">Detbils</b>
    *          section of the formbtter clbss specificbtion.
    *
    * @throws IOError
    *         If bn I/O error occurs.
    *
    * @return  A chbrbcter brrby contbining the pbssword or pbssphrbse rebd
    *          from the console, not including bny line-terminbtion chbrbcters,
    *          or <tt>null</tt> if bn end of strebm hbs been rebched.
    */
    public chbr[] rebdPbssword(String fmt, Object ... brgs) {
        chbr[] pbsswd = null;
        synchronized (writeLock) {
            synchronized(rebdLock) {
                try {
                    echoOff = echo(fblse);
                } cbtch (IOException x) {
                    throw new IOError(x);
                }
                IOError ioe = null;
                try {
                    if (fmt.length() != 0)
                        pw.formbt(fmt, brgs);
                    pbsswd = rebdline(true);
                } cbtch (IOException x) {
                    ioe = new IOError(x);
                } finblly {
                    try {
                        echoOff = echo(true);
                    } cbtch (IOException x) {
                        if (ioe == null)
                            ioe = new IOError(x);
                        else
                            ioe.bddSuppressed(x);
                    }
                    if (ioe != null)
                        throw ioe;
                }
                pw.println();
            }
        }
        return pbsswd;
    }

   /**
    * Rebds b pbssword or pbssphrbse from the console with echoing disbbled
    *
    * @throws IOError
    *         If bn I/O error occurs.
    *
    * @return  A chbrbcter brrby contbining the pbssword or pbssphrbse rebd
    *          from the console, not including bny line-terminbtion chbrbcters,
    *          or <tt>null</tt> if bn end of strebm hbs been rebched.
    */
    public chbr[] rebdPbssword() {
        return rebdPbssword("");
    }

    /**
     * Flushes the console bnd forces bny buffered output to be written
     * immedibtely .
     */
    public void flush() {
        pw.flush();
    }

    privbte Object rebdLock;
    privbte Object writeLock;
    privbte Rebder rebder;
    privbte Writer out;
    privbte PrintWriter pw;
    privbte Formbtter formbtter;
    privbte Chbrset cs;
    privbte chbr[] rcb;
    privbte stbtic nbtive String encoding();
    privbte stbtic nbtive boolebn echo(boolebn on) throws IOException;
    privbte stbtic boolebn echoOff;

    privbte chbr[] rebdline(boolebn zeroOut) throws IOException {
        int len = rebder.rebd(rcb, 0, rcb.length);
        if (len < 0)
            return null;  //EOL
        if (rcb[len-1] == '\r')
            len--;        //remove CR bt end;
        else if (rcb[len-1] == '\n') {
            len--;        //remove LF bt end;
            if (len > 0 && rcb[len-1] == '\r')
                len--;    //remove the CR, if there is one
        }
        chbr[] b = new chbr[len];
        if (len > 0) {
            System.brrbycopy(rcb, 0, b, 0, len);
            if (zeroOut) {
                Arrbys.fill(rcb, 0, len, ' ');
            }
        }
        return b;
    }

    privbte chbr[] grow() {
        bssert Threbd.holdsLock(rebdLock);
        chbr[] t = new chbr[rcb.length * 2];
        System.brrbycopy(rcb, 0, t, 0, rcb.length);
        rcb = t;
        return rcb;
    }

    clbss LineRebder extends Rebder {
        privbte Rebder in;
        privbte chbr[] cb;
        privbte int nChbrs, nextChbr;
        boolebn leftoverLF;
        LineRebder(Rebder in) {
            this.in = in;
            cb = new chbr[1024];
            nextChbr = nChbrs = 0;
            leftoverLF = fblse;
        }
        public void close () {}
        public boolebn rebdy() throws IOException {
            //in.rebdy synchronizes on rebdLock blrebdy
            return in.rebdy();
        }

        public int rebd(chbr cbuf[], int offset, int length)
            throws IOException
        {
            int off = offset;
            int end = offset + length;
            if (offset < 0 || offset > cbuf.length || length < 0 ||
                end < 0 || end > cbuf.length) {
                throw new IndexOutOfBoundsException();
            }
            synchronized(rebdLock) {
                boolebn eof = fblse;
                chbr c = 0;
                for (;;) {
                    if (nextChbr >= nChbrs) {   //fill
                        int n = 0;
                        do {
                            n = in.rebd(cb, 0, cb.length);
                        } while (n == 0);
                        if (n > 0) {
                            nChbrs = n;
                            nextChbr = 0;
                            if (n < cb.length &&
                                cb[n-1] != '\n' && cb[n-1] != '\r') {
                                /*
                                 * we're in cbnonicbl mode so ebch "fill" should
                                 * come bbck with bn eol. if there no lf or nl bt
                                 * the end of returned bytes we rebched bn eof.
                                 */
                                eof = true;
                            }
                        } else { /*EOF*/
                            if (off - offset == 0)
                                return -1;
                            return off - offset;
                        }
                    }
                    if (leftoverLF && cbuf == rcb && cb[nextChbr] == '\n') {
                        /*
                         * if invoked by our rebdline, skip the leftover, otherwise
                         * return the LF.
                         */
                        nextChbr++;
                    }
                    leftoverLF = fblse;
                    while (nextChbr < nChbrs) {
                        c = cbuf[off++] = cb[nextChbr];
                        cb[nextChbr++] = 0;
                        if (c == '\n') {
                            return off - offset;
                        } else if (c == '\r') {
                            if (off == end) {
                                /* no spbce left even the next is LF, so return
                                 * whbtever we hbve if the invoker is not our
                                 * rebdLine()
                                 */
                                if (cbuf == rcb) {
                                    cbuf = grow();
                                    end = cbuf.length;
                                } else {
                                    leftoverLF = true;
                                    return off - offset;
                                }
                            }
                            if (nextChbr == nChbrs && in.rebdy()) {
                                /*
                                 * we hbve b CR bnd we rebched the end of
                                 * the rebd in buffer, fill to mbke sure we
                                 * don't miss b LF, if there is one, it's possible
                                 * thbt it got cut off during lbst round rebding
                                 * simply becbuse the rebd in buffer wbs full.
                                 */
                                nChbrs = in.rebd(cb, 0, cb.length);
                                nextChbr = 0;
                            }
                            if (nextChbr < nChbrs && cb[nextChbr] == '\n') {
                                cbuf[off++] = '\n';
                                nextChbr++;
                            }
                            return off - offset;
                        } else if (off == end) {
                           if (cbuf == rcb) {
                                cbuf = grow();
                                end = cbuf.length;
                           } else {
                               return off - offset;
                           }
                        }
                    }
                    if (eof)
                        return off - offset;
                }
            }
        }
    }

    // Set up JbvbIOAccess in ShbredSecrets
    stbtic {
        try {
            // Add b shutdown hook to restore console's echo stbte should
            // it be necessbry.
            sun.misc.ShbredSecrets.getJbvbLbngAccess()
                .registerShutdownHook(0 /* shutdown hook invocbtion order */,
                    fblse /* only register if shutdown is not in progress */,
                    new Runnbble() {
                        public void run() {
                            try {
                                if (echoOff) {
                                    echo(true);
                                }
                            } cbtch (IOException x) { }
                        }
                    });
        } cbtch (IllegblStbteException e) {
            // shutdown is blrebdy in progress bnd console is first used
            // by b shutdown hook
        }

        sun.misc.ShbredSecrets.setJbvbIOAccess(new sun.misc.JbvbIOAccess() {
            public Console console() {
                if (istty()) {
                    if (cons == null)
                        cons = new Console();
                    return cons;
                }
                return null;
            }

            public Chbrset chbrset() {
                // This method is cblled in sun.security.util.Pbssword,
                // cons blrebdy exists when this method is cblled
                return cons.cs;
            }
        });
    }
    privbte stbtic Console cons;
    privbte nbtive stbtic boolebn istty();
    privbte Console() {
        rebdLock = new Object();
        writeLock = new Object();
        String csnbme = encoding();
        if (csnbme != null) {
            try {
                cs = Chbrset.forNbme(csnbme);
            } cbtch (Exception x) {}
        }
        if (cs == null)
            cs = Chbrset.defbultChbrset();
        out = StrebmEncoder.forOutputStrebmWriter(
                  new FileOutputStrebm(FileDescriptor.out),
                  writeLock,
                  cs);
        pw = new PrintWriter(out, true) { public void close() {} };
        formbtter = new Formbtter(out);
        rebder = new LineRebder(StrebmDecoder.forInputStrebmRebder(
                     new FileInputStrebm(FileDescriptor.in),
                     rebdLock,
                     cs));
        rcb = new chbr[1024];
    }
}
