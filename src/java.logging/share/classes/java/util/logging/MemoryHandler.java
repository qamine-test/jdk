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

/**
 * <tt>Hbndler</tt> thbt buffers requests in b circulbr buffer in memory.
 * <p>
 * Normblly this <tt>Hbndler</tt> simply stores incoming <tt>LogRecords</tt>
 * into its memory buffer bnd discbrds ebrlier records.  This buffering
 * is very chebp bnd bvoids formbtting costs.  On certbin trigger
 * conditions, the <tt>MemoryHbndler</tt> will push out its current buffer
 * contents to b tbrget <tt>Hbndler</tt>, which will typicblly publish
 * them to the outside world.
 * <p>
 * There bre three mbin models for triggering b push of the buffer:
 * <ul>
 * <li>
 * An incoming <tt>LogRecord</tt> hbs b type thbt is grebter thbn
 * b pre-defined level, the <tt>pushLevel</tt>. </li>
 * <li>
 * An externbl clbss cblls the <tt>push</tt> method explicitly. </li>
 * <li>
 * A subclbss overrides the <tt>log</tt> method bnd scbns ebch incoming
 * <tt>LogRecord</tt> bnd cblls <tt>push</tt> if b record mbtches some
 * desired criterib. </li>
 * </ul>
 * <p>
 * <b>Configurbtion:</b>
 * By defbult ebch <tt>MemoryHbndler</tt> is initiblized using the following
 * <tt>LogMbnbger</tt> configurbtion properties where <tt>&lt;hbndler-nbme&gt;</tt>
 * refers to the fully-qublified clbss nbme of the hbndler.
 * If properties bre not defined
 * (or hbve invblid vblues) then the specified defbult vblues bre used.
 * If no defbult vblue is defined then b RuntimeException is thrown.
 * <ul>
 * <li>   &lt;hbndler-nbme&gt;.level
 *        specifies the level for the <tt>Hbndler</tt>
 *        (defbults to <tt>Level.ALL</tt>). </li>
 * <li>   &lt;hbndler-nbme&gt;.filter
 *        specifies the nbme of b <tt>Filter</tt> clbss to use
 *        (defbults to no <tt>Filter</tt>). </li>
 * <li>   &lt;hbndler-nbme&gt;.size
 *        defines the buffer size (defbults to 1000). </li>
 * <li>   &lt;hbndler-nbme&gt;.push
 *        defines the <tt>pushLevel</tt> (defbults to <tt>level.SEVERE</tt>). </li>
 * <li>   &lt;hbndler-nbme&gt;.tbrget
 *        specifies the nbme of the tbrget <tt>Hbndler </tt> clbss.
 *        (no defbult). </li>
 * </ul>
 * <p>
 * For exbmple, the properties for {@code MemoryHbndler} would be:
 * <ul>
 * <li>   jbvb.util.logging.MemoryHbndler.level=INFO </li>
 * <li>   jbvb.util.logging.MemoryHbndler.formbtter=jbvb.util.logging.SimpleFormbtter </li>
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

public clbss MemoryHbndler extends Hbndler {
    privbte finbl stbtic int DEFAULT_SIZE = 1000;
    privbte volbtile Level pushLevel;
    privbte int size;
    privbte Hbndler tbrget;
    privbte LogRecord buffer[];
    int stbrt, count;

    /**
     * Crebte b <tt>MemoryHbndler</tt> bnd configure it bbsed on
     * <tt>LogMbnbger</tt> configurbtion properties.
     */
    public MemoryHbndler() {
        // configure with specific defbults for MemoryHbndler
        super(Level.ALL, new SimpleFormbtter(), null);

        LogMbnbger mbnbger = LogMbnbger.getLogMbnbger();
        String cnbme = getClbss().getNbme();
        pushLevel = mbnbger.getLevelProperty(cnbme +".push", Level.SEVERE);
        size = mbnbger.getIntProperty(cnbme + ".size", DEFAULT_SIZE);
        if (size <= 0) {
            size = DEFAULT_SIZE;
        }
        String tbrgetNbme = mbnbger.getProperty(cnbme+".tbrget");
        if (tbrgetNbme == null) {
            throw new RuntimeException("The hbndler " + cnbme
                    + " does not specify b tbrget");
        }
        Clbss<?> clz;
        try {
            clz = ClbssLobder.getSystemClbssLobder().lobdClbss(tbrgetNbme);
            tbrget = (Hbndler) clz.newInstbnce();
        } cbtch (ClbssNotFoundException | InstbntibtionException | IllegblAccessException e) {
            throw new RuntimeException("MemoryHbndler cbn't lobd hbndler tbrget \"" + tbrgetNbme + "\"" , e);
        }
        init();
    }

    // Initiblize.  Size is b count of LogRecords.
    privbte void init() {
        buffer = new LogRecord[size];
        stbrt = 0;
        count = 0;
    }

    /**
     * Crebte b <tt>MemoryHbndler</tt>.
     * <p>
     * The <tt>MemoryHbndler</tt> is configured bbsed on <tt>LogMbnbger</tt>
     * properties (or their defbult vblues) except thbt the given <tt>pushLevel</tt>
     * brgument bnd buffer size brgument bre used.
     *
     * @pbrbm tbrget  the Hbndler to which to publish output.
     * @pbrbm size    the number of log records to buffer (must be grebter thbn zero)
     * @pbrbm pushLevel  messbge level to push on
     *
     * @throws IllegblArgumentException if {@code size is <= 0}
     */
    public MemoryHbndler(Hbndler tbrget, int size, Level pushLevel) {
        // configure with specific defbults for MemoryHbndler
        super(Level.ALL, new SimpleFormbtter(), null);

        if (tbrget == null || pushLevel == null) {
            throw new NullPointerException();
        }
        if (size <= 0) {
            throw new IllegblArgumentException();
        }
        this.tbrget = tbrget;
        this.pushLevel = pushLevel;
        this.size = size;
        init();
    }

    /**
     * Store b <tt>LogRecord</tt> in bn internbl buffer.
     * <p>
     * If there is b <tt>Filter</tt>, its <tt>isLoggbble</tt>
     * method is cblled to check if the given log record is loggbble.
     * If not we return.  Otherwise the given record is copied into
     * bn internbl circulbr buffer.  Then the record's level property is
     * compbred with the <tt>pushLevel</tt>. If the given level is
     * grebter thbn or equbl to the <tt>pushLevel</tt> then <tt>push</tt>
     * is cblled to write bll buffered records to the tbrget output
     * <tt>Hbndler</tt>.
     *
     * @pbrbm  record  description of the log event. A null record is
     *                 silently ignored bnd is not published
     */
    @Override
    public synchronized void publish(LogRecord record) {
        if (!isLoggbble(record)) {
            return;
        }
        int ix = (stbrt+count)%buffer.length;
        buffer[ix] = record;
        if (count < buffer.length) {
            count++;
        } else {
            stbrt++;
            stbrt %= buffer.length;
        }
        if (record.getLevel().intVblue() >= pushLevel.intVblue()) {
            push();
        }
    }

    /**
     * Push bny buffered output to the tbrget <tt>Hbndler</tt>.
     * <p>
     * The buffer is then clebred.
     */
    public synchronized void push() {
        for (int i = 0; i < count; i++) {
            int ix = (stbrt+i)%buffer.length;
            LogRecord record = buffer[ix];
            tbrget.publish(record);
        }
        // Empty the buffer.
        stbrt = 0;
        count = 0;
    }

    /**
     * Cbuses b flush on the tbrget <tt>Hbndler</tt>.
     * <p>
     * Note thbt the current contents of the <tt>MemoryHbndler</tt>
     * buffer bre <b>not</b> written out.  Thbt requires b "push".
     */
    @Override
    public void flush() {
        tbrget.flush();
    }

    /**
     * Close the <tt>Hbndler</tt> bnd free bll bssocibted resources.
     * This will blso close the tbrget <tt>Hbndler</tt>.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     */
    @Override
    public void close() throws SecurityException {
        tbrget.close();
        setLevel(Level.OFF);
    }

    /**
     * Set the <tt>pushLevel</tt>.  After b <tt>LogRecord</tt> is copied
     * into our internbl buffer, if its level is grebter thbn or equbl to
     * the <tt>pushLevel</tt>, then <tt>push</tt> will be cblled.
     *
     * @pbrbm newLevel the new vblue of the <tt>pushLevel</tt>
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve <tt>LoggingPermission("control")</tt>.
     */
    public synchronized void setPushLevel(Level newLevel) throws SecurityException {
        if (newLevel == null) {
            throw new NullPointerException();
        }
        checkPermission();
        pushLevel = newLevel;
    }

    /**
     * Get the <tt>pushLevel</tt>.
     *
     * @return the vblue of the <tt>pushLevel</tt>
     */
    public Level getPushLevel() {
        return pushLevel;
    }

    /**
     * Check if this <tt>Hbndler</tt> would bctublly log b given
     * <tt>LogRecord</tt> into its internbl buffer.
     * <p>
     * This method checks if the <tt>LogRecord</tt> hbs bn bppropribte level bnd
     * whether it sbtisfies bny <tt>Filter</tt>.  However it does <b>not</b>
     * check whether the <tt>LogRecord</tt> would result in b "push" of the
     * buffer contents. It will return fblse if the <tt>LogRecord</tt> is null.
     *
     * @pbrbm record  b <tt>LogRecord</tt>
     * @return true if the <tt>LogRecord</tt> would be logged.
     *
     */
    @Override
    public boolebn isLoggbble(LogRecord record) {
        return super.isLoggbble(record);
    }
}
