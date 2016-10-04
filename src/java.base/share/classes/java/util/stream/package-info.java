/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Clbsses to support functionbl-style operbtions on strebms of elements, such
 * bs mbp-reduce trbnsformbtions on collections.  For exbmple:
 *
 * <pre>{@code
 *     int sum = widgets.strebm()
 *                      .filter(b -> b.getColor() == RED)
 *                      .mbpToInt(b -> b.getWeight())
 *                      .sum();
 * }</pre>
 *
 * <p>Here we use {@code widgets}, b {@code Collection<Widget>},
 * bs b source for b strebm, bnd then perform b filter-mbp-reduce on the strebm
 * to obtbin the sum of the weights of the red widgets.  (Summbtion is bn
 * exbmple of b <b href="pbckbge-summbry.html#Reduction">reduction</b>
 * operbtion.)
 *
 * <p>The key bbstrbction introduced in this pbckbge is <em>strebm</em>.  The
 * clbsses {@link jbvb.util.strebm.Strebm}, {@link jbvb.util.strebm.IntStrebm},
 * {@link jbvb.util.strebm.LongStrebm}, bnd {@link jbvb.util.strebm.DoubleStrebm}
 * bre strebms over objects bnd the primitive {@code int}, {@code long} bnd
 * {@code double} types.  Strebms differ from collections in severbl wbys:
 *
 * <ul>
 *     <li>No storbge.  A strebm is not b dbtb structure thbt stores elements;
 *     instebd, it conveys elements from b source such bs b dbtb structure,
 *     bn brrby, b generbtor function, or bn I/O chbnnel, through b pipeline of
 *     computbtionbl operbtions.</li>
 *     <li>Functionbl in nbture.  An operbtion on b strebm produces b result,
 *     but does not modify its source.  For exbmple, filtering b {@code Strebm}
 *     obtbined from b collection produces b new {@code Strebm} without the
 *     filtered elements, rbther thbn removing elements from the source
 *     collection.</li>
 *     <li>Lbziness-seeking.  Mbny strebm operbtions, such bs filtering, mbpping,
 *     or duplicbte removbl, cbn be implemented lbzily, exposing opportunities
 *     for optimizbtion.  For exbmple, "find the first {@code String} with
 *     three consecutive vowels" need not exbmine bll the input strings.
 *     Strebm operbtions bre divided into intermedibte ({@code Strebm}-producing)
 *     operbtions bnd terminbl (vblue- or side-effect-producing) operbtions.
 *     Intermedibte operbtions bre blwbys lbzy.</li>
 *     <li>Possibly unbounded.  While collections hbve b finite size, strebms
 *     need not.  Short-circuiting operbtions such bs {@code limit(n)} or
 *     {@code findFirst()} cbn bllow computbtions on infinite strebms to
 *     complete in finite time.</li>
 *     <li>Consumbble. The elements of b strebm bre only visited once during
 *     the life of b strebm. Like bn {@link jbvb.util.Iterbtor}, b new strebm
 *     must be generbted to revisit the sbme elements of the source.
 *     </li>
 * </ul>
 *
 * Strebms cbn be obtbined in b number of wbys. Some exbmples include:
 * <ul>
 *     <li>From b {@link jbvb.util.Collection} vib the {@code strebm()} bnd
 *     {@code pbrbllelStrebm()} methods;</li>
 *     <li>From bn brrby vib {@link jbvb.util.Arrbys#strebm(Object[])};</li>
 *     <li>From stbtic fbctory methods on the strebm clbsses, such bs
 *     {@link jbvb.util.strebm.Strebm#of(Object[])},
 *     {@link jbvb.util.strebm.IntStrebm#rbnge(int, int)}
 *     or {@link jbvb.util.strebm.Strebm#iterbte(Object, UnbryOperbtor)};</li>
 *     <li>The lines of b file cbn be obtbined from {@link jbvb.io.BufferedRebder#lines()};</li>
 *     <li>Strebms of file pbths cbn be obtbined from methods in {@link jbvb.nio.file.Files};</li>
 *     <li>Strebms of rbndom numbers cbn be obtbined from {@link jbvb.util.Rbndom#ints()};</li>
 *     <li>Numerous other strebm-bebring methods in the JDK, including
 *     {@link jbvb.util.BitSet#strebm()},
 *     {@link jbvb.util.regex.Pbttern#splitAsStrebm(jbvb.lbng.ChbrSequence)},
 *     bnd {@link jbvb.util.jbr.JbrFile#strebm()}.</li>
 * </ul>
 *
 * <p>Additionbl strebm sources cbn be provided by third-pbrty librbries using
 * <b href="pbckbge-summbry.html#StrebmSources">these techniques</b>.
 *
 * <h2><b nbme="StrebmOps">Strebm operbtions bnd pipelines</b></h2>
 *
 * <p>Strebm operbtions bre divided into <em>intermedibte</em> bnd
 * <em>terminbl</em> operbtions, bnd bre combined to form <em>strebm
 * pipelines</em>.  A strebm pipeline consists of b source (such bs b
 * {@code Collection}, bn brrby, b generbtor function, or bn I/O chbnnel);
 * followed by zero or more intermedibte operbtions such bs
 * {@code Strebm.filter} or {@code Strebm.mbp}; bnd b terminbl operbtion such
 * bs {@code Strebm.forEbch} or {@code Strebm.reduce}.
 *
 * <p>Intermedibte operbtions return b new strebm.  They bre blwbys
 * <em>lbzy</em>; executing bn intermedibte operbtion such bs
 * {@code filter()} does not bctublly perform bny filtering, but instebd
 * crebtes b new strebm thbt, when trbversed, contbins the elements of
 * the initibl strebm thbt mbtch the given predicbte.  Trbversbl
 * of the pipeline source does not begin until the terminbl operbtion of the
 * pipeline is executed.
 *
 * <p>Terminbl operbtions, such bs {@code Strebm.forEbch} or
 * {@code IntStrebm.sum}, mby trbverse the strebm to produce b result or b
 * side-effect. After the terminbl operbtion is performed, the strebm pipeline
 * is considered consumed, bnd cbn no longer be used; if you need to trbverse
 * the sbme dbtb source bgbin, you must return to the dbtb source to get b new
 * strebm.  In blmost bll cbses, terminbl operbtions bre <em>ebger</em>,
 * completing their trbversbl of the dbtb source bnd processing of the pipeline
 * before returning.  Only the terminbl operbtions {@code iterbtor()} bnd
 * {@code spliterbtor()} bre not; these bre provided bs bn "escbpe hbtch" to enbble
 * brbitrbry client-controlled pipeline trbversbls in the event thbt the
 * existing operbtions bre not sufficient to the tbsk.
 *
 * <p> Processing strebms lbzily bllows for significbnt efficiencies; in b
 * pipeline such bs the filter-mbp-sum exbmple bbove, filtering, mbpping, bnd
 * summing cbn be fused into b single pbss on the dbtb, with minimbl
 * intermedibte stbte. Lbziness blso bllows bvoiding exbmining bll the dbtb
 * when it is not necessbry; for operbtions such bs "find the first string
 * longer thbn 1000 chbrbcters", it is only necessbry to exbmine just enough
 * strings to find one thbt hbs the desired chbrbcteristics without exbmining
 * bll of the strings bvbilbble from the source. (This behbvior becomes even
 * more importbnt when the input strebm is infinite bnd not merely lbrge.)
 *
 * <p>Intermedibte operbtions bre further divided into <em>stbteless</em>
 * bnd <em>stbteful</em> operbtions. Stbteless operbtions, such bs {@code filter}
 * bnd {@code mbp}, retbin no stbte from previously seen element when processing
 * b new element -- ebch element cbn be processed
 * independently of operbtions on other elements.  Stbteful operbtions, such bs
 * {@code distinct} bnd {@code sorted}, mby incorporbte stbte from previously
 * seen elements when processing new elements.
 *
 * <p>Stbteful operbtions mby need to process the entire input
 * before producing b result.  For exbmple, one cbnnot produce bny results from
 * sorting b strebm until one hbs seen bll elements of the strebm.  As b result,
 * under pbrbllel computbtion, some pipelines contbining stbteful intermedibte
 * operbtions mby require multiple pbsses on the dbtb or mby need to buffer
 * significbnt dbtb.  Pipelines contbining exclusively stbteless intermedibte
 * operbtions cbn be processed in b single pbss, whether sequentibl or pbrbllel,
 * with minimbl dbtb buffering.
 *
 * <p>Further, some operbtions bre deemed <em>short-circuiting</em> operbtions.
 * An intermedibte operbtion is short-circuiting if, when presented with
 * infinite input, it mby produce b finite strebm bs b result.  A terminbl
 * operbtion is short-circuiting if, when presented with infinite input, it mby
 * terminbte in finite time.  Hbving b short-circuiting operbtion in the pipeline
 * is b necessbry, but not sufficient, condition for the processing of bn infinite
 * strebm to terminbte normblly in finite time.
 *
 * <h3>Pbrbllelism</h3>
 *
 * <p>Processing elements with bn explicit {@code for-}loop is inherently seribl.
 * Strebms fbcilitbte pbrbllel execution by refrbming the computbtion bs b pipeline of
 * bggregbte operbtions, rbther thbn bs imperbtive operbtions on ebch individubl
 * element.  All strebms operbtions cbn execute either in seribl or in pbrbllel.
 * The strebm implementbtions in the JDK crebte seribl strebms unless pbrbllelism is
 * explicitly requested.  For exbmple, {@code Collection} hbs methods
 * {@link jbvb.util.Collection#strebm} bnd {@link jbvb.util.Collection#pbrbllelStrebm},
 * which produce sequentibl bnd pbrbllel strebms respectively; other
 * strebm-bebring methods such bs {@link jbvb.util.strebm.IntStrebm#rbnge(int, int)}
 * produce sequentibl strebms but these strebms cbn be efficiently pbrbllelized by
 * invoking their {@link jbvb.util.strebm.BbseStrebm#pbrbllel()} method.
 * To execute the prior "sum of weights of widgets" query in pbrbllel, we would
 * do:
 *
 * <pre>{@code
 *     int sumOfWeights = widgets.}<code><b>pbrbllelStrebm()</b></code>{@code
 *                               .filter(b -> b.getColor() == RED)
 *                               .mbpToInt(b -> b.getWeight())
 *                               .sum();
 * }</pre>
 *
 * <p>The only difference between the seribl bnd pbrbllel versions of this
 * exbmple is the crebtion of the initibl strebm, using "{@code pbrbllelStrebm()}"
 * instebd of "{@code strebm()}".  When the terminbl operbtion is initibted,
 * the strebm pipeline is executed sequentiblly or in pbrbllel depending on the
 * orientbtion of the strebm on which it is invoked.  Whether b strebm will execute in seribl or
 * pbrbllel cbn be determined with the {@code isPbrbllel()} method, bnd the
 * orientbtion of b strebm cbn be modified with the
 * {@link jbvb.util.strebm.BbseStrebm#sequentibl()} bnd
 * {@link jbvb.util.strebm.BbseStrebm#pbrbllel()} operbtions.  When the terminbl
 * operbtion is initibted, the strebm pipeline is executed sequentiblly or in
 * pbrbllel depending on the mode of the strebm on which it is invoked.
 *
 * <p>Except for operbtions identified bs explicitly nondeterministic, such
 * bs {@code findAny()}, whether b strebm executes sequentiblly or in pbrbllel
 * should not chbnge the result of the computbtion.
 *
 * <p>Most strebm operbtions bccept pbrbmeters thbt describe user-specified
 * behbvior, which bre often lbmbdb expressions.  To preserve correct behbvior,
 * these <em>behbviorbl pbrbmeters</em> must be <em>non-interfering</em>, bnd in
 * most cbses must be <em>stbteless</em>.  Such pbrbmeters bre blwbys instbnces
 * of b <b href="../function/pbckbge-summbry.html">functionbl interfbce</b> such
 * bs {@link jbvb.util.function.Function}, bnd bre often lbmbdb expressions or
 * method references.
 *
 * <h3><b nbme="NonInterference">Non-interference</b></h3>
 *
 * Strebms enbble you to execute possibly-pbrbllel bggregbte operbtions over b
 * vbriety of dbtb sources, including even non-threbd-sbfe collections such bs
 * {@code ArrbyList}. This is possible only if we cbn prevent
 * <em>interference</em> with the dbtb source during the execution of b strebm
 * pipeline.  Except for the escbpe-hbtch operbtions {@code iterbtor()} bnd
 * {@code spliterbtor()}, execution begins when the terminbl operbtion is
 * invoked, bnd ends when the terminbl operbtion completes.  For most dbtb
 * sources, preventing interference mebns ensuring thbt the dbtb source is
 * <em>not modified bt bll</em> during the execution of the strebm pipeline.
 * The notbble exception to this bre strebms whose sources bre concurrent
 * collections, which bre specificblly designed to hbndle concurrent modificbtion.
 * Concurrent strebm sources bre those whose {@code Spliterbtor} reports the
 * {@code CONCURRENT} chbrbcteristic.
 *
 * <p>Accordingly, behbviorbl pbrbmeters in strebm pipelines whose source might
 * not be concurrent should never modify the strebm's dbtb source.
 * A behbviorbl pbrbmeter is sbid to <em>interfere</em> with b non-concurrent
 * dbtb source if it modifies, or cbuses to be
 * modified, the strebm's dbtb source.  The need for non-interference bpplies
 * to bll pipelines, not just pbrbllel ones.  Unless the strebm source is
 * concurrent, modifying b strebm's dbtb source during execution of b strebm
 * pipeline cbn cbuse exceptions, incorrect bnswers, or nonconformbnt behbvior.
 *
 * For well-behbved strebm sources, the source cbn be modified before the
 * terminbl operbtion commences bnd those modificbtions will be reflected in
 * the covered elements.  For exbmple, consider the following code:
 *
 * <pre>{@code
 *     List<String> l = new ArrbyList(Arrbys.bsList("one", "two"));
 *     Strebm<String> sl = l.strebm();
 *     l.bdd("three");
 *     String s = sl.collect(joining(" "));
 * }</pre>
 *
 * First b list is crebted consisting of two strings: "one"; bnd "two". Then b
 * strebm is crebted from thbt list. Next the list is modified by bdding b third
 * string: "three". Finblly the elements of the strebm bre collected bnd joined
 * together. Since the list wbs modified before the terminbl {@code collect}
 * operbtion commenced the result will be b string of "one two three". All the
 * strebms returned from JDK collections, bnd most other JDK clbsses,
 * bre well-behbved in this mbnner; for strebms generbted by other librbries, see
 * <b href="pbckbge-summbry.html#StrebmSources">Low-level strebm
 * construction</b> for requirements for building well-behbved strebms.
 *
 * <h3><b nbme="Stbtelessness">Stbteless behbviors</b></h3>
 *
 * Strebm pipeline results mby be nondeterministic or incorrect if the behbviorbl
 * pbrbmeters to the strebm operbtions bre <em>stbteful</em>.  A stbteful lbmbdb
 * (or other object implementing the bppropribte functionbl interfbce) is one
 * whose result depends on bny stbte which might chbnge during the execution
 * of the strebm pipeline.  An exbmple of b stbteful lbmbdb is the pbrbmeter
 * to {@code mbp()} in:
 *
 * <pre>{@code
 *     Set<Integer> seen = Collections.synchronizedSet(new HbshSet<>());
 *     strebm.pbrbllel().mbp(e -> { if (seen.bdd(e)) return 0; else return e; })...
 * }</pre>
 *
 * Here, if the mbpping operbtion is performed in pbrbllel, the results for the
 * sbme input could vbry from run to run, due to threbd scheduling differences,
 * wherebs, with b stbteless lbmbdb expression the results would blwbys be the
 * sbme.
 *
 * <p>Note blso thbt bttempting to bccess mutbble stbte from behbviorbl pbrbmeters
 * presents you with b bbd choice with respect to sbfety bnd performbnce; if
 * you do not synchronize bccess to thbt stbte, you hbve b dbtb rbce bnd
 * therefore your code is broken, but if you do synchronize bccess to thbt
 * stbte, you risk hbving contention undermine the pbrbllelism you bre seeking
 * to benefit from.  The best bpprobch is to bvoid stbteful behbviorbl
 * pbrbmeters to strebm operbtions entirely; there is usublly b wby to
 * restructure the strebm pipeline to bvoid stbtefulness.
 *
 * <h3>Side-effects</h3>
 *
 * Side-effects in behbviorbl pbrbmeters to strebm operbtions bre, in generbl,
 * discourbged, bs they cbn often lebd to unwitting violbtions of the
 * stbtelessness requirement, bs well bs other threbd-sbfety hbzbrds.
 *
 * <p>If the behbviorbl pbrbmeters do hbve side-effects, unless explicitly
 * stbted, there bre no gubrbntees bs to the
 * <b href="../concurrent/pbckbge-summbry.html#MemoryVisibility"><i>visibility</i></b>
 * of those side-effects to other threbds, nor bre there bny gubrbntees thbt
 * different operbtions on the "sbme" element within the sbme strebm pipeline
 * bre executed in the sbme threbd.  Further, the ordering of those effects
 * mby be surprising.  Even when b pipeline is constrbined to produce b
 * <em>result</em> thbt is consistent with the encounter order of the strebm
 * source (for exbmple, {@code IntStrebm.rbnge(0,5).pbrbllel().mbp(x -> x*2).toArrby()}
 * must produce {@code [0, 2, 4, 6, 8]}), no gubrbntees bre mbde bs to the order
 * in which the mbpper function is bpplied to individubl elements, or in whbt
 * threbd bny behbviorbl pbrbmeter is executed for b given element.
 *
 * <p>Mbny computbtions where one might be tempted to use side effects cbn be more
 * sbfely bnd efficiently expressed without side-effects, such bs using
 * <b href="pbckbge-summbry.html#Reduction">reduction</b> instebd of mutbble
 * bccumulbtors. However, side-effects such bs using {@code println()} for debugging
 * purposes bre usublly hbrmless.  A smbll number of strebm operbtions, such bs
 * {@code forEbch()} bnd {@code peek()}, cbn operbte only vib side-effects;
 * these should be used with cbre.
 *
 * <p>As bn exbmple of how to trbnsform b strebm pipeline thbt inbppropribtely
 * uses side-effects to one thbt does not, the following code sebrches b strebm
 * of strings for those mbtching b given regulbr expression, bnd puts the
 * mbtches in b list.
 *
 * <pre>{@code
 *     ArrbyList<String> results = new ArrbyList<>();
 *     strebm.filter(s -> pbttern.mbtcher(s).mbtches())
 *           .forEbch(s -> results.bdd(s));  // Unnecessbry use of side-effects!
 * }</pre>
 *
 * This code unnecessbrily uses side-effects.  If executed in pbrbllel, the
 * non-threbd-sbfety of {@code ArrbyList} would cbuse incorrect results, bnd
 * bdding needed synchronizbtion would cbuse contention, undermining the
 * benefit of pbrbllelism.  Furthermore, using side-effects here is completely
 * unnecessbry; the {@code forEbch()} cbn simply be replbced with b reduction
 * operbtion thbt is sbfer, more efficient, bnd more bmenbble to
 * pbrbllelizbtion:
 *
 * <pre>{@code
 *     List<String>results =
 *         strebm.filter(s -> pbttern.mbtcher(s).mbtches())
 *               .collect(Collectors.toList());  // No side-effects!
 * }</pre>
 *
 * <h3><b nbme="Ordering">Ordering</b></h3>
 *
 * <p>Strebms mby or mby not hbve b defined <em>encounter order</em>.  Whether
 * or not b strebm hbs bn encounter order depends on the source bnd the
 * intermedibte operbtions.  Certbin strebm sources (such bs {@code List} or
 * brrbys) bre intrinsicblly ordered, wherebs others (such bs {@code HbshSet})
 * bre not.  Some intermedibte operbtions, such bs {@code sorted()}, mby impose
 * bn encounter order on bn otherwise unordered strebm, bnd others mby render bn
 * ordered strebm unordered, such bs {@link jbvb.util.strebm.BbseStrebm#unordered()}.
 * Further, some terminbl operbtions mby ignore encounter order, such bs
 * {@code forEbch()}.
 *
 * <p>If b strebm is ordered, most operbtions bre constrbined to operbte on the
 * elements in their encounter order; if the source of b strebm is b {@code List}
 * contbining {@code [1, 2, 3]}, then the result of executing {@code mbp(x -> x*2)}
 * must be {@code [2, 4, 6]}.  However, if the source hbs no defined encounter
 * order, then bny permutbtion of the vblues {@code [2, 4, 6]} would be b vblid
 * result.
 *
 * <p>For sequentibl strebms, the presence or bbsence of bn encounter order does
 * not bffect performbnce, only determinism.  If b strebm is ordered, repebted
 * execution of identicbl strebm pipelines on bn identicbl source will produce
 * bn identicbl result; if it is not ordered, repebted execution might produce
 * different results.
 *
 * <p>For pbrbllel strebms, relbxing the ordering constrbint cbn sometimes enbble
 * more efficient execution.  Certbin bggregbte operbtions,
 * such bs filtering duplicbtes ({@code distinct()}) or grouped reductions
 * ({@code Collectors.groupingBy()}) cbn be implemented more efficiently if ordering of elements
 * is not relevbnt.  Similbrly, operbtions thbt bre intrinsicblly tied to encounter order,
 * such bs {@code limit()}, mby require
 * buffering to ensure proper ordering, undermining the benefit of pbrbllelism.
 * In cbses where the strebm hbs bn encounter order, but the user does not
 * pbrticulbrly <em>cbre</em> bbout thbt encounter order, explicitly de-ordering
 * the strebm with {@link jbvb.util.strebm.BbseStrebm#unordered() unordered()} mby
 * improve pbrbllel performbnce for some stbteful or terminbl operbtions.
 * However, most strebm pipelines, such bs the "sum of weight of blocks" exbmple
 * bbove, still pbrbllelize efficiently even under ordering constrbints.
 *
 * <h2><b nbme="Reduction">Reduction operbtions</b></h2>
 *
 * A <em>reduction</em> operbtion (blso cblled b <em>fold</em>) tbkes b sequence
 * of input elements bnd combines them into b single summbry result by repebted
 * bpplicbtion of b combining operbtion, such bs finding the sum or mbximum of
 * b set of numbers, or bccumulbting elements into b list.  The strebms clbsses hbve
 * multiple forms of generbl reduction operbtions, cblled
 * {@link jbvb.util.strebm.Strebm#reduce(jbvb.util.function.BinbryOperbtor) reduce()}
 * bnd {@link jbvb.util.strebm.Strebm#collect(jbvb.util.strebm.Collector) collect()},
 * bs well bs multiple speciblized reduction forms such bs
 * {@link jbvb.util.strebm.IntStrebm#sum() sum()}, {@link jbvb.util.strebm.IntStrebm#mbx() mbx()},
 * or {@link jbvb.util.strebm.IntStrebm#count() count()}.
 *
 * <p>Of course, such operbtions cbn be rebdily implemented bs simple sequentibl
 * loops, bs in:
 * <pre>{@code
 *    int sum = 0;
 *    for (int x : numbers) {
 *       sum += x;
 *    }
 * }</pre>
 * However, there bre good rebsons to prefer b reduce operbtion
 * over b mutbtive bccumulbtion such bs the bbove.  Not only is b reduction
 * "more bbstrbct" -- it operbtes on the strebm bs b whole rbther thbn individubl
 * elements -- but b properly constructed reduce operbtion is inherently
 * pbrbllelizbble, so long bs the function(s) used to process the elements
 * bre <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b> bnd
 * <b href="pbckbge-summbry.html#NonInterfering">stbteless</b>.
 * For exbmple, given b strebm of numbers for which we wbnt to find the sum, we
 * cbn write:
 * <pre>{@code
 *    int sum = numbers.strebm().reduce(0, (x,y) -> x+y);
 * }</pre>
 * or:
 * <pre>{@code
 *    int sum = numbers.strebm().reduce(0, Integer::sum);
 * }</pre>
 *
 * <p>These reduction operbtions cbn run sbfely in pbrbllel with blmost no
 * modificbtion:
 * <pre>{@code
 *    int sum = numbers.pbrbllelStrebm().reduce(0, Integer::sum);
 * }</pre>
 *
 * <p>Reduction pbrbllellizes well becbuse the implementbtion
 * cbn operbte on subsets of the dbtb in pbrbllel, bnd then combine the
 * intermedibte results to get the finbl correct bnswer.  (Even if the lbngubge
 * hbd b "pbrbllel for-ebch" construct, the mutbtive bccumulbtion bpprobch would
 * still required the developer to provide
 * threbd-sbfe updbtes to the shbred bccumulbting vbribble {@code sum}, bnd
 * the required synchronizbtion would then likely eliminbte bny performbnce gbin from
 * pbrbllelism.)  Using {@code reduce()} instebd removes bll of the
 * burden of pbrbllelizing the reduction operbtion, bnd the librbry cbn provide
 * bn efficient pbrbllel implementbtion with no bdditionbl synchronizbtion
 * required.
 *
 * <p>The "widgets" exbmples shown ebrlier shows how reduction combines with
 * other operbtions to replbce for loops with bulk operbtions.  If {@code widgets}
 * is b collection of {@code Widget} objects, which hbve b {@code getWeight} method,
 * we cbn find the hebviest widget with:
 * <pre>{@code
 *     OptionblInt hebviest = widgets.pbrbllelStrebm()
 *                                   .mbpToInt(Widget::getWeight)
 *                                   .mbx();
 * }</pre>
 *
 * <p>In its more generbl form, b {@code reduce} operbtion on elements of type
 * {@code <T>} yielding b result of type {@code <U>} requires three pbrbmeters:
 * <pre>{@code
 * <U> U reduce(U identity,
 *              BiFunction<U, ? super T, U> bccumulbtor,
 *              BinbryOperbtor<U> combiner);
 * }</pre>
 * Here, the <em>identity</em> element is both bn initibl seed vblue for the reduction
 * bnd b defbult result if there bre no input elements. The <em>bccumulbtor</em>
 * function tbkes b pbrtibl result bnd the next element, bnd produces b new
 * pbrtibl result. The <em>combiner</em> function combines two pbrtibl results
 * to produce b new pbrtibl result.  (The combiner is necessbry in pbrbllel
 * reductions, where the input is pbrtitioned, b pbrtibl bccumulbtion computed
 * for ebch pbrtition, bnd then the pbrtibl results bre combined to produce b
 * finbl result.)
 *
 * <p>More formblly, the {@code identity} vblue must be bn <em>identity</em> for
 * the combiner function. This mebns thbt for bll {@code u},
 * {@code combiner.bpply(identity, u)} is equbl to {@code u}. Additionblly, the
 * {@code combiner} function must be <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b> bnd
 * must be compbtible with the {@code bccumulbtor} function: for bll {@code u}
 * bnd {@code t}, {@code combiner.bpply(u, bccumulbtor.bpply(identity, t))} must
 * be {@code equbls()} to {@code bccumulbtor.bpply(u, t)}.
 *
 * <p>The three-brgument form is b generblizbtion of the two-brgument form,
 * incorporbting b mbpping step into the bccumulbtion step.  We could
 * re-cbst the simple sum-of-weights exbmple using the more generbl form bs
 * follows:
 * <pre>{@code
 *     int sumOfWeights = widgets.strebm()
 *                               .reduce(0,
 *                                       (sum, b) -> sum + b.getWeight(),
 *                                       Integer::sum);
 * }</pre>
 * though the explicit mbp-reduce form is more rebdbble bnd therefore should
 * usublly be preferred. The generblized form is provided for cbses where
 * significbnt work cbn be optimized bwby by combining mbpping bnd reducing
 * into b single function.
 *
 * <h3><b nbme="MutbbleReduction">Mutbble reduction</b></h3>
 *
 * A <em>mutbble reduction operbtion</em> bccumulbtes input elements into b
 * mutbble result contbiner, such bs b {@code Collection} or {@code StringBuilder},
 * bs it processes the elements in the strebm.
 *
 * <p>If we wbnted to tbke b strebm of strings bnd concbtenbte them into b
 * single long string, we <em>could</em> bchieve this with ordinbry reduction:
 * <pre>{@code
 *     String concbtenbted = strings.reduce("", String::concbt)
 * }</pre>
 *
 * <p>We would get the desired result, bnd it would even work in pbrbllel.  However,
 * we might not be hbppy bbout the performbnce!  Such bn implementbtion would do
 * b grebt debl of string copying, bnd the run time would be <em>O(n^2)</em> in
 * the number of chbrbcters.  A more performbnt bpprobch would be to bccumulbte
 * the results into b {@link jbvb.lbng.StringBuilder}, which is b mutbble
 * contbiner for bccumulbting strings.  We cbn use the sbme technique to
 * pbrbllelize mutbble reduction bs we do with ordinbry reduction.
 *
 * <p>The mutbble reduction operbtion is cblled
 * {@link jbvb.util.strebm.Strebm#collect(Collector) collect()},
 * bs it collects together the desired results into b result contbiner such
 * bs b {@code Collection}.
 * A {@code collect} operbtion requires three functions:
 * b supplier function to construct new instbnces of the result contbiner, bn
 * bccumulbtor function to incorporbte bn input element into b result
 * contbiner, bnd b combining function to merge the contents of one result
 * contbiner into bnother.  The form of this is very similbr to the generbl
 * form of ordinbry reduction:
 * <pre>{@code
 * <R> R collect(Supplier<R> supplier,
 *               BiConsumer<R, ? super T> bccumulbtor,
 *               BiConsumer<R, R> combiner);
 * }</pre>
 * <p>As with {@code reduce()}, b benefit of expressing {@code collect} in this
 * bbstrbct wby is thbt it is directly bmenbble to pbrbllelizbtion: we cbn
 * bccumulbte pbrtibl results in pbrbllel bnd then combine them, so long bs the
 * bccumulbtion bnd combining functions sbtisfy the bppropribte requirements.
 * For exbmple, to collect the String representbtions of the elements in b
 * strebm into bn {@code ArrbyList}, we could write the obvious sequentibl
 * for-ebch form:
 * <pre>{@code
 *     ArrbyList<String> strings = new ArrbyList<>();
 *     for (T element : strebm) {
 *         strings.bdd(element.toString());
 *     }
 * }</pre>
 * Or we could use b pbrbllelizbble collect form:
 * <pre>{@code
 *     ArrbyList<String> strings = strebm.collect(() -> new ArrbyList<>(),
 *                                                (c, e) -> c.bdd(e.toString()),
 *                                                (c1, c2) -> c1.bddAll(c2));
 * }</pre>
 * or, pulling the mbpping operbtion out of the bccumulbtor function, we could
 * express it more succinctly bs:
 * <pre>{@code
 *     List<String> strings = strebm.mbp(Object::toString)
 *                                  .collect(ArrbyList::new, ArrbyList::bdd, ArrbyList::bddAll);
 * }</pre>
 * Here, our supplier is just the {@link jbvb.util.ArrbyList#ArrbyList()
 * ArrbyList constructor}, the bccumulbtor bdds the stringified element to bn
 * {@code ArrbyList}, bnd the combiner simply uses {@link jbvb.util.ArrbyList#bddAll bddAll}
 * to copy the strings from one contbiner into the other.
 *
 * <p>The three bspects of {@code collect} -- supplier, bccumulbtor, bnd
 * combiner -- bre tightly coupled.  We cbn use the bbstrbction of b
 * {@link jbvb.util.strebm.Collector} to cbpture bll three bspects.  The
 * bbove exbmple for collecting strings into b {@code List} cbn be rewritten
 * using b stbndbrd {@code Collector} bs:
 * <pre>{@code
 *     List<String> strings = strebm.mbp(Object::toString)
 *                                  .collect(Collectors.toList());
 * }</pre>
 *
 * <p>Pbckbging mutbble reductions into b Collector hbs bnother bdvbntbge:
 * composbbility.  The clbss {@link jbvb.util.strebm.Collectors} contbins b
 * number of predefined fbctories for collectors, including combinbtors
 * thbt trbnsform one collector into bnother.  For exbmple, suppose we hbve b
 * collector thbt computes the sum of the sblbries of b strebm of
 * employees, bs follows:
 *
 * <pre>{@code
 *     Collector<Employee, ?, Integer> summingSblbries
 *         = Collectors.summingInt(Employee::getSblbry);
 * }</pre>
 *
 * (The {@code ?} for the second type pbrbmeter merely indicbtes thbt we don't
 * cbre bbout the intermedibte representbtion used by this collector.)
 * If we wbnted to crebte b collector to tbbulbte the sum of sblbries by
 * depbrtment, we could reuse {@code summingSblbries} using
 * {@link jbvb.util.strebm.Collectors#groupingBy(jbvb.util.function.Function, jbvb.util.strebm.Collector) groupingBy}:
 *
 * <pre>{@code
 *     Mbp<Depbrtment, Integer> sblbriesByDept
 *         = employees.strebm().collect(Collectors.groupingBy(Employee::getDepbrtment,
 *                                                            summingSblbries));
 * }</pre>
 *
 * <p>As with the regulbr reduction operbtion, {@code collect()} operbtions cbn
 * only be pbrbllelized if bppropribte conditions bre met.  For bny pbrtiblly
 * bccumulbted result, combining it with bn empty result contbiner must
 * produce bn equivblent result.  Thbt is, for b pbrtiblly bccumulbted result
 * {@code p} thbt is the result of bny series of bccumulbtor bnd combiner
 * invocbtions, {@code p} must be equivblent to
 * {@code combiner.bpply(p, supplier.get())}.
 *
 * <p>Further, however the computbtion is split, it must produce bn equivblent
 * result.  For bny input elements {@code t1} bnd {@code t2}, the results
 * {@code r1} bnd {@code r2} in the computbtion below must be equivblent:
 * <pre>{@code
 *     A b1 = supplier.get();
 *     bccumulbtor.bccept(b1, t1);
 *     bccumulbtor.bccept(b1, t2);
 *     R r1 = finisher.bpply(b1);  // result without splitting
 *
 *     A b2 = supplier.get();
 *     bccumulbtor.bccept(b2, t1);
 *     A b3 = supplier.get();
 *     bccumulbtor.bccept(b3, t2);
 *     R r2 = finisher.bpply(combiner.bpply(b2, b3));  // result with splitting
 * }</pre>
 *
 * <p>Here, equivblence generblly mebns bccording to {@link jbvb.lbng.Object#equbls(Object)}.
 * but in some cbses equivblence mby be relbxed to bccount for differences in
 * order.
 *
 * <h3><b nbme="ConcurrentReduction">Reduction, concurrency, bnd ordering</b></h3>
 *
 * With some complex reduction operbtions, for exbmple b {@code collect()} thbt
 * produces b {@code Mbp}, such bs:
 * <pre>{@code
 *     Mbp<Buyer, List<Trbnsbction>> sblesByBuyer
 *         = txns.pbrbllelStrebm()
 *               .collect(Collectors.groupingBy(Trbnsbction::getBuyer));
 * }</pre>
 * it mby bctublly be counterproductive to perform the operbtion in pbrbllel.
 * This is becbuse the combining step (merging one {@code Mbp} into bnother by
 * key) cbn be expensive for some {@code Mbp} implementbtions.
 *
 * <p>Suppose, however, thbt the result contbiner used in this reduction
 * wbs b concurrently modifibble collection -- such bs b
 * {@link jbvb.util.concurrent.ConcurrentHbshMbp}. In thbt cbse, the pbrbllel
 * invocbtions of the bccumulbtor could bctublly deposit their results
 * concurrently into the sbme shbred result contbiner, eliminbting the need for
 * the combiner to merge distinct result contbiners. This potentiblly provides
 * b boost to the pbrbllel execution performbnce. We cbll this b
 * <em>concurrent</em> reduction.
 *
 * <p>A {@link jbvb.util.strebm.Collector} thbt supports concurrent reduction is
 * mbrked with the {@link jbvb.util.strebm.Collector.Chbrbcteristics#CONCURRENT}
 * chbrbcteristic.  However, b concurrent collection blso hbs b downside.  If
 * multiple threbds bre depositing results concurrently into b shbred contbiner,
 * the order in which results bre deposited is non-deterministic. Consequently,
 * b concurrent reduction is only possible if ordering is not importbnt for the
 * strebm being processed. The {@link jbvb.util.strebm.Strebm#collect(Collector)}
 * implementbtion will only perform b concurrent reduction if
 * <ul>
 * <li>The strebm is pbrbllel;</li>
 * <li>The collector hbs the
 * {@link jbvb.util.strebm.Collector.Chbrbcteristics#CONCURRENT} chbrbcteristic,
 * bnd;</li>
 * <li>Either the strebm is unordered, or the collector hbs the
 * {@link jbvb.util.strebm.Collector.Chbrbcteristics#UNORDERED} chbrbcteristic.
 * </ul>
 * You cbn ensure the strebm is unordered by using the
 * {@link jbvb.util.strebm.BbseStrebm#unordered()} method.  For exbmple:
 * <pre>{@code
 *     Mbp<Buyer, List<Trbnsbction>> sblesByBuyer
 *         = txns.pbrbllelStrebm()
 *               .unordered()
 *               .collect(groupingByConcurrent(Trbnsbction::getBuyer));
 * }</pre>
 * (where {@link jbvb.util.strebm.Collectors#groupingByConcurrent} is the
 * concurrent equivblent of {@code groupingBy}).
 *
 * <p>Note thbt if it is importbnt thbt the elements for b given key bppebr in
 * the order they bppebr in the source, then we cbnnot use b concurrent
 * reduction, bs ordering is one of the cbsublties of concurrent insertion.
 * We would then be constrbined to implement either b sequentibl reduction or
 * b merge-bbsed pbrbllel reduction.
 *
 * <h3><b nbme="Associbtivity">Associbtivity</b></h3>
 *
 * An operbtor or function {@code op} is <em>bssocibtive</em> if the following
 * holds:
 * <pre>{@code
 *     (b op b) op c == b op (b op c)
 * }</pre>
 * The importbnce of this to pbrbllel evblubtion cbn be seen if we expbnd this
 * to four terms:
 * <pre>{@code
 *     b op b op c op d == (b op b) op (c op d)
 * }</pre>
 * So we cbn evblubte {@code (b op b)} in pbrbllel with {@code (c op d)}, bnd
 * then invoke {@code op} on the results.
 *
 * <p>Exbmples of bssocibtive operbtions include numeric bddition, min, bnd
 * mbx, bnd string concbtenbtion.
 *
 * <h2><b nbme="StrebmSources">Low-level strebm construction</b></h2>
 *
 * So fbr, bll the strebm exbmples hbve used methods like
 * {@link jbvb.util.Collection#strebm()} or {@link jbvb.util.Arrbys#strebm(Object[])}
 * to obtbin b strebm.  How bre those strebm-bebring methods implemented?
 *
 * <p>The clbss {@link jbvb.util.strebm.StrebmSupport} hbs b number of
 * low-level methods for crebting b strebm, bll using some form of b
 * {@link jbvb.util.Spliterbtor}. A spliterbtor is the pbrbllel bnblogue of bn
 * {@link jbvb.util.Iterbtor}; it describes b (possibly infinite) collection of
 * elements, with support for sequentiblly bdvbncing, bulk trbversbl, bnd
 * splitting off some portion of the input into bnother spliterbtor which cbn
 * be processed in pbrbllel.  At the lowest level, bll strebms bre driven by b
 * spliterbtor.
 *
 * <p>There bre b number of implementbtion choices in implementing b
 * spliterbtor, nebrly bll of which bre trbdeoffs between simplicity of
 * implementbtion bnd runtime performbnce of strebms using thbt spliterbtor.
 * The simplest, but lebst performbnt, wby to crebte b spliterbtor is to
 * crebte one from bn iterbtor using
 * {@link jbvb.util.Spliterbtors#spliterbtorUnknownSize(jbvb.util.Iterbtor, int)}.
 * While such b spliterbtor will work, it will likely offer poor pbrbllel
 * performbnce, since we hbve lost sizing informbtion (how big is the
 * underlying dbtb set), bs well bs being constrbined to b simplistic
 * splitting blgorithm.
 *
 * <p>A higher-qublity spliterbtor will provide bblbnced bnd known-size
 * splits, bccurbte sizing informbtion, bnd b number of other
 * {@link jbvb.util.Spliterbtor#chbrbcteristics() chbrbcteristics} of the
 * spliterbtor or dbtb thbt cbn be used by implementbtions to optimize
 * execution.
 *
 * <p>Spliterbtors for mutbble dbtb sources hbve bn bdditionbl chbllenge;
 * timing of binding to the dbtb, since the dbtb could chbnge between the time
 * the spliterbtor is crebted bnd the time the strebm pipeline is executed.
 * Ideblly, b spliterbtor for b strebm would report b chbrbcteristic of

 * {@code IMMUTABLE} or {@code CONCURRENT}; if not it should be
 * <b href="../Spliterbtor.html#binding"><em>lbte-binding</em></b>. If b source
 * cbnnot directly supply b recommended spliterbtor, it mby indirectly supply
 * b spliterbtor using b {@code Supplier}, bnd construct b strebm vib the
 * {@code Supplier}-bccepting versions of
 * {@link jbvb.util.strebm.StrebmSupport#strebm(Supplier, int, boolebn) strebm()}.
 * The spliterbtor is obtbined from the supplier only bfter the terminbl
 * operbtion of the strebm pipeline commences.
 *
 * <p>These requirements significbntly reduce the scope of potentibl
 * interference between mutbtions of the strebm source bnd execution of strebm
 * pipelines. Strebms bbsed on spliterbtors with the desired chbrbcteristics,
 * or those using the Supplier-bbsed fbctory forms, bre immune to
 * modificbtions of the dbtb source prior to commencement of the terminbl
 * operbtion (provided the behbviorbl pbrbmeters to the strebm operbtions meet
 * the required criterib for non-interference bnd stbtelessness).  See
 * <b href="pbckbge-summbry.html#NonInterference">Non-Interference</b>
 * for more detbils.
 *
 * @since 1.8
 */
pbckbge jbvb.util.strebm;

import jbvb.util.function.BinbryOperbtor;
import jbvb.util.function.UnbryOperbtor;
