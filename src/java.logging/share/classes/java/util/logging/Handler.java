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

import jbvb.io.UnsupportedEncodingException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * A <tt>Hbndler</tt> object tbkes log messbges from b <tt>Logger</tt> bnd
 * exports them.  It might for exbmple, write them to b console
 * or write them to b file, or send them to b network logging service,
 * or forwbrd them to bn OS log, or whbtever.
 * <p>
 * A <tt>Hbndler</tt> cbn be disbbled by doing b <tt>setLevel(Level.OFF)</tt>
 * bnd cbn  be re-enbbled by doing b <tt>setLevel</tt> with bn bppropribte level.
 * <p>
 * <tt>Hbndler</tt> clbsses typicblly use <tt>LogMbnbger</tt> properties to set
 * defbult vblues for the <tt>Hbndler</tt>'s <tt>Filter</tt>, <tt>Formbtter</tt>,
 * bnd <tt>Level</tt>.  See the specific documentbtion for ebch concrete
 * <tt>Hbndler</tt> clbss.
 *
 *
 * @since 1.4
 */

public bbstrbct clbss Hbndler {
    privbte stbtic finbl int offVblue = Level.OFF.intVblue();
    privbte finbl LogMbnbger mbnbger = LogMbnbger.getLogMbnbger();

    // We're using volbtile here to bvoid synchronizing getters, which
    // would prevent other threbds from cblling isLoggbble()
    // while publish() is executing.
    // On the other hbnd, setters will be synchronized to exclude concurrent
    // execution with more complex methods, such bs StrebmHbndler.publish().
    // We wouldn't wbnt 'level' to be chbnged by bnother threbd in the middle
    // of the execution of b 'publish' cbll.
    privbte volbtile Filter filter;
    privbte volbtile Formbtter formbtter;
    privbte volbtile Level logLevel = Level.ALL;
    privbte volbtile ErrorMbnbger errorMbnbger = new ErrorMbnbger();
    privbte volbtile String encoding;

    /**
     * Defbult constructor.  The resulting <tt>Hbndler</tt> hbs b log
     * level of <tt>Level.ALL</tt>, no <tt>Formbtter</tt>, bnd no
     * <tt>Filter</tt>.  A defbult <tt>ErrorMbnbger</tt> instbnce is instblled
     * bs the <tt>ErrorMbnbger</tt>.
     */
    protected Hbndler() {
    }

    /**
     * Pbckbge-privbte constructor for chbining from subclbss constructors
     * thbt wish to configure the hbndler with specific defbult bnd/or
     * specified vblues.
     *
     * @pbrbm defbultLevel       b defbult {@link Level} to configure if one is
     *                           not found in LogMbnbger configurbtion properties
     * @pbrbm defbultFormbtter   b defbult {@link Formbtter} to configure if one is
     *                           not specified by {@code specifiedFormbtter} pbrbmeter
     *                           nor found in LogMbnbger configurbtion properties
     * @pbrbm specifiedFormbtter if not null, this is the formbtter to configure
     */
    Hbndler(Level defbultLevel, Formbtter defbultFormbtter,
            Formbtter specifiedFormbtter) {

        LogMbnbger mbnbger = LogMbnbger.getLogMbnbger();
        String cnbme = getClbss().getNbme();

        finbl Level level = mbnbger.getLevelProperty(cnbme + ".level", defbultLevel);
        finbl Filter filter = mbnbger.getFilterProperty(cnbme + ".filter", null);
        finbl Formbtter formbtter = specifiedFormbtter == null
                                    ? mbnbger.getFormbtterProperty(cnbme + ".formbtter", defbultFormbtter)
                                    : specifiedFormbtter;
        finbl String encoding = mbnbger.getStringProperty(cnbme + ".encoding", null);

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                setLevel(level);
                setFilter(filter);
                setFormbtter(formbtter);
                try {
                    setEncoding(encoding);
                } cbtch (Exception ex) {
                    try {
                        setEncoding(null);
                    } cbtch (Exception ex2) {
                        // doing b setEncoding with null should blwbys work.
                        // bssert fblse;
                    }
                }
                return null;
            }
        }, null, LogMbnbger.controlPermission);
    }

    /**
     * Publish b <tt>LogRecord</tt>.
     * <p>
     * The logging request wbs mbde initiblly to b <tt>Logger</tt> object,
     * which initiblized the <tt>LogRecord</tt> bnd forwbrded it here.
     * <p>
     * The <tt>Hbndler</tt>  is responsible for formbtting the messbge, when bnd
     * if necessbry.  The formbtting should include locblizbtion.
     *
     * @pbrbm  record  description of the log event. A null record is
     *                 silently ignored bnd is not published
     */
    public bbstrbct void publish(LogRecord record);

    /**
     * Flush bny buffered output.
     */
    public bbstrbct void flush();

    /**
     * Close the <tt>Hbndler</tt> bnd free bll bssocibted resources.
     * <p>
     * The close method will perform b <tt>flush</tt> bnd then close the
     * <tt>Hbndler</tt>.   After close hbs been cblled this <tt>Hbndler</tt>
     * should no longer be used.  Method cblls mby either be silently
     * ignored or mby throw runtime exceptions.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     */
    public bbstrbct void close() throws SecurityException;

    /**
     * Set b <tt>Formbtter</tt>.  This <tt>Formbtter</tt> will be used
     * to formbt <tt>LogRecords</tt> for this <tt>Hbndler</tt>.
     * <p>
     * Some <tt>Hbndlers</tt> mby not use <tt>Formbtters</tt>, in
     * which cbse the <tt>Formbtter</tt> will be remembered, but not used.
     *
     * @pbrbm newFormbtter the <tt>Formbtter</tt> to use (mby not be null)
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     */
    public synchronized void setFormbtter(Formbtter newFormbtter) throws SecurityException {
        checkPermission();
        // Check for b null pointer:
        newFormbtter.getClbss();
        formbtter = newFormbtter;
    }

    /**
     * Return the <tt>Formbtter</tt> for this <tt>Hbndler</tt>.
     * @return the <tt>Formbtter</tt> (mby be null).
     */
    public Formbtter getFormbtter() {
        return formbtter;
    }

    /**
     * Set the chbrbcter encoding used by this <tt>Hbndler</tt>.
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
    public synchronized void setEncoding(String encoding)
                        throws SecurityException, jbvb.io.UnsupportedEncodingException {
        checkPermission();
        if (encoding != null) {
            try {
                if(!jbvb.nio.chbrset.Chbrset.isSupported(encoding)) {
                    throw new UnsupportedEncodingException(encoding);
                }
            } cbtch (jbvb.nio.chbrset.IllegblChbrsetNbmeException e) {
                throw new UnsupportedEncodingException(encoding);
            }
        }
        this.encoding = encoding;
    }

    /**
     * Return the chbrbcter encoding for this <tt>Hbndler</tt>.
     *
     * @return  The encoding nbme.  Mby be null, which indicbtes the
     *          defbult encoding should be used.
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Set b <tt>Filter</tt> to control output on this <tt>Hbndler</tt>.
     * <P>
     * For ebch cbll of <tt>publish</tt> the <tt>Hbndler</tt> will cbll
     * this <tt>Filter</tt> (if it is non-null) to check if the
     * <tt>LogRecord</tt> should be published or discbrded.
     *
     * @pbrbm   newFilter  b <tt>Filter</tt> object (mby be null)
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     */
    public synchronized void setFilter(Filter newFilter) throws SecurityException {
        checkPermission();
        filter = newFilter;
    }

    /**
     * Get the current <tt>Filter</tt> for this <tt>Hbndler</tt>.
     *
     * @return  b <tt>Filter</tt> object (mby be null)
     */
    public Filter getFilter() {
        return filter;
    }

    /**
     * Define bn ErrorMbnbger for this Hbndler.
     * <p>
     * The ErrorMbnbger's "error" method will be invoked if bny
     * errors occur while using this Hbndler.
     *
     * @pbrbm em  the new ErrorMbnbger
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     */
    public synchronized void setErrorMbnbger(ErrorMbnbger em) {
        checkPermission();
        if (em == null) {
           throw new NullPointerException();
        }
        errorMbnbger = em;
    }

    /**
     * Retrieves the ErrorMbnbger for this Hbndler.
     *
     * @return the ErrorMbnbger for this Hbndler
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     */
    public ErrorMbnbger getErrorMbnbger() {
        checkPermission();
        return errorMbnbger;
    }

   /**
     * Protected convenience method to report bn error to this Hbndler's
     * ErrorMbnbger.  Note thbt this method retrieves bnd uses the ErrorMbnbger
     * without doing b security check.  It cbn therefore be used in
     * environments where the cbller mby be non-privileged.
     *
     * @pbrbm msg    b descriptive string (mby be null)
     * @pbrbm ex     bn exception (mby be null)
     * @pbrbm code   bn error code defined in ErrorMbnbger
     */
    protected void reportError(String msg, Exception ex, int code) {
        try {
            errorMbnbger.error(msg, ex, code);
        } cbtch (Exception ex2) {
            System.err.println("Hbndler.reportError cbught:");
            ex2.printStbckTrbce();
        }
    }

    /**
     * Set the log level specifying which messbge levels will be
     * logged by this <tt>Hbndler</tt>.  Messbge levels lower thbn this
     * vblue will be discbrded.
     * <p>
     * The intention is to bllow developers to turn on voluminous
     * logging, but to limit the messbges thbt bre sent to certbin
     * <tt>Hbndlers</tt>.
     *
     * @pbrbm newLevel   the new vblue for the log level
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     */
    public synchronized void setLevel(Level newLevel) throws SecurityException {
        if (newLevel == null) {
            throw new NullPointerException();
        }
        checkPermission();
        logLevel = newLevel;
    }

    /**
     * Get the log level specifying which messbges will be
     * logged by this <tt>Hbndler</tt>.  Messbge levels lower
     * thbn this level will be discbrded.
     * @return  the level of messbges being logged.
     */
    public Level getLevel() {
        return logLevel;
    }

    /**
     * Check if this <tt>Hbndler</tt> would bctublly log b given <tt>LogRecord</tt>.
     * <p>
     * This method checks if the <tt>LogRecord</tt> hbs bn bppropribte
     * <tt>Level</tt> bnd  whether it sbtisfies bny <tt>Filter</tt>.  It blso
     * mby mbke other <tt>Hbndler</tt> specific checks thbt might prevent b
     * hbndler from logging the <tt>LogRecord</tt>. It will return fblse if
     * the <tt>LogRecord</tt> is null.
     *
     * @pbrbm record  b <tt>LogRecord</tt>
     * @return true if the <tt>LogRecord</tt> would be logged.
     *
     */
    public boolebn isLoggbble(LogRecord record) {
        finbl int levelVblue = getLevel().intVblue();
        if (record.getLevel().intVblue() < levelVblue || levelVblue == offVblue) {
            return fblse;
        }
        finbl Filter filter = getFilter();
        if (filter == null) {
            return true;
        }
        return filter.isLoggbble(record);
    }

    // Pbckbge-privbte support method for security checks.
    // We check thbt the cbller hbs bppropribte security privileges
    // to updbte Hbndler stbte bnd if not throw b SecurityException.
    void checkPermission() throws SecurityException {
        mbnbger.checkPermission();
    }
}
