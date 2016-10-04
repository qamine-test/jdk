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

import com.sun.jdi.event.EventQueue;
import com.sun.jdi.request.EventRequestMbnbger;

import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * A virtubl mbchine tbrgeted for debugging.
 * More precisely, b {@link Mirror mirror} representing the
 * composite stbte of the tbrget VM.
 * All other mirrors bre bssocibted with bn instbnce of this
 * interfbce.  Access to bll other mirrors is bchieved
 * directly or indirectly through bn instbnce of this
 * interfbce.
 * Access to globbl VM properties bnd control of VM execution
 * bre supported directly by this interfbce.
 * <P>
 * Instbnces of this interfbce bre crebted by instbnces of
 * {@link com.sun.jdi.connect.Connector}. For exbmple,
 * bn {@link com.sun.jdi.connect.AttbchingConnector AttbchingConnector}
 * bttbches to b tbrget VM bnd returns its virtubl mbchine mirror.
 * A Connector will typicblly crebte b VirtublMbchine by invoking
 * the VirtublMbchineMbnbger's {@link
 * com.sun.jdi.VirtublMbchineMbnbger#crebteVirtublMbchine(Connection)}
 * crebteVirtublMbchine(Connection) method.
 * <p>
 * Note thbt b tbrget VM lbunched by b lbunching connector is not
 * gubrbnteed to be stbble until bfter the {@link com.sun.jdi.event.VMStbrtEvent} hbs been
 * received.
 * <p>
 * Any method on <code>VirtublMbchine</code> which
 * tbkes <code>VirtublMbchine</code> bs bn pbrbmeter mby throw
 * {@link com.sun.jdi.VMDisconnectedException} if the tbrget VM is
 * disconnected bnd the {@link com.sun.jdi.event.VMDisconnectEvent} hbs been or is
 * bvbilbble to be rebd from the {@link com.sun.jdi.event.EventQueue}.
 * <p>
 * Any method on <code>VirtublMbchine</code> which
 * tbkes <code>VirtublMbchine</code> bs bn pbrbmeter mby throw
 * {@link com.sun.jdi.VMOutOfMemoryException} if the tbrget VM hbs run out of memory.
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce VirtublMbchine extends Mirror {

    /**
     * Returns the lobded reference types thbt
     * mbtch b given nbme. The nbme must be fully qublified
     * (for exbmple, jbvb.lbng.String). The returned list
     * will contbin b {@link ReferenceType} for ebch clbss
     * or interfbce found with the given nbme. The sebrch
     * is confined to lobded clbsses only; no bttempt is mbde
     * to lobd b clbss of the given nbme.
     * <P>
     * The returned list will include reference types
     * lobded bt lebst to the point of prepbrbtion bnd
     * types (like brrby) for which prepbrbtion is
     * not defined.
     *
     * @pbrbm clbssNbme the clbss/interfbce nbme to sebrch for
     * @return b list of {@link ReferenceType} objects, ebch
     * mirroring b type in the tbrget VM with the given nbme.
     */
    List<ReferenceType> clbssesByNbme(String clbssNbme);

    /**
     * Returns bll lobded types. For ebch lobded type in the tbrget
     * VM b {@link ReferenceType} will be plbced in the returned list.
     * The list will include ReferenceTypes which mirror clbsses,
     * interfbces, bnd brrby types.
     * <P>
     * The returned list will include reference types
     * lobded bt lebst to the point of prepbrbtion bnd
     * types (like brrby) for which prepbrbtion is
     * not defined.
     *
     * @return b list of {@link ReferenceType} objects, ebch mirroring
     * b lobded type in the tbrget VM.
     */
    List<ReferenceType> bllClbsses();

    /**
     * All clbsses given bre redefined bccording to the
     * definitions supplied.  A method in b redefined clbss
     * is cblled 'equivblent' (to the old version of the
     * method) if
     * <UL>
     * <LI>their bytecodes bre the sbme except for indicies into
     *   the constbnt pool, bnd
     * <LI>the referenced constbnts bre equbl.
     * </UL>
     * Otherwise, the new method is cblled 'non-equivblent'.
     * If b redefined method hbs bctive stbck frbmes, those bctive
     * frbmes continue to run the bytecodes of the previous version of the
     * method.  If the new version of such b method is non-equivblent,
     * then b method from one of these bctive frbmes is cblled 'obsolete' bnd
     * {@link Method#isObsolete Method.isObsolete()}
     * will return true when cblled on one of these methods.
     * If resetting such b frbme is desired, use
     * {@link ThrebdReference#popFrbmes ThrebdReference.popFrbmes(StbckFrbme)}
     * to pop the old obsolete method execution from the stbck.
     * New invocbtions of redefined methods will blwbys invoke the new versions.
     * <p>
     * This function does not cbuse bny initiblizbtion except
     * thbt which would occur under the custombry JVM sembntics.
     * In other words, redefining b clbss does not cbuse
     * its initiblizers to be run. The vblues of preexisting
     * stbtic vbribbles will rembin bs they were prior to the
     * cbll. However, completely uninitiblized (new) stbtic
     * vbribbles will be bssigned their defbult vblue.
     * <p>
     * If b redefined clbss hbs instbnces then bll those
     * instbnces will hbve the fields defined by the redefined
     * clbss bt the completion of the cbll. Preexisting fields
     * will retbin their previous vblues. Any new fields will
     * hbve their defbult vblues; no instbnce initiblizers or
     * constructors bre run.
     * <p>
     * Threbds need not be suspended.
     * <p>
     * No events bre generbted by this function.
     * <p>
     * All brebkpoints in the redefined clbsses bre deleted.
     * <p>
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link #cbnRedefineClbsses() cbnRedefineClbsses()}
     * to determine if the operbtion is supported.
     * Use {@link #cbnAddMethod() cbnAddMethod()}
     * to determine if the redefinition cbn bdd methods.
     * Use {@link #cbnUnrestrictedlyRedefineClbsses() cbnUnrestrictedlyRedefineClbsses()}
     * to determine if the redefinition cbn chbnge the schemb,
     * delete methods, chbnge the clbss hierbrchy, etc.
     *
     * @pbrbm clbssToBytes A mbp from {@link ReferenceType}
     * to brrby of byte.
     * The bytes represent the new clbss definition bnd
     * bre in Jbvb Virtubl Mbchine clbss file formbt.
     *
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support this
     * operbtion.
     * <UL>
     * <LI>If {@link #cbnRedefineClbsses() cbnRedefineClbsses()}
     * is fblse bny cbll of this method will throw this exception.
     * <LI>If {@link #cbnAddMethod() cbnAddMethod()} is fblse
     * bttempting to bdd b method will throw this exception.
     * <LI>If {@link #cbnUnrestrictedlyRedefineClbsses()
     *            cbnUnrestrictedlyRedefineClbsses()}
     * is fblse, bttempting bny of the following will throw
     * this exception
     *   <UL>
     *   <LI>chbnging the schemb (the fields)
     *   <LI>chbnging the hierbrchy (subclbsses, interfbces)
     *   <LI>deleting b method
     *   <LI>chbnging clbss modifiers
     *   <LI>chbnging method modifiers
     *   </UL>
     * </UL>
     *
     * @throws jbvb.lbng.NoClbssDefFoundError if the bytes
     * don't correspond to the reference type (the nbmes
     * don't mbtch).
     *
     * @throws jbvb.lbng.VerifyError if b "verifier" detects
     * thbt b clbss, though well formed, contbins bn internbl
     * inconsistency or security problem.
     *
     * @throws jbvb.lbng.ClbssFormbtError if the bytes
     * do not represent b vblid clbss.
     *
     * @throws jbvb.lbng.ClbssCirculbrityError if b
     * circulbrity hbs been detected while initiblizing b clbss.
     *
     * @throws jbvb.lbng.UnsupportedClbssVersionError if the
     * mbjor bnd minor version numbers in bytes
     * bre not supported by the VM.
     *
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     *
     * @see Method#isObsolete
     * @see ThrebdReference#popFrbmes
     * @see #cbnRedefineClbsses
     * @see #cbnAddMethod
     * @see #cbnUnrestrictedlyRedefineClbsses
     *
     * @since 1.4
     */
    void redefineClbsses(Mbp<? extends ReferenceType,byte[]> clbssToBytes);

    /**
     * Returns b list of the currently running threbds. For ebch
     * running threbd in the tbrget VM, b {@link ThrebdReference}
     * thbt mirrors it is plbced in the list.
     * The returned list contbins threbds crebted through
     * jbvb.lbng.Threbd, bll nbtive threbds bttbched to
     * the tbrget VM through JNI, bnd system threbds crebted
     * by the tbrget VM. Threbd objects thbt hbve
     * not yet been stbrted
     * (see {@link jbvb.lbng.Threbd#stbrt Threbd.stbrt()})
     * bnd threbd objects thbt hbve
     * completed their execution bre not included in the returned list.
     *
     * @return b list of {@link ThrebdReference} objects, one for ebch
     * running threbd in the mirrored VM.
     */
    List<ThrebdReference> bllThrebds();

    /**
     * Suspends the execution of the bpplicbtion running in this
     * virtubl mbchine. All threbds currently running will be suspended.
     * <p>
     * Unlike {@link jbvb.lbng.Threbd#suspend Threbd.suspend()},
     * suspends of both the virtubl mbchine bnd individubl threbds bre
     * counted. Before b threbd will run bgbin, it must be resumed
     * (through {@link #resume} or {@link ThrebdReference#resume})
     * the sbme number of times it hbs been suspended.
     *
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     */
    void suspend();

    /**
     * Continues the execution of the bpplicbtion running in this
     * virtubl mbchine. All threbds bre resumed bs documented in
     * {@link ThrebdReference#resume}.
     *
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     *
     * @see #suspend
     */
    void resume();

    /**
     * Returns ebch threbd group which does not hbve b pbrent. For ebch
     * top level threbd group b {@link ThrebdGroupReference} is plbced in the
     * returned list.
     * <p>
     * This commbnd mby be used bs the first step in building b tree
     * (or trees) of the existing threbd groups.
     *
     * @return b list of {@link ThrebdGroupReference} objects, one for ebch
     * top level threbd group.
     */
    List<ThrebdGroupReference> topLevelThrebdGroups();

    /**
     * Returns the event queue for this virtubl mbchine.
     * A virtubl mbchine hbs only one {@link EventQueue} object, this
     * method will return the sbme instbnce ebch time it
     * is invoked.
     *
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     *
     * @return the {@link EventQueue} for this virtubl mbchine.
     */
    EventQueue eventQueue();

    /**
     * Returns the event request mbnbger for this virtubl mbchine.
     * The {@link EventRequestMbnbger} controls user settbble events
     * such bs brebkpoints.
     * A virtubl mbchine hbs only one {@link EventRequestMbnbger} object,
     * this method will return the sbme instbnce ebch time it
     * is invoked.
     *
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     *
     * @return the {@link EventRequestMbnbger} for this virtubl mbchine.
     */
    EventRequestMbnbger eventRequestMbnbger();

    /**
     * Crebtes b {@link BoolebnVblue} for the given vblue. This vblue
     * cbn be used for setting bnd compbring bgbinst b vblue retrieved
     * from b vbribble or field in this virtubl mbchine.
     *
     * @pbrbm vblue b boolebn for which to crebte the vblue
     * @return the {@link BoolebnVblue} for the given boolebn.
     */
    BoolebnVblue mirrorOf(boolebn vblue);

    /**
     * Crebtes b {@link ByteVblue} for the given vblue. This vblue
     * cbn be used for setting bnd compbring bgbinst b vblue retrieved
     * from b vbribble or field in this virtubl mbchine.
     *
     * @pbrbm vblue b byte for which to crebte the vblue
     * @return the {@link ByteVblue} for the given byte.
     */
    ByteVblue mirrorOf(byte vblue);

    /**
     * Crebtes b {@link ChbrVblue} for the given vblue. This vblue
     * cbn be used for setting bnd compbring bgbinst b vblue retrieved
     * from b vbribble or field in this virtubl mbchine.
     *
     * @pbrbm vblue b chbr for which to crebte the vblue
     * @return the {@link ChbrVblue} for the given chbr.
     */
    ChbrVblue mirrorOf(chbr vblue);

    /**
     * Crebtes b {@link ShortVblue} for the given vblue. This vblue
     * cbn be used for setting bnd compbring bgbinst b vblue retrieved
     * from b vbribble or field in this virtubl mbchine.
     *
     * @pbrbm vblue b short for which to crebte the vblue
     * @return the {@link ShortVblue} for the given short.
     */
    ShortVblue mirrorOf(short vblue);

    /**
     * Crebtes bn {@link IntegerVblue} for the given vblue. This vblue
     * cbn be used for setting bnd compbring bgbinst b vblue retrieved
     * from b vbribble or field in this virtubl mbchine.
     *
     * @pbrbm vblue bn int for which to crebte the vblue
     * @return the {@link IntegerVblue} for the given int.
     */
    IntegerVblue mirrorOf(int vblue);

    /**
     * Crebtes b {@link LongVblue} for the given vblue. This vblue
     * cbn be used for setting bnd compbring bgbinst b vblue retrieved
     * from b vbribble or field in this virtubl mbchine.
     *
     * @pbrbm vblue b long for which to crebte the vblue
     * @return the {@link LongVblue} for the given long.
     */
    LongVblue mirrorOf(long vblue);

    /**
     * Crebtes b {@link FlobtVblue} for the given vblue. This vblue
     * cbn be used for setting bnd compbring bgbinst b vblue retrieved
     * from b vbribble or field in this virtubl mbchine.
     *
     * @pbrbm vblue b flobt for which to crebte the vblue
     * @return the {@link FlobtVblue} for the given flobt.
     */
    FlobtVblue mirrorOf(flobt vblue);

    /**
     * Crebtes b {@link DoubleVblue} for the given vblue. This vblue
     * cbn be used for setting bnd compbring bgbinst b vblue retrieved
     * from b vbribble or field in this virtubl mbchine.
     *
     * @pbrbm vblue b double for which to crebte the vblue
     * @return the {@link DoubleVblue} for the given double.
     */
    DoubleVblue mirrorOf(double vblue);

    /**
     * Crebtes b string in this virtubl mbchine.
     * The crebted string cbn be used for setting bnd compbring bgbinst
     * b string vblue retrieved from b vbribble or field in this
     * virtubl mbchine.
     *
     * @pbrbm vblue the string to be crebted
     * @return b {@link StringReference} thbt mirrors the newly crebted
     * string in the tbrget VM.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only
     * -see {@link VirtublMbchine#cbnBeModified()}.
     */
    StringReference mirrorOf(String vblue);


    /**
     * Crebtes b {@link VoidVblue}.  This vblue
     * cbn be pbssed to {@link ThrebdReference#forceEbrlyReturn}
     * when b void method is to be exited.
     *
     * @return the {@link VoidVblue}.
     */
    VoidVblue mirrorOfVoid();

    /**
     * Returns the {@link jbvb.lbng.Process} object for this
     * virtubl mbchine if lbunched
     * by b {@link com.sun.jdi.connect.LbunchingConnector}
     *
     * @return the {@link jbvb.lbng.Process} object for this virtubl
     * mbchine, or null if it wbs not lbunched by b
     * {@link com.sun.jdi.connect.LbunchingConnector}.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only
     * -see {@link VirtublMbchine#cbnBeModified()}.
     */
    Process process();

    /**
     * Invblidbtes this virtubl mbchine mirror.
     * The communicbtion chbnnel to the tbrget VM is closed, bnd
     * the tbrget VM prepbres to bccept bnother subsequent connection
     * from this debugger or bnother debugger, including the
     * following tbsks:
     * <ul>
     * <li>All event requests bre cbncelled.
     * <li>All threbds suspended by {@link #suspend} or by
     * {@link ThrebdReference#suspend} bre resumed bs mbny
     * times bs necessbry for them to run.
     * <li>Gbrbbge collection is re-enbbled in bll cbses where it wbs
     * disbbled through {@link ObjectReference#disbbleCollection}.
     * </ul>
     * Any current method invocbtions executing in the tbrget VM
     * bre continued bfter the disconnection. Upon completion of bny such
     * method invocbtion, the invoking threbd continues from the
     * locbtion where it wbs originblly stopped.
     * <p>
     * Resources originbting in
     * this VirtublMbchine (ObjectReferences, ReferenceTypes, etc.)
     * will become invblid.
     */
    void dispose();

    /**
     * Cbuses the mirrored VM to terminbte with the given error code.
     * All resources bssocibted with this VirtublMbchine bre freed.
     * If the mirrored VM is remote, the communicbtion chbnnel
     * to it will be closed. Resources originbting in
     * this VirtublMbchine (ObjectReferences, ReferenceTypes, etc.)
     * will become invblid.
     * <p>
     * Threbds running in the mirrored VM bre bbruptly terminbted.
     * A threbd debth exception is not thrown bnd
     * finblly blocks bre not run.
     *
     * @pbrbm exitCode the exit code for the tbrget VM.  On some plbtforms,
     * the exit code might be truncbted, for exbmple, to the lower order 8 bits.
     *
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     */
    void exit(int exitCode);

    /**
     * Determines if the tbrget VM supports wbtchpoints
     * for field modificbtion.
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     */
    boolebn cbnWbtchFieldModificbtion();

    /**
     * Determines if the tbrget VM supports wbtchpoints
     * for field bccess.
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     */
    boolebn cbnWbtchFieldAccess();

    /**
     * Determines if the tbrget VM supports the retrievbl
     * of b method's bytecodes.
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     */
    boolebn cbnGetBytecodes();

    /**
     * Determines if the tbrget VM supports the query
     * of the synthetic bttribute of b method or field.
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     */
    boolebn cbnGetSyntheticAttribute();

    /**
     * Determines if the tbrget VM supports the retrievbl
     * of the monitors owned by b threbd.
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     */
    boolebn cbnGetOwnedMonitorInfo();

    /**
     * Determines if the tbrget VM supports the retrievbl
     * of the monitor for which b threbd is currently wbiting.
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     */
    boolebn cbnGetCurrentContendedMonitor();

    /**
     * Determines if the tbrget VM supports the retrievbl
     * of the monitor informbtion for bn object.
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     */
    boolebn cbnGetMonitorInfo();

    /**
     * Determines if the tbrget VM supports filtering
     * events by specific instbnce object.  For exbmple,
     * see {@link com.sun.jdi.request.BrebkpointRequest#bddInstbnceFilter}.
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     */
    boolebn cbnUseInstbnceFilters();

    /**
     * Determines if the tbrget VM supports bny level
     * of clbss redefinition.
     * @see #redefineClbsses
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.4
     */
    boolebn cbnRedefineClbsses();

    /**
     * Determines if the tbrget VM supports the bddition
     * of methods when performing clbss redefinition.
     * @see #redefineClbsses
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.4
     */
    boolebn cbnAddMethod();

    /**
     * Determines if the tbrget VM supports unrestricted
     * chbnges when performing clbss redefinition.
     * @see #redefineClbsses
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.4
     */
    boolebn cbnUnrestrictedlyRedefineClbsses();

    /**
     * Determines if the tbrget VM supports popping
     * frbmes of b threbds stbck.
     * @see ThrebdReference#popFrbmes
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.4
     */
    boolebn cbnPopFrbmes();

    /**
     * Determines if the tbrget VM supports getting
     * the source debug extension.
     * @see ReferenceType#sourceDebugExtension
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.4
     */
    boolebn cbnGetSourceDebugExtension();

    /**
     * Determines if the tbrget VM supports the crebtion of
     * {@link com.sun.jdi.request.VMDebthRequest}s.
     * @see com.sun.jdi.request.EventRequestMbnbger#crebteVMDebthRequest
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.4
     */
    boolebn cbnRequestVMDebthEvent();

    /**
     * Determines if the tbrget VM supports the inclusion of return vblues
     * in
     * {@link com.sun.jdi.event.MethodExitEvent}s.
     * @see com.sun.jdi.request.EventRequestMbnbger#crebteMethodExitRequest
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.6
     */
    boolebn cbnGetMethodReturnVblues();

    /**
     * Determines if the tbrget VM supports the bccessing of clbss instbnces,
     * instbnce counts, bnd referring objects.
     *
     * @see #instbnceCounts
     * @see ReferenceType#instbnces(long)
     * @see ObjectReference#referringObjects(long)
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.6
     */
    boolebn cbnGetInstbnceInfo();


    /**
     * Determines if the tbrget VM supports the filtering of
     * clbss prepbre events by source nbme.
     *
     * see {@link com.sun.jdi.request.ClbssPrepbreRequest#bddSourceNbmeFilter}.
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.6
     */
    boolebn cbnUseSourceNbmeFilters();

    /**
     * Determines if the tbrget VM supports the forcing of b method to
     * return ebrly.
     *
     * @see ThrebdReference#forceEbrlyReturn(Vblue)
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.6
     */
    boolebn cbnForceEbrlyReturn();

    /**
     * Determines if the tbrget VM is b rebd-only VM.  If b method which
     * would modify the stbte of the VM is cblled on b rebd-only VM,
     * then {@link VMCbnnotBeModifiedException} is thrown.
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.5
     */

    boolebn cbnBeModified();

    /**
     * Determines if the tbrget VM supports the crebtion of
     * {@link com.sun.jdi.request.MonitorContendedEnterRequest}s.
     * {@link com.sun.jdi.request.MonitorContendedEnteredRequest}s.
     * {@link com.sun.jdi.request.MonitorWbitRequest}s.
     * {@link com.sun.jdi.request.MonitorWbitedRequest}s.
     * @see com.sun.jdi.request.EventRequestMbnbger#crebteMonitorContendedEnterRequest
     * @see com.sun.jdi.request.EventRequestMbnbger#crebteMonitorContendedEnteredRequest
     * @see com.sun.jdi.request.EventRequestMbnbger#crebteMonitorWbitRequest
     * @see com.sun.jdi.request.EventRequestMbnbger#crebteMonitorWbitedRequest
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.6
     */

    boolebn cbnRequestMonitorEvents();

    /**
     * Determines if the tbrget VM supports getting which
     * frbme hbs bcquired b monitor.
     * @see com.sun.jdi.ThrebdReference#ownedMonitorsAndFrbmes
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.6
     */

     boolebn cbnGetMonitorFrbmeInfo();


    /**
     * Determines if the tbrget VM supports rebding clbss file
     * mbjor bnd minor versions.
     *
     * @see ReferenceType#mbjorVersion()
     * @see ReferenceType#minorVersion()
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.6
     */
    boolebn cbnGetClbssFileVersion();

    /**
     * Determines if the tbrget VM supports getting constbnt pool
     * informbtion of b clbss.
     *
     * @see ReferenceType#constbntPoolCount()
     * @see ReferenceType#constbntPool()
     *
     * @return <code>true</code> if the febture is supported,
     * <code>fblse</code> otherwise.
     *
     * @since 1.6
     */
    boolebn cbnGetConstbntPool();

    /**
     * Set this VM's defbult strbtum (see {@link Locbtion} for b
     * discussion of strbtb).  Overrides the per-clbss defbult set
     * in the clbss file.
     * <P>
     * Affects locbtion queries (such bs,
     * {@link Locbtion#sourceNbme()})
     * bnd the line boundbries used in
     * single stepping.
     *
     * @pbrbm strbtum the strbtum to set bs VM defbult,
     * or null to use per-clbss defbults.
     *
     * @throws jbvb.lbng.UnsupportedOperbtionException if the
     * tbrget virtubl mbchine does not support this operbtion.
     *
     * @since 1.4
     */
    void setDefbultStrbtum(String strbtum);

    /**
     * Return this VM's defbult strbtum.
     *
     * @see #setDefbultStrbtum(String)
     * @see ReferenceType#defbultStrbtum()
     * @return <code>null</code> (mebning thbt the per-clbss
     * defbult - {@link ReferenceType#defbultStrbtum()} -
     * should be used) unless the defbult strbtum hbs been
     * set with
     * {@link #setDefbultStrbtum(String)}.
     *
     * @since 1.4
     */
    String getDefbultStrbtum();

    /**
     * Returns the number of instbnces of ebch ReferenceType in the 'refTypes'
     * list.
     * Only instbnces thbt bre rebchbble for the purposes of gbrbbge collection
     * bre counted.
     * <p>
     * Not bll tbrget virtubl mbchines support this operbtion.
     * Use {@link VirtublMbchine#cbnGetInstbnceInfo()}
     * to determine if the operbtion is supported.
     *
     * @see ReferenceType#instbnces(long)
     * @see ObjectReference#referringObjects(long)
     * @pbrbm refTypes the list of {@link ReferenceType} objects for which counts
     *        bre to be obtbined.
     *
     * @return bn brrby of <code>long</code> contbining one element for ebch
     *         element in the 'refTypes' list.  Element i of the brrby contbins
     *         the number of instbnces in the tbrget VM of the ReferenceType bt
     *         position i in the 'refTypes' list.
     *         If the 'refTypes' list is empty, b zero-length brrby is returned.
     *         If b ReferenceType in refTypes hbs been gbrbbge collected, zero
     *         is returned for its instbnce count.
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support this
     * operbtion - see
     * {@link VirtublMbchine#cbnGetInstbnceInfo() cbnGetInstbnceInfo()}
     * @throws NullPointerException if the 'refTypes' list is null.
     * @since 1.6
     */
    long[] instbnceCounts(List<? extends ReferenceType> refTypes);

    /**
     * Returns text informbtion on the tbrget VM bnd the
     * debugger support thbt mirrors it. No specific formbt
     * for this informbtion is gubrbnteed.
     * Typicblly, this string contbins version informbtion for the
     * tbrget VM bnd debugger interfbces.
     * More precise informbtion
     * on VM bnd JDI versions is bvbilbble through
     * {@link #version}, {@link VirtublMbchineMbnbger#mbjorInterfbceVersion},
     * bnd {@link VirtublMbchineMbnbger#minorInterfbceVersion}
     *
     * @return the description.
     */
    String description();

    /**
     * Returns the version of the Jbvb Runtime Environment in the tbrget
     * VM bs reported by the property <code>jbvb.version</code>.
     * For obtbining the JDI interfbce version, use
     * {@link VirtublMbchineMbnbger#mbjorInterfbceVersion}
     * bnd {@link VirtublMbchineMbnbger#minorInterfbceVersion}
     *
     * @return the tbrget VM version.
     */
    String version();

    /**
     * Returns the nbme of the tbrget VM bs reported by the
     * property <code>jbvb.vm.nbme</code>.
     *
     * @return the tbrget VM nbme.
     */
    String nbme();

    /** All trbcing is disbbled. */
    int TRACE_NONE        = 0x00000000;
    /** Trbcing enbbled for JDWP pbckets sent to tbrget VM. */
    int TRACE_SENDS       = 0x00000001;
    /** Trbcing enbbled for JDWP pbckets received from tbrget VM. */
    int TRACE_RECEIVES    = 0x00000002;
    /** Trbcing enbbled for internbl event hbndling. */
    int TRACE_EVENTS      = 0x00000004;
    /** Trbcing enbbled for internbl mbnbgment of reference types. */
    int TRACE_REFTYPES    = 0x00000008;
    /** Trbcing enbbled for internbl mbnbgement of object references. */
    int TRACE_OBJREFS      = 0x00000010;
    /** All trbcing is enbbled. */
    int TRACE_ALL         = 0x00ffffff;

    /**
     * Trbces the bctivities performed by the com.sun.jdi implementbtion.
     * All trbce informbtion is output to System.err. The given trbce
     * flbgs bre used to limit the output to only the informbtion
     * desired. The given flbgs bre in effect bnd the corresponding
     * trbce will continue until the next cbll to
     * this method.
     * <p>
     * Output is implementbtion dependent bnd trbce mode mby be ignored.
     *
     * @pbrbm trbceFlbgs identifies which kinds of trbcing to enbble.
     */
    void setDebugTrbceMode(int trbceFlbgs);
}
