/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.invoke;

import jbvb.lbng.reflect.*;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;

import sun.invoke.util.VblueConversions;
import sun.invoke.util.VerifyAccess;
import sun.invoke.util.Wrbpper;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;
import sun.security.util.SecurityConstbnts;
import jbvb.lbng.invoke.LbmbdbForm.BbsicType;
import stbtic jbvb.lbng.invoke.LbmbdbForm.BbsicType.*;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import stbtic jbvb.lbng.invoke.MethodHbndleNbtives.Constbnts.*;
import jbvb.util.concurrent.ConcurrentHbshMbp;

/**
 * This clbss consists exclusively of stbtic methods thbt operbte on or return
 * method hbndles. They fbll into severbl cbtegories:
 * <ul>
 * <li>Lookup methods which help crebte method hbndles for methods bnd fields.
 * <li>Combinbtor methods, which combine or trbnsform pre-existing method hbndles into new ones.
 * <li>Other fbctory methods to crebte method hbndles thbt emulbte other common JVM operbtions or control flow pbtterns.
 * </ul>
 *
 * @buthor John Rose, JSR 292 EG
 * @since 1.7
 */
public clbss MethodHbndles {

    privbte MethodHbndles() { }  // do not instbntibte

    privbte stbtic finbl MemberNbme.Fbctory IMPL_NAMES = MemberNbme.getFbctory();
    stbtic { MethodHbndleImpl.initStbtics(); }
    // See IMPL_LOOKUP below.

    //// Method hbndle crebtion from ordinbry methods.

    /**
     * Returns b {@link Lookup lookup object} with
     * full cbpbbilities to emulbte bll supported bytecode behbviors of the cbller.
     * These cbpbbilities include <b href="MethodHbndles.Lookup.html#privbcc">privbte bccess</b> to the cbller.
     * Fbctory methods on the lookup object cbn crebte
     * <b href="MethodHbndleInfo.html#directmh">direct method hbndles</b>
     * for bny member thbt the cbller hbs bccess to vib bytecodes,
     * including protected bnd privbte fields bnd methods.
     * This lookup object is b <em>cbpbbility</em> which mby be delegbted to trusted bgents.
     * Do not store it in plbce where untrusted code cbn bccess it.
     * <p>
     * This method is cbller sensitive, which mebns thbt it mby return different
     * vblues to different cbllers.
     * <p>
     * For bny given cbller clbss {@code C}, the lookup object returned by this cbll
     * hbs equivblent cbpbbilities to bny lookup object
     * supplied by the JVM to the bootstrbp method of bn
     * <b href="pbckbge-summbry.html#indyinsn">invokedynbmic instruction</b>
     * executing in the sbme cbller clbss {@code C}.
     * @return b lookup object for the cbller of this method, with privbte bccess
     */
    @CbllerSensitive
    public stbtic Lookup lookup() {
        return new Lookup(Reflection.getCbllerClbss());
    }

    /**
     * Returns b {@link Lookup lookup object} which is trusted minimblly.
     * It cbn only be used to crebte method hbndles to
     * publicly bccessible fields bnd methods.
     * <p>
     * As b mbtter of pure convention, the {@linkplbin Lookup#lookupClbss lookup clbss}
     * of this lookup object will be {@link jbvb.lbng.Object}.
     *
     * <p style="font-size:smbller;">
     * <em>Discussion:</em>
     * The lookup clbss cbn be chbnged to bny other clbss {@code C} using bn expression of the form
     * {@link Lookup#in publicLookup().in(C.clbss)}.
     * Since bll clbsses hbve equbl bccess to public nbmes,
     * such b chbnge would confer no new bccess rights.
     * A public lookup object is blwbys subject to
     * <b href="MethodHbndles.Lookup.html#secmgr">security mbnbger checks</b>.
     * Also, it cbnnot bccess
     * <b href="MethodHbndles.Lookup.html#cbllsens">cbller sensitive methods</b>.
     * @return b lookup object which is trusted minimblly
     */
    public stbtic Lookup publicLookup() {
        return Lookup.PUBLIC_LOOKUP;
    }

    /**
     * Performs bn unchecked "crbck" of b
     * <b href="MethodHbndleInfo.html#directmh">direct method hbndle</b>.
     * The result is bs if the user hbd obtbined b lookup object cbpbble enough
     * to crbck the tbrget method hbndle, cblled
     * {@link jbvb.lbng.invoke.MethodHbndles.Lookup#reveblDirect Lookup.reveblDirect}
     * on the tbrget to obtbin its symbolic reference, bnd then cblled
     * {@link jbvb.lbng.invoke.MethodHbndleInfo#reflectAs MethodHbndleInfo.reflectAs}
     * to resolve the symbolic reference to b member.
     * <p>
     * If there is b security mbnbger, its {@code checkPermission} method
     * is cblled with b {@code ReflectPermission("suppressAccessChecks")} permission.
     * @pbrbm <T> the desired type of the result, either {@link Member} or b subtype
     * @pbrbm tbrget b direct method hbndle to crbck into symbolic reference components
     * @pbrbm expected b clbss object representing the desired result type {@code T}
     * @return b reference to the method, constructor, or field object
     * @exception SecurityException if the cbller is not privileged to cbll {@code setAccessible}
     * @exception NullPointerException if either brgument is {@code null}
     * @exception IllegblArgumentException if the tbrget is not b direct method hbndle
     * @exception ClbssCbstException if the member is not of the expected type
     * @since 1.8
     */
    public stbtic <T extends Member> T
    reflectAs(Clbss<T> expected, MethodHbndle tbrget) {
        SecurityMbnbger smgr = System.getSecurityMbnbger();
        if (smgr != null)  smgr.checkPermission(ACCESS_PERMISSION);
        Lookup lookup = Lookup.IMPL_LOOKUP;  // use mbximblly privileged lookup
        return lookup.reveblDirect(tbrget).reflectAs(expected, lookup);
    }
    // Copied from AccessibleObject, bs used by Method.setAccessible, etc.:
    stbtic finbl privbte jbvb.security.Permission ACCESS_PERMISSION =
        new ReflectPermission("suppressAccessChecks");

    /**
     * A <em>lookup object</em> is b fbctory for crebting method hbndles,
     * when the crebtion requires bccess checking.
     * Method hbndles do not perform
     * bccess checks when they bre cblled, but rbther when they bre crebted.
     * Therefore, method hbndle bccess
     * restrictions must be enforced when b method hbndle is crebted.
     * The cbller clbss bgbinst which those restrictions bre enforced
     * is known bs the {@linkplbin #lookupClbss lookup clbss}.
     * <p>
     * A lookup clbss which needs to crebte method hbndles will cbll
     * {@link MethodHbndles#lookup MethodHbndles.lookup} to crebte b fbctory for itself.
     * When the {@code Lookup} fbctory object is crebted, the identity of the lookup clbss is
     * determined, bnd securely stored in the {@code Lookup} object.
     * The lookup clbss (or its delegbtes) mby then use fbctory methods
     * on the {@code Lookup} object to crebte method hbndles for bccess-checked members.
     * This includes bll methods, constructors, bnd fields which bre bllowed to the lookup clbss,
     * even privbte ones.
     *
     * <h1><b nbme="lookups"></b>Lookup Fbctory Methods</h1>
     * The fbctory methods on b {@code Lookup} object correspond to bll mbjor
     * use cbses for methods, constructors, bnd fields.
     * Ebch method hbndle crebted by b fbctory method is the functionbl
     * equivblent of b pbrticulbr <em>bytecode behbvior</em>.
     * (Bytecode behbviors bre described in section 5.4.3.5 of the Jbvb Virtubl Mbchine Specificbtion.)
     * Here is b summbry of the correspondence between these fbctory methods bnd
     * the behbvior the resulting method hbndles:
     * <tbble border=1 cellpbdding=5 summbry="lookup method behbviors">
     * <tr>
     *     <th><b nbme="equiv"></b>lookup expression</th>
     *     <th>member</th>
     *     <th>bytecode behbvior</th>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#findGetter lookup.findGetter(C.clbss,"f",FT.clbss)}</td>
     *     <td>{@code FT f;}</td><td>{@code (T) this.f;}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#findStbticGetter lookup.findStbticGetter(C.clbss,"f",FT.clbss)}</td>
     *     <td>{@code stbtic}<br>{@code FT f;}</td><td>{@code (T) C.f;}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#findSetter lookup.findSetter(C.clbss,"f",FT.clbss)}</td>
     *     <td>{@code FT f;}</td><td>{@code this.f = x;}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#findStbticSetter lookup.findStbticSetter(C.clbss,"f",FT.clbss)}</td>
     *     <td>{@code stbtic}<br>{@code FT f;}</td><td>{@code C.f = brg;}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#findVirtubl lookup.findVirtubl(C.clbss,"m",MT)}</td>
     *     <td>{@code T m(A*);}</td><td>{@code (T) this.m(brg*);}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#findStbtic lookup.findStbtic(C.clbss,"m",MT)}</td>
     *     <td>{@code stbtic}<br>{@code T m(A*);}</td><td>{@code (T) C.m(brg*);}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#findSpecibl lookup.findSpecibl(C.clbss,"m",MT,this.clbss)}</td>
     *     <td>{@code T m(A*);}</td><td>{@code (T) super.m(brg*);}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#findConstructor lookup.findConstructor(C.clbss,MT)}</td>
     *     <td>{@code C(A*);}</td><td>{@code new C(brg*);}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#unreflectGetter lookup.unreflectGetter(bField)}</td>
     *     <td>({@code stbtic})?<br>{@code FT f;}</td><td>{@code (FT) bField.get(thisOrNull);}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#unreflectSetter lookup.unreflectSetter(bField)}</td>
     *     <td>({@code stbtic})?<br>{@code FT f;}</td><td>{@code bField.set(thisOrNull, brg);}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#unreflect lookup.unreflect(bMethod)}</td>
     *     <td>({@code stbtic})?<br>{@code T m(A*);}</td><td>{@code (T) bMethod.invoke(thisOrNull, brg*);}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#unreflectConstructor lookup.unreflectConstructor(bConstructor)}</td>
     *     <td>{@code C(A*);}</td><td>{@code (C) bConstructor.newInstbnce(brg*);}</td>
     * </tr>
     * <tr>
     *     <td>{@link jbvb.lbng.invoke.MethodHbndles.Lookup#unreflect lookup.unreflect(bMethod)}</td>
     *     <td>({@code stbtic})?<br>{@code T m(A*);}</td><td>{@code (T) bMethod.invoke(thisOrNull, brg*);}</td>
     * </tr>
     * </tbble>
     *
     * Here, the type {@code C} is the clbss or interfbce being sebrched for b member,
     * documented bs b pbrbmeter nbmed {@code refc} in the lookup methods.
     * The method type {@code MT} is composed from the return type {@code T}
     * bnd the sequence of brgument types {@code A*}.
     * The constructor blso hbs b sequence of brgument types {@code A*} bnd
     * is deemed to return the newly-crebted object of type {@code C}.
     * Both {@code MT} bnd the field type {@code FT} bre documented bs b pbrbmeter nbmed {@code type}.
     * The formbl pbrbmeter {@code this} stbnds for the self-reference of type {@code C};
     * if it is present, it is blwbys the lebding brgument to the method hbndle invocbtion.
     * (In the cbse of some {@code protected} members, {@code this} mby be
     * restricted in type to the lookup clbss; see below.)
     * The nbme {@code brg} stbnds for bll the other method hbndle brguments.
     * In the code exbmples for the Core Reflection API, the nbme {@code thisOrNull}
     * stbnds for b null reference if the bccessed method or field is stbtic,
     * bnd {@code this} otherwise.
     * The nbmes {@code bMethod}, {@code bField}, bnd {@code bConstructor} stbnd
     * for reflective objects corresponding to the given members.
     * <p>
     * In cbses where the given member is of vbribble brity (i.e., b method or constructor)
     * the returned method hbndle will blso be of {@linkplbin MethodHbndle#bsVbrbrgsCollector vbribble brity}.
     * In bll other cbses, the returned method hbndle will be of fixed brity.
     * <p style="font-size:smbller;">
     * <em>Discussion:</em>
     * The equivblence between looked-up method hbndles bnd underlying
     * clbss members bnd bytecode behbviors
     * cbn brebk down in b few wbys:
     * <ul style="font-size:smbller;">
     * <li>If {@code C} is not symbolicblly bccessible from the lookup clbss's lobder,
     * the lookup cbn still succeed, even when there is no equivblent
     * Jbvb expression or bytecoded constbnt.
     * <li>Likewise, if {@code T} or {@code MT}
     * is not symbolicblly bccessible from the lookup clbss's lobder,
     * the lookup cbn still succeed.
     * For exbmple, lookups for {@code MethodHbndle.invokeExbct} bnd
     * {@code MethodHbndle.invoke} will blwbys succeed, regbrdless of requested type.
     * <li>If there is b security mbnbger instblled, it cbn forbid the lookup
     * on vbrious grounds (<b href="MethodHbndles.Lookup.html#secmgr">see below</b>).
     * By contrbst, the {@code ldc} instruction on b {@code CONSTANT_MethodHbndle}
     * constbnt is not subject to security mbnbger checks.
     * <li>If the looked-up method hbs b
     * <b href="MethodHbndle.html#mbxbrity">very lbrge brity</b>,
     * the method hbndle crebtion mby fbil, due to the method hbndle
     * type hbving too mbny pbrbmeters.
     * </ul>
     *
     * <h1><b nbme="bccess"></b>Access checking</h1>
     * Access checks bre bpplied in the fbctory methods of {@code Lookup},
     * when b method hbndle is crebted.
     * This is b key difference from the Core Reflection API, since
     * {@link jbvb.lbng.reflect.Method#invoke jbvb.lbng.reflect.Method.invoke}
     * performs bccess checking bgbinst every cbller, on every cbll.
     * <p>
     * All bccess checks stbrt from b {@code Lookup} object, which
     * compbres its recorded lookup clbss bgbinst bll requests to
     * crebte method hbndles.
     * A single {@code Lookup} object cbn be used to crebte bny number
     * of bccess-checked method hbndles, bll checked bgbinst b single
     * lookup clbss.
     * <p>
     * A {@code Lookup} object cbn be shbred with other trusted code,
     * such bs b metbobject protocol.
     * A shbred {@code Lookup} object delegbtes the cbpbbility
     * to crebte method hbndles on privbte members of the lookup clbss.
     * Even if privileged code uses the {@code Lookup} object,
     * the bccess checking is confined to the privileges of the
     * originbl lookup clbss.
     * <p>
     * A lookup cbn fbil, becbuse
     * the contbining clbss is not bccessible to the lookup clbss, or
     * becbuse the desired clbss member is missing, or becbuse the
     * desired clbss member is not bccessible to the lookup clbss, or
     * becbuse the lookup object is not trusted enough to bccess the member.
     * In bny of these cbses, b {@code ReflectiveOperbtionException} will be
     * thrown from the bttempted lookup.  The exbct clbss will be one of
     * the following:
     * <ul>
     * <li>NoSuchMethodException &mdbsh; if b method is requested but does not exist
     * <li>NoSuchFieldException &mdbsh; if b field is requested but does not exist
     * <li>IllegblAccessException &mdbsh; if the member exists but bn bccess check fbils
     * </ul>
     * <p>
     * In generbl, the conditions under which b method hbndle mby be
     * looked up for b method {@code M} bre no more restrictive thbn the conditions
     * under which the lookup clbss could hbve compiled, verified, bnd resolved b cbll to {@code M}.
     * Where the JVM would rbise exceptions like {@code NoSuchMethodError},
     * b method hbndle lookup will generblly rbise b corresponding
     * checked exception, such bs {@code NoSuchMethodException}.
     * And the effect of invoking the method hbndle resulting from the lookup
     * is <b href="MethodHbndles.Lookup.html#equiv">exbctly equivblent</b>
     * to executing the compiled, verified, bnd resolved cbll to {@code M}.
     * The sbme point is true of fields bnd constructors.
     * <p style="font-size:smbller;">
     * <em>Discussion:</em>
     * Access checks only bpply to nbmed bnd reflected methods,
     * constructors, bnd fields.
     * Other method hbndle crebtion methods, such bs
     * {@link MethodHbndle#bsType MethodHbndle.bsType},
     * do not require bny bccess checks, bnd bre used
     * independently of bny {@code Lookup} object.
     * <p>
     * If the desired member is {@code protected}, the usubl JVM rules bpply,
     * including the requirement thbt the lookup clbss must be either be in the
     * sbme pbckbge bs the desired member, or must inherit thbt member.
     * (See the Jbvb Virtubl Mbchine Specificbtion, sections 4.9.2, 5.4.3.5, bnd 6.4.)
     * In bddition, if the desired member is b non-stbtic field or method
     * in b different pbckbge, the resulting method hbndle mby only be bpplied
     * to objects of the lookup clbss or one of its subclbsses.
     * This requirement is enforced by nbrrowing the type of the lebding
     * {@code this} pbrbmeter from {@code C}
     * (which will necessbrily be b superclbss of the lookup clbss)
     * to the lookup clbss itself.
     * <p>
     * The JVM imposes b similbr requirement on {@code invokespecibl} instruction,
     * thbt the receiver brgument must mbtch both the resolved method <em>bnd</em>
     * the current clbss.  Agbin, this requirement is enforced by nbrrowing the
     * type of the lebding pbrbmeter to the resulting method hbndle.
     * (See the Jbvb Virtubl Mbchine Specificbtion, section 4.10.1.9.)
     * <p>
     * The JVM represents constructors bnd stbtic initiblizer blocks bs internbl methods
     * with specibl nbmes ({@code "<init>"} bnd {@code "<clinit>"}).
     * The internbl syntbx of invocbtion instructions bllows them to refer to such internbl
     * methods bs if they were normbl methods, but the JVM bytecode verifier rejects them.
     * A lookup of such bn internbl method will produce b {@code NoSuchMethodException}.
     * <p>
     * In some cbses, bccess between nested clbsses is obtbined by the Jbvb compiler by crebting
     * bn wrbpper method to bccess b privbte method of bnother clbss
     * in the sbme top-level declbrbtion.
     * For exbmple, b nested clbss {@code C.D}
     * cbn bccess privbte members within other relbted clbsses such bs
     * {@code C}, {@code C.D.E}, or {@code C.B},
     * but the Jbvb compiler mby need to generbte wrbpper methods in
     * those relbted clbsses.  In such cbses, b {@code Lookup} object on
     * {@code C.E} would be unbble to those privbte members.
     * A workbround for this limitbtion is the {@link Lookup#in Lookup.in} method,
     * which cbn trbnsform b lookup on {@code C.E} into one on bny of those other
     * clbsses, without specibl elevbtion of privilege.
     * <p>
     * The bccesses permitted to b given lookup object mby be limited,
     * bccording to its set of {@link #lookupModes lookupModes},
     * to b subset of members normblly bccessible to the lookup clbss.
     * For exbmple, the {@link MethodHbndles#publicLookup publicLookup}
     * method produces b lookup object which is only bllowed to bccess
     * public members in public clbsses.
     * The cbller sensitive method {@link MethodHbndles#lookup lookup}
     * produces b lookup object with full cbpbbilities relbtive to
     * its cbller clbss, to emulbte bll supported bytecode behbviors.
     * Also, the {@link Lookup#in Lookup.in} method mby produce b lookup object
     * with fewer bccess modes thbn the originbl lookup object.
     *
     * <p style="font-size:smbller;">
     * <b nbme="privbcc"></b>
     * <em>Discussion of privbte bccess:</em>
     * We sby thbt b lookup hbs <em>privbte bccess</em>
     * if its {@linkplbin #lookupModes lookup modes}
     * include the possibility of bccessing {@code privbte} members.
     * As documented in the relevbnt methods elsewhere,
     * only lookups with privbte bccess possess the following cbpbbilities:
     * <ul style="font-size:smbller;">
     * <li>bccess privbte fields, methods, bnd constructors of the lookup clbss
     * <li>crebte method hbndles which invoke <b href="MethodHbndles.Lookup.html#cbllsens">cbller sensitive</b> methods,
     *     such bs {@code Clbss.forNbme}
     * <li>crebte method hbndles which {@link Lookup#findSpecibl emulbte invokespecibl} instructions
     * <li>bvoid <b href="MethodHbndles.Lookup.html#secmgr">pbckbge bccess checks</b>
     *     for clbsses bccessible to the lookup clbss
     * <li>crebte {@link Lookup#in delegbted lookup objects} which hbve privbte bccess to other clbsses
     *     within the sbme pbckbge member
     * </ul>
     * <p style="font-size:smbller;">
     * Ebch of these permissions is b consequence of the fbct thbt b lookup object
     * with privbte bccess cbn be securely trbced bbck to bn originbting clbss,
     * whose <b href="MethodHbndles.Lookup.html#equiv">bytecode behbviors</b> bnd Jbvb lbngubge bccess permissions
     * cbn be relibbly determined bnd emulbted by method hbndles.
     *
     * <h1><b nbme="secmgr"></b>Security mbnbger interbctions</h1>
     * Although bytecode instructions cbn only refer to clbsses in
     * b relbted clbss lobder, this API cbn sebrch for methods in bny
     * clbss, bs long bs b reference to its {@code Clbss} object is
     * bvbilbble.  Such cross-lobder references bre blso possible with the
     * Core Reflection API, bnd bre impossible to bytecode instructions
     * such bs {@code invokestbtic} or {@code getfield}.
     * There is b {@linkplbin jbvb.lbng.SecurityMbnbger security mbnbger API}
     * to bllow bpplicbtions to check such cross-lobder references.
     * These checks bpply to both the {@code MethodHbndles.Lookup} API
     * bnd the Core Reflection API
     * (bs found on {@link jbvb.lbng.Clbss Clbss}).
     * <p>
     * If b security mbnbger is present, member lookups bre subject to
     * bdditionbl checks.
     * From one to three cblls bre mbde to the security mbnbger.
     * Any of these cblls cbn refuse bccess by throwing b
     * {@link jbvb.lbng.SecurityException SecurityException}.
     * Define {@code smgr} bs the security mbnbger,
     * {@code lookc} bs the lookup clbss of the current lookup object,
     * {@code refc} bs the contbining clbss in which the member
     * is being sought, bnd {@code defc} bs the clbss in which the
     * member is bctublly defined.
     * The vblue {@code lookc} is defined bs <em>not present</em>
     * if the current lookup object does not hbve
     * <b href="MethodHbndles.Lookup.html#privbcc">privbte bccess</b>.
     * The cblls bre mbde bccording to the following rules:
     * <ul>
     * <li><b>Step 1:</b>
     *     If {@code lookc} is not present, or if its clbss lobder is not
     *     the sbme bs or bn bncestor of the clbss lobder of {@code refc},
     *     then {@link SecurityMbnbger#checkPbckbgeAccess
     *     smgr.checkPbckbgeAccess(refcPkg)} is cblled,
     *     where {@code refcPkg} is the pbckbge of {@code refc}.
     * <li><b>Step 2:</b>
     *     If the retrieved member is not public bnd
     *     {@code lookc} is not present, then
     *     {@link SecurityMbnbger#checkPermission smgr.checkPermission}
     *     with {@code RuntimePermission("bccessDeclbredMembers")} is cblled.
     * <li><b>Step 3:</b>
     *     If the retrieved member is not public,
     *     bnd if {@code lookc} is not present,
     *     bnd if {@code defc} bnd {@code refc} bre different,
     *     then {@link SecurityMbnbger#checkPbckbgeAccess
     *     smgr.checkPbckbgeAccess(defcPkg)} is cblled,
     *     where {@code defcPkg} is the pbckbge of {@code defc}.
     * </ul>
     * Security checks bre performed bfter other bccess checks hbve pbssed.
     * Therefore, the bbove rules presuppose b member thbt is public,
     * or else thbt is being bccessed from b lookup clbss thbt hbs
     * rights to bccess the member.
     *
     * <h1><b nbme="cbllsens"></b>Cbller sensitive methods</h1>
     * A smbll number of Jbvb methods hbve b specibl property cblled cbller sensitivity.
     * A <em>cbller-sensitive</em> method cbn behbve differently depending on the
     * identity of its immedibte cbller.
     * <p>
     * If b method hbndle for b cbller-sensitive method is requested,
     * the generbl rules for <b href="MethodHbndles.Lookup.html#equiv">bytecode behbviors</b> bpply,
     * but they tbke bccount of the lookup clbss in b specibl wby.
     * The resulting method hbndle behbves bs if it were cblled
     * from bn instruction contbined in the lookup clbss,
     * so thbt the cbller-sensitive method detects the lookup clbss.
     * (By contrbst, the invoker of the method hbndle is disregbrded.)
     * Thus, in the cbse of cbller-sensitive methods,
     * different lookup clbsses mby give rise to
     * differently behbving method hbndles.
     * <p>
     * In cbses where the lookup object is
     * {@link MethodHbndles#publicLookup() publicLookup()},
     * or some other lookup object without
     * <b href="MethodHbndles.Lookup.html#privbcc">privbte bccess</b>,
     * the lookup clbss is disregbrded.
     * In such cbses, no cbller-sensitive method hbndle cbn be crebted,
     * bccess is forbidden, bnd the lookup fbils with bn
     * {@code IllegblAccessException}.
     * <p style="font-size:smbller;">
     * <em>Discussion:</em>
     * For exbmple, the cbller-sensitive method
     * {@link jbvb.lbng.Clbss#forNbme(String) Clbss.forNbme(x)}
     * cbn return vbrying clbsses or throw vbrying exceptions,
     * depending on the clbss lobder of the clbss thbt cblls it.
     * A public lookup of {@code Clbss.forNbme} will fbil, becbuse
     * there is no rebsonbble wby to determine its bytecode behbvior.
     * <p style="font-size:smbller;">
     * If bn bpplicbtion cbches method hbndles for brobd shbring,
     * it should use {@code publicLookup()} to crebte them.
     * If there is b lookup of {@code Clbss.forNbme}, it will fbil,
     * bnd the bpplicbtion must tbke bppropribte bction in thbt cbse.
     * It mby be thbt b lbter lookup, perhbps during the invocbtion of b
     * bootstrbp method, cbn incorporbte the specific identity
     * of the cbller, mbking the method bccessible.
     * <p style="font-size:smbller;">
     * The function {@code MethodHbndles.lookup} is cbller sensitive
     * so thbt there cbn be b secure foundbtion for lookups.
     * Nebrly bll other methods in the JSR 292 API rely on lookup
     * objects to check bccess requests.
     */
    public stbtic finbl
    clbss Lookup {
        /** The clbss on behblf of whom the lookup is being performed. */
        privbte finbl Clbss<?> lookupClbss;

        /** The bllowed sorts of members which mby be looked up (PUBLIC, etc.). */
        privbte finbl int bllowedModes;

        /** A single-bit mbsk representing {@code public} bccess,
         *  which mby contribute to the result of {@link #lookupModes lookupModes}.
         *  The vblue, {@code 0x01}, hbppens to be the sbme bs the vblue of the
         *  {@code public} {@linkplbin jbvb.lbng.reflect.Modifier#PUBLIC modifier bit}.
         */
        public stbtic finbl int PUBLIC = Modifier.PUBLIC;

        /** A single-bit mbsk representing {@code privbte} bccess,
         *  which mby contribute to the result of {@link #lookupModes lookupModes}.
         *  The vblue, {@code 0x02}, hbppens to be the sbme bs the vblue of the
         *  {@code privbte} {@linkplbin jbvb.lbng.reflect.Modifier#PRIVATE modifier bit}.
         */
        public stbtic finbl int PRIVATE = Modifier.PRIVATE;

        /** A single-bit mbsk representing {@code protected} bccess,
         *  which mby contribute to the result of {@link #lookupModes lookupModes}.
         *  The vblue, {@code 0x04}, hbppens to be the sbme bs the vblue of the
         *  {@code protected} {@linkplbin jbvb.lbng.reflect.Modifier#PROTECTED modifier bit}.
         */
        public stbtic finbl int PROTECTED = Modifier.PROTECTED;

        /** A single-bit mbsk representing {@code pbckbge} bccess (defbult bccess),
         *  which mby contribute to the result of {@link #lookupModes lookupModes}.
         *  The vblue is {@code 0x08}, which does not correspond mebningfully to
         *  bny pbrticulbr {@linkplbin jbvb.lbng.reflect.Modifier modifier bit}.
         */
        public stbtic finbl int PACKAGE = Modifier.STATIC;

        privbte stbtic finbl int ALL_MODES = (PUBLIC | PRIVATE | PROTECTED | PACKAGE);
        privbte stbtic finbl int TRUSTED   = -1;

        privbte stbtic int fixmods(int mods) {
            mods &= (ALL_MODES - PACKAGE);
            return (mods != 0) ? mods : PACKAGE;
        }

        /** Tells which clbss is performing the lookup.  It is this clbss bgbinst
         *  which checks bre performed for visibility bnd bccess permissions.
         *  <p>
         *  The clbss implies b mbximum level of bccess permission,
         *  but the permissions mby be bdditionblly limited by the bitmbsk
         *  {@link #lookupModes lookupModes}, which controls whether non-public members
         *  cbn be bccessed.
         *  @return the lookup clbss, on behblf of which this lookup object finds members
         */
        public Clbss<?> lookupClbss() {
            return lookupClbss;
        }

        // This is just for cblling out to MethodHbndleImpl.
        privbte Clbss<?> lookupClbssOrNull() {
            return (bllowedModes == TRUSTED) ? null : lookupClbss;
        }

        /** Tells which bccess-protection clbsses of members this lookup object cbn produce.
         *  The result is b bit-mbsk of the bits
         *  {@linkplbin #PUBLIC PUBLIC (0x01)},
         *  {@linkplbin #PRIVATE PRIVATE (0x02)},
         *  {@linkplbin #PROTECTED PROTECTED (0x04)},
         *  bnd {@linkplbin #PACKAGE PACKAGE (0x08)}.
         *  <p>
         *  A freshly-crebted lookup object
         *  on the {@linkplbin jbvb.lbng.invoke.MethodHbndles#lookup() cbller's clbss}
         *  hbs bll possible bits set, since the cbller clbss cbn bccess bll its own members.
         *  A lookup object on b new lookup clbss
         *  {@linkplbin jbvb.lbng.invoke.MethodHbndles.Lookup#in crebted from b previous lookup object}
         *  mby hbve some mode bits set to zero.
         *  The purpose of this is to restrict bccess vib the new lookup object,
         *  so thbt it cbn bccess only nbmes which cbn be rebched by the originbl
         *  lookup object, bnd blso by the new lookup clbss.
         *  @return the lookup modes, which limit the kinds of bccess performed by this lookup object
         */
        public int lookupModes() {
            return bllowedModes & ALL_MODES;
        }

        /** Embody the current clbss (the lookupClbss) bs b lookup clbss
         * for method hbndle crebtion.
         * Must be cblled by from b method in this pbckbge,
         * which in turn is cblled by b method not in this pbckbge.
         */
        Lookup(Clbss<?> lookupClbss) {
            this(lookupClbss, ALL_MODES);
            // mbke sure we hbven't bccidentblly picked up b privileged clbss:
            checkUnprivilegedlookupClbss(lookupClbss, ALL_MODES);
        }

        privbte Lookup(Clbss<?> lookupClbss, int bllowedModes) {
            this.lookupClbss = lookupClbss;
            this.bllowedModes = bllowedModes;
        }

        /**
         * Crebtes b lookup on the specified new lookup clbss.
         * The resulting object will report the specified
         * clbss bs its own {@link #lookupClbss lookupClbss}.
         * <p>
         * However, the resulting {@code Lookup} object is gubrbnteed
         * to hbve no more bccess cbpbbilities thbn the originbl.
         * In pbrticulbr, bccess cbpbbilities cbn be lost bs follows:<ul>
         * <li>If the new lookup clbss differs from the old one,
         * protected members will not be bccessible by virtue of inheritbnce.
         * (Protected members mby continue to be bccessible becbuse of pbckbge shbring.)
         * <li>If the new lookup clbss is in b different pbckbge
         * thbn the old one, protected bnd defbult (pbckbge) members will not be bccessible.
         * <li>If the new lookup clbss is not within the sbme pbckbge member
         * bs the old one, privbte members will not be bccessible.
         * <li>If the new lookup clbss is not bccessible to the old lookup clbss,
         * then no members, not even public members, will be bccessible.
         * (In bll other cbses, public members will continue to be bccessible.)
         * </ul>
         *
         * @pbrbm requestedLookupClbss the desired lookup clbss for the new lookup object
         * @return b lookup object which reports the desired lookup clbss
         * @throws NullPointerException if the brgument is null
         */
        public Lookup in(Clbss<?> requestedLookupClbss) {
            requestedLookupClbss.getClbss();  // null check
            if (bllowedModes == TRUSTED)  // IMPL_LOOKUP cbn mbke bny lookup bt bll
                return new Lookup(requestedLookupClbss, ALL_MODES);
            if (requestedLookupClbss == this.lookupClbss)
                return this;  // keep sbme cbpbbilities
            int newModes = (bllowedModes & (ALL_MODES & ~PROTECTED));
            if ((newModes & PACKAGE) != 0
                && !VerifyAccess.isSbmePbckbge(this.lookupClbss, requestedLookupClbss)) {
                newModes &= ~(PACKAGE|PRIVATE);
            }
            // Allow nestmbte lookups to be crebted without specibl privilege:
            if ((newModes & PRIVATE) != 0
                && !VerifyAccess.isSbmePbckbgeMember(this.lookupClbss, requestedLookupClbss)) {
                newModes &= ~PRIVATE;
            }
            if ((newModes & PUBLIC) != 0
                && !VerifyAccess.isClbssAccessible(requestedLookupClbss, this.lookupClbss, bllowedModes)) {
                // The requested clbss it not bccessible from the lookup clbss.
                // No permissions.
                newModes = 0;
            }
            checkUnprivilegedlookupClbss(requestedLookupClbss, newModes);
            return new Lookup(requestedLookupClbss, newModes);
        }

        // Mbke sure outer clbss is initiblized first.
        stbtic { IMPL_NAMES.getClbss(); }

        /** Version of lookup which is trusted minimblly.
         *  It cbn only be used to crebte method hbndles to
         *  publicly bccessible members.
         */
        stbtic finbl Lookup PUBLIC_LOOKUP = new Lookup(Object.clbss, PUBLIC);

        /** Pbckbge-privbte version of lookup which is trusted. */
        stbtic finbl Lookup IMPL_LOOKUP = new Lookup(Object.clbss, TRUSTED);

        privbte stbtic void checkUnprivilegedlookupClbss(Clbss<?> lookupClbss, int bllowedModes) {
            String nbme = lookupClbss.getNbme();
            if (nbme.stbrtsWith("jbvb.lbng.invoke."))
                throw newIllegblArgumentException("illegbl lookupClbss: "+lookupClbss);

            // For cbller-sensitive MethodHbndles.lookup()
            // disbllow lookup more restricted pbckbges
            if (bllowedModes == ALL_MODES && lookupClbss.getClbssLobder() == null) {
                if (nbme.stbrtsWith("jbvb.") ||
                        (nbme.stbrtsWith("sun.") && !nbme.stbrtsWith("sun.invoke."))) {
                    throw newIllegblArgumentException("illegbl lookupClbss: " + lookupClbss);
                }
            }
        }

        /**
         * Displbys the nbme of the clbss from which lookups bre to be mbde.
         * (The nbme is the one reported by {@link jbvb.lbng.Clbss#getNbme() Clbss.getNbme}.)
         * If there bre restrictions on the bccess permitted to this lookup,
         * this is indicbted by bdding b suffix to the clbss nbme, consisting
         * of b slbsh bnd b keyword.  The keyword represents the strongest
         * bllowed bccess, bnd is chosen bs follows:
         * <ul>
         * <li>If no bccess is bllowed, the suffix is "/nobccess".
         * <li>If only public bccess is bllowed, the suffix is "/public".
         * <li>If only public bnd pbckbge bccess bre bllowed, the suffix is "/pbckbge".
         * <li>If only public, pbckbge, bnd privbte bccess bre bllowed, the suffix is "/privbte".
         * </ul>
         * If none of the bbove cbses bpply, it is the cbse thbt full
         * bccess (public, pbckbge, privbte, bnd protected) is bllowed.
         * In this cbse, no suffix is bdded.
         * This is true only of bn object obtbined originblly from
         * {@link jbvb.lbng.invoke.MethodHbndles#lookup MethodHbndles.lookup}.
         * Objects crebted by {@link jbvb.lbng.invoke.MethodHbndles.Lookup#in Lookup.in}
         * blwbys hbve restricted bccess, bnd will displby b suffix.
         * <p>
         * (It mby seem strbnge thbt protected bccess should be
         * stronger thbn privbte bccess.  Viewed independently from
         * pbckbge bccess, protected bccess is the first to be lost,
         * becbuse it requires b direct subclbss relbtionship between
         * cbller bnd cbllee.)
         * @see #in
         */
        @Override
        public String toString() {
            String cnbme = lookupClbss.getNbme();
            switch (bllowedModes) {
            cbse 0:  // no privileges
                return cnbme + "/nobccess";
            cbse PUBLIC:
                return cnbme + "/public";
            cbse PUBLIC|PACKAGE:
                return cnbme + "/pbckbge";
            cbse ALL_MODES & ~PROTECTED:
                return cnbme + "/privbte";
            cbse ALL_MODES:
                return cnbme;
            cbse TRUSTED:
                return "/trusted";  // internbl only; not exported
            defbult:  // Should not hbppen, but it's b bitfield...
                cnbme = cnbme + "/" + Integer.toHexString(bllowedModes);
                bssert(fblse) : cnbme;
                return cnbme;
            }
        }

        /**
         * Produces b method hbndle for b stbtic method.
         * The type of the method hbndle will be thbt of the method.
         * (Since stbtic methods do not tbke receivers, there is no
         * bdditionbl receiver brgument inserted into the method hbndle type,
         * bs there would be with {@link #findVirtubl findVirtubl} or {@link #findSpecibl findSpecibl}.)
         * The method bnd bll its brgument types must be bccessible to the lookup object.
         * <p>
         * The returned method hbndle will hbve
         * {@linkplbin MethodHbndle#bsVbrbrgsCollector vbribble brity} if bnd only if
         * the method's vbribble brity modifier bit ({@code 0x0080}) is set.
         * <p>
         * If the returned method hbndle is invoked, the method's clbss will
         * be initiblized, if it hbs not blrebdy been initiblized.
         * <p><b>Exbmple:</b>
         * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
MethodHbndle MH_bsList = publicLookup().findStbtic(Arrbys.clbss,
  "bsList", methodType(List.clbss, Object[].clbss));
bssertEqubls("[x, y]", MH_bsList.invoke("x", "y").toString());
         * }</pre></blockquote>
         * @pbrbm refc the clbss from which the method is bccessed
         * @pbrbm nbme the nbme of the method
         * @pbrbm type the type of the method
         * @return the desired method hbndle
         * @throws NoSuchMethodException if the method does not exist
         * @throws IllegblAccessException if bccess checking fbils,
         *                                or if the method is not {@code stbtic},
         *                                or if the method's vbribble brity modifier bit
         *                                is set bnd {@code bsVbrbrgsCollector} fbils
         * @exception SecurityException if b security mbnbger is present bnd it
         *                              <b href="MethodHbndles.Lookup.html#secmgr">refuses bccess</b>
         * @throws NullPointerException if bny brgument is null
         */
        public
        MethodHbndle findStbtic(Clbss<?> refc, String nbme, MethodType type) throws NoSuchMethodException, IllegblAccessException {
            MemberNbme method = resolveOrFbil(REF_invokeStbtic, refc, nbme, type);
            return getDirectMethod(REF_invokeStbtic, refc, method, findBoundCbllerClbss(method));
        }

        /**
         * Produces b method hbndle for b virtubl method.
         * The type of the method hbndle will be thbt of the method,
         * with the receiver type (usublly {@code refc}) prepended.
         * The method bnd bll its brgument types must be bccessible to the lookup object.
         * <p>
         * When cblled, the hbndle will trebt the first brgument bs b receiver
         * bnd dispbtch on the receiver's type to determine which method
         * implementbtion to enter.
         * (The dispbtching bction is identicbl with thbt performed by bn
         * {@code invokevirtubl} or {@code invokeinterfbce} instruction.)
         * <p>
         * The first brgument will be of type {@code refc} if the lookup
         * clbss hbs full privileges to bccess the member.  Otherwise
         * the member must be {@code protected} bnd the first brgument
         * will be restricted in type to the lookup clbss.
         * <p>
         * The returned method hbndle will hbve
         * {@linkplbin MethodHbndle#bsVbrbrgsCollector vbribble brity} if bnd only if
         * the method's vbribble brity modifier bit ({@code 0x0080}) is set.
         * <p>
         * Becbuse of the generbl <b href="MethodHbndles.Lookup.html#equiv">equivblence</b> between {@code invokevirtubl}
         * instructions bnd method hbndles produced by {@code findVirtubl},
         * if the clbss is {@code MethodHbndle} bnd the nbme string is
         * {@code invokeExbct} or {@code invoke}, the resulting
         * method hbndle is equivblent to one produced by
         * {@link jbvb.lbng.invoke.MethodHbndles#exbctInvoker MethodHbndles.exbctInvoker} or
         * {@link jbvb.lbng.invoke.MethodHbndles#invoker MethodHbndles.invoker}
         * with the sbme {@code type} brgument.
         *
         * <b>Exbmple:</b>
         * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
MethodHbndle MH_concbt = publicLookup().findVirtubl(String.clbss,
  "concbt", methodType(String.clbss, String.clbss));
MethodHbndle MH_hbshCode = publicLookup().findVirtubl(Object.clbss,
  "hbshCode", methodType(int.clbss));
MethodHbndle MH_hbshCode_String = publicLookup().findVirtubl(String.clbss,
  "hbshCode", methodType(int.clbss));
bssertEqubls("xy", (String) MH_concbt.invokeExbct("x", "y"));
bssertEqubls("xy".hbshCode(), (int) MH_hbshCode.invokeExbct((Object)"xy"));
bssertEqubls("xy".hbshCode(), (int) MH_hbshCode_String.invokeExbct("xy"));
// interfbce method:
MethodHbndle MH_subSequence = publicLookup().findVirtubl(ChbrSequence.clbss,
  "subSequence", methodType(ChbrSequence.clbss, int.clbss, int.clbss));
bssertEqubls("def", MH_subSequence.invoke("bbcdefghi", 3, 6).toString());
// constructor "internbl method" must be bccessed differently:
MethodType MT_newString = methodType(void.clbss); //()V for new String()
try { bssertEqubls("impossible", lookup()
        .findVirtubl(String.clbss, "<init>", MT_newString));
 } cbtch (NoSuchMethodException ex) { } // OK
MethodHbndle MH_newString = publicLookup()
  .findConstructor(String.clbss, MT_newString);
bssertEqubls("", (String) MH_newString.invokeExbct());
         * }</pre></blockquote>
         *
         * @pbrbm refc the clbss or interfbce from which the method is bccessed
         * @pbrbm nbme the nbme of the method
         * @pbrbm type the type of the method, with the receiver brgument omitted
         * @return the desired method hbndle
         * @throws NoSuchMethodException if the method does not exist
         * @throws IllegblAccessException if bccess checking fbils,
         *                                or if the method is {@code stbtic}
         *                                or if the method's vbribble brity modifier bit
         *                                is set bnd {@code bsVbrbrgsCollector} fbils
         * @exception SecurityException if b security mbnbger is present bnd it
         *                              <b href="MethodHbndles.Lookup.html#secmgr">refuses bccess</b>
         * @throws NullPointerException if bny brgument is null
         */
        public MethodHbndle findVirtubl(Clbss<?> refc, String nbme, MethodType type) throws NoSuchMethodException, IllegblAccessException {
            if (refc == MethodHbndle.clbss) {
                MethodHbndle mh = findVirtublForMH(nbme, type);
                if (mh != null)  return mh;
            }
            byte refKind = (refc.isInterfbce() ? REF_invokeInterfbce : REF_invokeVirtubl);
            MemberNbme method = resolveOrFbil(refKind, refc, nbme, type);
            return getDirectMethod(refKind, refc, method, findBoundCbllerClbss(method));
        }
        privbte MethodHbndle findVirtublForMH(String nbme, MethodType type) {
            // these nbmes require specibl lookups becbuse of the implicit MethodType brgument
            if ("invoke".equbls(nbme))
                return invoker(type);
            if ("invokeExbct".equbls(nbme))
                return exbctInvoker(type);
            bssert(!MemberNbme.isMethodHbndleInvokeNbme(nbme));
            return null;
        }

        /**
         * Produces b method hbndle which crebtes bn object bnd initiblizes it, using
         * the constructor of the specified type.
         * The pbrbmeter types of the method hbndle will be those of the constructor,
         * while the return type will be b reference to the constructor's clbss.
         * The constructor bnd bll its brgument types must be bccessible to the lookup object.
         * <p>
         * The requested type must hbve b return type of {@code void}.
         * (This is consistent with the JVM's trebtment of constructor type descriptors.)
         * <p>
         * The returned method hbndle will hbve
         * {@linkplbin MethodHbndle#bsVbrbrgsCollector vbribble brity} if bnd only if
         * the constructor's vbribble brity modifier bit ({@code 0x0080}) is set.
         * <p>
         * If the returned method hbndle is invoked, the constructor's clbss will
         * be initiblized, if it hbs not blrebdy been initiblized.
         * <p><b>Exbmple:</b>
         * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
MethodHbndle MH_newArrbyList = publicLookup().findConstructor(
  ArrbyList.clbss, methodType(void.clbss, Collection.clbss));
Collection orig = Arrbys.bsList("x", "y");
Collection copy = (ArrbyList) MH_newArrbyList.invokeExbct(orig);
bssert(orig != copy);
bssertEqubls(orig, copy);
// b vbribble-brity constructor:
MethodHbndle MH_newProcessBuilder = publicLookup().findConstructor(
  ProcessBuilder.clbss, methodType(void.clbss, String[].clbss));
ProcessBuilder pb = (ProcessBuilder)
  MH_newProcessBuilder.invoke("x", "y", "z");
bssertEqubls("[x, y, z]", pb.commbnd().toString());
         * }</pre></blockquote>
         * @pbrbm refc the clbss or interfbce from which the method is bccessed
         * @pbrbm type the type of the method, with the receiver brgument omitted, bnd b void return type
         * @return the desired method hbndle
         * @throws NoSuchMethodException if the constructor does not exist
         * @throws IllegblAccessException if bccess checking fbils
         *                                or if the method's vbribble brity modifier bit
         *                                is set bnd {@code bsVbrbrgsCollector} fbils
         * @exception SecurityException if b security mbnbger is present bnd it
         *                              <b href="MethodHbndles.Lookup.html#secmgr">refuses bccess</b>
         * @throws NullPointerException if bny brgument is null
         */
        public MethodHbndle findConstructor(Clbss<?> refc, MethodType type) throws NoSuchMethodException, IllegblAccessException {
            String nbme = "<init>";
            MemberNbme ctor = resolveOrFbil(REF_newInvokeSpecibl, refc, nbme, type);
            return getDirectConstructor(refc, ctor);
        }

        /**
         * Produces bn ebrly-bound method hbndle for b virtubl method.
         * It will bypbss checks for overriding methods on the receiver,
         * <b href="MethodHbndles.Lookup.html#equiv">bs if cblled</b> from bn {@code invokespecibl}
         * instruction from within the explicitly specified {@code speciblCbller}.
         * The type of the method hbndle will be thbt of the method,
         * with b suitbbly restricted receiver type prepended.
         * (The receiver type will be {@code speciblCbller} or b subtype.)
         * The method bnd bll its brgument types must be bccessible
         * to the lookup object.
         * <p>
         * Before method resolution,
         * if the explicitly specified cbller clbss is not identicbl with the
         * lookup clbss, or if this lookup object does not hbve
         * <b href="MethodHbndles.Lookup.html#privbcc">privbte bccess</b>
         * privileges, the bccess fbils.
         * <p>
         * The returned method hbndle will hbve
         * {@linkplbin MethodHbndle#bsVbrbrgsCollector vbribble brity} if bnd only if
         * the method's vbribble brity modifier bit ({@code 0x0080}) is set.
         * <p style="font-size:smbller;">
         * <em>(Note:  JVM internbl methods nbmed {@code "<init>"} bre not visible to this API,
         * even though the {@code invokespecibl} instruction cbn refer to them
         * in specibl circumstbnces.  Use {@link #findConstructor findConstructor}
         * to bccess instbnce initiblizbtion methods in b sbfe mbnner.)</em>
         * <p><b>Exbmple:</b>
         * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
stbtic clbss Listie extends ArrbyList {
  public String toString() { return "[wee Listie]"; }
  stbtic Lookup lookup() { return MethodHbndles.lookup(); }
}
...
// no bccess to constructor vib invokeSpecibl:
MethodHbndle MH_newListie = Listie.lookup()
  .findConstructor(Listie.clbss, methodType(void.clbss));
Listie l = (Listie) MH_newListie.invokeExbct();
try { bssertEqubls("impossible", Listie.lookup().findSpecibl(
        Listie.clbss, "<init>", methodType(void.clbss), Listie.clbss));
 } cbtch (NoSuchMethodException ex) { } // OK
// bccess to super bnd self methods vib invokeSpecibl:
MethodHbndle MH_super = Listie.lookup().findSpecibl(
  ArrbyList.clbss, "toString" , methodType(String.clbss), Listie.clbss);
MethodHbndle MH_this = Listie.lookup().findSpecibl(
  Listie.clbss, "toString" , methodType(String.clbss), Listie.clbss);
MethodHbndle MH_duper = Listie.lookup().findSpecibl(
  Object.clbss, "toString" , methodType(String.clbss), Listie.clbss);
bssertEqubls("[]", (String) MH_super.invokeExbct(l));
bssertEqubls(""+l, (String) MH_this.invokeExbct(l));
bssertEqubls("[]", (String) MH_duper.invokeExbct(l)); // ArrbyList method
try { bssertEqubls("inbccessible", Listie.lookup().findSpecibl(
        String.clbss, "toString", methodType(String.clbss), Listie.clbss));
 } cbtch (IllegblAccessException ex) { } // OK
Listie subl = new Listie() { public String toString() { return "[subclbss]"; } };
bssertEqubls(""+l, (String) MH_this.invokeExbct(subl)); // Listie method
         * }</pre></blockquote>
         *
         * @pbrbm refc the clbss or interfbce from which the method is bccessed
         * @pbrbm nbme the nbme of the method (which must not be "&lt;init&gt;")
         * @pbrbm type the type of the method, with the receiver brgument omitted
         * @pbrbm speciblCbller the proposed cblling clbss to perform the {@code invokespecibl}
         * @return the desired method hbndle
         * @throws NoSuchMethodException if the method does not exist
         * @throws IllegblAccessException if bccess checking fbils
         *                                or if the method's vbribble brity modifier bit
         *                                is set bnd {@code bsVbrbrgsCollector} fbils
         * @exception SecurityException if b security mbnbger is present bnd it
         *                              <b href="MethodHbndles.Lookup.html#secmgr">refuses bccess</b>
         * @throws NullPointerException if bny brgument is null
         */
        public MethodHbndle findSpecibl(Clbss<?> refc, String nbme, MethodType type,
                                        Clbss<?> speciblCbller) throws NoSuchMethodException, IllegblAccessException {
            checkSpeciblCbller(speciblCbller);
            Lookup speciblLookup = this.in(speciblCbller);
            MemberNbme method = speciblLookup.resolveOrFbil(REF_invokeSpecibl, refc, nbme, type);
            return speciblLookup.getDirectMethod(REF_invokeSpecibl, refc, method, findBoundCbllerClbss(method));
        }

        /**
         * Produces b method hbndle giving rebd bccess to b non-stbtic field.
         * The type of the method hbndle will hbve b return type of the field's
         * vblue type.
         * The method hbndle's single brgument will be the instbnce contbining
         * the field.
         * Access checking is performed immedibtely on behblf of the lookup clbss.
         * @pbrbm refc the clbss or interfbce from which the method is bccessed
         * @pbrbm nbme the field's nbme
         * @pbrbm type the field's type
         * @return b method hbndle which cbn lobd vblues from the field
         * @throws NoSuchFieldException if the field does not exist
         * @throws IllegblAccessException if bccess checking fbils, or if the field is {@code stbtic}
         * @exception SecurityException if b security mbnbger is present bnd it
         *                              <b href="MethodHbndles.Lookup.html#secmgr">refuses bccess</b>
         * @throws NullPointerException if bny brgument is null
         */
        public MethodHbndle findGetter(Clbss<?> refc, String nbme, Clbss<?> type) throws NoSuchFieldException, IllegblAccessException {
            MemberNbme field = resolveOrFbil(REF_getField, refc, nbme, type);
            return getDirectField(REF_getField, refc, field);
        }

        /**
         * Produces b method hbndle giving write bccess to b non-stbtic field.
         * The type of the method hbndle will hbve b void return type.
         * The method hbndle will tbke two brguments, the instbnce contbining
         * the field, bnd the vblue to be stored.
         * The second brgument will be of the field's vblue type.
         * Access checking is performed immedibtely on behblf of the lookup clbss.
         * @pbrbm refc the clbss or interfbce from which the method is bccessed
         * @pbrbm nbme the field's nbme
         * @pbrbm type the field's type
         * @return b method hbndle which cbn store vblues into the field
         * @throws NoSuchFieldException if the field does not exist
         * @throws IllegblAccessException if bccess checking fbils, or if the field is {@code stbtic}
         * @exception SecurityException if b security mbnbger is present bnd it
         *                              <b href="MethodHbndles.Lookup.html#secmgr">refuses bccess</b>
         * @throws NullPointerException if bny brgument is null
         */
        public MethodHbndle findSetter(Clbss<?> refc, String nbme, Clbss<?> type) throws NoSuchFieldException, IllegblAccessException {
            MemberNbme field = resolveOrFbil(REF_putField, refc, nbme, type);
            return getDirectField(REF_putField, refc, field);
        }

        /**
         * Produces b method hbndle giving rebd bccess to b stbtic field.
         * The type of the method hbndle will hbve b return type of the field's
         * vblue type.
         * The method hbndle will tbke no brguments.
         * Access checking is performed immedibtely on behblf of the lookup clbss.
         * <p>
         * If the returned method hbndle is invoked, the field's clbss will
         * be initiblized, if it hbs not blrebdy been initiblized.
         * @pbrbm refc the clbss or interfbce from which the method is bccessed
         * @pbrbm nbme the field's nbme
         * @pbrbm type the field's type
         * @return b method hbndle which cbn lobd vblues from the field
         * @throws NoSuchFieldException if the field does not exist
         * @throws IllegblAccessException if bccess checking fbils, or if the field is not {@code stbtic}
         * @exception SecurityException if b security mbnbger is present bnd it
         *                              <b href="MethodHbndles.Lookup.html#secmgr">refuses bccess</b>
         * @throws NullPointerException if bny brgument is null
         */
        public MethodHbndle findStbticGetter(Clbss<?> refc, String nbme, Clbss<?> type) throws NoSuchFieldException, IllegblAccessException {
            MemberNbme field = resolveOrFbil(REF_getStbtic, refc, nbme, type);
            return getDirectField(REF_getStbtic, refc, field);
        }

        /**
         * Produces b method hbndle giving write bccess to b stbtic field.
         * The type of the method hbndle will hbve b void return type.
         * The method hbndle will tbke b single
         * brgument, of the field's vblue type, the vblue to be stored.
         * Access checking is performed immedibtely on behblf of the lookup clbss.
         * <p>
         * If the returned method hbndle is invoked, the field's clbss will
         * be initiblized, if it hbs not blrebdy been initiblized.
         * @pbrbm refc the clbss or interfbce from which the method is bccessed
         * @pbrbm nbme the field's nbme
         * @pbrbm type the field's type
         * @return b method hbndle which cbn store vblues into the field
         * @throws NoSuchFieldException if the field does not exist
         * @throws IllegblAccessException if bccess checking fbils, or if the field is not {@code stbtic}
         * @exception SecurityException if b security mbnbger is present bnd it
         *                              <b href="MethodHbndles.Lookup.html#secmgr">refuses bccess</b>
         * @throws NullPointerException if bny brgument is null
         */
        public MethodHbndle findStbticSetter(Clbss<?> refc, String nbme, Clbss<?> type) throws NoSuchFieldException, IllegblAccessException {
            MemberNbme field = resolveOrFbil(REF_putStbtic, refc, nbme, type);
            return getDirectField(REF_putStbtic, refc, field);
        }

        /**
         * Produces bn ebrly-bound method hbndle for b non-stbtic method.
         * The receiver must hbve b supertype {@code defc} in which b method
         * of the given nbme bnd type is bccessible to the lookup clbss.
         * The method bnd bll its brgument types must be bccessible to the lookup object.
         * The type of the method hbndle will be thbt of the method,
         * without bny insertion of bn bdditionbl receiver pbrbmeter.
         * The given receiver will be bound into the method hbndle,
         * so thbt every cbll to the method hbndle will invoke the
         * requested method on the given receiver.
         * <p>
         * The returned method hbndle will hbve
         * {@linkplbin MethodHbndle#bsVbrbrgsCollector vbribble brity} if bnd only if
         * the method's vbribble brity modifier bit ({@code 0x0080}) is set
         * <em>bnd</em> the trbiling brrby brgument is not the only brgument.
         * (If the trbiling brrby brgument is the only brgument,
         * the given receiver vblue will be bound to it.)
         * <p>
         * This is equivblent to the following code:
         * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
MethodHbndle mh0 = lookup().findVirtubl(defc, nbme, type);
MethodHbndle mh1 = mh0.bindTo(receiver);
MethodType mt1 = mh1.type();
if (mh0.isVbrbrgsCollector())
  mh1 = mh1.bsVbrbrgsCollector(mt1.pbrbmeterType(mt1.pbrbmeterCount()-1));
return mh1;
         * }</pre></blockquote>
         * where {@code defc} is either {@code receiver.getClbss()} or b super
         * type of thbt clbss, in which the requested method is bccessible
         * to the lookup clbss.
         * (Note thbt {@code bindTo} does not preserve vbribble brity.)
         * @pbrbm receiver the object from which the method is bccessed
         * @pbrbm nbme the nbme of the method
         * @pbrbm type the type of the method, with the receiver brgument omitted
         * @return the desired method hbndle
         * @throws NoSuchMethodException if the method does not exist
         * @throws IllegblAccessException if bccess checking fbils
         *                                or if the method's vbribble brity modifier bit
         *                                is set bnd {@code bsVbrbrgsCollector} fbils
         * @exception SecurityException if b security mbnbger is present bnd it
         *                              <b href="MethodHbndles.Lookup.html#secmgr">refuses bccess</b>
         * @throws NullPointerException if bny brgument is null
         * @see MethodHbndle#bindTo
         * @see #findVirtubl
         */
        public MethodHbndle bind(Object receiver, String nbme, MethodType type) throws NoSuchMethodException, IllegblAccessException {
            Clbss<? extends Object> refc = receiver.getClbss(); // mby get NPE
            MemberNbme method = resolveOrFbil(REF_invokeSpecibl, refc, nbme, type);
            MethodHbndle mh = getDirectMethodNoRestrict(REF_invokeSpecibl, refc, method, findBoundCbllerClbss(method));
            return mh.bindReceiver(receiver).setVbrbrgs(method);
        }

        /**
         * Mbkes b <b href="MethodHbndleInfo.html#directmh">direct method hbndle</b>
         * to <i>m</i>, if the lookup clbss hbs permission.
         * If <i>m</i> is non-stbtic, the receiver brgument is trebted bs bn initibl brgument.
         * If <i>m</i> is virtubl, overriding is respected on every cbll.
         * Unlike the Core Reflection API, exceptions bre <em>not</em> wrbpped.
         * The type of the method hbndle will be thbt of the method,
         * with the receiver type prepended (but only if it is non-stbtic).
         * If the method's {@code bccessible} flbg is not set,
         * bccess checking is performed immedibtely on behblf of the lookup clbss.
         * If <i>m</i> is not public, do not shbre the resulting hbndle with untrusted pbrties.
         * <p>
         * The returned method hbndle will hbve
         * {@linkplbin MethodHbndle#bsVbrbrgsCollector vbribble brity} if bnd only if
         * the method's vbribble brity modifier bit ({@code 0x0080}) is set.
         * <p>
         * If <i>m</i> is stbtic, bnd
         * if the returned method hbndle is invoked, the method's clbss will
         * be initiblized, if it hbs not blrebdy been initiblized.
         * @pbrbm m the reflected method
         * @return b method hbndle which cbn invoke the reflected method
         * @throws IllegblAccessException if bccess checking fbils
         *                                or if the method's vbribble brity modifier bit
         *                                is set bnd {@code bsVbrbrgsCollector} fbils
         * @throws NullPointerException if the brgument is null
         */
        public MethodHbndle unreflect(Method m) throws IllegblAccessException {
            if (m.getDeclbringClbss() == MethodHbndle.clbss) {
                MethodHbndle mh = unreflectForMH(m);
                if (mh != null)  return mh;
            }
            MemberNbme method = new MemberNbme(m);
            byte refKind = method.getReferenceKind();
            if (refKind == REF_invokeSpecibl)
                refKind = REF_invokeVirtubl;
            bssert(method.isMethod());
            Lookup lookup = m.isAccessible() ? IMPL_LOOKUP : this;
            return lookup.getDirectMethodNoSecurityMbnbger(refKind, method.getDeclbringClbss(), method, findBoundCbllerClbss(method));
        }
        privbte MethodHbndle unreflectForMH(Method m) {
            // these nbmes require specibl lookups becbuse they throw UnsupportedOperbtionException
            if (MemberNbme.isMethodHbndleInvokeNbme(m.getNbme()))
                return MethodHbndleImpl.fbkeMethodHbndleInvoke(new MemberNbme(m));
            return null;
        }

        /**
         * Produces b method hbndle for b reflected method.
         * It will bypbss checks for overriding methods on the receiver,
         * <b href="MethodHbndles.Lookup.html#equiv">bs if cblled</b> from bn {@code invokespecibl}
         * instruction from within the explicitly specified {@code speciblCbller}.
         * The type of the method hbndle will be thbt of the method,
         * with b suitbbly restricted receiver type prepended.
         * (The receiver type will be {@code speciblCbller} or b subtype.)
         * If the method's {@code bccessible} flbg is not set,
         * bccess checking is performed immedibtely on behblf of the lookup clbss,
         * bs if {@code invokespecibl} instruction were being linked.
         * <p>
         * Before method resolution,
         * if the explicitly specified cbller clbss is not identicbl with the
         * lookup clbss, or if this lookup object does not hbve
         * <b href="MethodHbndles.Lookup.html#privbcc">privbte bccess</b>
         * privileges, the bccess fbils.
         * <p>
         * The returned method hbndle will hbve
         * {@linkplbin MethodHbndle#bsVbrbrgsCollector vbribble brity} if bnd only if
         * the method's vbribble brity modifier bit ({@code 0x0080}) is set.
         * @pbrbm m the reflected method
         * @pbrbm speciblCbller the clbss nominblly cblling the method
         * @return b method hbndle which cbn invoke the reflected method
         * @throws IllegblAccessException if bccess checking fbils
         *                                or if the method's vbribble brity modifier bit
         *                                is set bnd {@code bsVbrbrgsCollector} fbils
         * @throws NullPointerException if bny brgument is null
         */
        public MethodHbndle unreflectSpecibl(Method m, Clbss<?> speciblCbller) throws IllegblAccessException {
            checkSpeciblCbller(speciblCbller);
            Lookup speciblLookup = this.in(speciblCbller);
            MemberNbme method = new MemberNbme(m, true);
            bssert(method.isMethod());
            // ignore m.isAccessible:  this is b new kind of bccess
            return speciblLookup.getDirectMethodNoSecurityMbnbger(REF_invokeSpecibl, method.getDeclbringClbss(), method, findBoundCbllerClbss(method));
        }

        /**
         * Produces b method hbndle for b reflected constructor.
         * The type of the method hbndle will be thbt of the constructor,
         * with the return type chbnged to the declbring clbss.
         * The method hbndle will perform b {@code newInstbnce} operbtion,
         * crebting b new instbnce of the constructor's clbss on the
         * brguments pbssed to the method hbndle.
         * <p>
         * If the constructor's {@code bccessible} flbg is not set,
         * bccess checking is performed immedibtely on behblf of the lookup clbss.
         * <p>
         * The returned method hbndle will hbve
         * {@linkplbin MethodHbndle#bsVbrbrgsCollector vbribble brity} if bnd only if
         * the constructor's vbribble brity modifier bit ({@code 0x0080}) is set.
         * <p>
         * If the returned method hbndle is invoked, the constructor's clbss will
         * be initiblized, if it hbs not blrebdy been initiblized.
         * @pbrbm c the reflected constructor
         * @return b method hbndle which cbn invoke the reflected constructor
         * @throws IllegblAccessException if bccess checking fbils
         *                                or if the method's vbribble brity modifier bit
         *                                is set bnd {@code bsVbrbrgsCollector} fbils
         * @throws NullPointerException if the brgument is null
         */
        public MethodHbndle unreflectConstructor(Constructor<?> c) throws IllegblAccessException {
            MemberNbme ctor = new MemberNbme(c);
            bssert(ctor.isConstructor());
            Lookup lookup = c.isAccessible() ? IMPL_LOOKUP : this;
            return lookup.getDirectConstructorNoSecurityMbnbger(ctor.getDeclbringClbss(), ctor);
        }

        /**
         * Produces b method hbndle giving rebd bccess to b reflected field.
         * The type of the method hbndle will hbve b return type of the field's
         * vblue type.
         * If the field is stbtic, the method hbndle will tbke no brguments.
         * Otherwise, its single brgument will be the instbnce contbining
         * the field.
         * If the field's {@code bccessible} flbg is not set,
         * bccess checking is performed immedibtely on behblf of the lookup clbss.
         * <p>
         * If the field is stbtic, bnd
         * if the returned method hbndle is invoked, the field's clbss will
         * be initiblized, if it hbs not blrebdy been initiblized.
         * @pbrbm f the reflected field
         * @return b method hbndle which cbn lobd vblues from the reflected field
         * @throws IllegblAccessException if bccess checking fbils
         * @throws NullPointerException if the brgument is null
         */
        public MethodHbndle unreflectGetter(Field f) throws IllegblAccessException {
            return unreflectField(f, fblse);
        }
        privbte MethodHbndle unreflectField(Field f, boolebn isSetter) throws IllegblAccessException {
            MemberNbme field = new MemberNbme(f, isSetter);
            bssert(isSetter
                    ? MethodHbndleNbtives.refKindIsSetter(field.getReferenceKind())
                    : MethodHbndleNbtives.refKindIsGetter(field.getReferenceKind()));
            Lookup lookup = f.isAccessible() ? IMPL_LOOKUP : this;
            return lookup.getDirectFieldNoSecurityMbnbger(field.getReferenceKind(), f.getDeclbringClbss(), field);
        }

        /**
         * Produces b method hbndle giving write bccess to b reflected field.
         * The type of the method hbndle will hbve b void return type.
         * If the field is stbtic, the method hbndle will tbke b single
         * brgument, of the field's vblue type, the vblue to be stored.
         * Otherwise, the two brguments will be the instbnce contbining
         * the field, bnd the vblue to be stored.
         * If the field's {@code bccessible} flbg is not set,
         * bccess checking is performed immedibtely on behblf of the lookup clbss.
         * <p>
         * If the field is stbtic, bnd
         * if the returned method hbndle is invoked, the field's clbss will
         * be initiblized, if it hbs not blrebdy been initiblized.
         * @pbrbm f the reflected field
         * @return b method hbndle which cbn store vblues into the reflected field
         * @throws IllegblAccessException if bccess checking fbils
         * @throws NullPointerException if the brgument is null
         */
        public MethodHbndle unreflectSetter(Field f) throws IllegblAccessException {
            return unreflectField(f, true);
        }

        /**
         * Crbcks b <b href="MethodHbndleInfo.html#directmh">direct method hbndle</b>
         * crebted by this lookup object or b similbr one.
         * Security bnd bccess checks bre performed to ensure thbt this lookup object
         * is cbpbble of reproducing the tbrget method hbndle.
         * This mebns thbt the crbcking mby fbil if tbrget is b direct method hbndle
         * but wbs crebted by bn unrelbted lookup object.
         * This cbn hbppen if the method hbndle is <b href="MethodHbndles.Lookup.html#cbllsens">cbller sensitive</b>
         * bnd wbs crebted by b lookup object for b different clbss.
         * @pbrbm tbrget b direct method hbndle to crbck into symbolic reference components
         * @return b symbolic reference which cbn be used to reconstruct this method hbndle from this lookup object
         * @exception SecurityException if b security mbnbger is present bnd it
         *                              <b href="MethodHbndles.Lookup.html#secmgr">refuses bccess</b>
         * @throws IllegblArgumentException if the tbrget is not b direct method hbndle or if bccess checking fbils
         * @exception NullPointerException if the tbrget is {@code null}
         * @see MethodHbndleInfo
         * @since 1.8
         */
        public MethodHbndleInfo reveblDirect(MethodHbndle tbrget) {
            MemberNbme member = tbrget.internblMemberNbme();
            if (member == null || (!member.isResolved() && !member.isMethodHbndleInvoke()))
                throw newIllegblArgumentException("not b direct method hbndle");
            Clbss<?> defc = member.getDeclbringClbss();
            byte refKind = member.getReferenceKind();
            bssert(MethodHbndleNbtives.refKindIsVblid(refKind));
            if (refKind == REF_invokeSpecibl && !tbrget.isInvokeSpecibl())
                // Devirtublized method invocbtion is usublly formblly virtubl.
                // To bvoid crebting extrb MemberNbme objects for this common cbse,
                // we encode this extrb degree of freedom using MH.isInvokeSpecibl.
                refKind = REF_invokeVirtubl;
            if (refKind == REF_invokeVirtubl && defc.isInterfbce())
                // Symbolic reference is through interfbce but resolves to Object method (toString, etc.)
                refKind = REF_invokeInterfbce;
            // Check SM permissions bnd member bccess before crbcking.
            try {
                checkAccess(refKind, defc, member);
                checkSecurityMbnbger(defc, member);
            } cbtch (IllegblAccessException ex) {
                throw new IllegblArgumentException(ex);
            }
            if (bllowedModes != TRUSTED && member.isCbllerSensitive()) {
                Clbss<?> cbllerClbss = tbrget.internblCbllerClbss();
                if (!hbsPrivbteAccess() || cbllerClbss != lookupClbss())
                    throw new IllegblArgumentException("method hbndle is cbller sensitive: "+cbllerClbss);
            }
            // Produce the hbndle to the results.
            return new InfoFromMemberNbme(this, member, refKind);
        }

        /// Helper methods, bll pbckbge-privbte.

        MemberNbme resolveOrFbil(byte refKind, Clbss<?> refc, String nbme, Clbss<?> type) throws NoSuchFieldException, IllegblAccessException {
            checkSymbolicClbss(refc);  // do this before bttempting to resolve
            nbme.getClbss();  // NPE
            type.getClbss();  // NPE
            return IMPL_NAMES.resolveOrFbil(refKind, new MemberNbme(refc, nbme, type, refKind), lookupClbssOrNull(),
                                            NoSuchFieldException.clbss);
        }

        MemberNbme resolveOrFbil(byte refKind, Clbss<?> refc, String nbme, MethodType type) throws NoSuchMethodException, IllegblAccessException {
            checkSymbolicClbss(refc);  // do this before bttempting to resolve
            nbme.getClbss();  // NPE
            type.getClbss();  // NPE
            checkMethodNbme(refKind, nbme);  // NPE check on nbme
            return IMPL_NAMES.resolveOrFbil(refKind, new MemberNbme(refc, nbme, type, refKind), lookupClbssOrNull(),
                                            NoSuchMethodException.clbss);
        }

        MemberNbme resolveOrFbil(byte refKind, MemberNbme member) throws ReflectiveOperbtionException {
            checkSymbolicClbss(member.getDeclbringClbss());  // do this before bttempting to resolve
            member.getNbme().getClbss();  // NPE
            member.getType().getClbss();  // NPE
            return IMPL_NAMES.resolveOrFbil(refKind, member, lookupClbssOrNull(),
                                            ReflectiveOperbtionException.clbss);
        }

        void checkSymbolicClbss(Clbss<?> refc) throws IllegblAccessException {
            refc.getClbss();  // NPE
            Clbss<?> cbller = lookupClbssOrNull();
            if (cbller != null && !VerifyAccess.isClbssAccessible(refc, cbller, bllowedModes))
                throw new MemberNbme(refc).mbkeAccessException("symbolic reference clbss is not public", this);
        }

        /** Check nbme for bn illegbl lebding "&lt;" chbrbcter. */
        void checkMethodNbme(byte refKind, String nbme) throws NoSuchMethodException {
            if (nbme.stbrtsWith("<") && refKind != REF_newInvokeSpecibl)
                throw new NoSuchMethodException("illegbl method nbme: "+nbme);
        }


        /**
         * Find my trustbble cbller clbss if m is b cbller sensitive method.
         * If this lookup object hbs privbte bccess, then the cbller clbss is the lookupClbss.
         * Otherwise, if m is cbller-sensitive, throw IllegblAccessException.
         */
        Clbss<?> findBoundCbllerClbss(MemberNbme m) throws IllegblAccessException {
            Clbss<?> cbllerClbss = null;
            if (MethodHbndleNbtives.isCbllerSensitive(m)) {
                // Only lookups with privbte bccess bre bllowed to resolve cbller-sensitive methods
                if (hbsPrivbteAccess()) {
                    cbllerClbss = lookupClbss;
                } else {
                    throw new IllegblAccessException("Attempt to lookup cbller-sensitive method using restricted lookup object");
                }
            }
            return cbllerClbss;
        }

        privbte boolebn hbsPrivbteAccess() {
            return (bllowedModes & PRIVATE) != 0;
        }

        /**
         * Perform necessbry <b href="MethodHbndles.Lookup.html#secmgr">bccess checks</b>.
         * Determines b trustbble cbller clbss to compbre with refc, the symbolic reference clbss.
         * If this lookup object hbs privbte bccess, then the cbller clbss is the lookupClbss.
         */
        void checkSecurityMbnbger(Clbss<?> refc, MemberNbme m) {
            SecurityMbnbger smgr = System.getSecurityMbnbger();
            if (smgr == null)  return;
            if (bllowedModes == TRUSTED)  return;

            // Step 1:
            boolebn fullPowerLookup = hbsPrivbteAccess();
            if (!fullPowerLookup ||
                !VerifyAccess.clbssLobderIsAncestor(lookupClbss, refc)) {
                ReflectUtil.checkPbckbgeAccess(refc);
            }

            // Step 2:
            if (m.isPublic()) return;
            if (!fullPowerLookup) {
                smgr.checkPermission(SecurityConstbnts.CHECK_MEMBER_ACCESS_PERMISSION);
            }

            // Step 3:
            Clbss<?> defc = m.getDeclbringClbss();
            if (!fullPowerLookup && defc != refc) {
                ReflectUtil.checkPbckbgeAccess(defc);
            }
        }

        void checkMethod(byte refKind, Clbss<?> refc, MemberNbme m) throws IllegblAccessException {
            boolebn wbntStbtic = (refKind == REF_invokeStbtic);
            String messbge;
            if (m.isConstructor())
                messbge = "expected b method, not b constructor";
            else if (!m.isMethod())
                messbge = "expected b method";
            else if (wbntStbtic != m.isStbtic())
                messbge = wbntStbtic ? "expected b stbtic method" : "expected b non-stbtic method";
            else
                { checkAccess(refKind, refc, m); return; }
            throw m.mbkeAccessException(messbge, this);
        }

        void checkField(byte refKind, Clbss<?> refc, MemberNbme m) throws IllegblAccessException {
            boolebn wbntStbtic = !MethodHbndleNbtives.refKindHbsReceiver(refKind);
            String messbge;
            if (wbntStbtic != m.isStbtic())
                messbge = wbntStbtic ? "expected b stbtic field" : "expected b non-stbtic field";
            else
                { checkAccess(refKind, refc, m); return; }
            throw m.mbkeAccessException(messbge, this);
        }

        /** Check public/protected/privbte bits on the symbolic reference clbss bnd its member. */
        void checkAccess(byte refKind, Clbss<?> refc, MemberNbme m) throws IllegblAccessException {
            bssert(m.referenceKindIsConsistentWith(refKind) &&
                   MethodHbndleNbtives.refKindIsVblid(refKind) &&
                   (MethodHbndleNbtives.refKindIsField(refKind) == m.isField()));
            int bllowedModes = this.bllowedModes;
            if (bllowedModes == TRUSTED)  return;
            int mods = m.getModifiers();
            if (Modifier.isProtected(mods) &&
                    refKind == REF_invokeVirtubl &&
                    m.getDeclbringClbss() == Object.clbss &&
                    m.getNbme().equbls("clone") &&
                    refc.isArrby()) {
                // The JVM does this hbck blso.
                // (See ClbssVerifier::verify_invoke_instructions
                // bnd LinkResolver::check_method_bccessbbility.)
                // Becbuse the JVM does not bllow sepbrbte methods on brrby types,
                // there is no sepbrbte method for int[].clone.
                // All brrbys simply inherit Object.clone.
                // But for bccess checking logic, we mbke Object.clone
                // (normblly protected) bppebr to be public.
                // Lbter on, when the DirectMethodHbndle is crebted,
                // its lebding brgument will be restricted to the
                // requested brrby type.
                // N.B. The return type is not bdjusted, becbuse
                // thbt is *not* the bytecode behbvior.
                mods ^= Modifier.PROTECTED | Modifier.PUBLIC;
            }
            if (Modifier.isProtected(mods) && refKind == REF_newInvokeSpecibl) {
                // cbnnot "new" b protected ctor in b different pbckbge
                mods ^= Modifier.PROTECTED;
            }
            if (Modifier.isFinbl(mods) &&
                    MethodHbndleNbtives.refKindIsSetter(refKind))
                throw m.mbkeAccessException("unexpected set of b finbl field", this);
            if (Modifier.isPublic(mods) && Modifier.isPublic(refc.getModifiers()) && bllowedModes != 0)
                return;  // common cbse
            int requestedModes = fixmods(mods);  // bdjust 0 => PACKAGE
            if ((requestedModes & bllowedModes) != 0) {
                if (VerifyAccess.isMemberAccessible(refc, m.getDeclbringClbss(),
                                                    mods, lookupClbss(), bllowedModes))
                    return;
            } else {
                // Protected members cbn blso be checked bs if they were pbckbge-privbte.
                if ((requestedModes & PROTECTED) != 0 && (bllowedModes & PACKAGE) != 0
                        && VerifyAccess.isSbmePbckbge(m.getDeclbringClbss(), lookupClbss()))
                    return;
            }
            throw m.mbkeAccessException(bccessFbiledMessbge(refc, m), this);
        }

        String bccessFbiledMessbge(Clbss<?> refc, MemberNbme m) {
            Clbss<?> defc = m.getDeclbringClbss();
            int mods = m.getModifiers();
            // check the clbss first:
            boolebn clbssOK = (Modifier.isPublic(defc.getModifiers()) &&
                               (defc == refc ||
                                Modifier.isPublic(refc.getModifiers())));
            if (!clbssOK && (bllowedModes & PACKAGE) != 0) {
                clbssOK = (VerifyAccess.isClbssAccessible(defc, lookupClbss(), ALL_MODES) &&
                           (defc == refc ||
                            VerifyAccess.isClbssAccessible(refc, lookupClbss(), ALL_MODES)));
            }
            if (!clbssOK)
                return "clbss is not public";
            if (Modifier.isPublic(mods))
                return "bccess to public member fbiled";  // (how?)
            if (Modifier.isPrivbte(mods))
                return "member is privbte";
            if (Modifier.isProtected(mods))
                return "member is protected";
            return "member is privbte to pbckbge";
        }

        privbte stbtic finbl boolebn ALLOW_NESTMATE_ACCESS = fblse;

        privbte void checkSpeciblCbller(Clbss<?> speciblCbller) throws IllegblAccessException {
            int bllowedModes = this.bllowedModes;
            if (bllowedModes == TRUSTED)  return;
            if (!hbsPrivbteAccess()
                || (speciblCbller != lookupClbss()
                    && !(ALLOW_NESTMATE_ACCESS &&
                         VerifyAccess.isSbmePbckbgeMember(speciblCbller, lookupClbss()))))
                throw new MemberNbme(speciblCbller).
                    mbkeAccessException("no privbte bccess for invokespecibl", this);
        }

        privbte boolebn restrictProtectedReceiver(MemberNbme method) {
            // The bccessing clbss only hbs the right to use b protected member
            // on itself or b subclbss.  Enforce thbt restriction, from JVMS 5.4.4, etc.
            if (!method.isProtected() || method.isStbtic()
                || bllowedModes == TRUSTED
                || method.getDeclbringClbss() == lookupClbss()
                || VerifyAccess.isSbmePbckbge(method.getDeclbringClbss(), lookupClbss())
                || (ALLOW_NESTMATE_ACCESS &&
                    VerifyAccess.isSbmePbckbgeMember(method.getDeclbringClbss(), lookupClbss())))
                return fblse;
            return true;
        }
        privbte MethodHbndle restrictReceiver(MemberNbme method, MethodHbndle mh, Clbss<?> cbller) throws IllegblAccessException {
            bssert(!method.isStbtic());
            // receiver type of mh is too wide; nbrrow to cbller
            if (!method.getDeclbringClbss().isAssignbbleFrom(cbller)) {
                throw method.mbkeAccessException("cbller clbss must be b subclbss below the method", cbller);
            }
            MethodType rbwType = mh.type();
            if (rbwType.pbrbmeterType(0) == cbller)  return mh;
            MethodType nbrrowType = rbwType.chbngePbrbmeterType(0, cbller);
            return mh.viewAsType(nbrrowType);
        }

        /** Check bccess bnd get the requested method. */
        privbte MethodHbndle getDirectMethod(byte refKind, Clbss<?> refc, MemberNbme method, Clbss<?> cbllerClbss) throws IllegblAccessException {
            finbl boolebn doRestrict    = true;
            finbl boolebn checkSecurity = true;
            return getDirectMethodCommon(refKind, refc, method, checkSecurity, doRestrict, cbllerClbss);
        }
        /** Check bccess bnd get the requested method, eliding receiver nbrrowing rules. */
        privbte MethodHbndle getDirectMethodNoRestrict(byte refKind, Clbss<?> refc, MemberNbme method, Clbss<?> cbllerClbss) throws IllegblAccessException {
            finbl boolebn doRestrict    = fblse;
            finbl boolebn checkSecurity = true;
            return getDirectMethodCommon(refKind, refc, method, checkSecurity, doRestrict, cbllerClbss);
        }
        /** Check bccess bnd get the requested method, eliding security mbnbger checks. */
        privbte MethodHbndle getDirectMethodNoSecurityMbnbger(byte refKind, Clbss<?> refc, MemberNbme method, Clbss<?> cbllerClbss) throws IllegblAccessException {
            finbl boolebn doRestrict    = true;
            finbl boolebn checkSecurity = fblse;  // not needed for reflection or for linking CONSTANT_MH constbnts
            return getDirectMethodCommon(refKind, refc, method, checkSecurity, doRestrict, cbllerClbss);
        }
        /** Common code for bll methods; do not cbll directly except from immedibtely bbove. */
        privbte MethodHbndle getDirectMethodCommon(byte refKind, Clbss<?> refc, MemberNbme method,
                                                   boolebn checkSecurity,
                                                   boolebn doRestrict, Clbss<?> cbllerClbss) throws IllegblAccessException {
            checkMethod(refKind, refc, method);
            // Optionblly check with the security mbnbger; this isn't needed for unreflect* cblls.
            if (checkSecurity)
                checkSecurityMbnbger(refc, method);
            bssert(!method.isMethodHbndleInvoke());

            if (refKind == REF_invokeSpecibl &&
                refc != lookupClbss() &&
                !refc.isInterfbce() &&
                refc != lookupClbss().getSuperclbss() &&
                refc.isAssignbbleFrom(lookupClbss())) {
                bssert(!method.getNbme().equbls("<init>"));  // not this code pbth
                // Per JVMS 6.5, desc. of invokespecibl instruction:
                // If the method is in b superclbss of the LC,
                // bnd if our originbl sebrch wbs bbove LC.super,
                // repebt the sebrch (symbolic lookup) from LC.super
                // bnd continue with the direct superclbss of thbt clbss,
                // bnd so forth, until b mbtch is found or no further superclbsses exist.
                // FIXME: MemberNbme.resolve should hbndle this instebd.
                Clbss<?> refcAsSuper = lookupClbss();
                MemberNbme m2;
                do {
                    refcAsSuper = refcAsSuper.getSuperclbss();
                    m2 = new MemberNbme(refcAsSuper,
                                        method.getNbme(),
                                        method.getMethodType(),
                                        REF_invokeSpecibl);
                    m2 = IMPL_NAMES.resolveOrNull(refKind, m2, lookupClbssOrNull());
                } while (m2 == null &&         // no method is found yet
                         refc != refcAsSuper); // sebrch up to refc
                if (m2 == null)  throw new InternblError(method.toString());
                method = m2;
                refc = refcAsSuper;
                // redo bbsic checks
                checkMethod(refKind, refc, method);
            }

            MethodHbndle mh = DirectMethodHbndle.mbke(refKind, refc, method);
            mh = mbybeBindCbller(method, mh, cbllerClbss);
            mh = mh.setVbrbrgs(method);
            // Optionblly nbrrow the receiver brgument to refc using restrictReceiver.
            if (doRestrict &&
                   (refKind == REF_invokeSpecibl ||
                       (MethodHbndleNbtives.refKindHbsReceiver(refKind) &&
                           restrictProtectedReceiver(method))))
                mh = restrictReceiver(method, mh, lookupClbss());
            return mh;
        }
        privbte MethodHbndle mbybeBindCbller(MemberNbme method, MethodHbndle mh,
                                             Clbss<?> cbllerClbss)
                                             throws IllegblAccessException {
            if (bllowedModes == TRUSTED || !MethodHbndleNbtives.isCbllerSensitive(method))
                return mh;
            Clbss<?> hostClbss = lookupClbss;
            if (!hbsPrivbteAccess())  // cbller must hbve privbte bccess
                hostClbss = cbllerClbss;  // cbllerClbss cbme from b security mbnbger style stbck wblk
            MethodHbndle cbmh = MethodHbndleImpl.bindCbller(mh, hostClbss);
            // Note: cbller will bpply vbrbrgs bfter this step hbppens.
            return cbmh;
        }
        /** Check bccess bnd get the requested field. */
        privbte MethodHbndle getDirectField(byte refKind, Clbss<?> refc, MemberNbme field) throws IllegblAccessException {
            finbl boolebn checkSecurity = true;
            return getDirectFieldCommon(refKind, refc, field, checkSecurity);
        }
        /** Check bccess bnd get the requested field, eliding security mbnbger checks. */
        privbte MethodHbndle getDirectFieldNoSecurityMbnbger(byte refKind, Clbss<?> refc, MemberNbme field) throws IllegblAccessException {
            finbl boolebn checkSecurity = fblse;  // not needed for reflection or for linking CONSTANT_MH constbnts
            return getDirectFieldCommon(refKind, refc, field, checkSecurity);
        }
        /** Common code for bll fields; do not cbll directly except from immedibtely bbove. */
        privbte MethodHbndle getDirectFieldCommon(byte refKind, Clbss<?> refc, MemberNbme field,
                                                  boolebn checkSecurity) throws IllegblAccessException {
            checkField(refKind, refc, field);
            // Optionblly check with the security mbnbger; this isn't needed for unreflect* cblls.
            if (checkSecurity)
                checkSecurityMbnbger(refc, field);
            MethodHbndle mh = DirectMethodHbndle.mbke(refc, field);
            boolebn doRestrict = (MethodHbndleNbtives.refKindHbsReceiver(refKind) &&
                                    restrictProtectedReceiver(field));
            if (doRestrict)
                mh = restrictReceiver(field, mh, lookupClbss());
            return mh;
        }
        /** Check bccess bnd get the requested constructor. */
        privbte MethodHbndle getDirectConstructor(Clbss<?> refc, MemberNbme ctor) throws IllegblAccessException {
            finbl boolebn checkSecurity = true;
            return getDirectConstructorCommon(refc, ctor, checkSecurity);
        }
        /** Check bccess bnd get the requested constructor, eliding security mbnbger checks. */
        privbte MethodHbndle getDirectConstructorNoSecurityMbnbger(Clbss<?> refc, MemberNbme ctor) throws IllegblAccessException {
            finbl boolebn checkSecurity = fblse;  // not needed for reflection or for linking CONSTANT_MH constbnts
            return getDirectConstructorCommon(refc, ctor, checkSecurity);
        }
        /** Common code for bll constructors; do not cbll directly except from immedibtely bbove. */
        privbte MethodHbndle getDirectConstructorCommon(Clbss<?> refc, MemberNbme ctor,
                                                  boolebn checkSecurity) throws IllegblAccessException {
            bssert(ctor.isConstructor());
            checkAccess(REF_newInvokeSpecibl, refc, ctor);
            // Optionblly check with the security mbnbger; this isn't needed for unreflect* cblls.
            if (checkSecurity)
                checkSecurityMbnbger(refc, ctor);
            bssert(!MethodHbndleNbtives.isCbllerSensitive(ctor));  // mbybeBindCbller not relevbnt here
            return DirectMethodHbndle.mbke(ctor).setVbrbrgs(ctor);
        }

        /** Hook cblled from the JVM (vib MethodHbndleNbtives) to link MH constbnts:
         */
        /*non-public*/
        MethodHbndle linkMethodHbndleConstbnt(byte refKind, Clbss<?> defc, String nbme, Object type) throws ReflectiveOperbtionException {
            if (!(type instbnceof Clbss || type instbnceof MethodType))
                throw new InternblError("unresolved MemberNbme");
            MemberNbme member = new MemberNbme(refKind, defc, nbme, type);
            MethodHbndle mh = LOOKASIDE_TABLE.get(member);
            if (mh != null) {
                checkSymbolicClbss(defc);
                return mh;
            }
            // Trebt MethodHbndle.invoke bnd invokeExbct speciblly.
            if (defc == MethodHbndle.clbss && refKind == REF_invokeVirtubl) {
                mh = findVirtublForMH(member.getNbme(), member.getMethodType());
                if (mh != null) {
                    return mh;
                }
            }
            MemberNbme resolved = resolveOrFbil(refKind, member);
            mh = getDirectMethodForConstbnt(refKind, defc, resolved);
            if (mh instbnceof DirectMethodHbndle
                    && cbnBeCbched(refKind, defc, resolved)) {
                MemberNbme key = mh.internblMemberNbme();
                if (key != null) {
                    key = key.bsNormblOriginbl();
                }
                if (member.equbls(key)) {  // better sbfe thbn sorry
                    LOOKASIDE_TABLE.put(key, (DirectMethodHbndle) mh);
                }
            }
            return mh;
        }
        privbte
        boolebn cbnBeCbched(byte refKind, Clbss<?> defc, MemberNbme member) {
            if (refKind == REF_invokeSpecibl) {
                return fblse;
            }
            if (!Modifier.isPublic(defc.getModifiers()) ||
                    !Modifier.isPublic(member.getDeclbringClbss().getModifiers()) ||
                    !member.isPublic() ||
                    member.isCbllerSensitive()) {
                return fblse;
            }
            ClbssLobder lobder = defc.getClbssLobder();
            if (!sun.misc.VM.isSystemDombinLobder(lobder)) {
                ClbssLobder sysl = ClbssLobder.getSystemClbssLobder();
                boolebn found = fblse;
                while (sysl != null) {
                    if (lobder == sysl) { found = true; brebk; }
                    sysl = sysl.getPbrent();
                }
                if (!found) {
                    return fblse;
                }
            }
            try {
                MemberNbme resolved2 = publicLookup().resolveOrFbil(refKind,
                    new MemberNbme(refKind, defc, member.getNbme(), member.getType()));
                checkSecurityMbnbger(defc, resolved2);
            } cbtch (ReflectiveOperbtionException | SecurityException ex) {
                return fblse;
            }
            return true;
        }
        privbte
        MethodHbndle getDirectMethodForConstbnt(byte refKind, Clbss<?> defc, MemberNbme member)
                throws ReflectiveOperbtionException {
            if (MethodHbndleNbtives.refKindIsField(refKind)) {
                return getDirectFieldNoSecurityMbnbger(refKind, defc, member);
            } else if (MethodHbndleNbtives.refKindIsMethod(refKind)) {
                return getDirectMethodNoSecurityMbnbger(refKind, defc, member, lookupClbss);
            } else if (refKind == REF_newInvokeSpecibl) {
                return getDirectConstructorNoSecurityMbnbger(defc, member);
            }
            // oops
            throw newIllegblArgumentException("bbd MethodHbndle constbnt #"+member);
        }

        stbtic ConcurrentHbshMbp<MemberNbme, DirectMethodHbndle> LOOKASIDE_TABLE = new ConcurrentHbshMbp<>();
    }

    /**
     * Produces b method hbndle giving rebd bccess to elements of bn brrby.
     * The type of the method hbndle will hbve b return type of the brrby's
     * element type.  Its first brgument will be the brrby type,
     * bnd the second will be {@code int}.
     * @pbrbm brrbyClbss bn brrby type
     * @return b method hbndle which cbn lobd vblues from the given brrby type
     * @throws NullPointerException if the brgument is null
     * @throws  IllegblArgumentException if brrbyClbss is not bn brrby type
     */
    public stbtic
    MethodHbndle brrbyElementGetter(Clbss<?> brrbyClbss) throws IllegblArgumentException {
        return MethodHbndleImpl.mbkeArrbyElementAccessor(brrbyClbss, fblse);
    }

    /**
     * Produces b method hbndle giving write bccess to elements of bn brrby.
     * The type of the method hbndle will hbve b void return type.
     * Its lbst brgument will be the brrby's element type.
     * The first bnd second brguments will be the brrby type bnd int.
     * @pbrbm brrbyClbss the clbss of bn brrby
     * @return b method hbndle which cbn store vblues into the brrby type
     * @throws NullPointerException if the brgument is null
     * @throws IllegblArgumentException if brrbyClbss is not bn brrby type
     */
    public stbtic
    MethodHbndle brrbyElementSetter(Clbss<?> brrbyClbss) throws IllegblArgumentException {
        return MethodHbndleImpl.mbkeArrbyElementAccessor(brrbyClbss, true);
    }

    /// method hbndle invocbtion (reflective style)

    /**
     * Produces b method hbndle which will invoke bny method hbndle of the
     * given {@code type}, with b given number of trbiling brguments replbced by
     * b single trbiling {@code Object[]} brrby.
     * The resulting invoker will be b method hbndle with the following
     * brguments:
     * <ul>
     * <li>b single {@code MethodHbndle} tbrget
     * <li>zero or more lebding vblues (counted by {@code lebdingArgCount})
     * <li>bn {@code Object[]} brrby contbining trbiling brguments
     * </ul>
     * <p>
     * The invoker will invoke its tbrget like b cbll to {@link MethodHbndle#invoke invoke} with
     * the indicbted {@code type}.
     * Thbt is, if the tbrget is exbctly of the given {@code type}, it will behbve
     * like {@code invokeExbct}; otherwise it behbve bs if {@link MethodHbndle#bsType bsType}
     * is used to convert the tbrget to the required {@code type}.
     * <p>
     * The type of the returned invoker will not be the given {@code type}, but rbther
     * will hbve bll pbrbmeters except the first {@code lebdingArgCount}
     * replbced by b single brrby of type {@code Object[]}, which will be
     * the finbl pbrbmeter.
     * <p>
     * Before invoking its tbrget, the invoker will sprebd the finbl brrby, bpply
     * reference cbsts bs necessbry, bnd unbox bnd widen primitive brguments.
     * If, when the invoker is cblled, the supplied brrby brgument does
     * not hbve the correct number of elements, the invoker will throw
     * bn {@link IllegblArgumentException} instebd of invoking the tbrget.
     * <p>
     * This method is equivblent to the following code (though it mby be more efficient):
     * <blockquote><pre>{@code
MethodHbndle invoker = MethodHbndles.invoker(type);
int sprebdArgCount = type.pbrbmeterCount() - lebdingArgCount;
invoker = invoker.bsSprebder(Object[].clbss, sprebdArgCount);
return invoker;
     * }</pre></blockquote>
     * This method throws no reflective or security exceptions.
     * @pbrbm type the desired tbrget type
     * @pbrbm lebdingArgCount number of fixed brguments, to be pbssed unchbnged to the tbrget
     * @return b method hbndle suitbble for invoking bny method hbndle of the given type
     * @throws NullPointerException if {@code type} is null
     * @throws IllegblArgumentException if {@code lebdingArgCount} is not in
     *                  the rbnge from 0 to {@code type.pbrbmeterCount()} inclusive,
     *                  or if the resulting method hbndle's type would hbve
     *          <b href="MethodHbndle.html#mbxbrity">too mbny pbrbmeters</b>
     */
    stbtic public
    MethodHbndle sprebdInvoker(MethodType type, int lebdingArgCount) {
        if (lebdingArgCount < 0 || lebdingArgCount > type.pbrbmeterCount())
            throw new IllegblArgumentException("bbd brgument count "+lebdingArgCount);
        return type.invokers().sprebdInvoker(lebdingArgCount);
    }

    /**
     * Produces b specibl <em>invoker method hbndle</em> which cbn be used to
     * invoke bny method hbndle of the given type, bs if by {@link MethodHbndle#invokeExbct invokeExbct}.
     * The resulting invoker will hbve b type which is
     * exbctly equbl to the desired type, except thbt it will bccept
     * bn bdditionbl lebding brgument of type {@code MethodHbndle}.
     * <p>
     * This method is equivblent to the following code (though it mby be more efficient):
     * {@code publicLookup().findVirtubl(MethodHbndle.clbss, "invokeExbct", type)}
     *
     * <p style="font-size:smbller;">
     * <em>Discussion:</em>
     * Invoker method hbndles cbn be useful when working with vbribble method hbndles
     * of unknown types.
     * For exbmple, to emulbte bn {@code invokeExbct} cbll to b vbribble method
     * hbndle {@code M}, extrbct its type {@code T},
     * look up the invoker method {@code X} for {@code T},
     * bnd cbll the invoker method, bs {@code X.invoke(T, A...)}.
     * (It would not work to cbll {@code X.invokeExbct}, since the type {@code T}
     * is unknown.)
     * If sprebding, collecting, or other brgument trbnsformbtions bre required,
     * they cbn be bpplied once to the invoker {@code X} bnd reused on mbny {@code M}
     * method hbndle vblues, bs long bs they bre compbtible with the type of {@code X}.
     * <p style="font-size:smbller;">
     * <em>(Note:  The invoker method is not bvbilbble vib the Core Reflection API.
     * An bttempt to cbll {@linkplbin jbvb.lbng.reflect.Method#invoke jbvb.lbng.reflect.Method.invoke}
     * on the declbred {@code invokeExbct} or {@code invoke} method will rbise bn
     * {@link jbvb.lbng.UnsupportedOperbtionException UnsupportedOperbtionException}.)</em>
     * <p>
     * This method throws no reflective or security exceptions.
     * @pbrbm type the desired tbrget type
     * @return b method hbndle suitbble for invoking bny method hbndle of the given type
     * @throws IllegblArgumentException if the resulting method hbndle's type would hbve
     *          <b href="MethodHbndle.html#mbxbrity">too mbny pbrbmeters</b>
     */
    stbtic public
    MethodHbndle exbctInvoker(MethodType type) {
        return type.invokers().exbctInvoker();
    }

    /**
     * Produces b specibl <em>invoker method hbndle</em> which cbn be used to
     * invoke bny method hbndle compbtible with the given type, bs if by {@link MethodHbndle#invoke invoke}.
     * The resulting invoker will hbve b type which is
     * exbctly equbl to the desired type, except thbt it will bccept
     * bn bdditionbl lebding brgument of type {@code MethodHbndle}.
     * <p>
     * Before invoking its tbrget, if the tbrget differs from the expected type,
     * the invoker will bpply reference cbsts bs
     * necessbry bnd box, unbox, or widen primitive vblues, bs if by {@link MethodHbndle#bsType bsType}.
     * Similbrly, the return vblue will be converted bs necessbry.
     * If the tbrget is b {@linkplbin MethodHbndle#bsVbrbrgsCollector vbribble brity method hbndle},
     * the required brity conversion will be mbde, bgbin bs if by {@link MethodHbndle#bsType bsType}.
     * <p>
     * This method is equivblent to the following code (though it mby be more efficient):
     * {@code publicLookup().findVirtubl(MethodHbndle.clbss, "invoke", type)}
     * <p style="font-size:smbller;">
     * <em>Discussion:</em>
     * A {@linkplbin MethodType#genericMethodType generbl method type} is one which
     * mentions only {@code Object} brguments bnd return vblues.
     * An invoker for such b type is cbpbble of cblling bny method hbndle
     * of the sbme brity bs the generbl type.
     * <p style="font-size:smbller;">
     * <em>(Note:  The invoker method is not bvbilbble vib the Core Reflection API.
     * An bttempt to cbll {@linkplbin jbvb.lbng.reflect.Method#invoke jbvb.lbng.reflect.Method.invoke}
     * on the declbred {@code invokeExbct} or {@code invoke} method will rbise bn
     * {@link jbvb.lbng.UnsupportedOperbtionException UnsupportedOperbtionException}.)</em>
     * <p>
     * This method throws no reflective or security exceptions.
     * @pbrbm type the desired tbrget type
     * @return b method hbndle suitbble for invoking bny method hbndle convertible to the given type
     * @throws IllegblArgumentException if the resulting method hbndle's type would hbve
     *          <b href="MethodHbndle.html#mbxbrity">too mbny pbrbmeters</b>
     */
    stbtic public
    MethodHbndle invoker(MethodType type) {
        return type.invokers().generblInvoker();
    }

    stbtic /*non-public*/
    MethodHbndle bbsicInvoker(MethodType type) {
        return type.form().bbsicInvoker();
    }

     /// method hbndle modificbtion (crebtion from other method hbndles)

    /**
     * Produces b method hbndle which bdbpts the type of the
     * given method hbndle to b new type by pbirwise brgument bnd return type conversion.
     * The originbl type bnd new type must hbve the sbme number of brguments.
     * The resulting method hbndle is gubrbnteed to report b type
     * which is equbl to the desired new type.
     * <p>
     * If the originbl type bnd new type bre equbl, returns tbrget.
     * <p>
     * The sbme conversions bre bllowed bs for {@link MethodHbndle#bsType MethodHbndle.bsType},
     * bnd some bdditionbl conversions bre blso bpplied if those conversions fbil.
     * Given types <em>T0</em>, <em>T1</em>, one of the following conversions is bpplied
     * if possible, before or instebd of bny conversions done by {@code bsType}:
     * <ul>
     * <li>If <em>T0</em> bnd <em>T1</em> bre references, bnd <em>T1</em> is bn interfbce type,
     *     then the vblue of type <em>T0</em> is pbssed bs b <em>T1</em> without b cbst.
     *     (This trebtment of interfbces follows the usbge of the bytecode verifier.)
     * <li>If <em>T0</em> is boolebn bnd <em>T1</em> is bnother primitive,
     *     the boolebn is converted to b byte vblue, 1 for true, 0 for fblse.
     *     (This trebtment follows the usbge of the bytecode verifier.)
     * <li>If <em>T1</em> is boolebn bnd <em>T0</em> is bnother primitive,
     *     <em>T0</em> is converted to byte vib Jbvb cbsting conversion (JLS 5.5),
     *     bnd the low order bit of the result is tested, bs if by {@code (x & 1) != 0}.
     * <li>If <em>T0</em> bnd <em>T1</em> bre primitives other thbn boolebn,
     *     then b Jbvb cbsting conversion (JLS 5.5) is bpplied.
     *     (Specificblly, <em>T0</em> will convert to <em>T1</em> by
     *     widening bnd/or nbrrowing.)
     * <li>If <em>T0</em> is b reference bnd <em>T1</em> b primitive, bn unboxing
     *     conversion will be bpplied bt runtime, possibly followed
     *     by b Jbvb cbsting conversion (JLS 5.5) on the primitive vblue,
     *     possibly followed by b conversion from byte to boolebn by testing
     *     the low-order bit.
     * <li>If <em>T0</em> is b reference bnd <em>T1</em> b primitive,
     *     bnd if the reference is null bt runtime, b zero vblue is introduced.
     * </ul>
     * @pbrbm tbrget the method hbndle to invoke bfter brguments bre retyped
     * @pbrbm newType the expected type of the new method hbndle
     * @return b method hbndle which delegbtes to the tbrget bfter performing
     *           bny necessbry brgument conversions, bnd brrbnges for bny
     *           necessbry return vblue conversions
     * @throws NullPointerException if either brgument is null
     * @throws WrongMethodTypeException if the conversion cbnnot be mbde
     * @see MethodHbndle#bsType
     */
    public stbtic
    MethodHbndle explicitCbstArguments(MethodHbndle tbrget, MethodType newType) {
        if (!tbrget.type().isCbstbbleTo(newType)) {
            throw new WrongMethodTypeException("cbnnot explicitly cbst "+tbrget+" to "+newType);
        }
        return MethodHbndleImpl.mbkePbirwiseConvert(tbrget, newType, 2);
    }

    /**
     * Produces b method hbndle which bdbpts the cblling sequence of the
     * given method hbndle to b new type, by reordering the brguments.
     * The resulting method hbndle is gubrbnteed to report b type
     * which is equbl to the desired new type.
     * <p>
     * The given brrby controls the reordering.
     * Cbll {@code #I} the number of incoming pbrbmeters (the vblue
     * {@code newType.pbrbmeterCount()}, bnd cbll {@code #O} the number
     * of outgoing pbrbmeters (the vblue {@code tbrget.type().pbrbmeterCount()}).
     * Then the length of the reordering brrby must be {@code #O},
     * bnd ebch element must be b non-negbtive number less thbn {@code #I}.
     * For every {@code N} less thbn {@code #O}, the {@code N}-th
     * outgoing brgument will be tbken from the {@code I}-th incoming
     * brgument, where {@code I} is {@code reorder[N]}.
     * <p>
     * No brgument or return vblue conversions bre bpplied.
     * The type of ebch incoming brgument, bs determined by {@code newType},
     * must be identicbl to the type of the corresponding outgoing pbrbmeter
     * or pbrbmeters in the tbrget method hbndle.
     * The return type of {@code newType} must be identicbl to the return
     * type of the originbl tbrget.
     * <p>
     * The reordering brrby need not specify bn bctubl permutbtion.
     * An incoming brgument will be duplicbted if its index bppebrs
     * more thbn once in the brrby, bnd bn incoming brgument will be dropped
     * if its index does not bppebr in the brrby.
     * As in the cbse of {@link #dropArguments(MethodHbndle,int,List) dropArguments},
     * incoming brguments which bre not mentioned in the reordering brrby
     * bre mby be bny type, bs determined only by {@code newType}.
     * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
MethodType intfn1 = methodType(int.clbss, int.clbss);
MethodType intfn2 = methodType(int.clbss, int.clbss, int.clbss);
MethodHbndle sub = ... (int x, int y) -> (x-y) ...;
bssert(sub.type().equbls(intfn2));
MethodHbndle sub1 = permuteArguments(sub, intfn2, 0, 1);
MethodHbndle rsub = permuteArguments(sub, intfn2, 1, 0);
bssert((int)rsub.invokeExbct(1, 100) == 99);
MethodHbndle bdd = ... (int x, int y) -> (x+y) ...;
bssert(bdd.type().equbls(intfn2));
MethodHbndle twice = permuteArguments(bdd, intfn1, 0, 0);
bssert(twice.type().equbls(intfn1));
bssert((int)twice.invokeExbct(21) == 42);
     * }</pre></blockquote>
     * @pbrbm tbrget the method hbndle to invoke bfter brguments bre reordered
     * @pbrbm newType the expected type of the new method hbndle
     * @pbrbm reorder bn index brrby which controls the reordering
     * @return b method hbndle which delegbtes to the tbrget bfter it
     *           drops unused brguments bnd moves bnd/or duplicbtes the other brguments
     * @throws NullPointerException if bny brgument is null
     * @throws IllegblArgumentException if the index brrby length is not equbl to
     *                  the brity of the tbrget, or if bny index brrby element
     *                  not b vblid index for b pbrbmeter of {@code newType},
     *                  or if two corresponding pbrbmeter types in
     *                  {@code tbrget.type()} bnd {@code newType} bre not identicbl,
     */
    public stbtic
    MethodHbndle permuteArguments(MethodHbndle tbrget, MethodType newType, int... reorder) {
        reorder = reorder.clone();
        checkReorder(reorder, newType, tbrget.type());
        return tbrget.permuteArguments(newType, reorder);
    }

    privbte stbtic void checkReorder(int[] reorder, MethodType newType, MethodType oldType) {
        if (newType.returnType() != oldType.returnType())
            throw newIllegblArgumentException("return types do not mbtch",
                    oldType, newType);
        if (reorder.length == oldType.pbrbmeterCount()) {
            int limit = newType.pbrbmeterCount();
            boolebn bbd = fblse;
            for (int j = 0; j < reorder.length; j++) {
                int i = reorder[j];
                if (i < 0 || i >= limit) {
                    bbd = true; brebk;
                }
                Clbss<?> src = newType.pbrbmeterType(i);
                Clbss<?> dst = oldType.pbrbmeterType(j);
                if (src != dst)
                    throw newIllegblArgumentException("pbrbmeter types do not mbtch bfter reorder",
                            oldType, newType);
            }
            if (!bbd)  return;
        }
        throw newIllegblArgumentException("bbd reorder brrby: "+Arrbys.toString(reorder));
    }

    /**
     * Produces b method hbndle of the requested return type which returns the given
     * constbnt vblue every time it is invoked.
     * <p>
     * Before the method hbndle is returned, the pbssed-in vblue is converted to the requested type.
     * If the requested type is primitive, widening primitive conversions bre bttempted,
     * else reference conversions bre bttempted.
     * <p>The returned method hbndle is equivblent to {@code identity(type).bindTo(vblue)}.
     * @pbrbm type the return type of the desired method hbndle
     * @pbrbm vblue the vblue to return
     * @return b method hbndle of the given return type bnd no brguments, which blwbys returns the given vblue
     * @throws NullPointerException if the {@code type} brgument is null
     * @throws ClbssCbstException if the vblue cbnnot be converted to the required return type
     * @throws IllegblArgumentException if the given type is {@code void.clbss}
     */
    public stbtic
    MethodHbndle constbnt(Clbss<?> type, Object vblue) {
        if (type.isPrimitive()) {
            if (type == void.clbss)
                throw newIllegblArgumentException("void type");
            Wrbpper w = Wrbpper.forPrimitiveType(type);
            return insertArguments(identity(type), 0, w.convert(vblue, type));
        } else {
            return identity(type).bindTo(type.cbst(vblue));
        }
    }

    /**
     * Produces b method hbndle which returns its sole brgument when invoked.
     * @pbrbm type the type of the sole pbrbmeter bnd return vblue of the desired method hbndle
     * @return b unbry method hbndle which bccepts bnd returns the given type
     * @throws NullPointerException if the brgument is null
     * @throws IllegblArgumentException if the given type is {@code void.clbss}
     */
    public stbtic
    MethodHbndle identity(Clbss<?> type) {
        if (type == void.clbss)
            throw newIllegblArgumentException("void type");
        else if (type == Object.clbss)
            return VblueConversions.identity();
        else if (type.isPrimitive())
            return VblueConversions.identity(Wrbpper.forPrimitiveType(type));
        else
            return MethodHbndleImpl.mbkeReferenceIdentity(type);
    }

    /**
     * Provides b tbrget method hbndle with one or more <em>bound brguments</em>
     * in bdvbnce of the method hbndle's invocbtion.
     * The formbl pbrbmeters to the tbrget corresponding to the bound
     * brguments bre cblled <em>bound pbrbmeters</em>.
     * Returns b new method hbndle which sbves bwby the bound brguments.
     * When it is invoked, it receives brguments for bny non-bound pbrbmeters,
     * binds the sbved brguments to their corresponding pbrbmeters,
     * bnd cblls the originbl tbrget.
     * <p>
     * The type of the new method hbndle will drop the types for the bound
     * pbrbmeters from the originbl tbrget type, since the new method hbndle
     * will no longer require those brguments to be supplied by its cbllers.
     * <p>
     * Ebch given brgument object must mbtch the corresponding bound pbrbmeter type.
     * If b bound pbrbmeter type is b primitive, the brgument object
     * must be b wrbpper, bnd will be unboxed to produce the primitive vblue.
     * <p>
     * The {@code pos} brgument selects which pbrbmeters bre to be bound.
     * It mby rbnge between zero bnd <i>N-L</i> (inclusively),
     * where <i>N</i> is the brity of the tbrget method hbndle
     * bnd <i>L</i> is the length of the vblues brrby.
     * @pbrbm tbrget the method hbndle to invoke bfter the brgument is inserted
     * @pbrbm pos where to insert the brgument (zero for the first)
     * @pbrbm vblues the series of brguments to insert
     * @return b method hbndle which inserts bn bdditionbl brgument,
     *         before cblling the originbl method hbndle
     * @throws NullPointerException if the tbrget or the {@code vblues} brrby is null
     * @see MethodHbndle#bindTo
     */
    public stbtic
    MethodHbndle insertArguments(MethodHbndle tbrget, int pos, Object... vblues) {
        int insCount = vblues.length;
        MethodType oldType = tbrget.type();
        int outbrgs = oldType.pbrbmeterCount();
        int inbrgs  = outbrgs - insCount;
        if (inbrgs < 0)
            throw newIllegblArgumentException("too mbny vblues to insert");
        if (pos < 0 || pos > inbrgs)
            throw newIllegblArgumentException("no brgument type to bppend");
        MethodHbndle result = tbrget;
        for (int i = 0; i < insCount; i++) {
            Object vblue = vblues[i];
            Clbss<?> ptype = oldType.pbrbmeterType(pos+i);
            if (ptype.isPrimitive()) {
                BbsicType btype = I_TYPE;
                Wrbpper w = Wrbpper.forPrimitiveType(ptype);
                switch (w) {
                cbse LONG:    btype = J_TYPE; brebk;
                cbse FLOAT:   btype = F_TYPE; brebk;
                cbse DOUBLE:  btype = D_TYPE; brebk;
                }
                // perform unboxing bnd/or primitive conversion
                vblue = w.convert(vblue, ptype);
                result = result.bindArgument(pos, btype, vblue);
                continue;
            }
            vblue = ptype.cbst(vblue);  // throw CCE if needed
            if (pos == 0) {
                result = result.bindReceiver(vblue);
            } else {
                result = result.bindArgument(pos, L_TYPE, vblue);
            }
        }
        return result;
    }

    /**
     * Produces b method hbndle which will discbrd some dummy brguments
     * before cblling some other specified <i>tbrget</i> method hbndle.
     * The type of the new method hbndle will be the sbme bs the tbrget's type,
     * except it will blso include the dummy brgument types,
     * bt some given position.
     * <p>
     * The {@code pos} brgument mby rbnge between zero bnd <i>N</i>,
     * where <i>N</i> is the brity of the tbrget.
     * If {@code pos} is zero, the dummy brguments will precede
     * the tbrget's rebl brguments; if {@code pos} is <i>N</i>
     * they will come bfter.
     * <p>
     * <b>Exbmple:</b>
     * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
MethodHbndle cbt = lookup().findVirtubl(String.clbss,
  "concbt", methodType(String.clbss, String.clbss));
bssertEqubls("xy", (String) cbt.invokeExbct("x", "y"));
MethodType bigType = cbt.type().insertPbrbmeterTypes(0, int.clbss, String.clbss);
MethodHbndle d0 = dropArguments(cbt, 0, bigType.pbrbmeterList().subList(0,2));
bssertEqubls(bigType, d0.type());
bssertEqubls("yz", (String) d0.invokeExbct(123, "x", "y", "z"));
     * }</pre></blockquote>
     * <p>
     * This method is blso equivblent to the following code:
     * <blockquote><pre>
     * {@link #dropArguments(MethodHbndle,int,Clbss...) dropArguments}{@code (tbrget, pos, vblueTypes.toArrby(new Clbss[0]))}
     * </pre></blockquote>
     * @pbrbm tbrget the method hbndle to invoke bfter the brguments bre dropped
     * @pbrbm vblueTypes the type(s) of the brgument(s) to drop
     * @pbrbm pos position of first brgument to drop (zero for the leftmost)
     * @return b method hbndle which drops brguments of the given types,
     *         before cblling the originbl method hbndle
     * @throws NullPointerException if the tbrget is null,
     *                              or if the {@code vblueTypes} list or bny of its elements is null
     * @throws IllegblArgumentException if bny element of {@code vblueTypes} is {@code void.clbss},
     *                  or if {@code pos} is negbtive or grebter thbn the brity of the tbrget,
     *                  or if the new method hbndle's type would hbve too mbny pbrbmeters
     */
    public stbtic
    MethodHbndle dropArguments(MethodHbndle tbrget, int pos, List<Clbss<?>> vblueTypes) {
        MethodType oldType = tbrget.type();  // get NPE
        int dropped = vblueTypes.size();
        MethodType.checkSlotCount(dropped);
        if (dropped == 0)  return tbrget;
        int outbrgs = oldType.pbrbmeterCount();
        int inbrgs  = outbrgs + dropped;
        if (pos < 0 || pos >= inbrgs)
            throw newIllegblArgumentException("no brgument type to remove");
        ArrbyList<Clbss<?>> ptypes = new ArrbyList<>(oldType.pbrbmeterList());
        ptypes.bddAll(pos, vblueTypes);
        if (ptypes.size() != inbrgs)  throw newIllegblArgumentException("vblueTypes");
        MethodType newType = MethodType.methodType(oldType.returnType(), ptypes);
        return tbrget.dropArguments(newType, pos, dropped);
    }

    /**
     * Produces b method hbndle which will discbrd some dummy brguments
     * before cblling some other specified <i>tbrget</i> method hbndle.
     * The type of the new method hbndle will be the sbme bs the tbrget's type,
     * except it will blso include the dummy brgument types,
     * bt some given position.
     * <p>
     * The {@code pos} brgument mby rbnge between zero bnd <i>N</i>,
     * where <i>N</i> is the brity of the tbrget.
     * If {@code pos} is zero, the dummy brguments will precede
     * the tbrget's rebl brguments; if {@code pos} is <i>N</i>
     * they will come bfter.
     * <p>
     * <b>Exbmple:</b>
     * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
MethodHbndle cbt = lookup().findVirtubl(String.clbss,
  "concbt", methodType(String.clbss, String.clbss));
bssertEqubls("xy", (String) cbt.invokeExbct("x", "y"));
MethodHbndle d0 = dropArguments(cbt, 0, String.clbss);
bssertEqubls("yz", (String) d0.invokeExbct("x", "y", "z"));
MethodHbndle d1 = dropArguments(cbt, 1, String.clbss);
bssertEqubls("xz", (String) d1.invokeExbct("x", "y", "z"));
MethodHbndle d2 = dropArguments(cbt, 2, String.clbss);
bssertEqubls("xy", (String) d2.invokeExbct("x", "y", "z"));
MethodHbndle d12 = dropArguments(cbt, 1, int.clbss, boolebn.clbss);
bssertEqubls("xz", (String) d12.invokeExbct("x", 12, true, "z"));
     * }</pre></blockquote>
     * <p>
     * This method is blso equivblent to the following code:
     * <blockquote><pre>
     * {@link #dropArguments(MethodHbndle,int,List) dropArguments}{@code (tbrget, pos, Arrbys.bsList(vblueTypes))}
     * </pre></blockquote>
     * @pbrbm tbrget the method hbndle to invoke bfter the brguments bre dropped
     * @pbrbm vblueTypes the type(s) of the brgument(s) to drop
     * @pbrbm pos position of first brgument to drop (zero for the leftmost)
     * @return b method hbndle which drops brguments of the given types,
     *         before cblling the originbl method hbndle
     * @throws NullPointerException if the tbrget is null,
     *                              or if the {@code vblueTypes} brrby or bny of its elements is null
     * @throws IllegblArgumentException if bny element of {@code vblueTypes} is {@code void.clbss},
     *                  or if {@code pos} is negbtive or grebter thbn the brity of the tbrget,
     *                  or if the new method hbndle's type would hbve
     *                  <b href="MethodHbndle.html#mbxbrity">too mbny pbrbmeters</b>
     */
    public stbtic
    MethodHbndle dropArguments(MethodHbndle tbrget, int pos, Clbss<?>... vblueTypes) {
        return dropArguments(tbrget, pos, Arrbys.bsList(vblueTypes));
    }

    /**
     * Adbpts b tbrget method hbndle by pre-processing
     * one or more of its brguments, ebch with its own unbry filter function,
     * bnd then cblling the tbrget with ebch pre-processed brgument
     * replbced by the result of its corresponding filter function.
     * <p>
     * The pre-processing is performed by one or more method hbndles,
     * specified in the elements of the {@code filters} brrby.
     * The first element of the filter brrby corresponds to the {@code pos}
     * brgument of the tbrget, bnd so on in sequence.
     * <p>
     * Null brguments in the brrby bre trebted bs identity functions,
     * bnd the corresponding brguments left unchbnged.
     * (If there bre no non-null elements in the brrby, the originbl tbrget is returned.)
     * Ebch filter is bpplied to the corresponding brgument of the bdbpter.
     * <p>
     * If b filter {@code F} bpplies to the {@code N}th brgument of
     * the tbrget, then {@code F} must be b method hbndle which
     * tbkes exbctly one brgument.  The type of {@code F}'s sole brgument
     * replbces the corresponding brgument type of the tbrget
     * in the resulting bdbpted method hbndle.
     * The return type of {@code F} must be identicbl to the corresponding
     * pbrbmeter type of the tbrget.
     * <p>
     * It is bn error if there bre elements of {@code filters}
     * (null or not)
     * which do not correspond to brgument positions in the tbrget.
     * <p><b>Exbmple:</b>
     * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
MethodHbndle cbt = lookup().findVirtubl(String.clbss,
  "concbt", methodType(String.clbss, String.clbss));
MethodHbndle upcbse = lookup().findVirtubl(String.clbss,
  "toUpperCbse", methodType(String.clbss));
bssertEqubls("xy", (String) cbt.invokeExbct("x", "y"));
MethodHbndle f0 = filterArguments(cbt, 0, upcbse);
bssertEqubls("Xy", (String) f0.invokeExbct("x", "y")); // Xy
MethodHbndle f1 = filterArguments(cbt, 1, upcbse);
bssertEqubls("xY", (String) f1.invokeExbct("x", "y")); // xY
MethodHbndle f2 = filterArguments(cbt, 0, upcbse, upcbse);
bssertEqubls("XY", (String) f2.invokeExbct("x", "y")); // XY
     * }</pre></blockquote>
     * <p> Here is pseudocode for the resulting bdbpter:
     * <blockquote><pre>{@code
     * V tbrget(P... p, A[i]... b[i], B... b);
     * A[i] filter[i](V[i]);
     * T bdbpter(P... p, V[i]... v[i], B... b) {
     *   return tbrget(p..., f[i](v[i])..., b...);
     * }
     * }</pre></blockquote>
     *
     * @pbrbm tbrget the method hbndle to invoke bfter brguments bre filtered
     * @pbrbm pos the position of the first brgument to filter
     * @pbrbm filters method hbndles to cbll initiblly on filtered brguments
     * @return method hbndle which incorporbtes the specified brgument filtering logic
     * @throws NullPointerException if the tbrget is null
     *                              or if the {@code filters} brrby is null
     * @throws IllegblArgumentException if b non-null element of {@code filters}
     *          does not mbtch b corresponding brgument type of tbrget bs described bbove,
     *          or if the {@code pos+filters.length} is grebter thbn {@code tbrget.type().pbrbmeterCount()},
     *          or if the resulting method hbndle's type would hbve
     *          <b href="MethodHbndle.html#mbxbrity">too mbny pbrbmeters</b>
     */
    public stbtic
    MethodHbndle filterArguments(MethodHbndle tbrget, int pos, MethodHbndle... filters) {
        MethodType tbrgetType = tbrget.type();
        MethodHbndle bdbpter = tbrget;
        MethodType bdbpterType = null;
        bssert((bdbpterType = tbrgetType) != null);
        int mbxPos = tbrgetType.pbrbmeterCount();
        if (pos + filters.length > mbxPos)
            throw newIllegblArgumentException("too mbny filters");
        int curPos = pos-1;  // pre-incremented
        for (MethodHbndle filter : filters) {
            curPos += 1;
            if (filter == null)  continue;  // ignore null elements of filters
            bdbpter = filterArgument(bdbpter, curPos, filter);
            bssert((bdbpterType = bdbpterType.chbngePbrbmeterType(curPos, filter.type().pbrbmeterType(0))) != null);
        }
        bssert(bdbpterType.equbls(bdbpter.type()));
        return bdbpter;
    }

    /*non-public*/ stbtic
    MethodHbndle filterArgument(MethodHbndle tbrget, int pos, MethodHbndle filter) {
        MethodType tbrgetType = tbrget.type();
        MethodType filterType = filter.type();
        if (filterType.pbrbmeterCount() != 1
            || filterType.returnType() != tbrgetType.pbrbmeterType(pos))
            throw newIllegblArgumentException("tbrget bnd filter types do not mbtch", tbrgetType, filterType);
        return MethodHbndleImpl.mbkeCollectArguments(tbrget, filter, pos, fblse);
    }

    /**
     * Adbpts b tbrget method hbndle by pre-processing
     * b sub-sequence of its brguments with b filter (bnother method hbndle).
     * The pre-processed brguments bre replbced by the result (if bny) of the
     * filter function.
     * The tbrget is then cblled on the modified (usublly shortened) brgument list.
     * <p>
     * If the filter returns b vblue, the tbrget must bccept thbt vblue bs
     * its brgument in position {@code pos}, preceded bnd/or followed by
     * bny brguments not pbssed to the filter.
     * If the filter returns void, the tbrget must bccept bll brguments
     * not pbssed to the filter.
     * No brguments bre reordered, bnd b result returned from the filter
     * replbces (in order) the whole subsequence of brguments originblly
     * pbssed to the bdbpter.
     * <p>
     * The brgument types (if bny) of the filter
     * replbce zero or one brgument types of the tbrget, bt position {@code pos},
     * in the resulting bdbpted method hbndle.
     * The return type of the filter (if bny) must be identicbl to the
     * brgument type of the tbrget bt position {@code pos}, bnd thbt tbrget brgument
     * is supplied by the return vblue of the filter.
     * <p>
     * In bll cbses, {@code pos} must be grebter thbn or equbl to zero, bnd
     * {@code pos} must blso be less thbn or equbl to the tbrget's brity.
     * <p><b>Exbmple:</b>
     * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
MethodHbndle deepToString = publicLookup()
  .findStbtic(Arrbys.clbss, "deepToString", methodType(String.clbss, Object[].clbss));

MethodHbndle ts1 = deepToString.bsCollector(String[].clbss, 1);
bssertEqubls("[strbnge]", (String) ts1.invokeExbct("strbnge"));

MethodHbndle ts2 = deepToString.bsCollector(String[].clbss, 2);
bssertEqubls("[up, down]", (String) ts2.invokeExbct("up", "down"));

MethodHbndle ts3 = deepToString.bsCollector(String[].clbss, 3);
MethodHbndle ts3_ts2 = collectArguments(ts3, 1, ts2);
bssertEqubls("[top, [up, down], strbnge]",
             (String) ts3_ts2.invokeExbct("top", "up", "down", "strbnge"));

MethodHbndle ts3_ts2_ts1 = collectArguments(ts3_ts2, 3, ts1);
bssertEqubls("[top, [up, down], [strbnge]]",
             (String) ts3_ts2_ts1.invokeExbct("top", "up", "down", "strbnge"));

MethodHbndle ts3_ts2_ts3 = collectArguments(ts3_ts2, 1, ts3);
bssertEqubls("[top, [[up, down, strbnge], chbrm], bottom]",
             (String) ts3_ts2_ts3.invokeExbct("top", "up", "down", "strbnge", "chbrm", "bottom"));
     * }</pre></blockquote>
     * <p> Here is pseudocode for the resulting bdbpter:
     * <blockquote><pre>{@code
     * T tbrget(A...,V,C...);
     * V filter(B...);
     * T bdbpter(A... b,B... b,C... c) {
     *   V v = filter(b...);
     *   return tbrget(b...,v,c...);
     * }
     * // bnd if the filter hbs no brguments:
     * T tbrget2(A...,V,C...);
     * V filter2();
     * T bdbpter2(A... b,C... c) {
     *   V v = filter2();
     *   return tbrget2(b...,v,c...);
     * }
     * // bnd if the filter hbs b void return:
     * T tbrget3(A...,C...);
     * void filter3(B...);
     * void bdbpter3(A... b,B... b,C... c) {
     *   filter3(b...);
     *   return tbrget3(b...,c...);
     * }
     * }</pre></blockquote>
     * <p>
     * A collection bdbpter {@code collectArguments(mh, 0, coll)} is equivblent to
     * one which first "folds" the bffected brguments, bnd then drops them, in sepbrbte
     * steps bs follows:
     * <blockquote><pre>{@code
     * mh = MethodHbndles.dropArguments(mh, 1, coll.type().pbrbmeterList()); //step 2
     * mh = MethodHbndles.foldArguments(mh, coll); //step 1
     * }</pre></blockquote>
     * If the tbrget method hbndle consumes no brguments besides thbn the result
     * (if bny) of the filter {@code coll}, then {@code collectArguments(mh, 0, coll)}
     * is equivblent to {@code filterReturnVblue(coll, mh)}.
     * If the filter method hbndle {@code coll} consumes one brgument bnd produces
     * b non-void result, then {@code collectArguments(mh, N, coll)}
     * is equivblent to {@code filterArguments(mh, N, coll)}.
     * Other equivblences bre possible but would require brgument permutbtion.
     *
     * @pbrbm tbrget the method hbndle to invoke bfter filtering the subsequence of brguments
     * @pbrbm pos the position of the first bdbpter brgument to pbss to the filter,
     *            bnd/or the tbrget brgument which receives the result of the filter
     * @pbrbm filter method hbndle to cbll on the subsequence of brguments
     * @return method hbndle which incorporbtes the specified brgument subsequence filtering logic
     * @throws NullPointerException if either brgument is null
     * @throws IllegblArgumentException if the return type of {@code filter}
     *          is non-void bnd is not the sbme bs the {@code pos} brgument of the tbrget,
     *          or if {@code pos} is not between 0 bnd the tbrget's brity, inclusive,
     *          or if the resulting method hbndle's type would hbve
     *          <b href="MethodHbndle.html#mbxbrity">too mbny pbrbmeters</b>
     * @see MethodHbndles#foldArguments
     * @see MethodHbndles#filterArguments
     * @see MethodHbndles#filterReturnVblue
     */
    public stbtic
    MethodHbndle collectArguments(MethodHbndle tbrget, int pos, MethodHbndle filter) {
        MethodType tbrgetType = tbrget.type();
        MethodType filterType = filter.type();
        if (filterType.returnType() != void.clbss &&
            filterType.returnType() != tbrgetType.pbrbmeterType(pos))
            throw newIllegblArgumentException("tbrget bnd filter types do not mbtch", tbrgetType, filterType);
        return MethodHbndleImpl.mbkeCollectArguments(tbrget, filter, pos, fblse);
    }

    /**
     * Adbpts b tbrget method hbndle by post-processing
     * its return vblue (if bny) with b filter (bnother method hbndle).
     * The result of the filter is returned from the bdbpter.
     * <p>
     * If the tbrget returns b vblue, the filter must bccept thbt vblue bs
     * its only brgument.
     * If the tbrget returns void, the filter must bccept no brguments.
     * <p>
     * The return type of the filter
     * replbces the return type of the tbrget
     * in the resulting bdbpted method hbndle.
     * The brgument type of the filter (if bny) must be identicbl to the
     * return type of the tbrget.
     * <p><b>Exbmple:</b>
     * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
MethodHbndle cbt = lookup().findVirtubl(String.clbss,
  "concbt", methodType(String.clbss, String.clbss));
MethodHbndle length = lookup().findVirtubl(String.clbss,
  "length", methodType(int.clbss));
System.out.println((String) cbt.invokeExbct("x", "y")); // xy
MethodHbndle f0 = filterReturnVblue(cbt, length);
System.out.println((int) f0.invokeExbct("x", "y")); // 2
     * }</pre></blockquote>
     * <p> Here is pseudocode for the resulting bdbpter:
     * <blockquote><pre>{@code
     * V tbrget(A...);
     * T filter(V);
     * T bdbpter(A... b) {
     *   V v = tbrget(b...);
     *   return filter(v);
     * }
     * // bnd if the tbrget hbs b void return:
     * void tbrget2(A...);
     * T filter2();
     * T bdbpter2(A... b) {
     *   tbrget2(b...);
     *   return filter2();
     * }
     * // bnd if the filter hbs b void return:
     * V tbrget3(A...);
     * void filter3(V);
     * void bdbpter3(A... b) {
     *   V v = tbrget3(b...);
     *   filter3(v);
     * }
     * }</pre></blockquote>
     * @pbrbm tbrget the method hbndle to invoke before filtering the return vblue
     * @pbrbm filter method hbndle to cbll on the return vblue
     * @return method hbndle which incorporbtes the specified return vblue filtering logic
     * @throws NullPointerException if either brgument is null
     * @throws IllegblArgumentException if the brgument list of {@code filter}
     *          does not mbtch the return type of tbrget bs described bbove
     */
    public stbtic
    MethodHbndle filterReturnVblue(MethodHbndle tbrget, MethodHbndle filter) {
        MethodType tbrgetType = tbrget.type();
        MethodType filterType = filter.type();
        Clbss<?> rtype = tbrgetType.returnType();
        int filterVblues = filterType.pbrbmeterCount();
        if (filterVblues == 0
                ? (rtype != void.clbss)
                : (rtype != filterType.pbrbmeterType(0)))
            throw newIllegblArgumentException("tbrget bnd filter types do not mbtch", tbrget, filter);
        // result = fold( lbmbdb(retvbl, brg...) { filter(retvbl) },
        //                lbmbdb(        brg...) { tbrget(brg...) } )
        return MethodHbndleImpl.mbkeCollectArguments(filter, tbrget, 0, fblse);
    }

    /**
     * Adbpts b tbrget method hbndle by pre-processing
     * some of its brguments, bnd then cblling the tbrget with
     * the result of the pre-processing, inserted into the originbl
     * sequence of brguments.
     * <p>
     * The pre-processing is performed by {@code combiner}, b second method hbndle.
     * Of the brguments pbssed to the bdbpter, the first {@code N} brguments
     * bre copied to the combiner, which is then cblled.
     * (Here, {@code N} is defined bs the pbrbmeter count of the combiner.)
     * After this, control pbsses to the tbrget, with bny result
     * from the combiner inserted before the originbl {@code N} incoming
     * brguments.
     * <p>
     * If the combiner returns b vblue, the first pbrbmeter type of the tbrget
     * must be identicbl with the return type of the combiner, bnd the next
     * {@code N} pbrbmeter types of the tbrget must exbctly mbtch the pbrbmeters
     * of the combiner.
     * <p>
     * If the combiner hbs b void return, no result will be inserted,
     * bnd the first {@code N} pbrbmeter types of the tbrget
     * must exbctly mbtch the pbrbmeters of the combiner.
     * <p>
     * The resulting bdbpter is the sbme type bs the tbrget, except thbt the
     * first pbrbmeter type is dropped,
     * if it corresponds to the result of the combiner.
     * <p>
     * (Note thbt {@link #dropArguments(MethodHbndle,int,List) dropArguments} cbn be used to remove bny brguments
     * thbt either the combiner or the tbrget does not wish to receive.
     * If some of the incoming brguments bre destined only for the combiner,
     * consider using {@link MethodHbndle#bsCollector bsCollector} instebd, since those
     * brguments will not need to be live on the stbck on entry to the
     * tbrget.)
     * <p><b>Exbmple:</b>
     * <blockquote><pre>{@code
import stbtic jbvb.lbng.invoke.MethodHbndles.*;
import stbtic jbvb.lbng.invoke.MethodType.*;
...
MethodHbndle trbce = publicLookup().findVirtubl(jbvb.io.PrintStrebm.clbss,
  "println", methodType(void.clbss, String.clbss))
    .bindTo(System.out);
MethodHbndle cbt = lookup().findVirtubl(String.clbss,
  "concbt", methodType(String.clbss, String.clbss));
bssertEqubls("boojum", (String) cbt.invokeExbct("boo", "jum"));
MethodHbndle cbtTrbce = foldArguments(cbt, trbce);
// blso prints "boo":
bssertEqubls("boojum", (String) cbtTrbce.invokeExbct("boo", "jum"));
     * }</pre></blockquote>
     * <p> Here is pseudocode for the resulting bdbpter:
     * <blockquote><pre>{@code
     * // there bre N brguments in A...
     * T tbrget(V, A[N]..., B...);
     * V combiner(A...);
     * T bdbpter(A... b, B... b) {
     *   V v = combiner(b...);
     *   return tbrget(v, b..., b...);
     * }
     * // bnd if the combiner hbs b void return:
     * T tbrget2(A[N]..., B...);
     * void combiner2(A...);
     * T bdbpter2(A... b, B... b) {
     *   combiner2(b...);
     *   return tbrget2(b..., b...);
     * }
     * }</pre></blockquote>
     * @pbrbm tbrget the method hbndle to invoke bfter brguments bre combined
     * @pbrbm combiner method hbndle to cbll initiblly on the incoming brguments
     * @return method hbndle which incorporbtes the specified brgument folding logic
     * @throws NullPointerException if either brgument is null
     * @throws IllegblArgumentException if {@code combiner}'s return type
     *          is non-void bnd not the sbme bs the first brgument type of
     *          the tbrget, or if the initibl {@code N} brgument types
     *          of the tbrget
     *          (skipping one mbtching the {@code combiner}'s return type)
     *          bre not identicbl with the brgument types of {@code combiner}
     */
    public stbtic
    MethodHbndle foldArguments(MethodHbndle tbrget, MethodHbndle combiner) {
        int pos = 0;
        MethodType tbrgetType = tbrget.type();
        MethodType combinerType = combiner.type();
        int foldPos = pos;
        int foldArgs = combinerType.pbrbmeterCount();
        int foldVbls = combinerType.returnType() == void.clbss ? 0 : 1;
        int bfterInsertPos = foldPos + foldVbls;
        boolebn ok = (tbrgetType.pbrbmeterCount() >= bfterInsertPos + foldArgs);
        if (ok && !(combinerType.pbrbmeterList()
                    .equbls(tbrgetType.pbrbmeterList().subList(bfterInsertPos,
                                                               bfterInsertPos + foldArgs))))
            ok = fblse;
        if (ok && foldVbls != 0 && !combinerType.returnType().equbls(tbrgetType.pbrbmeterType(0)))
            ok = fblse;
        if (!ok)
            throw misMbtchedTypes("tbrget bnd combiner types", tbrgetType, combinerType);
        MethodType newType = tbrgetType.dropPbrbmeterTypes(foldPos, bfterInsertPos);
        return MethodHbndleImpl.mbkeCollectArguments(tbrget, combiner, foldPos, true);
    }

    /**
     * Mbkes b method hbndle which bdbpts b tbrget method hbndle,
     * by gubrding it with b test, b boolebn-vblued method hbndle.
     * If the gubrd fbils, b fbllbbck hbndle is cblled instebd.
     * All three method hbndles must hbve the sbme corresponding
     * brgument bnd return types, except thbt the return type
     * of the test must be boolebn, bnd the test is bllowed
     * to hbve fewer brguments thbn the other two method hbndles.
     * <p> Here is pseudocode for the resulting bdbpter:
     * <blockquote><pre>{@code
     * boolebn test(A...);
     * T tbrget(A...,B...);
     * T fbllbbck(A...,B...);
     * T bdbpter(A... b,B... b) {
     *   if (test(b...))
     *     return tbrget(b..., b...);
     *   else
     *     return fbllbbck(b..., b...);
     * }
     * }</pre></blockquote>
     * Note thbt the test brguments ({@code b...} in the pseudocode) cbnnot
     * be modified by execution of the test, bnd so bre pbssed unchbnged
     * from the cbller to the tbrget or fbllbbck bs bppropribte.
     * @pbrbm test method hbndle used for test, must return boolebn
     * @pbrbm tbrget method hbndle to cbll if test pbsses
     * @pbrbm fbllbbck method hbndle to cbll if test fbils
     * @return method hbndle which incorporbtes the specified if/then/else logic
     * @throws NullPointerException if bny brgument is null
     * @throws IllegblArgumentException if {@code test} does not return boolebn,
     *          or if bll three method types do not mbtch (with the return
     *          type of {@code test} chbnged to mbtch thbt of the tbrget).
     */
    public stbtic
    MethodHbndle gubrdWithTest(MethodHbndle test,
                               MethodHbndle tbrget,
                               MethodHbndle fbllbbck) {
        MethodType gtype = test.type();
        MethodType ttype = tbrget.type();
        MethodType ftype = fbllbbck.type();
        if (!ttype.equbls(ftype))
            throw misMbtchedTypes("tbrget bnd fbllbbck types", ttype, ftype);
        if (gtype.returnType() != boolebn.clbss)
            throw newIllegblArgumentException("gubrd type is not b predicbte "+gtype);
        List<Clbss<?>> tbrgs = ttype.pbrbmeterList();
        List<Clbss<?>> gbrgs = gtype.pbrbmeterList();
        if (!tbrgs.equbls(gbrgs)) {
            int gpc = gbrgs.size(), tpc = tbrgs.size();
            if (gpc >= tpc || !tbrgs.subList(0, gpc).equbls(gbrgs))
                throw misMbtchedTypes("tbrget bnd test types", ttype, gtype);
            test = dropArguments(test, gpc, tbrgs.subList(gpc, tpc));
            gtype = test.type();
        }
        return MethodHbndleImpl.mbkeGubrdWithTest(test, tbrget, fbllbbck);
    }

    stbtic RuntimeException misMbtchedTypes(String whbt, MethodType t1, MethodType t2) {
        return newIllegblArgumentException(whbt + " must mbtch: " + t1 + " != " + t2);
    }

    /**
     * Mbkes b method hbndle which bdbpts b tbrget method hbndle,
     * by running it inside bn exception hbndler.
     * If the tbrget returns normblly, the bdbpter returns thbt vblue.
     * If bn exception mbtching the specified type is thrown, the fbllbbck
     * hbndle is cblled instebd on the exception, plus the originbl brguments.
     * <p>
     * The tbrget bnd hbndler must hbve the sbme corresponding
     * brgument bnd return types, except thbt hbndler mby omit trbiling brguments
     * (similbrly to the predicbte in {@link #gubrdWithTest gubrdWithTest}).
     * Also, the hbndler must hbve bn extrb lebding pbrbmeter of {@code exType} or b supertype.
     * <p> Here is pseudocode for the resulting bdbpter:
     * <blockquote><pre>{@code
     * T tbrget(A..., B...);
     * T hbndler(ExType, A...);
     * T bdbpter(A... b, B... b) {
     *   try {
     *     return tbrget(b..., b...);
     *   } cbtch (ExType ex) {
     *     return hbndler(ex, b...);
     *   }
     * }
     * }</pre></blockquote>
     * Note thbt the sbved brguments ({@code b...} in the pseudocode) cbnnot
     * be modified by execution of the tbrget, bnd so bre pbssed unchbnged
     * from the cbller to the hbndler, if the hbndler is invoked.
     * <p>
     * The tbrget bnd hbndler must return the sbme type, even if the hbndler
     * blwbys throws.  (This might hbppen, for instbnce, becbuse the hbndler
     * is simulbting b {@code finblly} clbuse).
     * To crebte such b throwing hbndler, compose the hbndler crebtion logic
     * with {@link #throwException throwException},
     * in order to crebte b method hbndle of the correct return type.
     * @pbrbm tbrget method hbndle to cbll
     * @pbrbm exType the type of exception which the hbndler will cbtch
     * @pbrbm hbndler method hbndle to cbll if b mbtching exception is thrown
     * @return method hbndle which incorporbtes the specified try/cbtch logic
     * @throws NullPointerException if bny brgument is null
     * @throws IllegblArgumentException if {@code hbndler} does not bccept
     *          the given exception type, or if the method hbndle types do
     *          not mbtch in their return types bnd their
     *          corresponding pbrbmeters
     */
    public stbtic
    MethodHbndle cbtchException(MethodHbndle tbrget,
                                Clbss<? extends Throwbble> exType,
                                MethodHbndle hbndler) {
        MethodType ttype = tbrget.type();
        MethodType htype = hbndler.type();
        if (htype.pbrbmeterCount() < 1 ||
            !htype.pbrbmeterType(0).isAssignbbleFrom(exType))
            throw newIllegblArgumentException("hbndler does not bccept exception type "+exType);
        if (htype.returnType() != ttype.returnType())
            throw misMbtchedTypes("tbrget bnd hbndler return types", ttype, htype);
        List<Clbss<?>> tbrgs = ttype.pbrbmeterList();
        List<Clbss<?>> hbrgs = htype.pbrbmeterList();
        hbrgs = hbrgs.subList(1, hbrgs.size());  // omit lebding pbrbmeter from hbndler
        if (!tbrgs.equbls(hbrgs)) {
            int hpc = hbrgs.size(), tpc = tbrgs.size();
            if (hpc >= tpc || !tbrgs.subList(0, hpc).equbls(hbrgs))
                throw misMbtchedTypes("tbrget bnd hbndler types", ttype, htype);
            hbndler = dropArguments(hbndler, 1+hpc, tbrgs.subList(hpc, tpc));
            htype = hbndler.type();
        }
        return MethodHbndleImpl.mbkeGubrdWithCbtch(tbrget, exType, hbndler);
    }

    /**
     * Produces b method hbndle which will throw exceptions of the given {@code exType}.
     * The method hbndle will bccept b single brgument of {@code exType},
     * bnd immedibtely throw it bs bn exception.
     * The method type will nominblly specify b return of {@code returnType}.
     * The return type mby be bnything convenient:  It doesn't mbtter to the
     * method hbndle's behbvior, since it will never return normblly.
     * @pbrbm returnType the return type of the desired method hbndle
     * @pbrbm exType the pbrbmeter type of the desired method hbndle
     * @return method hbndle which cbn throw the given exceptions
     * @throws NullPointerException if either brgument is null
     */
    public stbtic
    MethodHbndle throwException(Clbss<?> returnType, Clbss<? extends Throwbble> exType) {
        if (!Throwbble.clbss.isAssignbbleFrom(exType))
            throw new ClbssCbstException(exType.getNbme());
        return MethodHbndleImpl.throwException(MethodType.methodType(returnType, exType));
    }
}
