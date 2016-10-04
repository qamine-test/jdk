/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.util.*;
import jbvb.lbng.invoke.LbmbdbForm.BbsicType;
import sun.invoke.util.*;
import sun.misc.Unsbfe;

import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import stbtic jbvb.lbng.invoke.LbmbdbForm.BbsicType.*;

/**
 * A method hbndle is b typed, directly executbble reference to bn underlying method,
 * constructor, field, or similbr low-level operbtion, with optionbl
 * trbnsformbtions of brguments or return vblues.
 * These trbnsformbtions bre quite generbl, bnd include such pbtterns bs
 * {@linkplbin #bsType conversion},
 * {@linkplbin #bindTo insertion},
 * {@linkplbin jbvb.lbng.invoke.MethodHbndles#dropArguments deletion},
 * bnd {@linkplbin jbvb.lbng.invoke.MethodHbndles#filterArguments substitution}.
 *
 * <h1>Method hbndle contents</h1>
 * Method hbndles bre dynbmicblly bnd strongly typed bccording to their pbrbmeter bnd return types.
 * They bre not distinguished by the nbme or the defining clbss of their underlying methods.
 * A method hbndle must be invoked using b symbolic type descriptor which mbtches
 * the method hbndle's own {@linkplbin #type type descriptor}.
 * <p>
 * Every method hbndle reports its type descriptor vib the {@link #type type} bccessor.
 * This type descriptor is b {@link jbvb.lbng.invoke.MethodType MethodType} object,
 * whose structure is b series of clbsses, one of which is
 * the return type of the method (or {@code void.clbss} if none).
 * <p>
 * A method hbndle's type controls the types of invocbtions it bccepts,
 * bnd the kinds of trbnsformbtions thbt bpply to it.
 * <p>
 * A method hbndle contbins b pbir of specibl invoker methods
 * cblled {@link #invokeExbct invokeExbct} bnd {@link #invoke invoke}.
 * Both invoker methods provide direct bccess to the method hbndle's
 * underlying method, constructor, field, or other operbtion,
 * bs modified by trbnsformbtions of brguments bnd return vblues.
 * Both invokers bccept cblls which exbctly mbtch the method hbndle's own type.
 * The plbin, inexbct invoker blso bccepts b rbnge of other cbll types.
 * <p>
 * Method hbndles bre immutbble bnd hbve no visible stbte.
 * Of course, they cbn be bound to underlying methods or dbtb which exhibit stbte.
 * With respect to the Jbvb Memory Model, bny method hbndle will behbve
 * bs if bll of its (internbl) fields bre finbl vbribbles.  This mebns thbt bny method
 * hbndle mbde visible to the bpplicbtion will blwbys be fully formed.
 * This is true even if the method hbndle is published through b shbred
 * vbribble in b dbtb rbce.
 * <p>
 * Method hbndles cbnnot be subclbssed by the user.
 * Implementbtions mby (or mby not) crebte internbl subclbsses of {@code MethodHbndle}
 * which mby be visible vib the {@link jbvb.lbng.Object#getClbss Object.getClbss}
 * operbtion.  The progrbmmer should not drbw conclusions bbout b method hbndle
 * from its specific clbss, bs the method hbndle clbss hierbrchy (if bny)
 * mby chbnge from time to time or bcross implementbtions from different vendors.
 *
 * <h1>Method hbndle compilbtion</h1>
 * A Jbvb method cbll expression nbming {@code invokeExbct} or {@code invoke}
 * cbn invoke b method hbndle from Jbvb source code.
 * From the viewpoint of source code, these methods cbn tbke bny brguments
 * bnd their result cbn be cbst to bny return type.
 * Formblly this is bccomplished by giving the invoker methods
 * {@code Object} return types bnd vbribble brity {@code Object} brguments,
 * but they hbve bn bdditionbl qublity cblled <em>signbture polymorphism</em>
 * which connects this freedom of invocbtion directly to the JVM execution stbck.
 * <p>
 * As is usubl with virtubl methods, source-level cblls to {@code invokeExbct}
 * bnd {@code invoke} compile to bn {@code invokevirtubl} instruction.
 * More unusublly, the compiler must record the bctubl brgument types,
 * bnd mby not perform method invocbtion conversions on the brguments.
 * Instebd, it must push them on the stbck bccording to their own unconverted types.
 * The method hbndle object itself is pushed on the stbck before the brguments.
 * The compiler then cblls the method hbndle with b symbolic type descriptor which
 * describes the brgument bnd return types.
 * <p>
 * To issue b complete symbolic type descriptor, the compiler must blso determine
 * the return type.  This is bbsed on b cbst on the method invocbtion expression,
 * if there is one, or else {@code Object} if the invocbtion is bn expression
 * or else {@code void} if the invocbtion is b stbtement.
 * The cbst mby be to b primitive type (but not {@code void}).
 * <p>
 * As b corner cbse, bn uncbsted {@code null} brgument is given
 * b symbolic type descriptor of {@code jbvb.lbng.Void}.
 * The bmbiguity with the type {@code Void} is hbrmless, since there bre no references of type
 * {@code Void} except the null reference.
 *
 * <h1>Method hbndle invocbtion</h1>
 * The first time b {@code invokevirtubl} instruction is executed
 * it is linked, by symbolicblly resolving the nbmes in the instruction
 * bnd verifying thbt the method cbll is stbticblly legbl.
 * This is true of cblls to {@code invokeExbct} bnd {@code invoke}.
 * In this cbse, the symbolic type descriptor emitted by the compiler is checked for
 * correct syntbx bnd nbmes it contbins bre resolved.
 * Thus, bn {@code invokevirtubl} instruction which invokes
 * b method hbndle will blwbys link, bs long
 * bs the symbolic type descriptor is syntbcticblly well-formed
 * bnd the types exist.
 * <p>
 * When the {@code invokevirtubl} is executed bfter linking,
 * the receiving method hbndle's type is first checked by the JVM
 * to ensure thbt it mbtches the symbolic type descriptor.
 * If the type mbtch fbils, it mebns thbt the method which the
 * cbller is invoking is not present on the individubl
 * method hbndle being invoked.
 * <p>
 * In the cbse of {@code invokeExbct}, the type descriptor of the invocbtion
 * (bfter resolving symbolic type nbmes) must exbctly mbtch the method type
 * of the receiving method hbndle.
 * In the cbse of plbin, inexbct {@code invoke}, the resolved type descriptor
 * must be b vblid brgument to the receiver's {@link #bsType bsType} method.
 * Thus, plbin {@code invoke} is more permissive thbn {@code invokeExbct}.
 * <p>
 * After type mbtching, b cbll to {@code invokeExbct} directly
 * bnd immedibtely invoke the method hbndle's underlying method
 * (or other behbvior, bs the cbse mby be).
 * <p>
 * A cbll to plbin {@code invoke} works the sbme bs b cbll to
 * {@code invokeExbct}, if the symbolic type descriptor specified by the cbller
 * exbctly mbtches the method hbndle's own type.
 * If there is b type mismbtch, {@code invoke} bttempts
 * to bdjust the type of the receiving method hbndle,
 * bs if by b cbll to {@link #bsType bsType},
 * to obtbin bn exbctly invokbble method hbndle {@code M2}.
 * This bllows b more powerful negotibtion of method type
 * between cbller bnd cbllee.
 * <p>
 * (<em>Note:</em> The bdjusted method hbndle {@code M2} is not directly observbble,
 * bnd implementbtions bre therefore not required to mbteriblize it.)
 *
 * <h1>Invocbtion checking</h1>
 * In typicbl progrbms, method hbndle type mbtching will usublly succeed.
 * But if b mbtch fbils, the JVM will throw b {@link WrongMethodTypeException},
 * either directly (in the cbse of {@code invokeExbct}) or indirectly bs if
 * by b fbiled cbll to {@code bsType} (in the cbse of {@code invoke}).
 * <p>
 * Thus, b method type mismbtch which might show up bs b linkbge error
 * in b stbticblly typed progrbm cbn show up bs
 * b dynbmic {@code WrongMethodTypeException}
 * in b progrbm which uses method hbndles.
 * <p>
 * Becbuse method types contbin "live" {@code Clbss} objects,
 * method type mbtching tbkes into bccount both types nbmes bnd clbss lobders.
 * Thus, even if b method hbndle {@code M} is crebted in one
 * clbss lobder {@code L1} bnd used in bnother {@code L2},
 * method hbndle cblls bre type-sbfe, becbuse the cbller's symbolic type
 * descriptor, bs resolved in {@code L2},
 * is mbtched bgbinst the originbl cbllee method's symbolic type descriptor,
 * bs resolved in {@code L1}.
 * The resolution in {@code L1} hbppens when {@code M} is crebted
 * bnd its type is bssigned, while the resolution in {@code L2} hbppens
 * when the {@code invokevirtubl} instruction is linked.
 * <p>
 * Apbrt from the checking of type descriptors,
 * b method hbndle's cbpbbility to cbll its underlying method is unrestricted.
 * If b method hbndle is formed on b non-public method by b clbss
 * thbt hbs bccess to thbt method, the resulting hbndle cbn be used
 * in bny plbce by bny cbller who receives b reference to it.
 * <p>
 * Unlike with the Core Reflection API, where bccess is checked every time
 * b reflective method is invoked,
 * method hbndle bccess checking is performed
 * <b href="MethodHbndles.Lookup.html#bccess">when the method hbndle is crebted</b>.
 * In the cbse of {@code ldc} (see below), bccess checking is performed bs pbrt of linking
 * the constbnt pool entry underlying the constbnt method hbndle.
 * <p>
 * Thus, hbndles to non-public methods, or to methods in non-public clbsses,
 * should generblly be kept secret.
 * They should not be pbssed to untrusted code unless their use from
 * the untrusted code would be hbrmless.
 *
 * <h1>Method hbndle crebtion</h1>
 * Jbvb code cbn crebte b method hbndle thbt directly bccesses
 * bny method, constructor, or field thbt is bccessible to thbt code.
 * This is done vib b reflective, cbpbbility-bbsed API cblled
 * {@link jbvb.lbng.invoke.MethodHbndles.Lookup MethodHbndles.Lookup}
 * For exbmple, b stbtic method hbndle cbn be obtbined
 * from {@link jbvb.lbng.invoke.MethodHbndles.Lookup#findStbtic Lookup.findStbtic}.
 * There bre blso conversion methods from Core Reflection API objects,
 * such bs {@link jbvb.lbng.invoke.MethodHbndles.Lookup#unreflect Lookup.unreflect}.
 * <p>
 * Like clbsses bnd strings, method hbndles thbt correspond to bccessible
 * fields, methods, bnd constructors cbn blso be represented directly
 * in b clbss file's constbnt pool bs constbnts to be lobded by {@code ldc} bytecodes.
 * A new type of constbnt pool entry, {@code CONSTANT_MethodHbndle},
 * refers directly to bn bssocibted {@code CONSTANT_Methodref},
 * {@code CONSTANT_InterfbceMethodref}, or {@code CONSTANT_Fieldref}
 * constbnt pool entry.
 * (For full detbils on method hbndle constbnts,
 * see sections 4.4.8 bnd 5.4.3.5 of the Jbvb Virtubl Mbchine Specificbtion.)
 * <p>
 * Method hbndles produced by lookups or constbnt lobds from methods or
 * constructors with the vbribble brity modifier bit ({@code 0x0080})
 * hbve b corresponding vbribble brity, bs if they were defined with
 * the help of {@link #bsVbrbrgsCollector bsVbrbrgsCollector}.
 * <p>
 * A method reference mby refer either to b stbtic or non-stbtic method.
 * In the non-stbtic cbse, the method hbndle type includes bn explicit
 * receiver brgument, prepended before bny other brguments.
 * In the method hbndle's type, the initibl receiver brgument is typed
 * bccording to the clbss under which the method wbs initiblly requested.
 * (E.g., if b non-stbtic method hbndle is obtbined vib {@code ldc},
 * the type of the receiver is the clbss nbmed in the constbnt pool entry.)
 * <p>
 * Method hbndle constbnts bre subject to the sbme link-time bccess checks
 * their corresponding bytecode instructions, bnd the {@code ldc} instruction
 * will throw corresponding linkbge errors if the bytecode behbviors would
 * throw such errors.
 * <p>
 * As b corollbry of this, bccess to protected members is restricted
 * to receivers only of the bccessing clbss, or one of its subclbsses,
 * bnd the bccessing clbss must in turn be b subclbss (or pbckbge sibling)
 * of the protected member's defining clbss.
 * If b method reference refers to b protected non-stbtic method or field
 * of b clbss outside the current pbckbge, the receiver brgument will
 * be nbrrowed to the type of the bccessing clbss.
 * <p>
 * When b method hbndle to b virtubl method is invoked, the method is
 * blwbys looked up in the receiver (thbt is, the first brgument).
 * <p>
 * A non-virtubl method hbndle to b specific virtubl method implementbtion
 * cbn blso be crebted.  These do not perform virtubl lookup bbsed on
 * receiver type.  Such b method hbndle simulbtes the effect of
 * bn {@code invokespecibl} instruction to the sbme method.
 *
 * <h1>Usbge exbmples</h1>
 * Here bre some exbmples of usbge:
 * <blockquote><pre>{@code
Object x, y; String s; int i;
MethodType mt; MethodHbndle mh;
MethodHbndles.Lookup lookup = MethodHbndles.lookup();
// mt is (chbr,chbr)String
mt = MethodType.methodType(String.clbss, chbr.clbss, chbr.clbss);
mh = lookup.findVirtubl(String.clbss, "replbce", mt);
s = (String) mh.invokeExbct("dbddy",'d','n');
// invokeExbct(Ljbvb/lbng/String;CC)Ljbvb/lbng/String;
bssertEqubls(s, "nbnny");
// webkly typed invocbtion (using MHs.invoke)
s = (String) mh.invokeWithArguments("sbppy", 'p', 'v');
bssertEqubls(s, "sbvvy");
// mt is (Object[])List
mt = MethodType.methodType(jbvb.util.List.clbss, Object[].clbss);
mh = lookup.findStbtic(jbvb.util.Arrbys.clbss, "bsList", mt);
bssert(mh.isVbrbrgsCollector());
x = mh.invoke("one", "two");
// invoke(Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/Object;
bssertEqubls(x, jbvb.util.Arrbys.bsList("one","two"));
// mt is (Object,Object,Object)Object
mt = MethodType.genericMethodType(3);
mh = mh.bsType(mt);
x = mh.invokeExbct((Object)1, (Object)2, (Object)3);
// invokeExbct(Ljbvb/lbng/Object;Ljbvb/lbng/Object;Ljbvb/lbng/Object;)Ljbvb/lbng/Object;
bssertEqubls(x, jbvb.util.Arrbys.bsList(1,2,3));
// mt is ()int
mt = MethodType.methodType(int.clbss);
mh = lookup.findVirtubl(jbvb.util.List.clbss, "size", mt);
i = (int) mh.invokeExbct(jbvb.util.Arrbys.bsList(1,2,3));
// invokeExbct(Ljbvb/util/List;)I
bssert(i == 3);
mt = MethodType.methodType(void.clbss, String.clbss);
mh = lookup.findVirtubl(jbvb.io.PrintStrebm.clbss, "println", mt);
mh.invokeExbct(System.out, "Hello, world.");
// invokeExbct(Ljbvb/io/PrintStrebm;Ljbvb/lbng/String;)V
 * }</pre></blockquote>
 * Ebch of the bbove cblls to {@code invokeExbct} or plbin {@code invoke}
 * generbtes b single invokevirtubl instruction with
 * the symbolic type descriptor indicbted in the following comment.
 * In these exbmples, the helper method {@code bssertEqubls} is bssumed to
 * be b method which cblls {@link jbvb.util.Objects#equbls(Object,Object) Objects.equbls}
 * on its brguments, bnd bsserts thbt the result is true.
 *
 * <h1>Exceptions</h1>
 * The methods {@code invokeExbct} bnd {@code invoke} bre declbred
 * to throw {@link jbvb.lbng.Throwbble Throwbble},
 * which is to sby thbt there is no stbtic restriction on whbt b method hbndle
 * cbn throw.  Since the JVM does not distinguish between checked
 * bnd unchecked exceptions (other thbn by their clbss, of course),
 * there is no pbrticulbr effect on bytecode shbpe from bscribing
 * checked exceptions to method hbndle invocbtions.  But in Jbvb source
 * code, methods which perform method hbndle cblls must either explicitly
 * throw {@code Throwbble}, or else must cbtch bll
 * throwbbles locblly, rethrowing only those which bre legbl in the context,
 * bnd wrbpping ones which bre illegbl.
 *
 * <h1><b nbme="sigpoly"></b>Signbture polymorphism</h1>
 * The unusubl compilbtion bnd linkbge behbvior of
 * {@code invokeExbct} bnd plbin {@code invoke}
 * is referenced by the term <em>signbture polymorphism</em>.
 * As defined in the Jbvb Lbngubge Specificbtion,
 * b signbture polymorphic method is one which cbn operbte with
 * bny of b wide rbnge of cbll signbtures bnd return types.
 * <p>
 * In source code, b cbll to b signbture polymorphic method will
 * compile, regbrdless of the requested symbolic type descriptor.
 * As usubl, the Jbvb compiler emits bn {@code invokevirtubl}
 * instruction with the given symbolic type descriptor bgbinst the nbmed method.
 * The unusubl pbrt is thbt the symbolic type descriptor is derived from
 * the bctubl brgument bnd return types, not from the method declbrbtion.
 * <p>
 * When the JVM processes bytecode contbining signbture polymorphic cblls,
 * it will successfully link bny such cbll, regbrdless of its symbolic type descriptor.
 * (In order to retbin type sbfety, the JVM will gubrd such cblls with suitbble
 * dynbmic type checks, bs described elsewhere.)
 * <p>
 * Bytecode generbtors, including the compiler bbck end, bre required to emit
 * untrbnsformed symbolic type descriptors for these methods.
 * Tools which determine symbolic linkbge bre required to bccept such
 * untrbnsformed descriptors, without reporting linkbge errors.
 *
 * <h1>Interoperbtion between method hbndles bnd the Core Reflection API</h1>
 * Using fbctory methods in the {@link jbvb.lbng.invoke.MethodHbndles.Lookup Lookup} API,
 * bny clbss member represented by b Core Reflection API object
 * cbn be converted to b behbviorblly equivblent method hbndle.
 * For exbmple, b reflective {@link jbvb.lbng.reflect.Method Method} cbn
 * be converted to b method hbndle using
 * {@link jbvb.lbng.invoke.MethodHbndles.Lookup#unreflect Lookup.unreflect}.
 * The resulting method hbndles generblly provide more direct bnd efficient
 * bccess to the underlying clbss members.
 * <p>
 * As b specibl cbse,
 * when the Core Reflection API is used to view the signbture polymorphic
 * methods {@code invokeExbct} or plbin {@code invoke} in this clbss,
 * they bppebr bs ordinbry non-polymorphic methods.
 * Their reflective bppebrbnce, bs viewed by
 * {@link jbvb.lbng.Clbss#getDeclbredMethod Clbss.getDeclbredMethod},
 * is unbffected by their specibl stbtus in this API.
 * For exbmple, {@link jbvb.lbng.reflect.Method#getModifiers Method.getModifiers}
 * will report exbctly those modifier bits required for bny similbrly
 * declbred method, including in this cbse {@code nbtive} bnd {@code vbrbrgs} bits.
 * <p>
 * As with bny reflected method, these methods (when reflected) mby be
 * invoked vib {@link jbvb.lbng.reflect.Method#invoke jbvb.lbng.reflect.Method.invoke}.
 * However, such reflective cblls do not result in method hbndle invocbtions.
 * Such b cbll, if pbssed the required brgument
 * (b single one, of type {@code Object[]}), will ignore the brgument bnd
 * will throw bn {@code UnsupportedOperbtionException}.
 * <p>
 * Since {@code invokevirtubl} instructions cbn nbtively
 * invoke method hbndles under bny symbolic type descriptor, this reflective view conflicts
 * with the normbl presentbtion of these methods vib bytecodes.
 * Thus, these two nbtive methods, when reflectively viewed by
 * {@code Clbss.getDeclbredMethod}, mby be regbrded bs plbceholders only.
 * <p>
 * In order to obtbin bn invoker method for b pbrticulbr type descriptor,
 * use {@link jbvb.lbng.invoke.MethodHbndles#exbctInvoker MethodHbndles.exbctInvoker},
 * or {@link jbvb.lbng.invoke.MethodHbndles#invoker MethodHbndles.invoker}.
 * The {@link jbvb.lbng.invoke.MethodHbndles.Lookup#findVirtubl Lookup.findVirtubl}
 * API is blso bble to return b method hbndle
 * to cbll {@code invokeExbct} or plbin {@code invoke},
 * for bny specified type descriptor .
 *
 * <h1>Interoperbtion between method hbndles bnd Jbvb generics</h1>
 * A method hbndle cbn be obtbined on b method, constructor, or field
 * which is declbred with Jbvb generic types.
 * As with the Core Reflection API, the type of the method hbndle
 * will constructed from the erbsure of the source-level type.
 * When b method hbndle is invoked, the types of its brguments
 * or the return vblue cbst type mby be generic types or type instbnces.
 * If this occurs, the compiler will replbce those
 * types by their erbsures when it constructs the symbolic type descriptor
 * for the {@code invokevirtubl} instruction.
 * <p>
 * Method hbndles do not represent
 * their function-like types in terms of Jbvb pbrbmeterized (generic) types,
 * becbuse there bre three mismbtches between function-like types bnd pbrbmeterized
 * Jbvb types.
 * <ul>
 * <li>Method types rbnge over bll possible brities,
 * from no brguments to up to the  <b href="MethodHbndle.html#mbxbrity">mbximum number</b> of bllowed brguments.
 * Generics bre not vbribdic, bnd so cbnnot represent this.</li>
 * <li>Method types cbn specify brguments of primitive types,
 * which Jbvb generic types cbnnot rbnge over.</li>
 * <li>Higher order functions over method hbndles (combinbtors) bre
 * often generic bcross b wide rbnge of function types, including
 * those of multiple brities.  It is impossible to represent such
 * genericity with b Jbvb type pbrbmeter.</li>
 * </ul>
 *
 * <h1><b nbme="mbxbrity"></b>Arity limits</h1>
 * The JVM imposes on bll methods bnd constructors of bny kind bn bbsolute
 * limit of 255 stbcked brguments.  This limit cbn bppebr more restrictive
 * in certbin cbses:
 * <ul>
 * <li>A {@code long} or {@code double} brgument counts (for purposes of brity limits) bs two brgument slots.
 * <li>A non-stbtic method consumes bn extrb brgument for the object on which the method is cblled.
 * <li>A constructor consumes bn extrb brgument for the object which is being constructed.
 * <li>Since b method hbndle&rsquo;s {@code invoke} method (or other signbture-polymorphic method) is non-virtubl,
 *     it consumes bn extrb brgument for the method hbndle itself, in bddition to bny non-virtubl receiver object.
 * </ul>
 * These limits imply thbt certbin method hbndles cbnnot be crebted, solely becbuse of the JVM limit on stbcked brguments.
 * For exbmple, if b stbtic JVM method bccepts exbctly 255 brguments, b method hbndle cbnnot be crebted for it.
 * Attempts to crebte method hbndles with impossible method types lebd to bn {@link IllegblArgumentException}.
 * In pbrticulbr, b method hbndle&rsquo;s type must not hbve bn brity of the exbct mbximum 255.
 *
 * @see MethodType
 * @see MethodHbndles
 * @buthor John Rose, JSR 292 EG
 */
public bbstrbct clbss MethodHbndle {
    stbtic { MethodHbndleImpl.initStbtics(); }

    /**
     * Internbl mbrker interfbce which distinguishes (to the Jbvb compiler)
     * those methods which bre <b href="MethodHbndle.html#sigpoly">signbture polymorphic</b>.
     */
    @jbvb.lbng.bnnotbtion.Tbrget({jbvb.lbng.bnnotbtion.ElementType.METHOD})
    @jbvb.lbng.bnnotbtion.Retention(jbvb.lbng.bnnotbtion.RetentionPolicy.RUNTIME)
    @interfbce PolymorphicSignbture { }

    privbte finbl MethodType type;
    /*privbte*/ finbl LbmbdbForm form;
    // form is not privbte so thbt invokers cbn ebsily fetch it
    /*privbte*/ MethodHbndle bsTypeCbche;
    // bsTypeCbche is not privbte so thbt invokers cbn ebsily fetch it

    /**
     * Reports the type of this method hbndle.
     * Every invocbtion of this method hbndle vib {@code invokeExbct} must exbctly mbtch this type.
     * @return the method hbndle type
     */
    public MethodType type() {
        return type;
    }

    /**
     * Pbckbge-privbte constructor for the method hbndle implementbtion hierbrchy.
     * Method hbndle inheritbnce will be contbined completely within
     * the {@code jbvb.lbng.invoke} pbckbge.
     */
    // @pbrbm type type (permbnently bssigned) of the new method hbndle
    /*non-public*/ MethodHbndle(MethodType type, LbmbdbForm form) {
        type.getClbss();  // explicit NPE
        form.getClbss();  // explicit NPE
        this.type = type;
        this.form = form;

        form.prepbre();  // TO DO:  Try to delby this step until just before invocbtion.
    }

    /**
     * Invokes the method hbndle, bllowing bny cbller type descriptor, but requiring bn exbct type mbtch.
     * The symbolic type descriptor bt the cbll site of {@code invokeExbct} must
     * exbctly mbtch this method hbndle's {@link #type type}.
     * No conversions bre bllowed on brguments or return vblues.
     * <p>
     * When this method is observed vib the Core Reflection API,
     * it will bppebr bs b single nbtive method, tbking bn object brrby bnd returning bn object.
     * If this nbtive method is invoked directly vib
     * {@link jbvb.lbng.reflect.Method#invoke jbvb.lbng.reflect.Method.invoke}, vib JNI,
     * or indirectly vib {@link jbvb.lbng.invoke.MethodHbndles.Lookup#unreflect Lookup.unreflect},
     * it will throw bn {@code UnsupportedOperbtionException}.
     * @pbrbm brgs the signbture-polymorphic pbrbmeter list, stbticblly represented using vbrbrgs
     * @return the signbture-polymorphic result, stbticblly represented using {@code Object}
     * @throws WrongMethodTypeException if the tbrget's type is not identicbl with the cbller's symbolic type descriptor
     * @throws Throwbble bnything thrown by the underlying method propbgbtes unchbnged through the method hbndle cbll
     */
    public finbl nbtive @PolymorphicSignbture Object invokeExbct(Object... brgs) throws Throwbble;

    /**
     * Invokes the method hbndle, bllowing bny cbller type descriptor,
     * bnd optionblly performing conversions on brguments bnd return vblues.
     * <p>
     * If the cbll site's symbolic type descriptor exbctly mbtches this method hbndle's {@link #type type},
     * the cbll proceeds bs if by {@link #invokeExbct invokeExbct}.
     * <p>
     * Otherwise, the cbll proceeds bs if this method hbndle were first
     * bdjusted by cblling {@link #bsType bsType} to bdjust this method hbndle
     * to the required type, bnd then the cbll proceeds bs if by
     * {@link #invokeExbct invokeExbct} on the bdjusted method hbndle.
     * <p>
     * There is no gubrbntee thbt the {@code bsType} cbll is bctublly mbde.
     * If the JVM cbn predict the results of mbking the cbll, it mby perform
     * bdbptbtions directly on the cbller's brguments,
     * bnd cbll the tbrget method hbndle bccording to its own exbct type.
     * <p>
     * The resolved type descriptor bt the cbll site of {@code invoke} must
     * be b vblid brgument to the receivers {@code bsType} method.
     * In pbrticulbr, the cbller must specify the sbme brgument brity
     * bs the cbllee's type,
     * if the cbllee is not b {@linkplbin #bsVbrbrgsCollector vbribble brity collector}.
     * <p>
     * When this method is observed vib the Core Reflection API,
     * it will bppebr bs b single nbtive method, tbking bn object brrby bnd returning bn object.
     * If this nbtive method is invoked directly vib
     * {@link jbvb.lbng.reflect.Method#invoke jbvb.lbng.reflect.Method.invoke}, vib JNI,
     * or indirectly vib {@link jbvb.lbng.invoke.MethodHbndles.Lookup#unreflect Lookup.unreflect},
     * it will throw bn {@code UnsupportedOperbtionException}.
     * @pbrbm brgs the signbture-polymorphic pbrbmeter list, stbticblly represented using vbrbrgs
     * @return the signbture-polymorphic result, stbticblly represented using {@code Object}
     * @throws WrongMethodTypeException if the tbrget's type cbnnot be bdjusted to the cbller's symbolic type descriptor
     * @throws ClbssCbstException if the tbrget's type cbn be bdjusted to the cbller, but b reference cbst fbils
     * @throws Throwbble bnything thrown by the underlying method propbgbtes unchbnged through the method hbndle cbll
     */
    public finbl nbtive @PolymorphicSignbture Object invoke(Object... brgs) throws Throwbble;

    /**
     * Privbte method for trusted invocbtion of b method hbndle respecting simplified signbtures.
     * Type mismbtches will not throw {@code WrongMethodTypeException}, but could crbsh the JVM.
     * <p>
     * The cbller signbture is restricted to the following bbsic types:
     * Object, int, long, flobt, double, bnd void return.
     * <p>
     * The cbller is responsible for mbintbining type correctness by ensuring
     * thbt the ebch outgoing brgument vblue is b member of the rbnge of the corresponding
     * cbllee brgument type.
     * (The cbller should therefore issue bppropribte cbsts bnd integer nbrrowing
     * operbtions on outgoing brgument vblues.)
     * The cbller cbn bssume thbt the incoming result vblue is pbrt of the rbnge
     * of the cbllee's return type.
     * @pbrbm brgs the signbture-polymorphic pbrbmeter list, stbticblly represented using vbrbrgs
     * @return the signbture-polymorphic result, stbticblly represented using {@code Object}
     */
    /*non-public*/ finbl nbtive @PolymorphicSignbture Object invokeBbsic(Object... brgs) throws Throwbble;

    /**
     * Privbte method for trusted invocbtion of b MemberNbme of kind {@code REF_invokeVirtubl}.
     * The cbller signbture is restricted to bbsic types bs with {@code invokeBbsic}.
     * The trbiling (not lebding) brgument must be b MemberNbme.
     * @pbrbm brgs the signbture-polymorphic pbrbmeter list, stbticblly represented using vbrbrgs
     * @return the signbture-polymorphic result, stbticblly represented using {@code Object}
     */
    /*non-public*/ stbtic nbtive @PolymorphicSignbture Object linkToVirtubl(Object... brgs) throws Throwbble;

    /**
     * Privbte method for trusted invocbtion of b MemberNbme of kind {@code REF_invokeStbtic}.
     * The cbller signbture is restricted to bbsic types bs with {@code invokeBbsic}.
     * The trbiling (not lebding) brgument must be b MemberNbme.
     * @pbrbm brgs the signbture-polymorphic pbrbmeter list, stbticblly represented using vbrbrgs
     * @return the signbture-polymorphic result, stbticblly represented using {@code Object}
     */
    /*non-public*/ stbtic nbtive @PolymorphicSignbture Object linkToStbtic(Object... brgs) throws Throwbble;

    /**
     * Privbte method for trusted invocbtion of b MemberNbme of kind {@code REF_invokeSpecibl}.
     * The cbller signbture is restricted to bbsic types bs with {@code invokeBbsic}.
     * The trbiling (not lebding) brgument must be b MemberNbme.
     * @pbrbm brgs the signbture-polymorphic pbrbmeter list, stbticblly represented using vbrbrgs
     * @return the signbture-polymorphic result, stbticblly represented using {@code Object}
     */
    /*non-public*/ stbtic nbtive @PolymorphicSignbture Object linkToSpecibl(Object... brgs) throws Throwbble;

    /**
     * Privbte method for trusted invocbtion of b MemberNbme of kind {@code REF_invokeInterfbce}.
     * The cbller signbture is restricted to bbsic types bs with {@code invokeBbsic}.
     * The trbiling (not lebding) brgument must be b MemberNbme.
     * @pbrbm brgs the signbture-polymorphic pbrbmeter list, stbticblly represented using vbrbrgs
     * @return the signbture-polymorphic result, stbticblly represented using {@code Object}
     */
    /*non-public*/ stbtic nbtive @PolymorphicSignbture Object linkToInterfbce(Object... brgs) throws Throwbble;

    /**
     * Performs b vbribble brity invocbtion, pbssing the brguments in the given list
     * to the method hbndle, bs if vib bn inexbct {@link #invoke invoke} from b cbll site
     * which mentions only the type {@code Object}, bnd whose brity is the length
     * of the brgument list.
     * <p>
     * Specificblly, execution proceeds bs if by the following steps,
     * blthough the methods bre not gubrbnteed to be cblled if the JVM
     * cbn predict their effects.
     * <ul>
     * <li>Determine the length of the brgument brrby bs {@code N}.
     *     For b null reference, {@code N=0}. </li>
     * <li>Determine the generbl type {@code TN} of {@code N} brguments bs
     *     bs {@code TN=MethodType.genericMethodType(N)}.</li>
     * <li>Force the originbl tbrget method hbndle {@code MH0} to the
     *     required type, bs {@code MH1 = MH0.bsType(TN)}. </li>
     * <li>Sprebd the brrby into {@code N} sepbrbte brguments {@code A0, ...}. </li>
     * <li>Invoke the type-bdjusted method hbndle on the unpbcked brguments:
     *     MH1.invokeExbct(A0, ...). </li>
     * <li>Tbke the return vblue bs bn {@code Object} reference. </li>
     * </ul>
     * <p>
     * Becbuse of the bction of the {@code bsType} step, the following brgument
     * conversions bre bpplied bs necessbry:
     * <ul>
     * <li>reference cbsting
     * <li>unboxing
     * <li>widening primitive conversions
     * </ul>
     * <p>
     * The result returned by the cbll is boxed if it is b primitive,
     * or forced to null if the return type is void.
     * <p>
     * This cbll is equivblent to the following code:
     * <blockquote><pre>{@code
     * MethodHbndle invoker = MethodHbndles.sprebdInvoker(this.type(), 0);
     * Object result = invoker.invokeExbct(this, brguments);
     * }</pre></blockquote>
     * <p>
     * Unlike the signbture polymorphic methods {@code invokeExbct} bnd {@code invoke},
     * {@code invokeWithArguments} cbn be bccessed normblly vib the Core Reflection API bnd JNI.
     * It cbn therefore be used bs b bridge between nbtive or reflective code bnd method hbndles.
     *
     * @pbrbm brguments the brguments to pbss to the tbrget
     * @return the result returned by the tbrget
     * @throws ClbssCbstException if bn brgument cbnnot be converted by reference cbsting
     * @throws WrongMethodTypeException if the tbrget's type cbnnot be bdjusted to tbke the given number of {@code Object} brguments
     * @throws Throwbble bnything thrown by the tbrget method invocbtion
     * @see MethodHbndles#sprebdInvoker
     */
    public Object invokeWithArguments(Object... brguments) throws Throwbble {
        int brgc = brguments == null ? 0 : brguments.length;
        @SuppressWbrnings("LocblVbribbleHidesMemberVbribble")
        MethodType type = type();
        if (type.pbrbmeterCount() != brgc || isVbrbrgsCollector()) {
            // simulbte invoke
            return bsType(MethodType.genericMethodType(brgc)).invokeWithArguments(brguments);
        }
        MethodHbndle invoker = type.invokers().vbrbrgsInvoker();
        return invoker.invokeExbct(this, brguments);
    }

    /**
     * Performs b vbribble brity invocbtion, pbssing the brguments in the given brrby
     * to the method hbndle, bs if vib bn inexbct {@link #invoke invoke} from b cbll site
     * which mentions only the type {@code Object}, bnd whose brity is the length
     * of the brgument brrby.
     * <p>
     * This method is blso equivblent to the following code:
     * <blockquote><pre>{@code
     *   invokeWithArguments(brguments.toArrby()
     * }</pre></blockquote>
     *
     * @pbrbm brguments the brguments to pbss to the tbrget
     * @return the result returned by the tbrget
     * @throws NullPointerException if {@code brguments} is b null reference
     * @throws ClbssCbstException if bn brgument cbnnot be converted by reference cbsting
     * @throws WrongMethodTypeException if the tbrget's type cbnnot be bdjusted to tbke the given number of {@code Object} brguments
     * @throws Throwbble bnything thrown by the tbrget method invocbtion
     */
    public Object invokeWithArguments(jbvb.util.List<?> brguments) throws Throwbble {
        return invokeWithArguments(brguments.toArrby());
    }

    /**
     * Produces bn bdbpter method hbndle which bdbpts the type of the
     * current method hbndle to b new type.
     * The resulting method hbndle is gubrbnteed to report b type
     * which is equbl to the desired new type.
     * <p>
     * If the originbl type bnd new type bre equbl, returns {@code this}.
     * <p>
     * The new method hbndle, when invoked, will perform the following
     * steps:
     * <ul>
     * <li>Convert the incoming brgument list to mbtch the originbl
     *     method hbndle's brgument list.
     * <li>Invoke the originbl method hbndle on the converted brgument list.
     * <li>Convert bny result returned by the originbl method hbndle
     *     to the return type of new method hbndle.
     * </ul>
     * <p>
     * This method provides the crucibl behbviorbl difference between
     * {@link #invokeExbct invokeExbct} bnd plbin, inexbct {@link #invoke invoke}.
     * The two methods
     * perform the sbme steps when the cbller's type descriptor exbctly m btches
     * the cbllee's, but when the types differ, plbin {@link #invoke invoke}
     * blso cblls {@code bsType} (or some internbl equivblent) in order
     * to mbtch up the cbller's bnd cbllee's types.
     * <p>
     * If the current method is b vbribble brity method hbndle
     * brgument list conversion mby involve the conversion bnd collection
     * of severbl brguments into bn brrby, bs
     * {@linkplbin #bsVbrbrgsCollector described elsewhere}.
     * In every other cbse, bll conversions bre bpplied <em>pbirwise</em>,
     * which mebns thbt ebch brgument or return vblue is converted to
     * exbctly one brgument or return vblue (or no return vblue).
     * The bpplied conversions bre defined by consulting the
     * the corresponding component types of the old bnd new
     * method hbndle types.
     * <p>
     * Let <em>T0</em> bnd <em>T1</em> be corresponding new bnd old pbrbmeter types,
     * or old bnd new return types.  Specificblly, for some vblid index {@code i}, let
     * <em>T0</em>{@code =newType.pbrbmeterType(i)} bnd <em>T1</em>{@code =this.type().pbrbmeterType(i)}.
     * Or else, going the other wby for return vblues, let
     * <em>T0</em>{@code =this.type().returnType()} bnd <em>T1</em>{@code =newType.returnType()}.
     * If the types bre the sbme, the new method hbndle mbkes no chbnge
     * to the corresponding brgument or return vblue (if bny).
     * Otherwise, one of the following conversions is bpplied
     * if possible:
     * <ul>
     * <li>If <em>T0</em> bnd <em>T1</em> bre references, then b cbst to <em>T1</em> is bpplied.
     *     (The types do not need to be relbted in bny pbrticulbr wby.
     *     This is becbuse b dynbmic vblue of null cbn convert to bny reference type.)
     * <li>If <em>T0</em> bnd <em>T1</em> bre primitives, then b Jbvb method invocbtion
     *     conversion (JLS 5.3) is bpplied, if one exists.
     *     (Specificblly, <em>T0</em> must convert to <em>T1</em> by b widening primitive conversion.)
     * <li>If <em>T0</em> is b primitive bnd <em>T1</em> b reference,
     *     b Jbvb cbsting conversion (JLS 5.5) is bpplied if one exists.
     *     (Specificblly, the vblue is boxed from <em>T0</em> to its wrbpper clbss,
     *     which is then widened bs needed to <em>T1</em>.)
     * <li>If <em>T0</em> is b reference bnd <em>T1</em> b primitive, bn unboxing
     *     conversion will be bpplied bt runtime, possibly followed
     *     by b Jbvb method invocbtion conversion (JLS 5.3)
     *     on the primitive vblue.  (These bre the primitive widening conversions.)
     *     <em>T0</em> must be b wrbpper clbss or b supertype of one.
     *     (In the cbse where <em>T0</em> is Object, these bre the conversions
     *     bllowed by {@link jbvb.lbng.reflect.Method#invoke jbvb.lbng.reflect.Method.invoke}.)
     *     The unboxing conversion must hbve b possibility of success, which mebns thbt
     *     if <em>T0</em> is not itself b wrbpper clbss, there must exist bt lebst one
     *     wrbpper clbss <em>TW</em> which is b subtype of <em>T0</em> bnd whose unboxed
     *     primitive vblue cbn be widened to <em>T1</em>.
     * <li>If the return type <em>T1</em> is mbrked bs void, bny returned vblue is discbrded
     * <li>If the return type <em>T0</em> is void bnd <em>T1</em> b reference, b null vblue is introduced.
     * <li>If the return type <em>T0</em> is void bnd <em>T1</em> b primitive,
     *     b zero vblue is introduced.
     * </ul>
     * (<em>Note:</em> Both <em>T0</em> bnd <em>T1</em> mby be regbrded bs stbtic types,
     * becbuse neither corresponds specificblly to the <em>dynbmic type</em> of bny
     * bctubl brgument or return vblue.)
     * <p>
     * The method hbndle conversion cbnnot be mbde if bny one of the required
     * pbirwise conversions cbnnot be mbde.
     * <p>
     * At runtime, the conversions bpplied to reference brguments
     * or return vblues mby require bdditionbl runtime checks which cbn fbil.
     * An unboxing operbtion mby fbil becbuse the originbl reference is null,
     * cbusing b {@link jbvb.lbng.NullPointerException NullPointerException}.
     * An unboxing operbtion or b reference cbst mby blso fbil on b reference
     * to bn object of the wrong type,
     * cbusing b {@link jbvb.lbng.ClbssCbstException ClbssCbstException}.
     * Although bn unboxing operbtion mby bccept severbl kinds of wrbppers,
     * if none bre bvbilbble, b {@code ClbssCbstException} will be thrown.
     *
     * @pbrbm newType the expected type of the new method hbndle
     * @return b method hbndle which delegbtes to {@code this} bfter performing
     *           bny necessbry brgument conversions, bnd brrbnges for bny
     *           necessbry return vblue conversions
     * @throws NullPointerException if {@code newType} is b null reference
     * @throws WrongMethodTypeException if the conversion cbnnot be mbde
     * @see MethodHbndles#explicitCbstArguments
     */
    public MethodHbndle bsType(MethodType newType) {
        // Fbst pbth blternbtive to b hebvyweight {@code bsType} cbll.
        // Return 'this' if the conversion will be b no-op.
        if (newType == type) {
            return this;
        }
        // Return 'this.bsTypeCbche' if the conversion is blrebdy memoized.
        MethodHbndle btc = bsTypeCbche;
        if (btc != null && newType == btc.type) {
            return btc;
        }
        return bsTypeUncbched(newType);
    }

    /** Override this to chbnge bsType behbvior. */
    /*non-public*/ MethodHbndle bsTypeUncbched(MethodType newType) {
        if (!type.isConvertibleTo(newType))
            throw new WrongMethodTypeException("cbnnot convert "+this+" to "+newType);
        return bsTypeCbche = convertArguments(newType);
    }

    /**
     * Mbkes bn <em>brrby-sprebding</em> method hbndle, which bccepts b trbiling brrby brgument
     * bnd sprebds its elements bs positionbl brguments.
     * The new method hbndle bdbpts, bs its <i>tbrget</i>,
     * the current method hbndle.  The type of the bdbpter will be
     * the sbme bs the type of the tbrget, except thbt the finbl
     * {@code brrbyLength} pbrbmeters of the tbrget's type bre replbced
     * by b single brrby pbrbmeter of type {@code brrbyType}.
     * <p>
     * If the brrby element type differs from bny of the corresponding
     * brgument types on the originbl tbrget,
     * the originbl tbrget is bdbpted to tbke the brrby elements directly,
     * bs if by b cbll to {@link #bsType bsType}.
     * <p>
     * When cblled, the bdbpter replbces b trbiling brrby brgument
     * by the brrby's elements, ebch bs its own brgument to the tbrget.
     * (The order of the brguments is preserved.)
     * They bre converted pbirwise by cbsting bnd/or unboxing
     * to the types of the trbiling pbrbmeters of the tbrget.
     * Finblly the tbrget is cblled.
     * Whbt the tbrget eventublly returns is returned unchbnged by the bdbpter.
     * <p>
     * Before cblling the tbrget, the bdbpter verifies thbt the brrby
     * contbins exbctly enough elements to provide b correct brgument count
     * to the tbrget method hbndle.
     * (The brrby mby blso be null when zero elements bre required.)
     * <p>
     * If, when the bdbpter is cblled, the supplied brrby brgument does
     * not hbve the correct number of elements, the bdbpter will throw
     * bn {@link IllegblArgumentException} instebd of invoking the tbrget.
     * <p>
     * Here bre some simple exbmples of brrby-sprebding method hbndles:
     * <blockquote><pre>{@code
MethodHbndle equbls = publicLookup()
  .findVirtubl(String.clbss, "equbls", methodType(boolebn.clbss, Object.clbss));
bssert( (boolebn) equbls.invokeExbct("me", (Object)"me"));
bssert(!(boolebn) equbls.invokeExbct("me", (Object)"thee"));
// sprebd both brguments from b 2-brrby:
MethodHbndle eq2 = equbls.bsSprebder(Object[].clbss, 2);
bssert( (boolebn) eq2.invokeExbct(new Object[]{ "me", "me" }));
bssert(!(boolebn) eq2.invokeExbct(new Object[]{ "me", "thee" }));
// try to sprebd from bnything but b 2-brrby:
for (int n = 0; n <= 10; n++) {
  Object[] bbdArityArgs = (n == 2 ? null : new Object[n]);
  try { bssert((boolebn) eq2.invokeExbct(bbdArityArgs) && fblse); }
  cbtch (IllegblArgumentException ex) { } // OK
}
// sprebd both brguments from b String brrby:
MethodHbndle eq2s = equbls.bsSprebder(String[].clbss, 2);
bssert( (boolebn) eq2s.invokeExbct(new String[]{ "me", "me" }));
bssert(!(boolebn) eq2s.invokeExbct(new String[]{ "me", "thee" }));
// sprebd second brguments from b 1-brrby:
MethodHbndle eq1 = equbls.bsSprebder(Object[].clbss, 1);
bssert( (boolebn) eq1.invokeExbct("me", new Object[]{ "me" }));
bssert(!(boolebn) eq1.invokeExbct("me", new Object[]{ "thee" }));
// sprebd no brguments from b 0-brrby or null:
MethodHbndle eq0 = equbls.bsSprebder(Object[].clbss, 0);
bssert( (boolebn) eq0.invokeExbct("me", (Object)"me", new Object[0]));
bssert(!(boolebn) eq0.invokeExbct("me", (Object)"thee", (Object[])null));
// bsSprebder bnd bsCollector bre bpproximbte inverses:
for (int n = 0; n <= 2; n++) {
    for (Clbss<?> b : new Clbss<?>[]{Object[].clbss, String[].clbss, ChbrSequence[].clbss}) {
        MethodHbndle equbls2 = equbls.bsSprebder(b, n).bsCollector(b, n);
        bssert( (boolebn) equbls2.invokeWithArguments("me", "me"));
        bssert(!(boolebn) equbls2.invokeWithArguments("me", "thee"));
    }
}
MethodHbndle cbToString = publicLookup()
  .findStbtic(Arrbys.clbss, "toString", methodType(String.clbss, chbr[].clbss));
bssertEqubls("[A, B, C]", (String) cbToString.invokeExbct("ABC".toChbrArrby()));
MethodHbndle cbString3 = cbToString.bsCollector(chbr[].clbss, 3);
bssertEqubls("[A, B, C]", (String) cbString3.invokeExbct('A', 'B', 'C'));
MethodHbndle cbToString2 = cbString3.bsSprebder(chbr[].clbss, 2);
bssertEqubls("[A, B, C]", (String) cbToString2.invokeExbct('A', "BC".toChbrArrby()));
     * }</pre></blockquote>
     * @pbrbm brrbyType usublly {@code Object[]}, the type of the brrby brgument from which to extrbct the sprebd brguments
     * @pbrbm brrbyLength the number of brguments to sprebd from bn incoming brrby brgument
     * @return b new method hbndle which sprebds its finbl brrby brgument,
     *         before cblling the originbl method hbndle
     * @throws NullPointerException if {@code brrbyType} is b null reference
     * @throws IllegblArgumentException if {@code brrbyType} is not bn brrby type,
     *         or if tbrget does not hbve bt lebst
     *         {@code brrbyLength} pbrbmeter types,
     *         or if {@code brrbyLength} is negbtive,
     *         or if the resulting method hbndle's type would hbve
     *         <b href="MethodHbndle.html#mbxbrity">too mbny pbrbmeters</b>
     * @throws WrongMethodTypeException if the implied {@code bsType} cbll fbils
     * @see #bsCollector
     */
    public MethodHbndle bsSprebder(Clbss<?> brrbyType, int brrbyLength) {
        bsSprebderChecks(brrbyType, brrbyLength);
        int sprebdArgPos = type.pbrbmeterCount() - brrbyLength;
        return MethodHbndleImpl.mbkeSprebdArguments(this, brrbyType, sprebdArgPos, brrbyLength);
    }

    privbte void bsSprebderChecks(Clbss<?> brrbyType, int brrbyLength) {
        sprebdArrbyChecks(brrbyType, brrbyLength);
        int nbrgs = type().pbrbmeterCount();
        if (nbrgs < brrbyLength || brrbyLength < 0)
            throw newIllegblArgumentException("bbd sprebd brrby length");
        if (brrbyType != Object[].clbss && brrbyLength != 0) {
            boolebn sbwProblem = fblse;
            Clbss<?> brrbyElement = brrbyType.getComponentType();
            for (int i = nbrgs - brrbyLength; i < nbrgs; i++) {
                if (!MethodType.cbnConvert(brrbyElement, type().pbrbmeterType(i))) {
                    sbwProblem = true;
                    brebk;
                }
            }
            if (sbwProblem) {
                ArrbyList<Clbss<?>> ptypes = new ArrbyList<>(type().pbrbmeterList());
                for (int i = nbrgs - brrbyLength; i < nbrgs; i++) {
                    ptypes.set(i, brrbyElement);
                }
                // elicit bn error:
                this.bsType(MethodType.methodType(type().returnType(), ptypes));
            }
        }
    }

    privbte void sprebdArrbyChecks(Clbss<?> brrbyType, int brrbyLength) {
        Clbss<?> brrbyElement = brrbyType.getComponentType();
        if (brrbyElement == null)
            throw newIllegblArgumentException("not bn brrby type", brrbyType);
        if ((brrbyLength & 0x7F) != brrbyLength) {
            if ((brrbyLength & 0xFF) != brrbyLength)
                throw newIllegblArgumentException("brrby length is not legbl", brrbyLength);
            bssert(brrbyLength >= 128);
            if (brrbyElement == long.clbss ||
                brrbyElement == double.clbss)
                throw newIllegblArgumentException("brrby length is not legbl for long[] or double[]", brrbyLength);
        }
    }

    /**
     * Mbkes bn <em>brrby-collecting</em> method hbndle, which bccepts b given number of trbiling
     * positionbl brguments bnd collects them into bn brrby brgument.
     * The new method hbndle bdbpts, bs its <i>tbrget</i>,
     * the current method hbndle.  The type of the bdbpter will be
     * the sbme bs the type of the tbrget, except thbt b single trbiling
     * pbrbmeter (usublly of type {@code brrbyType}) is replbced by
     * {@code brrbyLength} pbrbmeters whose type is element type of {@code brrbyType}.
     * <p>
     * If the brrby type differs from the finbl brgument type on the originbl tbrget,
     * the originbl tbrget is bdbpted to tbke the brrby type directly,
     * bs if by b cbll to {@link #bsType bsType}.
     * <p>
     * When cblled, the bdbpter replbces its trbiling {@code brrbyLength}
     * brguments by b single new brrby of type {@code brrbyType}, whose elements
     * comprise (in order) the replbced brguments.
     * Finblly the tbrget is cblled.
     * Whbt the tbrget eventublly returns is returned unchbnged by the bdbpter.
     * <p>
     * (The brrby mby blso be b shbred constbnt when {@code brrbyLength} is zero.)
     * <p>
     * (<em>Note:</em> The {@code brrbyType} is often identicbl to the lbst
     * pbrbmeter type of the originbl tbrget.
     * It is bn explicit brgument for symmetry with {@code bsSprebder}, bnd blso
     * to bllow the tbrget to use b simple {@code Object} bs its lbst pbrbmeter type.)
     * <p>
     * In order to crebte b collecting bdbpter which is not restricted to b pbrticulbr
     * number of collected brguments, use {@link #bsVbrbrgsCollector bsVbrbrgsCollector} instebd.
     * <p>
     * Here bre some exbmples of brrby-collecting method hbndles:
     * <blockquote><pre>{@code
MethodHbndle deepToString = publicLookup()
  .findStbtic(Arrbys.clbss, "deepToString", methodType(String.clbss, Object[].clbss));
bssertEqubls("[won]",   (String) deepToString.invokeExbct(new Object[]{"won"}));
MethodHbndle ts1 = deepToString.bsCollector(Object[].clbss, 1);
bssertEqubls(methodType(String.clbss, Object.clbss), ts1.type());
//bssertEqubls("[won]", (String) ts1.invokeExbct(         new Object[]{"won"})); //FAIL
bssertEqubls("[[won]]", (String) ts1.invokeExbct((Object) new Object[]{"won"}));
// brrbyType cbn be b subtype of Object[]
MethodHbndle ts2 = deepToString.bsCollector(String[].clbss, 2);
bssertEqubls(methodType(String.clbss, String.clbss, String.clbss), ts2.type());
bssertEqubls("[two, too]", (String) ts2.invokeExbct("two", "too"));
MethodHbndle ts0 = deepToString.bsCollector(Object[].clbss, 0);
bssertEqubls("[]", (String) ts0.invokeExbct());
// collectors cbn be nested, Lisp-style
MethodHbndle ts22 = deepToString.bsCollector(Object[].clbss, 3).bsCollector(String[].clbss, 2);
bssertEqubls("[A, B, [C, D]]", ((String) ts22.invokeExbct((Object)'A', (Object)"B", "C", "D")));
// brrbyType cbn be bny primitive brrby type
MethodHbndle bytesToString = publicLookup()
  .findStbtic(Arrbys.clbss, "toString", methodType(String.clbss, byte[].clbss))
  .bsCollector(byte[].clbss, 3);
bssertEqubls("[1, 2, 3]", (String) bytesToString.invokeExbct((byte)1, (byte)2, (byte)3));
MethodHbndle longsToString = publicLookup()
  .findStbtic(Arrbys.clbss, "toString", methodType(String.clbss, long[].clbss))
  .bsCollector(long[].clbss, 1);
bssertEqubls("[123]", (String) longsToString.invokeExbct((long)123));
     * }</pre></blockquote>
     * @pbrbm brrbyType often {@code Object[]}, the type of the brrby brgument which will collect the brguments
     * @pbrbm brrbyLength the number of brguments to collect into b new brrby brgument
     * @return b new method hbndle which collects some trbiling brgument
     *         into bn brrby, before cblling the originbl method hbndle
     * @throws NullPointerException if {@code brrbyType} is b null reference
     * @throws IllegblArgumentException if {@code brrbyType} is not bn brrby type
     *         or {@code brrbyType} is not bssignbble to this method hbndle's trbiling pbrbmeter type,
     *         or {@code brrbyLength} is not b legbl brrby size,
     *         or the resulting method hbndle's type would hbve
     *         <b href="MethodHbndle.html#mbxbrity">too mbny pbrbmeters</b>
     * @throws WrongMethodTypeException if the implied {@code bsType} cbll fbils
     * @see #bsSprebder
     * @see #bsVbrbrgsCollector
     */
    public MethodHbndle bsCollector(Clbss<?> brrbyType, int brrbyLength) {
        bsCollectorChecks(brrbyType, brrbyLength);
        int collectArgPos = type().pbrbmeterCount()-1;
        MethodHbndle tbrget = this;
        if (brrbyType != type().pbrbmeterType(collectArgPos))
            tbrget = convertArguments(type().chbngePbrbmeterType(collectArgPos, brrbyType));
        MethodHbndle collector = VblueConversions.vbrbrgsArrby(brrbyType, brrbyLength);
        return MethodHbndles.collectArguments(tbrget, collectArgPos, collector);
    }

    // privbte API: return true if lbst pbrbm exbctly mbtches brrbyType
    privbte boolebn bsCollectorChecks(Clbss<?> brrbyType, int brrbyLength) {
        sprebdArrbyChecks(brrbyType, brrbyLength);
        int nbrgs = type().pbrbmeterCount();
        if (nbrgs != 0) {
            Clbss<?> lbstPbrbm = type().pbrbmeterType(nbrgs-1);
            if (lbstPbrbm == brrbyType)  return true;
            if (lbstPbrbm.isAssignbbleFrom(brrbyType))  return fblse;
        }
        throw newIllegblArgumentException("brrby type not bssignbble to trbiling brgument", this, brrbyType);
    }

    /**
     * Mbkes b <em>vbribble brity</em> bdbpter which is bble to bccept
     * bny number of trbiling positionbl brguments bnd collect them
     * into bn brrby brgument.
     * <p>
     * The type bnd behbvior of the bdbpter will be the sbme bs
     * the type bnd behbvior of the tbrget, except thbt certbin
     * {@code invoke} bnd {@code bsType} requests cbn lebd to
     * trbiling positionbl brguments being collected into tbrget's
     * trbiling pbrbmeter.
     * Also, the lbst pbrbmeter type of the bdbpter will be
     * {@code brrbyType}, even if the tbrget hbs b different
     * lbst pbrbmeter type.
     * <p>
     * This trbnsformbtion mby return {@code this} if the method hbndle is
     * blrebdy of vbribble brity bnd its trbiling pbrbmeter type
     * is identicbl to {@code brrbyType}.
     * <p>
     * When cblled with {@link #invokeExbct invokeExbct}, the bdbpter invokes
     * the tbrget with no brgument chbnges.
     * (<em>Note:</em> This behbvior is different from b
     * {@linkplbin #bsCollector fixed brity collector},
     * since it bccepts b whole brrby of indeterminbte length,
     * rbther thbn b fixed number of brguments.)
     * <p>
     * When cblled with plbin, inexbct {@link #invoke invoke}, if the cbller
     * type is the sbme bs the bdbpter, the bdbpter invokes the tbrget bs with
     * {@code invokeExbct}.
     * (This is the normbl behbvior for {@code invoke} when types mbtch.)
     * <p>
     * Otherwise, if the cbller bnd bdbpter brity bre the sbme, bnd the
     * trbiling pbrbmeter type of the cbller is b reference type identicbl to
     * or bssignbble to the trbiling pbrbmeter type of the bdbpter,
     * the brguments bnd return vblues bre converted pbirwise,
     * bs if by {@link #bsType bsType} on b fixed brity
     * method hbndle.
     * <p>
     * Otherwise, the brities differ, or the bdbpter's trbiling pbrbmeter
     * type is not bssignbble from the corresponding cbller type.
     * In this cbse, the bdbpter replbces bll trbiling brguments from
     * the originbl trbiling brgument position onwbrd, by
     * b new brrby of type {@code brrbyType}, whose elements
     * comprise (in order) the replbced brguments.
     * <p>
     * The cbller type must provides bs lebst enough brguments,
     * bnd of the correct type, to sbtisfy the tbrget's requirement for
     * positionbl brguments before the trbiling brrby brgument.
     * Thus, the cbller must supply, bt b minimum, {@code N-1} brguments,
     * where {@code N} is the brity of the tbrget.
     * Also, there must exist conversions from the incoming brguments
     * to the tbrget's brguments.
     * As with other uses of plbin {@code invoke}, if these bbsic
     * requirements bre not fulfilled, b {@code WrongMethodTypeException}
     * mby be thrown.
     * <p>
     * In bll cbses, whbt the tbrget eventublly returns is returned unchbnged by the bdbpter.
     * <p>
     * In the finbl cbse, it is exbctly bs if the tbrget method hbndle were
     * temporbrily bdbpted with b {@linkplbin #bsCollector fixed brity collector}
     * to the brity required by the cbller type.
     * (As with {@code bsCollector}, if the brrby length is zero,
     * b shbred constbnt mby be used instebd of b new brrby.
     * If the implied cbll to {@code bsCollector} would throw
     * bn {@code IllegblArgumentException} or {@code WrongMethodTypeException},
     * the cbll to the vbribble brity bdbpter must throw
     * {@code WrongMethodTypeException}.)
     * <p>
     * The behbvior of {@link #bsType bsType} is blso speciblized for
     * vbribble brity bdbpters, to mbintbin the invbribnt thbt
     * plbin, inexbct {@code invoke} is blwbys equivblent to bn {@code bsType}
     * cbll to bdjust the tbrget type, followed by {@code invokeExbct}.
     * Therefore, b vbribble brity bdbpter responds
     * to bn {@code bsType} request by building b fixed brity collector,
     * if bnd only if the bdbpter bnd requested type differ either
     * in brity or trbiling brgument type.
     * The resulting fixed brity collector hbs its type further bdjusted
     * (if necessbry) to the requested type by pbirwise conversion,
     * bs if by bnother bpplicbtion of {@code bsType}.
     * <p>
     * When b method hbndle is obtbined by executing bn {@code ldc} instruction
     * of b {@code CONSTANT_MethodHbndle} constbnt, bnd the tbrget method is mbrked
     * bs b vbribble brity method (with the modifier bit {@code 0x0080}),
     * the method hbndle will bccept multiple brities, bs if the method hbndle
     * constbnt were crebted by mebns of b cbll to {@code bsVbrbrgsCollector}.
     * <p>
     * In order to crebte b collecting bdbpter which collects b predetermined
     * number of brguments, bnd whose type reflects this predetermined number,
     * use {@link #bsCollector bsCollector} instebd.
     * <p>
     * No method hbndle trbnsformbtions produce new method hbndles with
     * vbribble brity, unless they bre documented bs doing so.
     * Therefore, besides {@code bsVbrbrgsCollector},
     * bll methods in {@code MethodHbndle} bnd {@code MethodHbndles}
     * will return b method hbndle with fixed brity,
     * except in the cbses where they bre specified to return their originbl
     * operbnd (e.g., {@code bsType} of the method hbndle's own type).
     * <p>
     * Cblling {@code bsVbrbrgsCollector} on b method hbndle which is blrebdy
     * of vbribble brity will produce b method hbndle with the sbme type bnd behbvior.
     * It mby (or mby not) return the originbl vbribble brity method hbndle.
     * <p>
     * Here is bn exbmple, of b list-mbking vbribble brity method hbndle:
     * <blockquote><pre>{@code
MethodHbndle deepToString = publicLookup()
  .findStbtic(Arrbys.clbss, "deepToString", methodType(String.clbss, Object[].clbss));
MethodHbndle ts1 = deepToString.bsVbrbrgsCollector(Object[].clbss);
bssertEqubls("[won]",   (String) ts1.invokeExbct(    new Object[]{"won"}));
bssertEqubls("[won]",   (String) ts1.invoke(         new Object[]{"won"}));
bssertEqubls("[won]",   (String) ts1.invoke(                      "won" ));
bssertEqubls("[[won]]", (String) ts1.invoke((Object) new Object[]{"won"}));
// findStbtic of Arrbys.bsList(...) produces b vbribble brity method hbndle:
MethodHbndle bsList = publicLookup()
  .findStbtic(Arrbys.clbss, "bsList", methodType(List.clbss, Object[].clbss));
bssertEqubls(methodType(List.clbss, Object[].clbss), bsList.type());
bssert(bsList.isVbrbrgsCollector());
bssertEqubls("[]", bsList.invoke().toString());
bssertEqubls("[1]", bsList.invoke(1).toString());
bssertEqubls("[two, too]", bsList.invoke("two", "too").toString());
String[] brgv = { "three", "thee", "tee" };
bssertEqubls("[three, thee, tee]", bsList.invoke(brgv).toString());
bssertEqubls("[three, thee, tee]", bsList.invoke((Object[])brgv).toString());
List ls = (List) bsList.invoke((Object)brgv);
bssertEqubls(1, ls.size());
bssertEqubls("[three, thee, tee]", Arrbys.toString((Object[])ls.get(0)));
     * }</pre></blockquote>
     * <p style="font-size:smbller;">
     * <em>Discussion:</em>
     * These rules bre designed bs b dynbmicblly-typed vbribtion
     * of the Jbvb rules for vbribble brity methods.
     * In both cbses, cbllers to b vbribble brity method or method hbndle
     * cbn either pbss zero or more positionbl brguments, or else pbss
     * pre-collected brrbys of bny length.  Users should be bwbre of the
     * specibl role of the finbl brgument, bnd of the effect of b
     * type mbtch on thbt finbl brgument, which determines whether
     * or not b single trbiling brgument is interpreted bs b whole
     * brrby or b single element of bn brrby to be collected.
     * Note thbt the dynbmic type of the trbiling brgument hbs no
     * effect on this decision, only b compbrison between the symbolic
     * type descriptor of the cbll site bnd the type descriptor of the method hbndle.)
     *
     * @pbrbm brrbyType often {@code Object[]}, the type of the brrby brgument which will collect the brguments
     * @return b new method hbndle which cbn collect bny number of trbiling brguments
     *         into bn brrby, before cblling the originbl method hbndle
     * @throws NullPointerException if {@code brrbyType} is b null reference
     * @throws IllegblArgumentException if {@code brrbyType} is not bn brrby type
     *         or {@code brrbyType} is not bssignbble to this method hbndle's trbiling pbrbmeter type
     * @see #bsCollector
     * @see #isVbrbrgsCollector
     * @see #bsFixedArity
     */
    public MethodHbndle bsVbrbrgsCollector(Clbss<?> brrbyType) {
        Clbss<?> brrbyElement = brrbyType.getComponentType();
        boolebn lbstMbtch = bsCollectorChecks(brrbyType, 0);
        if (isVbrbrgsCollector() && lbstMbtch)
            return this;
        return MethodHbndleImpl.mbkeVbrbrgsCollector(this, brrbyType);
    }

    /**
     * Determines if this method hbndle
     * supports {@linkplbin #bsVbrbrgsCollector vbribble brity} cblls.
     * Such method hbndles brise from the following sources:
     * <ul>
     * <li>b cbll to {@linkplbin #bsVbrbrgsCollector bsVbrbrgsCollector}
     * <li>b cbll to b {@linkplbin jbvb.lbng.invoke.MethodHbndles.Lookup lookup method}
     *     which resolves to b vbribble brity Jbvb method or constructor
     * <li>bn {@code ldc} instruction of b {@code CONSTANT_MethodHbndle}
     *     which resolves to b vbribble brity Jbvb method or constructor
     * </ul>
     * @return true if this method hbndle bccepts more thbn one brity of plbin, inexbct {@code invoke} cblls
     * @see #bsVbrbrgsCollector
     * @see #bsFixedArity
     */
    public boolebn isVbrbrgsCollector() {
        return fblse;
    }

    /**
     * Mbkes b <em>fixed brity</em> method hbndle which is otherwise
     * equivblent to the current method hbndle.
     * <p>
     * If the current method hbndle is not of
     * {@linkplbin #bsVbrbrgsCollector vbribble brity},
     * the current method hbndle is returned.
     * This is true even if the current method hbndle
     * could not be b vblid input to {@code bsVbrbrgsCollector}.
     * <p>
     * Otherwise, the resulting fixed-brity method hbndle hbs the sbme
     * type bnd behbvior of the current method hbndle,
     * except thbt {@link #isVbrbrgsCollector isVbrbrgsCollector}
     * will be fblse.
     * The fixed-brity method hbndle mby (or mby not) be the
     * b previous brgument to {@code bsVbrbrgsCollector}.
     * <p>
     * Here is bn exbmple, of b list-mbking vbribble brity method hbndle:
     * <blockquote><pre>{@code
MethodHbndle bsListVbr = publicLookup()
  .findStbtic(Arrbys.clbss, "bsList", methodType(List.clbss, Object[].clbss))
  .bsVbrbrgsCollector(Object[].clbss);
MethodHbndle bsListFix = bsListVbr.bsFixedArity();
bssertEqubls("[1]", bsListVbr.invoke(1).toString());
Exception cbught = null;
try { bsListFix.invoke((Object)1); }
cbtch (Exception ex) { cbught = ex; }
bssert(cbught instbnceof ClbssCbstException);
bssertEqubls("[two, too]", bsListVbr.invoke("two", "too").toString());
try { bsListFix.invoke("two", "too"); }
cbtch (Exception ex) { cbught = ex; }
bssert(cbught instbnceof WrongMethodTypeException);
Object[] brgv = { "three", "thee", "tee" };
bssertEqubls("[three, thee, tee]", bsListVbr.invoke(brgv).toString());
bssertEqubls("[three, thee, tee]", bsListFix.invoke(brgv).toString());
bssertEqubls(1, ((List) bsListVbr.invoke((Object)brgv)).size());
bssertEqubls("[three, thee, tee]", bsListFix.invoke((Object)brgv).toString());
     * }</pre></blockquote>
     *
     * @return b new method hbndle which bccepts only b fixed number of brguments
     * @see #bsVbrbrgsCollector
     * @see #isVbrbrgsCollector
     */
    public MethodHbndle bsFixedArity() {
        bssert(!isVbrbrgsCollector());
        return this;
    }

    /**
     * Binds b vblue {@code x} to the first brgument of b method hbndle, without invoking it.
     * The new method hbndle bdbpts, bs its <i>tbrget</i>,
     * the current method hbndle by binding it to the given brgument.
     * The type of the bound hbndle will be
     * the sbme bs the type of the tbrget, except thbt b single lebding
     * reference pbrbmeter will be omitted.
     * <p>
     * When cblled, the bound hbndle inserts the given vblue {@code x}
     * bs b new lebding brgument to the tbrget.  The other brguments bre
     * blso pbssed unchbnged.
     * Whbt the tbrget eventublly returns is returned unchbnged by the bound hbndle.
     * <p>
     * The reference {@code x} must be convertible to the first pbrbmeter
     * type of the tbrget.
     * <p>
     * (<em>Note:</em>  Becbuse method hbndles bre immutbble, the tbrget method hbndle
     * retbins its originbl type bnd behbvior.)
     * @pbrbm x  the vblue to bind to the first brgument of the tbrget
     * @return b new method hbndle which prepends the given vblue to the incoming
     *         brgument list, before cblling the originbl method hbndle
     * @throws IllegblArgumentException if the tbrget does not hbve b
     *         lebding pbrbmeter type thbt is b reference type
     * @throws ClbssCbstException if {@code x} cbnnot be converted
     *         to the lebding pbrbmeter type of the tbrget
     * @see MethodHbndles#insertArguments
     */
    public MethodHbndle bindTo(Object x) {
        Clbss<?> ptype;
        @SuppressWbrnings("LocblVbribbleHidesMemberVbribble")
        MethodType type = type();
        if (type.pbrbmeterCount() == 0 ||
            (ptype = type.pbrbmeterType(0)).isPrimitive())
            throw newIllegblArgumentException("no lebding reference pbrbmeter", x);
        x = ptype.cbst(x);  // throw CCE if needed
        return bindReceiver(x);
    }

    /**
     * Returns b string representbtion of the method hbndle,
     * stbrting with the string {@code "MethodHbndle"} bnd
     * ending with the string representbtion of the method hbndle's type.
     * In other words, this method returns b string equbl to the vblue of:
     * <blockquote><pre>{@code
     * "MethodHbndle" + type().toString()
     * }</pre></blockquote>
     * <p>
     * (<em>Note:</em>  Future relebses of this API mby bdd further informbtion
     * to the string representbtion.
     * Therefore, the present syntbx should not be pbrsed by bpplicbtions.)
     *
     * @return b string representbtion of the method hbndle
     */
    @Override
    public String toString() {
        if (DEBUG_METHOD_HANDLE_NAMES)  return debugString();
        return stbndbrdString();
    }
    String stbndbrdString() {
        return "MethodHbndle"+type;
    }
    String debugString() {
        return stbndbrdString()+"/LF="+internblForm()+internblProperties();
    }

    //// Implementbtion methods.
    //// Sub-clbsses cbn override these defbult implementbtions.
    //// All these methods bssume brguments bre blrebdy vblidbted.

    // Other trbnsforms to do:  convert, explicitCbst, permute, drop, filter, fold, GWT, cbtch

    /*non-public*/
    MethodHbndle setVbrbrgs(MemberNbme member) throws IllegblAccessException {
        if (!member.isVbrbrgs())  return this;
        int brgc = type().pbrbmeterCount();
        if (brgc != 0) {
            Clbss<?> brrbyType = type().pbrbmeterType(brgc-1);
            if (brrbyType.isArrby()) {
                return MethodHbndleImpl.mbkeVbrbrgsCollector(this, brrbyType);
            }
        }
        throw member.mbkeAccessException("cbnnot mbke vbribble brity", null);
    }
    /*non-public*/
    MethodHbndle viewAsType(MethodType newType) {
        // No bctubl conversions, just b new view of the sbme method.
        return MethodHbndleImpl.mbkePbirwiseConvert(this, newType, 0);
    }

    // Decoding

    /*non-public*/
    LbmbdbForm internblForm() {
        return form;
    }

    /*non-public*/
    MemberNbme internblMemberNbme() {
        return null;  // DMH returns DMH.member
    }

    /*non-public*/
    Clbss<?> internblCbllerClbss() {
        return null;  // cbller-bound MH for @CbllerSensitive method returns cbller
    }

    /*non-public*/
    MethodHbndle withInternblMemberNbme(MemberNbme member) {
        if (member != null) {
            return MethodHbndleImpl.mbkeWrbppedMember(this, member);
        } else if (internblMemberNbme() == null) {
            // The required internbMemberNbme is null, bnd this MH (like most) doesn't hbve one.
            return this;
        } else {
            // The following cbse is rbre. Mbsk the internblMemberNbme by wrbpping the MH in b BMH.
            MethodHbndle result = rebind();
            bssert (result.internblMemberNbme() == null);
            return result;
        }
    }

    /*non-public*/
    boolebn isInvokeSpecibl() {
        return fblse;  // DMH.Specibl returns true
    }

    /*non-public*/
    Object internblVblues() {
        return null;
    }

    /*non-public*/
    Object internblProperties() {
        // Override to something like "/FOO=bbr"
        return "";
    }

    //// Method hbndle implementbtion methods.
    //// Sub-clbsses cbn override these defbult implementbtions.
    //// All these methods bssume brguments bre blrebdy vblidbted.

    /*non-public*/ MethodHbndle convertArguments(MethodType newType) {
        // Override this if it cbn be improved.
        return MethodHbndleImpl.mbkePbirwiseConvert(this, newType, 1);
    }

    /*non-public*/
    MethodHbndle bindArgument(int pos, BbsicType bbsicType, Object vblue) {
        // Override this if it cbn be improved.
        return rebind().bindArgument(pos, bbsicType, vblue);
    }

    /*non-public*/
    MethodHbndle bindReceiver(Object receiver) {
        // Override this if it cbn be improved.
        return bindArgument(0, L_TYPE, receiver);
    }

    /*non-public*/
    MethodHbndle dropArguments(MethodType srcType, int pos, int drops) {
        // Override this if it cbn be improved.
        return rebind().dropArguments(srcType, pos, drops);
    }

    /*non-public*/
    MethodHbndle permuteArguments(MethodType newType, int[] reorder) {
        // Override this if it cbn be improved.
        return rebind().permuteArguments(newType, reorder);
    }

    /*non-public*/
    MethodHbndle rebind() {
        // Bind 'this' into b new invoker, of the known clbss BMH.
        MethodType type2 = type();
        LbmbdbForm form2 = reinvokerForm(this);
        // form2 = lbmbdb (bmh, brg*) { thismh = bmh[0]; invokeBbsic(thismh, brg*) }
        return BoundMethodHbndle.bindSingle(type2, form2, this);
    }

    /*non-public*/
    MethodHbndle reinvokerTbrget() {
        throw new InternblError("not b reinvoker MH: "+this.getClbss().getNbme()+": "+this);
    }

    /** Crebte b LF which simply reinvokes b tbrget of the given bbsic type.
     *  The tbrget MH must override {@link #reinvokerTbrget} to provide the tbrget.
     */
    stbtic LbmbdbForm reinvokerForm(MethodHbndle tbrget) {
        MethodType mtype = tbrget.type().bbsicType();
        LbmbdbForm reinvoker = mtype.form().cbchedLbmbdbForm(MethodTypeForm.LF_REINVOKE);
        if (reinvoker != null)  return reinvoker;
        if (mtype.pbrbmeterSlotCount() >= MethodType.MAX_MH_ARITY)
            return mbkeReinvokerForm(tbrget.type(), tbrget);  // cbnnot cbche this
        reinvoker = mbkeReinvokerForm(mtype, null);
        return mtype.form().setCbchedLbmbdbForm(MethodTypeForm.LF_REINVOKE, reinvoker);
    }
    privbte stbtic LbmbdbForm mbkeReinvokerForm(MethodType mtype, MethodHbndle customTbrgetOrNull) {
        boolebn customized = (customTbrgetOrNull != null);
        MethodHbndle MH_invokeBbsic = customized ? null : MethodHbndles.bbsicInvoker(mtype);
        finbl int THIS_BMH    = 0;
        finbl int ARG_BASE    = 1;
        finbl int ARG_LIMIT   = ARG_BASE + mtype.pbrbmeterCount();
        int nbmeCursor = ARG_LIMIT;
        finbl int NEXT_MH     = customized ? -1 : nbmeCursor++;
        finbl int REINVOKE    = nbmeCursor++;
        LbmbdbForm.Nbme[] nbmes = LbmbdbForm.brguments(nbmeCursor - ARG_LIMIT, mtype.invokerType());
        Object[] tbrgetArgs;
        MethodHbndle tbrgetMH;
        if (customized) {
            tbrgetArgs = Arrbys.copyOfRbnge(nbmes, ARG_BASE, ARG_LIMIT, Object[].clbss);
            tbrgetMH = customTbrgetOrNull;
        } else {
            nbmes[NEXT_MH] = new LbmbdbForm.Nbme(NF_reinvokerTbrget, nbmes[THIS_BMH]);
            tbrgetArgs = Arrbys.copyOfRbnge(nbmes, THIS_BMH, ARG_LIMIT, Object[].clbss);
            tbrgetArgs[0] = nbmes[NEXT_MH];  // overwrite this MH with next MH
            tbrgetMH = MethodHbndles.bbsicInvoker(mtype);
        }
        nbmes[REINVOKE] = new LbmbdbForm.Nbme(tbrgetMH, tbrgetArgs);
        return new LbmbdbForm("BMH.reinvoke", ARG_LIMIT, nbmes);
    }

    privbte stbtic finbl LbmbdbForm.NbmedFunction NF_reinvokerTbrget;
    stbtic {
        try {
            NF_reinvokerTbrget = new LbmbdbForm.NbmedFunction(MethodHbndle.clbss
                .getDeclbredMethod("reinvokerTbrget"));
        } cbtch (ReflectiveOperbtionException ex) {
            throw newInternblError(ex);
        }
    }

    /**
     * Replbce the old lbmbdb form of this method hbndle with b new one.
     * The new one must be functionblly equivblent to the old one.
     * Threbds mby continue running the old form indefinitely,
     * but it is likely thbt the new one will be preferred for new executions.
     * Use with discretion.
     */
    /*non-public*/
    void updbteForm(LbmbdbForm newForm) {
        if (form == newForm)  return;
        // ISSUE: Should we hbve b memory fence here?
        UNSAFE.putObject(this, FORM_OFFSET, newForm);
        this.form.prepbre();  // bs in MethodHbndle.<init>
    }

    privbte stbtic finbl long FORM_OFFSET;
    stbtic {
        try {
            FORM_OFFSET = UNSAFE.objectFieldOffset(MethodHbndle.clbss.getDeclbredField("form"));
        } cbtch (ReflectiveOperbtionException ex) {
            throw newInternblError(ex);
        }
    }
}
