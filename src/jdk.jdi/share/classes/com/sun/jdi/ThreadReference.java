/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi;
import jbvb.util.List;

/**
 * A threbd object from the tbrget VM.
 * A ThrebdReference is bn {@link ObjectReference} with bdditionbl
 * bccess to threbd-specific informbtion from the tbrget VM.
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce ThrebdReference extends ObjectReference {
    /** Threbd stbtus is unknown */
    public finbl int THREAD_STATUS_UNKNOWN  =-1;
    /** Threbd hbs completed execution */
    public finbl int THREAD_STATUS_ZOMBIE = 0;
    /** Threbd is runnbble */
    public finbl int THREAD_STATUS_RUNNING = 1;
    /** Threbd is sleeping - Threbd.sleep() or JVM_Sleep() wbs cblled */
    public finbl int THREAD_STATUS_SLEEPING = 2;
    /** Threbd is wbiting on b jbvb monitor */
    public finbl int THREAD_STATUS_MONITOR = 3;
    /** Threbd is wbiting - Object.wbit() or JVM_MonitorWbit() wbs cblled */
    public finbl int THREAD_STATUS_WAIT = 4;
    /** Threbd hbs not yet been stbrted */
    public finbl int THREAD_STATUS_NOT_STARTED = 5;

    /**
     * Returns the nbme of this threbd.
     *
     * @return the string contbining the threbd nbme.
     */
    String nbme();

    /**
     * Suspends this threbd. The threbd cbn be resumed through
     * {@link #resume} or resumed with other threbds through
     * {@link VirtublMbchine#resume}.
     * <p>
     * Unlike {@link jbvb.lbng.Threbd#suspend},
     * suspends of both the virtubl mbchine bnd individubl threbds bre
     * counted. Before b threbd will run bgbin, it must be resumed
     * (through {@link #resume} or {@link ThrebdReference#resume})
     * the sbme number of times it hbs been suspended.
     * <p>
     * Suspending single threbds with this method hbs the sbme dbngers
     * bs {@link jbvb.lbng.Threbd#suspend()}. If the suspended threbd
     * holds b monitor needed by bnother running threbd, debdlock is
     * possible in the tbrget VM (bt lebst until the suspended threbd
     * is resumed bgbin).
     * <p>
     * The suspended threbd is gubrbnteed to rembin suspended until
     * resumed through one of the JDI resume methods mentioned bbove;
     * the bpplicbtion in the tbrget VM cbnnot resume the suspended threbd
     * through {@link jbvb.lbng.Threbd#resume}.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     */
    void suspend();

    /**
     * Resumes this threbd. If this threbd wbs not previously suspended
     * through {@link #suspend} or through {@link VirtublMbchine#suspend},
     * or becbuse of b SUSPEND_ALL or SUSPEND_EVENT_THREAD event, then
     * invoking this method hbs no effect. Otherwise, the count of pending
     * suspends on this threbd is decremented. If it is decremented to 0,
     * the threbd will continue to execute.
     * Note: the normbl wby to resume from bn event relbted suspension is
     * vib {@link com.sun.jdi.event.EventSet#resume}.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     */
    void resume();

    /**
     * Returns the number of pending suspends for this threbd. See
     * {@link #suspend} for bn explbnbtion of counted suspends.
     * @return pending suspend count bs bn integer
     */
    int suspendCount();

    /**
     * Stops this threbd with bn bsynchronous exception.
     * A debugger threbd in the tbrget VM will stop this threbd
     * with the given {@link jbvb.lbng.Throwbble} object.
     *
     * @pbrbm throwbble the bsynchronous exception to throw.
     * @throws InvblidTypeException if <code>throwbble</code> is not
     * bn instbnce of jbvb.lbng.Throwbble in the tbrget VM.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     * @see jbvb.lbng.Threbd#stop(Throwbble)
     */
    void stop(ObjectReference throwbble) throws InvblidTypeException;

    /**
     * Interrupts this threbd unless the threbd hbs been suspended by the
     * debugger.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     *
     * @see jbvb.lbng.Threbd#interrupt()
     */
    void interrupt();

    /**
     * Returns the threbd's stbtus. If the threbd is not suspended the
     * threbd's current stbtus is returned. If the threbd is suspended, the
     * threbd's stbtus before the suspension is returned (or
     * {@link #THREAD_STATUS_UNKNOWN} if this informbtion is not bvbilbble.
     * {@link #isSuspended} cbn be used to determine if the threbd hbs been
     * suspended.
     *
     * @return one of
     * {@link #THREAD_STATUS_UNKNOWN},
     * {@link #THREAD_STATUS_ZOMBIE},
     * {@link #THREAD_STATUS_RUNNING},
     * {@link #THREAD_STATUS_SLEEPING},
     * {@link #THREAD_STATUS_MONITOR},
     * {@link #THREAD_STATUS_WAIT},
     * {@link #THREAD_STATUS_NOT_STARTED},
     */
    int stbtus();

    /**
     * Determines whether the threbd hbs been suspended by the
     * the debugger.
     *
     * @return <code>true</code> if the threbd is currently suspended;
     * <code>fblse</code> otherwise.
     */
    boolebn isSuspended();

    /**
     * Determines whether the threbd is suspended bt b brebkpoint.
     *
     * @return <code>true</code> if the threbd is currently stopped bt
     * b brebkpoint; <code>fblse</code> otherwise.
     */
    boolebn isAtBrebkpoint();

    /**
     * Returns this threbd's threbd group.
     * @return b {@link ThrebdGroupReference} thbt mirrors this threbd's
     * threbd group in the tbrget VM.
     */
    ThrebdGroupReference threbdGroup();

    /**
     * Returns the number of stbck frbmes in the threbd's current
     * cbll stbck.
     * The threbd must be suspended (normblly through bn interruption
     * to the VM) to get this informbtion, bnd
     * it is only vblid until the threbd is resumed bgbin.
     *
     * @return bn integer frbme count
     * @throws IncompbtibleThrebdStbteException if the threbd is
     * not suspended in the tbrget VM
     */
    int frbmeCount() throws IncompbtibleThrebdStbteException;

    /**
     * Returns b List contbining ebch {@link StbckFrbme} in the
     * threbd's current cbll stbck.
     * The threbd must be suspended (normblly through bn interruption
     * to the VM) to get this informbtion, bnd
     * it is only vblid until the threbd is resumed bgbin.
     *
     * @return b List of {@link StbckFrbme} with the current frbme first
     * followed by ebch cbller's frbme.
     * @throws IncompbtibleThrebdStbteException if the threbd is
     * not suspended in the tbrget VM
     */
    List<StbckFrbme> frbmes() throws IncompbtibleThrebdStbteException;

    /**
     * Returns the {@link StbckFrbme} bt the given index in the
     * threbd's current cbll stbck. Index 0 retrieves the current
     * frbme; higher indices retrieve cbller frbmes.
     * The threbd must be suspended (normblly through bn interruption
     * to the VM) to get this informbtion, bnd
     * it is only vblid until the threbd is resumed bgbin.
     *
     * @pbrbm index the desired frbme
     * @return the requested {@link StbckFrbme}
     * @throws IncompbtibleThrebdStbteException if the threbd is
     * not suspended in the tbrget VM
     * @throws jbvb.lbng.IndexOutOfBoundsException if the index is grebter thbn
     * or equbl to {@link #frbmeCount} or is negbtive.
     */
    StbckFrbme frbme(int index) throws IncompbtibleThrebdStbteException;

    /**
     * Returns b List contbining b rbnge of {@link StbckFrbme} mirrors
     * from the threbd's current cbll stbck.
     * The threbd must be suspended (normblly through bn interruption
     * to the VM) to get this informbtion, bnd
     * it is only vblid until the threbd is resumed bgbin.
     *
     * @pbrbm stbrt the index of the first frbme to retrieve.
     *       Index 0 represents the current frbme.
     * @pbrbm length the number of frbmes to retrieve
     * @return b List of {@link StbckFrbme} with the current frbme first
     * followed by ebch cbller's frbme.
     * @throws IncompbtibleThrebdStbteException if the threbd is
     * not suspended in the tbrget VM
     * @throws IndexOutOfBoundsException if the specified rbnge is not
     * within the rbnge of stbck frbme indicies.
     * Thbt is, the exception is thrown if bny of the following bre true:
     * <pre>    stbrt &lt; 0
     *    stbrt &gt;= {@link #frbmeCount}
     *    length &lt; 0
     *    (stbrt+length) &gt; {@link #frbmeCount}</pre>
     */
    List<StbckFrbme> frbmes(int stbrt, int length)
        throws IncompbtibleThrebdStbteException;

    /**
     * Returns b List contbining bn {@link ObjectReference} for
     * ebch monitor owned by the threbd.
     * A monitor is owned by b threbd if it hbs been entered
     * (vib the synchronized stbtement or entry into b synchronized
     * method) bnd hbs not been relinquished through {@link Object#wbit}.
     * <p>
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnGetOwnedMonitorInfo()}
     * to determine if the operbtion is supported.
     *
     * @return b List of {@link ObjectReference} objects. The list
     * hbs zero length if no monitors bre owned by this threbd.
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support this
     * operbtion.
     * @throws IncompbtibleThrebdStbteException if the threbd is
     * not suspended in the tbrget VM
     */
    List<ObjectReference> ownedMonitors()
        throws IncompbtibleThrebdStbteException;

    /**
     * Returns b List contbining b {@link MonitorInfo} object for
     * ebch monitor owned by the threbd.
     * A monitor is owned by b threbd if it hbs been entered
     * (vib the synchronized stbtement or entry into b synchronized
     * method) bnd hbs not been relinquished through {@link Object#wbit}.
     * <p>
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnGetMonitorFrbmeInfo()}
     * to determine if the operbtion is supported.
     *
     * @return b List of {@link MonitorInfo} objects. The list
     * hbs zero length if no monitors bre owned by this threbd.
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support this
     * operbtion.
     * @throws IncompbtibleThrebdStbteException if the threbd is
     * not suspended in the tbrget VM
     *
     * @since 1.6
     */
    List<MonitorInfo> ownedMonitorsAndFrbmes()
        throws IncompbtibleThrebdStbteException;

    /**
     * Returns bn {@link ObjectReference} for the monitor, if bny,
     * for which this threbd is currently wbiting.
     * The threbd cbn be wbiting for b monitor through entry into b
     * synchronized method, the synchronized stbtement, or
     * {@link Object#wbit}.  The {@link #stbtus} method cbn be used
     * to differentibte between the first two cbses bnd the third.
     * <p>
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnGetCurrentContendedMonitor()}
     * to determine if the operbtion is supported.
     *
     * @return the {@link ObjectReference} corresponding to the
     * contended monitor, or null if it is not wbiting for b monitor.
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support this
     * operbtion.
     * @throws IncompbtibleThrebdStbteException if the threbd is
     * not suspended in the tbrget VM
     */
    ObjectReference currentContendedMonitor() throws IncompbtibleThrebdStbteException;

    /**
     * Pop stbck frbmes.
     * <P>
     * All frbmes up to bnd including the <CODE>frbme</CODE> bre
     * popped off the stbck.
     * The frbme previous to the pbrbmeter <CODE>frbme</CODE>
     * will become the current frbme.
     * <P>
     * After this operbtion, this threbd will be
     * suspended bt the invoke instruction of the tbrget method
     * thbt crebted <CODE>frbme</CODE>.
     * The <CODE>frbme</CODE>'s method cbn be reentered with b step into
     * the instruction.
     * <P>
     * The operbnd stbck is restored, however, bny chbnges
     * to the brguments thbt occurred in the cblled method, rembin.
     * For exbmple, if the method <CODE>foo</CODE>:
     * <PRE>
     *    void foo(int x) {
     *        System.out.println("Foo: " + x);
     *        x = 4;
     *        System.out.println("pop here");
     *    }
     * </PRE>
     * wbs cblled with <CODE>foo(7)</CODE> bnd <CODE>foo</CODE>
     * is popped bt the second <CODE>println</CODE> bnd resumed,
     * it will print: <CODE>Foo: 4</CODE>.
     * <P>
     * Locks bcquired by b popped frbme bre relebsed when it
     * is popped. This bpplies to synchronized methods thbt
     * bre popped, bnd to bny synchronized blocks within them.
     * <P>
     * Finblly blocks bre not executed.
     * <P>
     * No bspect of stbte, other thbn this threbd's execution point bnd
     * locks, is bffected by this cbll.  Specificblly, the vblues of
     * fields bre unchbnged, bs bre externbl resources such bs
     * I/O strebms.  Additionblly, the tbrget progrbm might be
     * plbced in b stbte thbt is impossible with normbl progrbm flow;
     * for exbmple, order of lock bcquisition might be perturbed.
     * Thus the tbrget progrbm mby
     * proceed differently thbn the user would expect.
     * <P>
     * The specified threbd must be suspended.
     * <P>
     * All <code>StbckFrbme</code> objects for this threbd bre
     * invblidbted.
     * <P>
     * No events bre generbted by this method.
     * <P>
     * None of the frbmes through bnd including the frbme for the cbller
     * of <i>frbme</i> mby be nbtive.
     * <P>
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnPopFrbmes() VirtublMbchine.cbnPopFrbmes()}
     * to determine if the operbtion is supported.
     *
     * @pbrbm frbme Stbck frbme to pop.  <CODE>frbme</CODE> is on this
     * threbd's cbll stbck.
     *
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support this
     * operbtion - see
     * {@link VirtublMbchine#cbnPopFrbmes() VirtublMbchine.cbnPopFrbmes()}.
     *
     * @throws IncompbtibleThrebdStbteException if this
     * threbd is not suspended.
     *
     * @throws jbvb.lbng.IllegblArgumentException if <CODE>frbme</CODE>
     * is not on this threbd's cbll stbck.
     *
     * @throws NbtiveMethodException if one of the frbmes thbt would be
     * popped is thbt of b nbtive method or if the frbme previous to
     * <i>frbme</i> is nbtive.
     *
     * @throws InvblidStbckFrbmeException if <CODE>frbme</CODE> hbs become
     * invblid. Once this threbd is resumed, the stbck frbme is
     * no longer vblid.  This exception is blso thrown if there bre no
     * more frbmes.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     *
     * @since 1.4 */
    void popFrbmes(StbckFrbme frbme) throws IncompbtibleThrebdStbteException;


    /**
     * Force b method to return before it rebches b return
     * stbtement.
     * <p>
     * The method which will return ebrly is referred to bs the
     * cblled method. The cblled method is the current method (bs
     * defined by the Frbmes section in the Jbvb Virtubl Mbchine
     * Specificbtion) for the specified threbd bt the time this
     * method is cblled.
     * <p>
     * The threbd must be suspended.
     * The return occurs when execution of Jbvb progrbmming
     * lbngubge code is resumed on this threbd. Between the cbll to
     * this method bnd resumption of threbd execution, the
     * stbte of the stbck is undefined.
     * <p>
     * No further instructions bre executed in the cblled
     * method. Specificblly, finblly blocks bre not executed. Note:
     * this cbn cbuse inconsistent stbtes in the bpplicbtion.
     * <p>
     * A lock bcquired by cblling the cblled method (if it is b
     * synchronized method) bnd locks bcquired by entering
     * synchronized blocks within the cblled method bre
     * relebsed. Note: this does not bpply to nbtive locks or
     * jbvb.util.concurrent.locks locks.
     * <p>
     * Events, such bs MethodExit, bre generbted bs they would be in
     * b normbl return.
     * <p>
     * The cblled method must be b non-nbtive Jbvb progrbmming
     * lbngubge method. Forcing return on b threbd with only one
     * frbme on the stbck cbuses the threbd to exit when resumed.
     * <p>
     * The <code>vblue</code> brgument is the vblue thbt the
     * method is to return.
     * If the return type of the method is void, then vblue must
     * be b  {@link VoidVblue VoidVblue}.
     * Object vblues must be bssignment compbtible with the method return type
     * (This implies thbt the method return type must be lobded through the
     * enclosing clbss's clbss lobder). Primitive vblues must be
     * either bssignment compbtible with the method return type or must be
     * convertible to the vbribble type without loss of informbtion.
     * See JLS section 5.2 for more informbtion on bssignment
     * compbtibility.
     * <p>
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnForceEbrlyReturn()}
     * to determine if the operbtion is supported.
     *
     * @pbrbm vblue the vblue the method is to return.
     *
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support this
     * operbtion - see
     * {@link VirtublMbchine#cbnGetInstbnceInfo() cbnForceEbrlyReturn()}
     *
     * @throws IncompbtibleThrebdStbteException if this
     * threbd is not suspended.
     *
     * @throws NbtiveMethodException if the frbme to be returned from
     * is thbt of b nbtive method.
     *
     * @throws InvblidStbckFrbmeException if there bre no frbmes.
     *
     * @throws InvblidTypeException if the vblue's type does not mbtch
     * the method's return type.
     *
     * @throws ClbssNotLobdedException if the method's return type hbs not yet
     * been lobded through the bppropribte clbss lobder.
     *
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     *
     * @since 1.6
     */
    void forceEbrlyReturn(Vblue vblue) throws InvblidTypeException,
                                              ClbssNotLobdedException,
                                              IncompbtibleThrebdStbteException;

}
