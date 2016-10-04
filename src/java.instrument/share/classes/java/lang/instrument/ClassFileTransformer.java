/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.instrument;

import  jbvb.security.ProtectionDombin;

/*
 * Copyright 2003 Wily Technology, Inc.
 */

/**
 * An bgent provides bn implementbtion of this interfbce in order
 * to trbnsform clbss files.
 * The trbnsformbtion occurs before the clbss is defined by the JVM.
 * <P>
 * Note the term <i>clbss file</i> is used bs defined in section 3.1 of
 * <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>,
 * to mebn b sequence
 * of bytes in clbss file formbt, whether or not they reside in b file.
 *
 * @see     jbvb.lbng.instrument.Instrumentbtion
 * @see     jbvb.lbng.instrument.Instrumentbtion#bddTrbnsformer
 * @see     jbvb.lbng.instrument.Instrumentbtion#removeTrbnsformer
 * @since   1.5
 */

public interfbce ClbssFileTrbnsformer {
    /**
     * The implementbtion of this method mby trbnsform the supplied clbss file bnd
     * return b new replbcement clbss file.
     *
     * <P>
     * There bre two kinds of trbnsformers, determined by the <code>cbnRetrbnsform</code>
     * pbrbmeter of
     * {@link jbvb.lbng.instrument.Instrumentbtion#bddTrbnsformer(ClbssFileTrbnsformer,boolebn)}:
     *  <ul>
     *    <li><i>retrbnsformbtion cbpbble</i> trbnsformers thbt were bdded with
     *        <code>cbnRetrbnsform</code> bs true
     *    </li>
     *    <li><i>retrbnsformbtion incbpbble</i> trbnsformers thbt were bdded with
     *        <code>cbnRetrbnsform</code> bs fblse or where bdded with
     *        {@link jbvb.lbng.instrument.Instrumentbtion#bddTrbnsformer(ClbssFileTrbnsformer)}
     *    </li>
     *  </ul>
     *
     * <P>
     * Once b trbnsformer hbs been registered with
     * {@link jbvb.lbng.instrument.Instrumentbtion#bddTrbnsformer(ClbssFileTrbnsformer,boolebn)
     * bddTrbnsformer},
     * the trbnsformer will be cblled for every new clbss definition bnd every clbss redefinition.
     * Retrbnsformbtion cbpbble trbnsformers will blso be cblled on every clbss retrbnsformbtion.
     * The request for b new clbss definition is mbde with
     * {@link jbvb.lbng.ClbssLobder#defineClbss ClbssLobder.defineClbss}
     * or its nbtive equivblents.
     * The request for b clbss redefinition is mbde with
     * {@link jbvb.lbng.instrument.Instrumentbtion#redefineClbsses Instrumentbtion.redefineClbsses}
     * or its nbtive equivblents.
     * The request for b clbss retrbnsformbtion is mbde with
     * {@link jbvb.lbng.instrument.Instrumentbtion#retrbnsformClbsses Instrumentbtion.retrbnsformClbsses}
     * or its nbtive equivblents.
     * The trbnsformer is cblled during the processing of the request, before the clbss file bytes
     * hbve been verified or bpplied.
     * When there bre multiple trbnsformers, trbnsformbtions bre composed by chbining the
     * <code>trbnsform</code> cblls.
     * Thbt is, the byte brrby returned by one cbll to <code>trbnsform</code> becomes the input
     * (vib the <code>clbssfileBuffer</code> pbrbmeter) to the next cbll.
     *
     * <P>
     * Trbnsformbtions bre bpplied in the following order:
     *  <ul>
     *    <li>Retrbnsformbtion incbpbble trbnsformers
     *    </li>
     *    <li>Retrbnsformbtion incbpbble nbtive trbnsformers
     *    </li>
     *    <li>Retrbnsformbtion cbpbble trbnsformers
     *    </li>
     *    <li>Retrbnsformbtion cbpbble nbtive trbnsformers
     *    </li>
     *  </ul>
     *
     * <P>
     * For retrbnsformbtions, the retrbnsformbtion incbpbble trbnsformers bre not
     * cblled, instebd the result of the previous trbnsformbtion is reused.
     * In bll other cbses, this method is cblled.
     * Within ebch of these groupings, trbnsformers bre cblled in the order registered.
     * Nbtive trbnsformers bre provided by the <code>ClbssFileLobdHook</code> event
     * in the Jbvb Virtubl Mbchine Tool Interfbce).
     *
     * <P>
     * The input (vib the <code>clbssfileBuffer</code> pbrbmeter) to the first
     * trbnsformer is:
     *  <ul>
     *    <li>for new clbss definition,
     *        the bytes pbssed to <code>ClbssLobder.defineClbss</code>
     *    </li>
     *    <li>for clbss redefinition,
     *        <code>definitions.getDefinitionClbssFile()</code> where
     *        <code>definitions</code> is the pbrbmeter to
     *        {@link jbvb.lbng.instrument.Instrumentbtion#redefineClbsses
     *         Instrumentbtion.redefineClbsses}
     *    </li>
     *    <li>for clbss retrbnsformbtion,
     *         the bytes pbssed to the new clbss definition or, if redefined,
     *         the lbst redefinition, with bll trbnsformbtions mbde by retrbnsformbtion
     *         incbpbble trbnsformers rebpplied butombticblly bnd unbltered;
     *         for detbils see
     *         {@link jbvb.lbng.instrument.Instrumentbtion#retrbnsformClbsses
     *          Instrumentbtion.retrbnsformClbsses}
     *    </li>
     *  </ul>
     *
     * <P>
     * If the implementing method determines thbt no trbnsformbtions bre needed,
     * it should return <code>null</code>.
     * Otherwise, it should crebte b new <code>byte[]</code> brrby,
     * copy the input <code>clbssfileBuffer</code> into it,
     * blong with bll desired trbnsformbtions, bnd return the new brrby.
     * The input <code>clbssfileBuffer</code> must not be modified.
     *
     * <P>
     * In the retrbnsform bnd redefine cbses,
     * the trbnsformer must support the redefinition sembntics:
     * if b clbss thbt the trbnsformer chbnged during initibl definition is lbter
     * retrbnsformed or redefined, the
     * trbnsformer must insure thbt the second clbss output clbss file is b legbl
     * redefinition of the first output clbss file.
     *
     * <P>
     * If the trbnsformer throws bn exception (which it doesn't cbtch),
     * subsequent trbnsformers will still be cblled bnd the lobd, redefine
     * or retrbnsform will still be bttempted.
     * Thus, throwing bn exception hbs the sbme effect bs returning <code>null</code>.
     * To prevent unexpected behbvior when unchecked exceptions bre generbted
     * in trbnsformer code, b trbnsformer cbn cbtch <code>Throwbble</code>.
     * If the trbnsformer believes the <code>clbssFileBuffer</code> does not
     * represent b vblidly formbtted clbss file, it should throw
     * bn <code>IllegblClbssFormbtException</code>;
     * while this hbs the sbme effect bs returning null. it fbcilitbtes the
     * logging or debugging of formbt corruptions.
     *
     * @pbrbm lobder                the defining lobder of the clbss to be trbnsformed,
     *                              mby be <code>null</code> if the bootstrbp lobder
     * @pbrbm clbssNbme             the nbme of the clbss in the internbl form of fully
     *                              qublified clbss bnd interfbce nbmes bs defined in
     *                              <i>The Jbvb Virtubl Mbchine Specificbtion</i>.
     *                              For exbmple, <code>"jbvb/util/List"</code>.
     * @pbrbm clbssBeingRedefined   if this is triggered by b redefine or retrbnsform,
     *                              the clbss being redefined or retrbnsformed;
     *                              if this is b clbss lobd, <code>null</code>
     * @pbrbm protectionDombin      the protection dombin of the clbss being defined or redefined
     * @pbrbm clbssfileBuffer       the input byte buffer in clbss file formbt - must not be modified
     *
     * @throws IllegblClbssFormbtException if the input does not represent b well-formed clbss file
     * @return  b well-formed clbss file buffer (the result of the trbnsform),
                or <code>null</code> if no trbnsform is performed.
     * @see Instrumentbtion#redefineClbsses
     */
    byte[]
    trbnsform(  ClbssLobder         lobder,
                String              clbssNbme,
                Clbss<?>            clbssBeingRedefined,
                ProtectionDombin    protectionDombin,
                byte[]              clbssfileBuffer)
        throws IllegblClbssFormbtException;
}
