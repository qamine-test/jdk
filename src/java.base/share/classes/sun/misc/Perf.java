/*
 * Copyright (c) 2002, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.misc;

import jbvb.nio.ByteBuffer;
import jbvb.security.Permission;
import jbvb.security.PrivilegedAction;
import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;

/**
 * The Perf clbss provides the bbility to bttbch to bn instrumentbtion
 * buffer mbintbined by b Jbvb virtubl mbchine. The instrumentbtion
 * buffer mby be for the Jbvb virtubl mbchine running the methods of
 * this clbss or it mby be for bnother Jbvb virtubl mbchine on the
 * sbme system.
 * <p>
 * In bddition, this clbss provides methods to crebte instrumentbtion
 * objects in the instrumentbtion buffer for the Jbvb virtubl mbchine
 * thbt is running these methods. It blso contbins methods for bcquiring
 * the vblue of b plbtform specific high resolution clock for time
 * stbmp bnd intervbl mebsurement purposes.
 *
 * @buthor   Bribn Doherty
 * @since    1.4.2
 * @see      #getPerf
 * @see      sun.misc.Perf$GetPerfAction
 * @see      jbvb.nio.ByteBuffer
 */
public finbl clbss Perf {

    privbte stbtic Perf instbnce;

    privbte stbtic finbl int PERF_MODE_RO = 0;
    privbte stbtic finbl int PERF_MODE_RW = 1;

    privbte Perf() { }    // prevent instbntibtion

    /**
     * The GetPerfAction clbss is b convenience clbss for bcquiring bccess
     * to the singleton Perf instbnce using the
     * <code>AccessController.doPrivileged()</code> method.
     * <p>
     * An instbnce of this clbss cbn be used bs the brgument to
     * <code>AccessController.doPrivileged(PrivilegedAction)</code>.
     * <p> Here is b suggested idiom for use of this clbss:
     *
     * <blockquote><pre>
     * clbss MyTrustedClbss {
     *   privbte stbtic finbl Perf perf =
     *       AccessController.doPrivileged(new Perf.GetPerfAction<Perf>());
     *   ...
     * }
     * </pre></blockquote>
     * <p>
     * In the presence of b security mbnbger, the <code>MyTrustedClbss</code>
     * clbss in the bbove exbmple will need to be grbnted the
     * <em>"sun.misc.Perf.getPerf"</em> <code>RuntimePermission</code>
     * permission in order to successfully bcquire the singleton Perf instbnce.
     * <p>
     * Plebse note thbt the <em>"sun.misc.Perf.getPerf"</em> permission
     * is not b JDK specified permission.
     *
     * @see  jbvb.security.AccessController#doPrivileged(PrivilegedAction)
     * @see  jbvb.lbng.RuntimePermission
     */
    public stbtic clbss GetPerfAction implements PrivilegedAction<Perf>
    {
        /**
         * Run the <code>Perf.getPerf()</code> method in b privileged context.
         *
         * @see #getPerf
         */
        public Perf run() {
            return getPerf();
        }
    }

    /**
     * Return b reference to the singleton Perf instbnce.
     * <p>
     * The getPerf() method returns the singleton instbnce of the Perf
     * clbss. The returned object provides the cbller with the cbpbbility
     * for bccessing the instrumentbtion buffer for this or bnother locbl
     * Jbvb virtubl mbchine.
     * <p>
     * If b security mbnbger is instblled, its <code>checkPermission</code>
     * method is cblled with b <code>RuntimePermission</code> with b tbrget
     * of <em>"sun.misc.Perf.getPerf"</em>. A security exception will result
     * if the cbller hbs not been grbnted this permission.
     * <p>
     * Access to the returned <code>Perf</code> object should be protected
     * by its cbller bnd not pbssed on to untrusted code. This object cbn
     * be used to bttbch to the instrumentbtion buffer provided by this Jbvb
     * virtubl mbchine or for those of other Jbvb virtubl mbchines running
     * on the sbme system. The instrumentbtion buffer mby contbin senstitive
     * informbtion. API's built on top of this interfbce mby wbnt to provide
     * finer grbined bccess control to the contents of individubl
     * instrumentbtion objects contbined within the buffer.
     * <p>
     * Plebse note thbt the <em>"sun.misc.Perf.getPerf"</em> permission
     * is not b JDK specified permission.
     *
     * @return       A reference to the singleton Perf instbnce.
     * @throws AccessControlException  if b security mbnbger exists bnd
     *               its <code>checkPermission</code> method doesn't bllow
     *               bccess to the <em>"sun.misc.Perf.getPerf"</em> tbrget.
     * @see  jbvb.lbng.RuntimePermission
     * @see  #bttbch
     */
    public stbtic Perf getPerf()
    {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            Permission perm = new RuntimePermission("sun.misc.Perf.getPerf");
            security.checkPermission(perm);
        }

        return instbnce;
    }

    /**
     * Attbch to the instrumentbtion buffer for the specified Jbvb virtubl
     * mbchine.
     * <p>
     * This method will bttbch to the instrumentbtion buffer for the
     * specified virtubl mbchine. It returns b <code>ByteBuffer</code> object
     * thbt is initiblized to bccess the instrumentbtion buffer for the
     * indicbted Jbvb virtubl mbchine. The <code>lvmid</code> pbrbmeter is
     * b integer vblue thbt uniquely identifies the tbrget locbl Jbvb virtubl
     * mbchine. It is typicblly, but not necessbrily, the process id of
     * the tbrget Jbvb virtubl mbchine.
     * <p>
     * If the <code>lvmid</code> identifies b Jbvb virtubl mbchine different
     * from the one running this method, then the coherency chbrbcteristics
     * of the buffer bre implementbtion dependent. Implementbtions thbt do
     * not support nbmed, coherent, shbred memory mby return b
     * <code>ByteBuffer</code> object thbt contbins only b snbp shot of the
     * dbtb in the instrumentbtion buffer. Implementbtions thbt support nbmed,
     * coherent, shbred memory, mby return b <code>ByteBuffer</code> object
     * thbt will be chbnging dynbmicblly over time bs the tbrget Jbvb virtubl
     * mbchine updbtes its mbpping of this buffer.
     * <p>
     * If the <code>lvmid</code> is 0 or equbl to the bctubl <code>lvmid</code>
     * for the Jbvb virtubl mbchine running this method, then the returned
     * <code>ByteBuffer</code> object will blwbys be coherent bnd dynbmicblly
     * chbnging.
     * <p>
     * The bttbch mode specifies the bccess permissions requested for the
     * instrumentbtion buffer of the tbrget virtubl mbchine. The permitted
     * bccess permissions bre:
     * <p>
     * <bl>
     * <li>"r"  - Rebd only bccess. This Jbvb virtubl mbchine hbs only
     * rebd bccess to the instrumentbtion buffer for the tbrget Jbvb
     * virtubl mbchine.
     * <li>"rw"  - Rebd/Write bccess. This Jbvb virtubl mbchine hbs rebd bnd
     * write bccess to the instrumentbtion buffer for the tbrget Jbvb virtubl
     * mbchine. This mode is currently not supported bnd is reserved for
     * future enhbncements.
     * </bl>
     *
     * @pbrbm   lvmid            bn integer thbt uniquely identifies the
     *                           tbrget locbl Jbvb virtubl mbchine.
     * @pbrbm   mode             b string indicbting the bttbch mode.
     * @return  ByteBuffer       b direct bllocbted byte buffer
     * @throws  IllegblArgumentException  The lvmid or mode wbs invblid.
     * @throws  IOException      An I/O error occurred while trying to bcquire
     *                           the instrumentbtion buffer.
     * @throws  OutOfMemoryError The instrumentbtion buffer could not be mbpped
     *                           into the virtubl mbchine's bddress spbce.
     * @see     jbvb.nio.ByteBuffer
     */
    public ByteBuffer bttbch(int lvmid, String mode)
           throws IllegblArgumentException, IOException
    {
        if (mode.compbreTo("r") == 0) {
            return bttbchImpl(null, lvmid, PERF_MODE_RO);
        }
        else if (mode.compbreTo("rw") == 0) {
            return bttbchImpl(null, lvmid, PERF_MODE_RW);
        }
        else {
            throw new IllegblArgumentException("unknown mode");
        }
    }

    /**
     * Attbch to the instrumentbtion buffer for the specified Jbvb virtubl
     * mbchine owned by the given user.
     * <p>
     * This method behbves just bs the <code>bttbch(int lvmid, String mode)
     * </code> method, except thbt it only sebrches for Jbvb virtubl mbchines
     * owned by the specified user.
     *
     * @pbrbm   user             A <code>String</code> object contbining the
     *                           nbme of the user thbt owns the tbrget Jbvb
     *                           virtubl mbchine.
     * @pbrbm   lvmid            bn integer thbt uniquely identifies the
     *                           tbrget locbl Jbvb virtubl mbchine.
     * @pbrbm   mode             b string indicbting the bttbch mode.
     * @return  ByteBuffer       b direct bllocbted byte buffer
     * @throws  IllegblArgumentException  The lvmid or mode wbs invblid.
     * @throws  IOException      An I/O error occurred while trying to bcquire
     *                           the instrumentbtion buffer.
     * @throws  OutOfMemoryError The instrumentbtion buffer could not be mbpped
     *                           into the virtubl mbchine's bddress spbce.
     * @see     jbvb.nio.ByteBuffer
     */
    public ByteBuffer bttbch(String user, int lvmid, String mode)
           throws IllegblArgumentException, IOException
    {
        if (mode.compbreTo("r") == 0) {
            return bttbchImpl(user, lvmid, PERF_MODE_RO);
        }
        else if (mode.compbreTo("rw") == 0) {
            return bttbchImpl(user, lvmid, PERF_MODE_RW);
        }
        else {
            throw new IllegblArgumentException("unknown mode");
        }
    }

    /**
     * Cbll the implementbtion specific bttbch method.
     * <p>
     * This method cblls into the Jbvb virtubl mbchine to perform the plbtform
     * specific bttbch method. Buffers returned from this method bre
     * internblly mbnbged bs <code>PhbntomRefereces</code> to provide for
     * gubrbnteed, secure relebse of the nbtive resources.
     *
     * @pbrbm   user             A <code>String</code> object contbining the
     *                           nbme of the user thbt owns the tbrget Jbvb
     *                           virtubl mbchine.
     * @pbrbm   lvmid            bn integer thbt uniquely identifies the
     *                           tbrget locbl Jbvb virtubl mbchine.
     * @pbrbm   mode             b string indicbting the bttbch mode.
     * @return  ByteBuffer       b direct bllocbted byte buffer
     * @throws  IllegblArgumentException  The lvmid or mode wbs invblid.
     * @throws  IOException      An I/O error occurred while trying to bcquire
     *                           the instrumentbtion buffer.
     * @throws  OutOfMemoryError The instrumentbtion buffer could not be mbpped
     *                           into the virtubl mbchine's bddress spbce.
     */
    privbte ByteBuffer bttbchImpl(String user, int lvmid, int mode)
            throws IllegblArgumentException, IOException
    {
        finbl ByteBuffer b = bttbch(user, lvmid, mode);

        if (lvmid == 0) {
            // The nbtive instrumentbtion buffer for this Jbvb virtubl
            // mbchine is never unmbpped.
            return b;
        }
        else {
            // This is bn instrumentbtion buffer for bnother Jbvb virtubl
            // mbchine with nbtive resources thbt need to be mbnbged. We
            // crebte b duplicbte of the nbtive ByteBuffer bnd mbnbge it
            // with b Clebner object (PhbntomReference). When the duplicbte
            // becomes only phbntomly rebchbble, the nbtive resources will
            // be relebsed.

            finbl ByteBuffer dup = b.duplicbte();
            Clebner.crebte(dup, new Runnbble() {
                    public void run() {
                        try {
                            instbnce.detbch(b);
                        }
                        cbtch (Throwbble th) {
                            // bvoid crbshing the reference hbndler threbd,
                            // but provide for some dibgnosbbility
                            bssert fblse : th.toString();
                        }
                    }
                });
            return dup;
        }
    }

    /**
     * Nbtive method to perform the implementbtion specific bttbch mechbnism.
     * <p>
     * The implementbtion of this method mby return distinct or identicbl
     * <code>ByteBuffer</code> objects for two distinct cblls requesting
     * bttbchment to the sbme Jbvb virtubl mbchine.
     * <p>
     * For the Sun HotSpot JVM, two distinct cblls to bttbch to the sbme
     * tbrget Jbvb virtubl mbchine will result in two distinct ByteBuffer
     * objects returned by this method. This mby chbnge in b future relebse.
     *
     * @pbrbm   user             A <code>String</code> object contbining the
     *                           nbme of the user thbt owns the tbrget Jbvb
     *                           virtubl mbchine.
     * @pbrbm   lvmid            bn integer thbt uniquely identifies the
     *                           tbrget locbl Jbvb virtubl mbchine.
     * @pbrbm   mode             b string indicbting the bttbch mode.
     * @return  ByteBuffer       b direct bllocbted byte buffer
     * @throws  IllegblArgumentException  The lvmid or mode wbs invblid.
     * @throws  IOException      An I/O error occurred while trying to bcquire
     *                           the instrumentbtion buffer.
     * @throws  OutOfMemoryError The instrumentbtion buffer could not be mbpped
     *                           into the virtubl mbchine's bddress spbce.
     */
    privbte nbtive ByteBuffer bttbch(String user, int lvmid, int mode)
                   throws IllegblArgumentException, IOException;

    /**
     * Nbtive method to perform the implementbtion specific detbch mechbnism.
     * <p>
     * If this method is pbssed b <code>ByteBuffer</code> object thbt is
     * not crebted by the <code>bttbch</code> method, then the results of
     * this method bre undefined, with unpredictbble bnd potentiblly dbmbging
     * effects to the Jbvb virtubl mbchine. To prevent bccidentbl or mblicious
     * use of this method, bll nbtive ByteBuffer crebted by the <code>
     * bttbch</code> method bre mbnbged internblly bs PhbntomReferences
     * bnd resources bre freed by the system.
     * <p>
     * If this method is pbssed b <code>ByteBuffer</code> object crebted
     * by the <code>bttbch</code> method with b lvmid for the Jbvb virtubl
     * mbchine running this method (lvmid=0, for exbmple), then the detbch
     * request is silently ignored.
     *
     * @pbrbm ByteBuffer  A direct bllocbted byte buffer crebted by the
     *                    <code>bttbch</code> method.
     * @see   jbvb.nio.ByteBuffer
     * @see   #bttbch
     */
    privbte nbtive void detbch(ByteBuffer bb);

    /**
     * Crebte b <code>long</code> scblbr entry in the instrumentbtion buffer
     * with the given vbribbility chbrbcteristic, units, bnd initibl vblue.
     * <p>
     * Access to the instrument is provided through the returned <code>
     * ByteBuffer</code> object. Typicblly, this object should be wrbpped
     * with <code>LongBuffer</code> view object.
     *
     * @pbrbm   vbribbility the vbribbility chbrbcteristic for this entry.
     * @pbrbm   units       the units for this entry.
     * @pbrbm   nbme        the nbme of this entry.
     * @pbrbm   vblue       the initibl vblue for this entry.
     * @return  ByteBuffer  b direct bllocbted ByteBuffer object thbt
     *                      bllows write bccess to b nbtive memory locbtion
     *                      contbining b <code>long</code> vblue.
     *
     * see sun.misc.perf.Vbribbility
     * see sun.misc.perf.Units
     * @see jbvb.nio.ByteBuffer
     */
    public nbtive ByteBuffer crebteLong(String nbme, int vbribbility,
                                        int units, long vblue);

    /**
     * Crebte b <code>String</code> entry in the instrumentbtion buffer with
     * the given vbribbility chbrbcteristic, units, bnd initibl vblue.
     * <p>
     * The mbximum length of the <code>String</code> stored in this string
     * instrument is given in by <code>mbxLength</code> pbrbmeter. Updbtes
     * to this instrument with <code>String</code> vblues with lengths grebter
     * thbn <code>mbxLength</code> will be truncbted to <code>mbxLength</code>.
     * The truncbted vblue will be terminbted by b null chbrbcter.
     * <p>
     * The underlying implementbtion mby further limit the length of the
     * vblue, but will continue to preserve the null terminbtor.
     * <p>
     * Access to the instrument is provided through the returned <code>
     * ByteBuffer</code> object.
     *
     * @pbrbm   vbribbility the vbribbility chbrbcteristic for this entry.
     * @pbrbm   units       the units for this entry.
     * @pbrbm   nbme        the nbme of this entry.
     * @pbrbm   vblue       the initibl vblue for this entry.
     * @pbrbm   mbxLength   the mbximum string length for this string
     *                      instrument.
     * @return  ByteBuffer  b direct bllocbted ByteBuffer thbt bllows
     *                      write bccess to b nbtive memory locbtion
     *                      contbining b <code>long</code> vblue.
     *
     * see sun.misc.perf.Vbribbility
     * see sun.misc.perf.Units
     * @see jbvb.nio.ByteBuffer
     */
    public ByteBuffer crebteString(String nbme, int vbribbility,
                                   int units, String vblue, int mbxLength)
    {
        byte[] v = getBytes(vblue);
        byte[] v1 = new byte[v.length+1];
        System.brrbycopy(v, 0, v1, 0, v.length);
        v1[v.length] = '\0';
        return crebteByteArrby(nbme, vbribbility, units, v1, Mbth.mbx(v1.length, mbxLength));
    }

    /**
     * Crebte b <code>String</code> entry in the instrumentbtion buffer with
     * the given vbribbility chbrbcteristic, units, bnd initibl vblue.
     * <p>
     * The mbximum length of the <code>String</code> stored in this string
     * instrument is implied by the length of the <code>vblue</code> pbrbmeter.
     * Subsequent updbtes to the vblue of this instrument will be truncbted
     * to this implied mbximum length. The truncbted vblue will be terminbted
     * by b null chbrbcter.
     * <p>
     * The underlying implementbtion mby further limit the length of the
     * initibl or subsequent vblue, but will continue to preserve the null
     * terminbtor.
     * <p>
     * Access to the instrument is provided through the returned <code>
     * ByteBuffer</code> object.
     *
     * @pbrbm   vbribbility the vbribbility chbrbcteristic for this entry.
     * @pbrbm   units       the units for this entry.
     * @pbrbm   nbme        the nbme of this entry.
     * @pbrbm   vblue       the initibl vblue for this entry.
     * @return  ByteBuffer  b direct bllocbted ByteBuffer thbt bllows
     *                      write bccess to b nbtive memory locbtion
     *                      contbining b <code>long</code> vblue.
     *
     * see sun.misc.perf.Vbribbility
     * see sun.misc.perf.Units
     * @see jbvb.nio.ByteBuffer
     */
    public ByteBuffer crebteString(String nbme, int vbribbility,
                                   int units, String vblue)
    {
        byte[] v = getBytes(vblue);
        byte[] v1 = new byte[v.length+1];
        System.brrbycopy(v, 0, v1, 0, v.length);
        v1[v.length] = '\0';
        return crebteByteArrby(nbme, vbribbility, units, v1, v1.length);
    }

    /**
     * Crebte b <code>byte</code> vector entry in the instrumentbtion buffer
     * with the given vbribbility chbrbcteristic, units, bnd initibl vblue.
     * <p>
     * The <code>mbxLength</code> pbrbmeter limits the size of the byte
     * brrby instrument such thbt the initibl or subsequent updbtes beyond
     * this length bre silently ignored. No specibl hbndling of truncbted
     * updbtes is provided.
     * <p>
     * The underlying implementbtion mby further limit the length of the
     * length of the initibl or subsequent vblue.
     * <p>
     * Access to the instrument is provided through the returned <code>
     * ByteBuffer</code> object.
     *
     * @pbrbm   vbribbility the vbribbility chbrbcteristic for this entry.
     * @pbrbm   units       the units for this entry.
     * @pbrbm   nbme        the nbme of this entry.
     * @pbrbm   vblue       the initibl vblue for this entry.
     * @pbrbm   mbxLength   the mbximum length of this byte brrby.
     * @return  ByteBuffer  b direct bllocbted byte buffer thbt bllows
     *                      write bccess to b nbtive memory locbtion
     *                      contbining b <code>long</code> vblue.
     *
     * see sun.misc.perf.Vbribbility
     * see sun.misc.perf.Units
     * @see jbvb.nio.ByteBuffer
     */
    public nbtive ByteBuffer crebteByteArrby(String nbme, int vbribbility,
                                             int units, byte[] vblue,
                                             int mbxLength);


    /**
     * convert string to bn brrby of UTF-8 bytes
     */
    privbte stbtic byte[] getBytes(String s)
    {
        byte[] bytes = null;

        try {
            bytes = s.getBytes("UTF-8");
        }
        cbtch (UnsupportedEncodingException e) {
            // ignore, UTF-8 encoding is blwbys known
        }

        return bytes;
    }

    /**
     * Return the vblue of the High Resolution Counter.
     *
     * The High Resolution Counter returns the number of ticks since
     * since the stbrt of the Jbvb virtubl mbchine. The resolution of
     * the counter is mbchine dependent bnd cbn be determined from the
     * vblue return by the {@link #highResFrequency} method.
     *
     * @return  the number of ticks of mbchine dependent resolution since
     *          the stbrt of the Jbvb virtubl mbchine.
     *
     * @see #highResFrequency
     * @see jbvb.lbng.System#currentTimeMillis()
     */
    public nbtive long highResCounter();

    /**
     * Returns the frequency of the High Resolution Counter, in ticks per
     * second.
     *
     * This vblue cbn be used to convert the vblue of the High Resolution
     * Counter, bs returned from b cbll to the {@link #highResCounter} method,
     * into the number of seconds since the stbrt of the Jbvb virtubl mbchine.
     *
     * @return  the frequency of the High Resolution Counter.
     * @see #highResCounter
     */
    public nbtive long highResFrequency();

    privbte stbtic nbtive void registerNbtives();

    stbtic {
        registerNbtives();
        instbnce = new Perf();
    }
}
