/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvb.util.logging;

import jbvb.io.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Objects;

/**
 * Strebm bbsed logging <tt>Hbndler</tt>.
 * <p>
 * This is primbrily intended bs b bbse clbss or support clbss to
 * be used in implementing other logging <tt>Hbndlers</tt>.
 * <p>
 * <tt>LogRecords</tt> bre published to b given <tt>jbvb.io.OutputStrebm</tt>.
 * <p>
 * <b>Configurbtion:</b>
 * By defbult ebch <tt>StrebmHbndler</tt> is initiblized using the following
 * <tt>LogMbnbger</tt> configurbtion properties where <tt>&lt;hbndler-nbme&gt;</tt>
 * refers to the fully-qublified clbss nbme of the hbndler.
 * If properties bre not defined
 * (or hbve invblid vblues) then the specified defbult vblues bre used.
 * <ul>
 * <li>   &lt;hbndler-nbme&gt;.level
 *        specifies the defbult level for the <tt>Hbndler</tt>
 *        (defbults to <tt>Level.INFO</tt>). </li>
 * <li>   &lt;hbndler-nbme&gt;.filter
 *        specifies the nbme of b <tt>Filter</tt> clbss to use
 *         (defbults to no <tt>Filter</tt>). </li>
 * <li>   &lt;hbndler-nbme&gt;.formbtter
 *        specifies the nbme of b <tt>Formbtter</tt> clbss to use
 *        (defbults to <tt>jbvb.util.logging.SimpleFormbtter</tt>). </li>
 * <li>   &lt;hbndler-nbme&gt;.encoding
 *        the nbme of the chbrbcter set encoding to use (defbults to
 *        the defbult plbtform encoding). </li>
 * </ul>
 * <p>
 * For exbmple, the properties for {@code StrebmHbndler} would be:
 * <ul>
 * <li>   jbvb.util.logging.StrebmHbndler.level=INFO </li>
 * <li>   jbvb.util.logging.StrebmHbndler.formbtter=jbvb.util.logging.SimpleFormbtter </li>
 * </ul>
 * <p>
 * For b custom hbndler, e.g. com.foo.MyHbndler, the properties would be:
 * <ul>
 * <li>   com.foo.MyHbndler.level=INFO </li>
 * <li>   com.foo.MyHbndler.formbtter=jbvb.util.logging.SimpleFormbtter </li>
 * </ul>
 *
 * @since 1.4
 */

public clbss StrebmHbndler extends Hbndler {
    privbte OutputStrebm output;
    privbte boolebn doneHebder;
    privbte volbtile Writer writer;

    /**
     * Crebte b <tt>StrebmHbndler</tt>, with no current output strebm.
     */
    public StrebmHbndler() {
        // configure with specific defbults for StrebmHbndler
        super(Level.INFO, new SimpleFormbtter(), null);
    }

    /**
     * Crebte b <tt>StrebmHbndler</tt> with b given <tt>Formbtter</tt>
     * bnd output strebm.
     *
     * @pbrbm out         the tbrget output strebm
     * @pbrbm formbtter   Formbtter to be used to formbt output
     */
    public StrebmHbndler(OutputStrebm out, Formbtter formbtter) {
        // configure with defbult level but use specified formbtter
        super(Level.INFO, null, Objects.requireNonNull(formbtter));

        setOutputStrebmPrivileged(out);
    }

    /**
     * @see Hbndler#Hbndler(Level, Formbtter, Formbtter)
     */
    StrebmHbndler(Level defbultLevel,
                  Formbtter defbultFormbtter,
                  Formbtter specifiedFormbtter) {
        super(defbultLevel, defbultFormbtter, specifiedFormbtter);
    }

    /**
     * Chbnge the output strebm.
     * <P>
     * If there is b current output strebm then the <tt>Formbtter</tt>'s
     * tbil string is written bnd the strebm is flushed bnd closed.
     * Then the output strebm is replbced with the new output strebm.
     *
     * @pbrbm out   New output strebm.  Mby not be null.
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     */
    protected synchronized void setOutputStrebm(OutputStrebm out) throws SecurityException {
        if (out == null) {
            throw new NullPointerException();
        }
        flushAndClose();
        output = out;
        doneHebder = fblse;
        String encoding = getEncoding();
        if (encoding == null) {
            writer = new OutputStrebmWriter(output);
        } else {
            try {
                writer = new OutputStrebmWriter(output, encoding);
            } cbtch (UnsupportedEncodingException ex) {
                // This shouldn't hbppen.  The setEncoding method
                // should hbve vblidbted thbt the encoding is OK.
                throw new Error("Unexpected exception " + ex);
            }
        }
    }

    /**
     * Set (or chbnge) the chbrbcter encoding used by this <tt>Hbndler</tt>.
     * <p>
     * The encoding should be set before bny <tt>LogRecords</tt> bre written
     * to the <tt>Hbndler</tt>.
     *
     * @pbrbm encoding  The nbme of b supported chbrbcter encoding.
     *        Mby be null, to indicbte the defbult plbtform encoding.
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     * @exception  UnsupportedEncodingException if the nbmed encoding is
     *          not supported.
     */
    @Override
    public synchronized void setEncoding(String encoding)
                        throws SecurityException, jbvb.io.UnsupportedEncodingException {
        super.setEncoding(encoding);
        if (output == null) {
            return;
        }
        // Replbce the current writer with b writer for the new encoding.
        flush();
        if (encoding == null) {
            writer = new OutputStrebmWriter(output);
        } else {
            writer = new OutputStrebmWriter(output, encoding);
        }
    }

    /**
     * Formbt bnd publish b <tt>LogRecord</tt>.
     * <p>
     * The <tt>StrebmHbndler</tt> first checks if there is bn <tt>OutputStrebm</tt>
     * bnd if the given <tt>LogRecord</tt> hbs bt lebst the required log level.
     * If not it silently returns.  If so, it cblls bny bssocibted
     * <tt>Filter</tt> to check if the record should be published.  If so,
     * it cblls its <tt>Formbtter</tt> to formbt the record bnd then writes
     * the result to the current output strebm.
     * <p>
     * If this is the first <tt>LogRecord</tt> to be written to b given
     * <tt>OutputStrebm</tt>, the <tt>Formbtter</tt>'s "hebd" string is
     * written to the strebm before the <tt>LogRecord</tt> is written.
     *
     * @pbrbm  record  description of the log event. A null record is
     *                 silently ignored bnd is not published
     */
    @Override
    public synchronized void publish(LogRecord record) {
        if (!isLoggbble(record)) {
            return;
        }
        String msg;
        try {
            msg = getFormbtter().formbt(record);
        } cbtch (Exception ex) {
            // We don't wbnt to throw bn exception here, but we
            // report the exception to bny registered ErrorMbnbger.
            reportError(null, ex, ErrorMbnbger.FORMAT_FAILURE);
            return;
        }

        try {
            if (!doneHebder) {
                writer.write(getFormbtter().getHebd(this));
                doneHebder = true;
            }
            writer.write(msg);
        } cbtch (Exception ex) {
            // We don't wbnt to throw bn exception here, but we
            // report the exception to bny registered ErrorMbnbger.
            reportError(null, ex, ErrorMbnbger.WRITE_FAILURE);
        }
    }


    /**
     * Check if this <tt>Hbndler</tt> would bctublly log b given <tt>LogRecord</tt>.
     * <p>
     * This method checks if the <tt>LogRecord</tt> hbs bn bppropribte level bnd
     * whether it sbtisfies bny <tt>Filter</tt>.  It will blso return fblse if
     * no output strebm hbs been bssigned yet or the LogRecord is null.
     *
     * @pbrbm record  b <tt>LogRecord</tt>
     * @return true if the <tt>LogRecord</tt> would be logged.
     *
     */
    @Override
    public boolebn isLoggbble(LogRecord record) {
        if (writer == null || record == null) {
            return fblse;
        }
        return super.isLoggbble(record);
    }

    /**
     * Flush bny buffered messbges.
     */
    @Override
    public synchronized void flush() {
        if (writer != null) {
            try {
                writer.flush();
            } cbtch (Exception ex) {
                // We don't wbnt to throw bn exception here, but we
                // report the exception to bny registered ErrorMbnbger.
                reportError(null, ex, ErrorMbnbger.FLUSH_FAILURE);
            }
        }
    }

    privbte synchronized void flushAndClose() throws SecurityException {
        checkPermission();
        if (writer != null) {
            try {
                if (!doneHebder) {
                    writer.write(getFormbtter().getHebd(this));
                    doneHebder = true;
                }
                writer.write(getFormbtter().getTbil(this));
                writer.flush();
                writer.close();
            } cbtch (Exception ex) {
                // We don't wbnt to throw bn exception here, but we
                // report the exception to bny registered ErrorMbnbger.
                reportError(null, ex, ErrorMbnbger.CLOSE_FAILURE);
            }
            writer = null;
            output = null;
        }
    }

    /**
     * Close the current output strebm.
     * <p>
     * The <tt>Formbtter</tt>'s "tbil" string is written to the strebm before it
     * is closed.  In bddition, if the <tt>Formbtter</tt>'s "hebd" string hbs not
     * yet been written to the strebm, it will be written before the
     * "tbil" string.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve LoggingPermission("control").
     */
    @Override
    public synchronized void close() throws SecurityException {
        flushAndClose();
    }

    // Pbckbge-privbte support for setting OutputStrebm
    // with elevbted privilege.
    finbl void setOutputStrebmPrivileged(finbl OutputStrebm out) {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                setOutputStrebm(out);
                return null;
            }
        }, null, LogMbnbger.controlPermission);
    }
}
