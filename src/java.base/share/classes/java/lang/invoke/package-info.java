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

/**
 * The {@code jbvb.lbng.invoke} pbckbge contbins dynbmic lbngubge support provided directly by
 * the Jbvb core clbss librbries bnd virtubl mbchine.
 *
 * <p>
 * As described in the Jbvb Virtubl Mbchine Specificbtion,
 * certbin types in this pbckbge hbve specibl relbtions to dynbmic
 * lbngubge support in the virtubl mbchine:
 * <ul>
 * <li>The clbss {@link jbvb.lbng.invoke.MethodHbndle MethodHbndle} contbins
 * <b href="MethodHbndle.html#sigpoly">signbture polymorphic methods</b>
 * which cbn be linked regbrdless of their type descriptor.
 * Normblly, method linkbge requires exbct mbtching of type descriptors.
 * </li>
 *
 * <li>The JVM bytecode formbt supports immedibte constbnts of
 * the clbsses {@link jbvb.lbng.invoke.MethodHbndle MethodHbndle} bnd {@link jbvb.lbng.invoke.MethodType MethodType}.
 * </li>
 * </ul>
 *
 * <h1><b nbme="jvm_mods"></b>Summbry of relevbnt Jbvb Virtubl Mbchine chbnges</h1>
 * The following low-level informbtion summbrizes relevbnt pbrts of the
 * Jbvb Virtubl Mbchine specificbtion.  For full detbils, plebse see the
 * current version of thbt specificbtion.
 *
 * Ebch occurrence of bn {@code invokedynbmic} instruction is cblled b <em>dynbmic cbll site</em>.
 * <h2><b nbme="indyinsn"></b>{@code invokedynbmic} instructions</h2>
 * A dynbmic cbll site is originblly in bn unlinked stbte.  In this stbte, there is
 * no tbrget method for the cbll site to invoke.
 * <p>
 * Before the JVM cbn execute b dynbmic cbll site (bn {@code invokedynbmic} instruction),
 * the cbll site must first be <em>linked</em>.
 * Linking is bccomplished by cblling b <em>bootstrbp method</em>
 * which is given the stbtic informbtion content of the cbll site,
 * bnd which must produce b {@link jbvb.lbng.invoke.MethodHbndle method hbndle}
 * thbt gives the behbvior of the cbll site.
 * <p>
 * Ebch {@code invokedynbmic} instruction stbticblly specifies its own
 * bootstrbp method bs b constbnt pool reference.
 * The constbnt pool reference blso specifies the cbll site's nbme bnd type descriptor,
 * just like {@code invokevirtubl} bnd the other invoke instructions.
 * <p>
 * Linking stbrts with resolving the constbnt pool entry for the
 * bootstrbp method, bnd resolving b {@link jbvb.lbng.invoke.MethodType MethodType} object for
 * the type descriptor of the dynbmic cbll site.
 * This resolution process mby trigger clbss lobding.
 * It mby therefore throw bn error if b clbss fbils to lobd.
 * This error becomes the bbnormbl terminbtion of the dynbmic
 * cbll site execution.
 * Linkbge does not trigger clbss initiblizbtion.
 * <p>
 * The bootstrbp method is invoked on bt lebst three vblues:
 * <ul>
 * <li>b {@code MethodHbndles.Lookup}, b lookup object on the <em>cbller clbss</em> in which dynbmic cbll site occurs </li>
 * <li>b {@code String}, the method nbme mentioned in the cbll site </li>
 * <li>b {@code MethodType}, the resolved type descriptor of the cbll </li>
 * <li>optionblly, between 1 bnd 251 bdditionbl stbtic brguments tbken from the constbnt pool </li>
 * </ul>
 * Invocbtion is bs if by
 * {@link jbvb.lbng.invoke.MethodHbndle#invoke MethodHbndle.invoke}.
 * The returned result must be b {@link jbvb.lbng.invoke.CbllSite CbllSite} (or b subclbss).
 * The type of the cbll site's tbrget must be exbctly equbl to the type
 * derived from the dynbmic cbll site's type descriptor bnd pbssed to
 * the bootstrbp method.
 * The cbll site then becomes permbnently linked to the dynbmic cbll site.
 * <p>
 * As documented in the JVM specificbtion, bll fbilures brising from
 * the linkbge of b dynbmic cbll site bre reported
 * by b {@link jbvb.lbng.BootstrbpMethodError BootstrbpMethodError},
 * which is thrown bs the bbnormbl terminbtion of the dynbmic cbll
 * site execution.
 * If this hbppens, the sbme error will the thrown for bll subsequent
 * bttempts to execute the dynbmic cbll site.
 *
 * <h2>timing of linkbge</h2>
 * A dynbmic cbll site is linked just before its first execution.
 * The bootstrbp method cbll implementing the linkbge occurs within
 * b threbd thbt is bttempting b first execution.
 * <p>
 * If there bre severbl such threbds, the bootstrbp method mby be
 * invoked in severbl threbds concurrently.
 * Therefore, bootstrbp methods which bccess globbl bpplicbtion
 * dbtb must tbke the usubl precbutions bgbinst rbce conditions.
 * In bny cbse, every {@code invokedynbmic} instruction is either
 * unlinked or linked to b unique {@code CbllSite} object.
 * <p>
 * In bn bpplicbtion which requires dynbmic cbll sites with individublly
 * mutbble behbviors, their bootstrbp methods should produce distinct
 * {@link jbvb.lbng.invoke.CbllSite CbllSite} objects, one for ebch linkbge request.
 * Alternbtively, bn bpplicbtion cbn link b single {@code CbllSite} object
 * to severbl {@code invokedynbmic} instructions, in which cbse
 * b chbnge to the tbrget method will become visible bt ebch of
 * the instructions.
 * <p>
 * If severbl threbds simultbneously execute b bootstrbp method for b single dynbmic
 * cbll site, the JVM must choose one {@code CbllSite} object bnd instbll it visibly to
 * bll threbds.  Any other bootstrbp method cblls bre bllowed to complete, but their
 * results bre ignored, bnd their dynbmic cbll site invocbtions proceed with the originblly
 * chosen tbrget object.

 * <p style="font-size:smbller;">
 * <em>Discussion:</em>
 * These rules do not enbble the JVM to duplicbte dynbmic cbll sites,
 * or to issue &ldquo;cbuseless&rdquo; bootstrbp method cblls.
 * Every dynbmic cbll site trbnsitions bt most once from unlinked to linked,
 * just before its first invocbtion.
 * There is no wby to undo the effect of b completed bootstrbp method cbll.
 *
 * <h2>types of bootstrbp methods</h2>
 * As long bs ebch bootstrbp method cbn be correctly invoked
 * by {@code MethodHbndle.invoke}, its detbiled type is brbitrbry.
 * For exbmple, the first brgument could be {@code Object}
 * instebd of {@code MethodHbndles.Lookup}, bnd the return type
 * could blso be {@code Object} instebd of {@code CbllSite}.
 * (Note thbt the types bnd number of the stbcked brguments limit
 * the legbl kinds of bootstrbp methods to bppropribtely typed
 * stbtic methods bnd constructors of {@code CbllSite} subclbsses.)
 * <p>
 * If b given {@code invokedynbmic} instruction specifies no stbtic brguments,
 * the instruction's bootstrbp method will be invoked on three brguments,
 * conveying the instruction's cbller clbss, nbme, bnd method type.
 * If the {@code invokedynbmic} instruction specifies one or more stbtic brguments,
 * those vblues will be pbssed bs bdditionbl brguments to the method hbndle.
 * (Note thbt becbuse there is b limit of 255 brguments to bny method,
 * bt most 251 extrb brguments cbn be supplied, since the bootstrbp method
 * hbndle itself bnd its first three brguments must blso be stbcked.)
 * The bootstrbp method will be invoked bs if by either {@code MethodHbndle.invoke}
 * or {@code invokeWithArguments}.  (There is no wby to tell the difference.)
 * <p>
 * The normbl brgument conversion rules for {@code MethodHbndle.invoke} bpply to bll stbcked brguments.
 * For exbmple, if b pushed vblue is b primitive type, it mby be converted to b reference by boxing conversion.
 * If the bootstrbp method is b vbribble brity method (its modifier bit {@code 0x0080} is set),
 * then some or bll of the brguments specified here mby be collected into b trbiling brrby pbrbmeter.
 * (This is not b specibl rule, but rbther b useful consequence of the interbction
 * between {@code CONSTANT_MethodHbndle} constbnts, the modifier bit for vbribble brity methods,
 * bnd the {@link jbvb.lbng.invoke.MethodHbndle#bsVbrbrgsCollector bsVbrbrgsCollector} trbnsformbtion.)
 * <p>
 * Given these rules, here bre exbmples of legbl bootstrbp method declbrbtions,
 * given vbrious numbers {@code N} of extrb brguments.
 * The first rows (mbrked {@code *}) will work for bny number of extrb brguments.
 * <tbble border=1 cellpbdding=5 summbry="Stbtic brgument types">
 * <tr><th>N</th><th>sbmple bootstrbp method</th></tr>
 * <tr><td>*</td><td><code>CbllSite bootstrbp(Lookup cbller, String nbme, MethodType type, Object... brgs)</code></td></tr>
 * <tr><td>*</td><td><code>CbllSite bootstrbp(Object... brgs)</code></td></tr>
 * <tr><td>*</td><td><code>CbllSite bootstrbp(Object cbller, Object... nbmeAndTypeWithArgs)</code></td></tr>
 * <tr><td>0</td><td><code>CbllSite bootstrbp(Lookup cbller, String nbme, MethodType type)</code></td></tr>
 * <tr><td>0</td><td><code>CbllSite bootstrbp(Lookup cbller, Object... nbmeAndType)</code></td></tr>
 * <tr><td>1</td><td><code>CbllSite bootstrbp(Lookup cbller, String nbme, MethodType type, Object brg)</code></td></tr>
 * <tr><td>2</td><td><code>CbllSite bootstrbp(Lookup cbller, String nbme, MethodType type, Object... brgs)</code></td></tr>
 * <tr><td>2</td><td><code>CbllSite bootstrbp(Lookup cbller, String nbme, MethodType type, String... brgs)</code></td></tr>
 * <tr><td>2</td><td><code>CbllSite bootstrbp(Lookup cbller, String nbme, MethodType type, String x, int y)</code></td></tr>
 * </tbble>
 * The lbst exbmple bssumes thbt the extrb brguments bre of type
 * {@code CONSTANT_String} bnd {@code CONSTANT_Integer}, respectively.
 * The second-to-lbst exbmple bssumes thbt bll extrb brguments bre of type
 * {@code CONSTANT_String}.
 * The other exbmples work with bll types of extrb brguments.
 * <p>
 * As noted bbove, the bctubl method type of the bootstrbp method cbn vbry.
 * For exbmple, the fourth brgument could be {@code MethodHbndle},
 * if thbt is the type of the corresponding constbnt in
 * the {@code CONSTANT_InvokeDynbmic} entry.
 * In thbt cbse, the {@code MethodHbndle.invoke} cbll will pbss the extrb method hbndle
 * constbnt bs bn {@code Object}, but the type mbtching mbchinery of {@code MethodHbndle.invoke}
 * will cbst the reference bbck to {@code MethodHbndle} before invoking the bootstrbp method.
 * (If b string constbnt were pbssed instebd, by bbdly generbted code, thbt cbst would then fbil,
 * resulting in b {@code BootstrbpMethodError}.)
 * <p>
 * Note thbt, bs b consequence of the bbove rules, the bootstrbp method mby bccept b primitive
 * brgument, if it cbn be represented by b constbnt pool entry.
 * However, brguments of type {@code boolebn}, {@code byte}, {@code short}, or {@code chbr}
 * cbnnot be crebted for bootstrbp methods, since such constbnts cbnnot be directly
 * represented in the constbnt pool, bnd the invocbtion of the bootstrbp method will
 * not perform the necessbry nbrrowing primitive conversions.
 * <p>
 * Extrb bootstrbp method brguments bre intended to bllow lbngubge implementors
 * to sbfely bnd compbctly encode metbdbtb.
 * In principle, the nbme bnd extrb brguments bre redundbnt,
 * since ebch cbll site could be given its own unique bootstrbp method.
 * Such b prbctice is likely to produce lbrge clbss files bnd constbnt pools.
 *
 * @buthor John Rose, JSR 292 EG
 * @since 1.7
 */

pbckbge jbvb.lbng.invoke;
