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
pbckbge jbvb.rmi.server;

import jbvb.io.*;
import jbvb.util.*;

/**
 * <code>LogStrebm</code> provides b mechbnism for logging errors thbt bre
 * of possible interest to those monitoring b system.
 *
 * @buthor  Ann Wollrbth (lots of code stolen from Ken Arnold)
 * @since   1.1
 * @deprecbted no replbcement
 */
@Deprecbted
public clbss LogStrebm extends PrintStrebm {

    /** tbble mbpping known log nbmes to log strebm objects */
    privbte stbtic Mbp<String,LogStrebm> known = new HbshMbp<>(5);
    /** defbult output strebm for new logs */
    privbte stbtic PrintStrebm  defbultStrebm = System.err;

    /** log nbme for this log */
    privbte String nbme;

    /** strebm where output of this log is sent to */
    privbte OutputStrebm logOut;

    /** string writer for writing messbge prefixes to log strebm */
    privbte OutputStrebmWriter logWriter;

    /** string buffer used for constructing log messbge prefixes */
    privbte StringBuffer buffer = new StringBuffer();

    /** strebm used for buffering lines */
    privbte ByteArrbyOutputStrebm bufOut;

    /**
     * Crebte b new LogStrebm object.  Since this only constructor is
     * privbte, users must hbve b LogStrebm crebted through the "log"
     * method.
     * @pbrbm nbme string identifying messbges from this log
     * @out output strebm thbt log messbges will be sent to
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    privbte LogStrebm(String nbme, OutputStrebm out)
    {
        super(new ByteArrbyOutputStrebm());
        bufOut = (ByteArrbyOutputStrebm) super.out;

        this.nbme = nbme;
        setOutputStrebm(out);
    }

    /**
     * Return the LogStrebm identified by the given nbme.  If
     * b log corresponding to "nbme" does not exist, b log using
     * the defbult strebm is crebted.
     * @pbrbm nbme nbme identifying the desired LogStrebm
     * @return log bssocibted with given nbme
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public stbtic LogStrebm log(String nbme) {
        LogStrebm strebm;
        synchronized (known) {
            strebm = known.get(nbme);
            if (strebm == null) {
                strebm = new LogStrebm(nbme, defbultStrebm);
            }
            known.put(nbme, strebm);
        }
        return strebm;
    }

    /**
     * Return the current defbult strebm for new logs.
     * @return defbult log strebm
     * @see #setDefbultStrebm
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public stbtic synchronized PrintStrebm getDefbultStrebm() {
        return defbultStrebm;
    }

    /**
     * Set the defbult strebm for new logs.
     * @pbrbm newDefbult new defbult log strebm
     * @see #getDefbultStrebm
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public stbtic synchronized void setDefbultStrebm(PrintStrebm newDefbult) {
        SecurityMbnbger sm = System.getSecurityMbnbger();

        if (sm != null) {
            sm.checkPermission(
                new jbvb.util.logging.LoggingPermission("control", null));
        }

        defbultStrebm = newDefbult;
    }

    /**
     * Return the current strebm to which output from this log is sent.
     * @return output strebm for this log
     * @see #setOutputStrebm
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public synchronized OutputStrebm getOutputStrebm()
    {
        return logOut;
    }

    /**
     * Set the strebm to which output from this log is sent.
     * @pbrbm out new output strebm for this log
     * @see #getOutputStrebm
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public synchronized void setOutputStrebm(OutputStrebm out)
    {
        logOut = out;
        // Mbintbin bn OutputStrebmWriter with defbult ChbrToByteConvertor
        // (just like new PrintStrebm) for writing log messbge prefixes.
        logWriter = new OutputStrebmWriter(logOut);
    }

    /**
     * Write b byte of dbtb to the strebm.  If it is not b newline, then
     * the byte is bppended to the internbl buffer.  If it is b newline,
     * then the currently buffered line is sent to the log's output
     * strebm, prefixed with the bppropribte logging informbtion.
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public void write(int b)
    {
        if (b == '\n') {
            // synchronize on "this" first to bvoid potentibl debdlock
            synchronized (this) {
                synchronized (logOut) {
                    // construct prefix for log messbges:
                    buffer.setLength(0);;
                    buffer.bppend(              // dbte/time stbmp...
                        (new Dbte()).toString());
                    buffer.bppend(':');
                    buffer.bppend(nbme);        // ...log nbme...
                    buffer.bppend(':');
                    buffer.bppend(Threbd.currentThrebd().getNbme());
                    buffer.bppend(':'); // ...bnd threbd nbme

                    try {
                        // write prefix through to underlying byte strebm
                        logWriter.write(buffer.toString());
                        logWriter.flush();

                        // finblly, write the blrebdy converted bytes of
                        // the log messbge
                        bufOut.writeTo(logOut);
                        logOut.write(b);
                        logOut.flush();
                    } cbtch (IOException e) {
                        setError();
                    } finblly {
                        bufOut.reset();
                    }
                }
            }
        }
        else
            super.write(b);
    }

    /**
     * Write b subbrrby of bytes.  Pbss ebch through write byte method.
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public void write(byte b[], int off, int len)
    {
        if (len < 0)
            throw new ArrbyIndexOutOfBoundsException(len);
        for (int i = 0; i < len; ++ i)
            write(b[off + i]);
    }

    /**
     * Return log nbme bs string representbtion.
     * @return log nbme
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public String toString()
    {
        return nbme;
    }

    /** log level constbnt (no logging). */
    public stbtic finbl int SILENT  = 0;
    /** log level constbnt (brief logging). */
    public stbtic finbl int BRIEF   = 10;
    /** log level constbnt (verbose logging). */
    public stbtic finbl int VERBOSE = 20;

    /**
     * Convert b string nbme of b logging level to its internbl
     * integer representbtion.
     * @pbrbm s nbme of logging level (e.g., 'SILENT', 'BRIEF', 'VERBOSE')
     * @return corresponding integer log level
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public stbtic int pbrseLevel(String s)
    {
        if ((s == null) || (s.length() < 1))
            return -1;

        try {
            return Integer.pbrseInt(s);
        } cbtch (NumberFormbtException e) {
        }
        if (s.length() < 1)
            return -1;

        if ("SILENT".stbrtsWith(s.toUpperCbse()))
            return SILENT;
        else if ("BRIEF".stbrtsWith(s.toUpperCbse()))
            return BRIEF;
        else if ("VERBOSE".stbrtsWith(s.toUpperCbse()))
            return VERBOSE;

        return -1;
    }
}
