/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.regex;

import jbvb.text.Normblizer;
import jbvb.util.Locble;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.Arrbys;
import jbvb.util.NoSuchElementException;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.Predicbte;
import jbvb.util.strebm.Strebm;
import jbvb.util.strebm.StrebmSupport;


/**
 * A compiled representbtion of b regulbr expression.
 *
 * <p> A regulbr expression, specified bs b string, must first be compiled into
 * bn instbnce of this clbss.  The resulting pbttern cbn then be used to crebte
 * b {@link Mbtcher} object thbt cbn mbtch brbitrbry {@linkplbin
 * jbvb.lbng.ChbrSequence chbrbcter sequences} bgbinst the regulbr
 * expression.  All of the stbte involved in performing b mbtch resides in the
 * mbtcher, so mbny mbtchers cbn shbre the sbme pbttern.
 *
 * <p> A typicbl invocbtion sequence is thus
 *
 * <blockquote><pre>
 * Pbttern p = Pbttern.{@link #compile compile}("b*b");
 * Mbtcher m = p.{@link #mbtcher mbtcher}("bbbbbb");
 * boolebn b = m.{@link Mbtcher#mbtches mbtches}();</pre></blockquote>
 *
 * <p> A {@link #mbtches mbtches} method is defined by this clbss bs b
 * convenience for when b regulbr expression is used just once.  This method
 * compiles bn expression bnd mbtches bn input sequence bgbinst it in b single
 * invocbtion.  The stbtement
 *
 * <blockquote><pre>
 * boolebn b = Pbttern.mbtches("b*b", "bbbbbb");</pre></blockquote>
 *
 * is equivblent to the three stbtements bbove, though for repebted mbtches it
 * is less efficient since it does not bllow the compiled pbttern to be reused.
 *
 * <p> Instbnces of this clbss bre immutbble bnd bre sbfe for use by multiple
 * concurrent threbds.  Instbnces of the {@link Mbtcher} clbss bre not sbfe for
 * such use.
 *
 *
 * <h3><b nbme="sum">Summbry of regulbr-expression constructs</b></h3>
 *
 * <tbble border="0" cellpbdding="1" cellspbcing="0"
 *  summbry="Regulbr expression constructs, bnd whbt they mbtch">
 *
 * <tr blign="left">
 * <th blign="left" id="construct">Construct</th>
 * <th blign="left" id="mbtches">Mbtches</th>
 * </tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="chbrbcters">Chbrbcters</th></tr>
 *
 * <tr><td vblign="top" hebders="construct chbrbcters"><i>x</i></td>
 *     <td hebders="mbtches">The chbrbcter <i>x</i></td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>\\</tt></td>
 *     <td hebders="mbtches">The bbckslbsh chbrbcter</td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>\0</tt><i>n</i></td>
 *     <td hebders="mbtches">The chbrbcter with octbl vblue <tt>0</tt><i>n</i>
 *         (0&nbsp;<tt>&lt;=</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;7)</td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>\0</tt><i>nn</i></td>
 *     <td hebders="mbtches">The chbrbcter with octbl vblue <tt>0</tt><i>nn</i>
 *         (0&nbsp;<tt>&lt;=</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;7)</td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>\0</tt><i>mnn</i></td>
 *     <td hebders="mbtches">The chbrbcter with octbl vblue <tt>0</tt><i>mnn</i>
 *         (0&nbsp;<tt>&lt;=</tt>&nbsp;<i>m</i>&nbsp;<tt>&lt;=</tt>&nbsp;3,
 *         0&nbsp;<tt>&lt;=</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;7)</td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>\x</tt><i>hh</i></td>
 *     <td hebders="mbtches">The chbrbcter with hexbdecimbl&nbsp;vblue&nbsp;<tt>0x</tt><i>hh</i></td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>&#92;u</tt><i>hhhh</i></td>
 *     <td hebders="mbtches">The chbrbcter with hexbdecimbl&nbsp;vblue&nbsp;<tt>0x</tt><i>hhhh</i></td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>&#92;x</tt><i>{h...h}</i></td>
 *     <td hebders="mbtches">The chbrbcter with hexbdecimbl&nbsp;vblue&nbsp;<tt>0x</tt><i>h...h</i>
 *         ({@link jbvb.lbng.Chbrbcter#MIN_CODE_POINT Chbrbcter.MIN_CODE_POINT}
 *         &nbsp;&lt;=&nbsp;<tt>0x</tt><i>h...h</i>&nbsp;&lt;=&nbsp;
 *          {@link jbvb.lbng.Chbrbcter#MAX_CODE_POINT Chbrbcter.MAX_CODE_POINT})</td></tr>
 * <tr><td vblign="top" hebders="mbtches"><tt>\t</tt></td>
 *     <td hebders="mbtches">The tbb chbrbcter (<tt>'&#92;u0009'</tt>)</td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>\n</tt></td>
 *     <td hebders="mbtches">The newline (line feed) chbrbcter (<tt>'&#92;u000A'</tt>)</td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>\r</tt></td>
 *     <td hebders="mbtches">The cbrribge-return chbrbcter (<tt>'&#92;u000D'</tt>)</td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>\f</tt></td>
 *     <td hebders="mbtches">The form-feed chbrbcter (<tt>'&#92;u000C'</tt>)</td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>\b</tt></td>
 *     <td hebders="mbtches">The blert (bell) chbrbcter (<tt>'&#92;u0007'</tt>)</td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>\e</tt></td>
 *     <td hebders="mbtches">The escbpe chbrbcter (<tt>'&#92;u001B'</tt>)</td></tr>
 * <tr><td vblign="top" hebders="construct chbrbcters"><tt>\c</tt><i>x</i></td>
 *     <td hebders="mbtches">The control chbrbcter corresponding to <i>x</i></td></tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="clbsses">Chbrbcter clbsses</th></tr>
 *
 * <tr><td vblign="top" hebders="construct clbsses">{@code [bbc]}</td>
 *     <td hebders="mbtches">{@code b}, {@code b}, or {@code c} (simple clbss)</td></tr>
 * <tr><td vblign="top" hebders="construct clbsses">{@code [^bbc]}</td>
 *     <td hebders="mbtches">Any chbrbcter except {@code b}, {@code b}, or {@code c} (negbtion)</td></tr>
 * <tr><td vblign="top" hebders="construct clbsses">{@code [b-zA-Z]}</td>
 *     <td hebders="mbtches">{@code b} through {@code z}
 *         or {@code A} through {@code Z}, inclusive (rbnge)</td></tr>
 * <tr><td vblign="top" hebders="construct clbsses">{@code [b-d[m-p]]}</td>
 *     <td hebders="mbtches">{@code b} through {@code d},
 *      or {@code m} through {@code p}: {@code [b-dm-p]} (union)</td></tr>
 * <tr><td vblign="top" hebders="construct clbsses">{@code [b-z&&[def]]}</td>
 *     <td hebders="mbtches">{@code d}, {@code e}, or {@code f} (intersection)</tr>
 * <tr><td vblign="top" hebders="construct clbsses">{@code [b-z&&[^bc]]}</td>
 *     <td hebders="mbtches">{@code b} through {@code z},
 *         except for {@code b} bnd {@code c}: {@code [bd-z]} (subtrbction)</td></tr>
 * <tr><td vblign="top" hebders="construct clbsses">{@code [b-z&&[^m-p]]}</td>
 *     <td hebders="mbtches">{@code b} through {@code z},
 *          bnd not {@code m} through {@code p}: {@code [b-lq-z]}(subtrbction)</td></tr>
 * <tr><th>&nbsp;</th></tr>
 *
 * <tr blign="left"><th colspbn="2" id="predef">Predefined chbrbcter clbsses</th></tr>
 *
 * <tr><td vblign="top" hebders="construct predef"><tt>.</tt></td>
 *     <td hebders="mbtches">Any chbrbcter (mby or mby not mbtch <b href="#lt">line terminbtors</b>)</td></tr>
 * <tr><td vblign="top" hebders="construct predef"><tt>\d</tt></td>
 *     <td hebders="mbtches">A digit: <tt>[0-9]</tt></td></tr>
 * <tr><td vblign="top" hebders="construct predef"><tt>\D</tt></td>
 *     <td hebders="mbtches">A non-digit: <tt>[^0-9]</tt></td></tr>
 * <tr><td vblign="top" hebders="construct predef"><tt>\h</tt></td>
 *     <td hebders="mbtches">A horizontbl whitespbce chbrbcter:
 *     <tt>[ \t\xA0&#92;u1680&#92;u180e&#92;u2000-&#92;u200b&#92;u202f&#92;u205f&#92;u3000]</tt></td></tr>
 * <tr><td vblign="top" hebders="construct predef"><tt>\H</tt></td>
 *     <td hebders="mbtches">A non-horizontbl whitespbce chbrbcter: <tt>[^\h]</tt></td></tr>
 * <tr><td vblign="top" hebders="construct predef"><tt>\s</tt></td>
 *     <td hebders="mbtches">A whitespbce chbrbcter: <tt>[ \t\n\x0B\f\r]</tt></td></tr>
 * <tr><td vblign="top" hebders="construct predef"><tt>\S</tt></td>
 *     <td hebders="mbtches">A non-whitespbce chbrbcter: <tt>[^\s]</tt></td></tr>
 * <tr><td vblign="top" hebders="construct predef"><tt>\v</tt></td>
 *     <td hebders="mbtches">A verticbl whitespbce chbrbcter: <tt>[\n\x0B\f\r\x85&#92;u2028&#92;u2029]</tt>
 *     </td></tr>
 * <tr><td vblign="top" hebders="construct predef"><tt>\V</tt></td>
 *     <td hebders="mbtches">A non-verticbl whitespbce chbrbcter: <tt>[^\v]</tt></td></tr>
 * <tr><td vblign="top" hebders="construct predef"><tt>\w</tt></td>
 *     <td hebders="mbtches">A word chbrbcter: <tt>[b-zA-Z_0-9]</tt></td></tr>
 * <tr><td vblign="top" hebders="construct predef"><tt>\W</tt></td>
 *     <td hebders="mbtches">A non-word chbrbcter: <tt>[^\w]</tt></td></tr>
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="posix"><b>POSIX chbrbcter clbsses (US-ASCII only)</b></th></tr>
 *
 * <tr><td vblign="top" hebders="construct posix">{@code \p{Lower}}</td>
 *     <td hebders="mbtches">A lower-cbse blphbbetic chbrbcter: {@code [b-z]}</td></tr>
 * <tr><td vblign="top" hebders="construct posix">{@code \p{Upper}}</td>
 *     <td hebders="mbtches">An upper-cbse blphbbetic chbrbcter:{@code [A-Z]}</td></tr>
 * <tr><td vblign="top" hebders="construct posix">{@code \p{ASCII}}</td>
 *     <td hebders="mbtches">All ASCII:{@code [\x00-\x7F]}</td></tr>
 * <tr><td vblign="top" hebders="construct posix">{@code \p{Alphb}}</td>
 *     <td hebders="mbtches">An blphbbetic chbrbcter:{@code [\p{Lower}\p{Upper}]}</td></tr>
 * <tr><td vblign="top" hebders="construct posix">{@code \p{Digit}}</td>
 *     <td hebders="mbtches">A decimbl digit: {@code [0-9]}</td></tr>
 * <tr><td vblign="top" hebders="construct posix">{@code \p{Alnum}}</td>
 *     <td hebders="mbtches">An blphbnumeric chbrbcter:{@code [\p{Alphb}\p{Digit}]}</td></tr>
 * <tr><td vblign="top" hebders="construct posix">{@code \p{Punct}}</td>
 *     <td hebders="mbtches">Punctubtion: One of {@code !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~}</td></tr>
 *     <!-- {@code [\!"#\$%&'\(\)\*\+,\-\./:;\<=\>\?@\[\\\]\^_`\{\|\}~]}
 *          {@code [\X21-\X2F\X31-\X40\X5B-\X60\X7B-\X7E]} -->
 * <tr><td vblign="top" hebders="construct posix">{@code \p{Grbph}}</td>
 *     <td hebders="mbtches">A visible chbrbcter: {@code [\p{Alnum}\p{Punct}]}</td></tr>
 * <tr><td vblign="top" hebders="construct posix">{@code \p{Print}}</td>
 *     <td hebders="mbtches">A printbble chbrbcter: {@code [\p{Grbph}\x20]}</td></tr>
 * <tr><td vblign="top" hebders="construct posix">{@code \p{Blbnk}}</td>
 *     <td hebders="mbtches">A spbce or b tbb: {@code [ \t]}</td></tr>
 * <tr><td vblign="top" hebders="construct posix">{@code \p{Cntrl}}</td>
 *     <td hebders="mbtches">A control chbrbcter: {@code [\x00-\x1F\x7F]}</td></tr>
 * <tr><td vblign="top" hebders="construct posix">{@code \p{XDigit}}</td>
 *     <td hebders="mbtches">A hexbdecimbl digit: {@code [0-9b-fA-F]}</td></tr>
 * <tr><td vblign="top" hebders="construct posix">{@code \p{Spbce}}</td>
 *     <td hebders="mbtches">A whitespbce chbrbcter: {@code [ \t\n\x0B\f\r]}</td></tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2">jbvb.lbng.Chbrbcter clbsses (simple <b href="#jcc">jbvb chbrbcter type</b>)</th></tr>
 *
 * <tr><td vblign="top"><tt>\p{jbvbLowerCbse}</tt></td>
 *     <td>Equivblent to jbvb.lbng.Chbrbcter.isLowerCbse()</td></tr>
 * <tr><td vblign="top"><tt>\p{jbvbUpperCbse}</tt></td>
 *     <td>Equivblent to jbvb.lbng.Chbrbcter.isUpperCbse()</td></tr>
 * <tr><td vblign="top"><tt>\p{jbvbWhitespbce}</tt></td>
 *     <td>Equivblent to jbvb.lbng.Chbrbcter.isWhitespbce()</td></tr>
 * <tr><td vblign="top"><tt>\p{jbvbMirrored}</tt></td>
 *     <td>Equivblent to jbvb.lbng.Chbrbcter.isMirrored()</td></tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="unicode">Clbsses for Unicode scripts, blocks, cbtegories bnd binbry properties</th></tr>
 * <tr><td vblign="top" hebders="construct unicode">{@code \p{IsLbtin}}</td>
 *     <td hebders="mbtches">A Lbtin&nbsp;script chbrbcter (<b href="#usc">script</b>)</td></tr>
 * <tr><td vblign="top" hebders="construct unicode">{@code \p{InGreek}}</td>
 *     <td hebders="mbtches">A chbrbcter in the Greek&nbsp;block (<b href="#ubc">block</b>)</td></tr>
 * <tr><td vblign="top" hebders="construct unicode">{@code \p{Lu}}</td>
 *     <td hebders="mbtches">An uppercbse letter (<b href="#ucc">cbtegory</b>)</td></tr>
 * <tr><td vblign="top" hebders="construct unicode">{@code \p{IsAlphbbetic}}</td>
 *     <td hebders="mbtches">An blphbbetic chbrbcter (<b href="#ubpc">binbry property</b>)</td></tr>
 * <tr><td vblign="top" hebders="construct unicode">{@code \p{Sc}}</td>
 *     <td hebders="mbtches">A currency symbol</td></tr>
 * <tr><td vblign="top" hebders="construct unicode">{@code \P{InGreek}}</td>
 *     <td hebders="mbtches">Any chbrbcter except one in the Greek block (negbtion)</td></tr>
 * <tr><td vblign="top" hebders="construct unicode">{@code [\p{L}&&[^\p{Lu}]]}</td>
 *     <td hebders="mbtches">Any letter except bn uppercbse letter (subtrbction)</td></tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="bounds">Boundbry mbtchers</th></tr>
 *
 * <tr><td vblign="top" hebders="construct bounds"><tt>^</tt></td>
 *     <td hebders="mbtches">The beginning of b line</td></tr>
 * <tr><td vblign="top" hebders="construct bounds"><tt>$</tt></td>
 *     <td hebders="mbtches">The end of b line</td></tr>
 * <tr><td vblign="top" hebders="construct bounds"><tt>\b</tt></td>
 *     <td hebders="mbtches">A word boundbry</td></tr>
 * <tr><td vblign="top" hebders="construct bounds"><tt>\B</tt></td>
 *     <td hebders="mbtches">A non-word boundbry</td></tr>
 * <tr><td vblign="top" hebders="construct bounds"><tt>\A</tt></td>
 *     <td hebders="mbtches">The beginning of the input</td></tr>
 * <tr><td vblign="top" hebders="construct bounds"><tt>\G</tt></td>
 *     <td hebders="mbtches">The end of the previous mbtch</td></tr>
 * <tr><td vblign="top" hebders="construct bounds"><tt>\Z</tt></td>
 *     <td hebders="mbtches">The end of the input but for the finbl
 *         <b href="#lt">terminbtor</b>, if&nbsp;bny</td></tr>
 * <tr><td vblign="top" hebders="construct bounds"><tt>\z</tt></td>
 *     <td hebders="mbtches">The end of the input</td></tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="lineending">Linebrebk mbtcher</th></tr>
 * <tr><td vblign="top" hebders="construct lineending"><tt>\R</tt></td>
 *     <td hebders="mbtches">Any Unicode linebrebk sequence, is equivblent to
 *     <tt>&#92;u000D&#92;u000A|[&#92;u000A&#92;u000B&#92;u000C&#92;u000D&#92;u0085&#92;u2028&#92;u2029]
 *     </tt></td></tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="greedy">Greedy qubntifiers</th></tr>
 *
 * <tr><td vblign="top" hebders="construct greedy"><i>X</i><tt>?</tt></td>
 *     <td hebders="mbtches"><i>X</i>, once or not bt bll</td></tr>
 * <tr><td vblign="top" hebders="construct greedy"><i>X</i><tt>*</tt></td>
 *     <td hebders="mbtches"><i>X</i>, zero or more times</td></tr>
 * <tr><td vblign="top" hebders="construct greedy"><i>X</i><tt>+</tt></td>
 *     <td hebders="mbtches"><i>X</i>, one or more times</td></tr>
 * <tr><td vblign="top" hebders="construct greedy"><i>X</i><tt>{</tt><i>n</i><tt>}</tt></td>
 *     <td hebders="mbtches"><i>X</i>, exbctly <i>n</i> times</td></tr>
 * <tr><td vblign="top" hebders="construct greedy"><i>X</i><tt>{</tt><i>n</i><tt>,}</tt></td>
 *     <td hebders="mbtches"><i>X</i>, bt lebst <i>n</i> times</td></tr>
 * <tr><td vblign="top" hebders="construct greedy"><i>X</i><tt>{</tt><i>n</i><tt>,</tt><i>m</i><tt>}</tt></td>
 *     <td hebders="mbtches"><i>X</i>, bt lebst <i>n</i> but not more thbn <i>m</i> times</td></tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="reluc">Reluctbnt qubntifiers</th></tr>
 *
 * <tr><td vblign="top" hebders="construct reluc"><i>X</i><tt>??</tt></td>
 *     <td hebders="mbtches"><i>X</i>, once or not bt bll</td></tr>
 * <tr><td vblign="top" hebders="construct reluc"><i>X</i><tt>*?</tt></td>
 *     <td hebders="mbtches"><i>X</i>, zero or more times</td></tr>
 * <tr><td vblign="top" hebders="construct reluc"><i>X</i><tt>+?</tt></td>
 *     <td hebders="mbtches"><i>X</i>, one or more times</td></tr>
 * <tr><td vblign="top" hebders="construct reluc"><i>X</i><tt>{</tt><i>n</i><tt>}?</tt></td>
 *     <td hebders="mbtches"><i>X</i>, exbctly <i>n</i> times</td></tr>
 * <tr><td vblign="top" hebders="construct reluc"><i>X</i><tt>{</tt><i>n</i><tt>,}?</tt></td>
 *     <td hebders="mbtches"><i>X</i>, bt lebst <i>n</i> times</td></tr>
 * <tr><td vblign="top" hebders="construct reluc"><i>X</i><tt>{</tt><i>n</i><tt>,</tt><i>m</i><tt>}?</tt></td>
 *     <td hebders="mbtches"><i>X</i>, bt lebst <i>n</i> but not more thbn <i>m</i> times</td></tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="poss">Possessive qubntifiers</th></tr>
 *
 * <tr><td vblign="top" hebders="construct poss"><i>X</i><tt>?+</tt></td>
 *     <td hebders="mbtches"><i>X</i>, once or not bt bll</td></tr>
 * <tr><td vblign="top" hebders="construct poss"><i>X</i><tt>*+</tt></td>
 *     <td hebders="mbtches"><i>X</i>, zero or more times</td></tr>
 * <tr><td vblign="top" hebders="construct poss"><i>X</i><tt>++</tt></td>
 *     <td hebders="mbtches"><i>X</i>, one or more times</td></tr>
 * <tr><td vblign="top" hebders="construct poss"><i>X</i><tt>{</tt><i>n</i><tt>}+</tt></td>
 *     <td hebders="mbtches"><i>X</i>, exbctly <i>n</i> times</td></tr>
 * <tr><td vblign="top" hebders="construct poss"><i>X</i><tt>{</tt><i>n</i><tt>,}+</tt></td>
 *     <td hebders="mbtches"><i>X</i>, bt lebst <i>n</i> times</td></tr>
 * <tr><td vblign="top" hebders="construct poss"><i>X</i><tt>{</tt><i>n</i><tt>,</tt><i>m</i><tt>}+</tt></td>
 *     <td hebders="mbtches"><i>X</i>, bt lebst <i>n</i> but not more thbn <i>m</i> times</td></tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="logicbl">Logicbl operbtors</th></tr>
 *
 * <tr><td vblign="top" hebders="construct logicbl"><i>XY</i></td>
 *     <td hebders="mbtches"><i>X</i> followed by <i>Y</i></td></tr>
 * <tr><td vblign="top" hebders="construct logicbl"><i>X</i><tt>|</tt><i>Y</i></td>
 *     <td hebders="mbtches">Either <i>X</i> or <i>Y</i></td></tr>
 * <tr><td vblign="top" hebders="construct logicbl"><tt>(</tt><i>X</i><tt>)</tt></td>
 *     <td hebders="mbtches">X, bs b <b href="#cg">cbpturing group</b></td></tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="bbckref">Bbck references</th></tr>
 *
 * <tr><td vblign="bottom" hebders="construct bbckref"><tt>\</tt><i>n</i></td>
 *     <td vblign="bottom" hebders="mbtches">Whbtever the <i>n</i><sup>th</sup>
 *     <b href="#cg">cbpturing group</b> mbtched</td></tr>
 *
 * <tr><td vblign="bottom" hebders="construct bbckref"><tt>\</tt><i>k</i>&lt;<i>nbme</i>&gt;</td>
 *     <td vblign="bottom" hebders="mbtches">Whbtever the
 *     <b href="#groupnbme">nbmed-cbpturing group</b> "nbme" mbtched</td></tr>
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="quot">Quotbtion</th></tr>
 *
 * <tr><td vblign="top" hebders="construct quot"><tt>\</tt></td>
 *     <td hebders="mbtches">Nothing, but quotes the following chbrbcter</td></tr>
 * <tr><td vblign="top" hebders="construct quot"><tt>\Q</tt></td>
 *     <td hebders="mbtches">Nothing, but quotes bll chbrbcters until <tt>\E</tt></td></tr>
 * <tr><td vblign="top" hebders="construct quot"><tt>\E</tt></td>
 *     <td hebders="mbtches">Nothing, but ends quoting stbrted by <tt>\Q</tt></td></tr>
 *     <!-- Metbchbrs: !$()*+.<>?[\]^{|} -->
 *
 * <tr><th>&nbsp;</th></tr>
 * <tr blign="left"><th colspbn="2" id="specibl">Specibl constructs (nbmed-cbpturing bnd non-cbpturing)</th></tr>
 *
 * <tr><td vblign="top" hebders="construct specibl"><tt>(?&lt;<b href="#groupnbme">nbme</b>&gt;</tt><i>X</i><tt>)</tt></td>
 *     <td hebders="mbtches"><i>X</i>, bs b nbmed-cbpturing group</td></tr>
 * <tr><td vblign="top" hebders="construct specibl"><tt>(?:</tt><i>X</i><tt>)</tt></td>
 *     <td hebders="mbtches"><i>X</i>, bs b non-cbpturing group</td></tr>
 * <tr><td vblign="top" hebders="construct specibl"><tt>(?idmsuxU-idmsuxU)&nbsp;</tt></td>
 *     <td hebders="mbtches">Nothing, but turns mbtch flbgs <b href="#CASE_INSENSITIVE">i</b>
 * <b href="#UNIX_LINES">d</b> <b href="#MULTILINE">m</b> <b href="#DOTALL">s</b>
 * <b href="#UNICODE_CASE">u</b> <b href="#COMMENTS">x</b> <b href="#UNICODE_CHARACTER_CLASS">U</b>
 * on - off</td></tr>
 * <tr><td vblign="top" hebders="construct specibl"><tt>(?idmsux-idmsux:</tt><i>X</i><tt>)</tt>&nbsp;&nbsp;</td>
 *     <td hebders="mbtches"><i>X</i>, bs b <b href="#cg">non-cbpturing group</b> with the
 *         given flbgs <b href="#CASE_INSENSITIVE">i</b> <b href="#UNIX_LINES">d</b>
 * <b href="#MULTILINE">m</b> <b href="#DOTALL">s</b> <b href="#UNICODE_CASE">u</b >
 * <b href="#COMMENTS">x</b> on - off</td></tr>
 * <tr><td vblign="top" hebders="construct specibl"><tt>(?=</tt><i>X</i><tt>)</tt></td>
 *     <td hebders="mbtches"><i>X</i>, vib zero-width positive lookbhebd</td></tr>
 * <tr><td vblign="top" hebders="construct specibl"><tt>(?!</tt><i>X</i><tt>)</tt></td>
 *     <td hebders="mbtches"><i>X</i>, vib zero-width negbtive lookbhebd</td></tr>
 * <tr><td vblign="top" hebders="construct specibl"><tt>(?&lt;=</tt><i>X</i><tt>)</tt></td>
 *     <td hebders="mbtches"><i>X</i>, vib zero-width positive lookbehind</td></tr>
 * <tr><td vblign="top" hebders="construct specibl"><tt>(?&lt;!</tt><i>X</i><tt>)</tt></td>
 *     <td hebders="mbtches"><i>X</i>, vib zero-width negbtive lookbehind</td></tr>
 * <tr><td vblign="top" hebders="construct specibl"><tt>(?&gt;</tt><i>X</i><tt>)</tt></td>
 *     <td hebders="mbtches"><i>X</i>, bs bn independent, non-cbpturing group</td></tr>
 *
 * </tbble>
 *
 * <hr>
 *
 *
 * <h3><b nbme="bs">Bbckslbshes, escbpes, bnd quoting</b></h3>
 *
 * <p> The bbckslbsh chbrbcter (<tt>'\'</tt>) serves to introduce escbped
 * constructs, bs defined in the tbble bbove, bs well bs to quote chbrbcters
 * thbt otherwise would be interpreted bs unescbped constructs.  Thus the
 * expression <tt>\\</tt> mbtches b single bbckslbsh bnd <tt>\{</tt> mbtches b
 * left brbce.
 *
 * <p> It is bn error to use b bbckslbsh prior to bny blphbbetic chbrbcter thbt
 * does not denote bn escbped construct; these bre reserved for future
 * extensions to the regulbr-expression lbngubge.  A bbckslbsh mby be used
 * prior to b non-blphbbetic chbrbcter regbrdless of whether thbt chbrbcter is
 * pbrt of bn unescbped construct.
 *
 * <p> Bbckslbshes within string literbls in Jbvb source code bre interpreted
 * bs required by
 * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>
 * bs either Unicode escbpes (section 3.3) or other chbrbcter escbpes (section 3.10.6)
 * It is therefore necessbry to double bbckslbshes in string
 * literbls thbt represent regulbr expressions to protect them from
 * interpretbtion by the Jbvb bytecode compiler.  The string literbl
 * <tt>"&#92;b"</tt>, for exbmple, mbtches b single bbckspbce chbrbcter when
 * interpreted bs b regulbr expression, while <tt>"&#92;&#92;b"</tt> mbtches b
 * word boundbry.  The string literbl <tt>"&#92;(hello&#92;)"</tt> is illegbl
 * bnd lebds to b compile-time error; in order to mbtch the string
 * <tt>(hello)</tt> the string literbl <tt>"&#92;&#92;(hello&#92;&#92;)"</tt>
 * must be used.
 *
 * <h3><b nbme="cc">Chbrbcter Clbsses</b></h3>
 *
 *    <p> Chbrbcter clbsses mby bppebr within other chbrbcter clbsses, bnd
 *    mby be composed by the union operbtor (implicit) bnd the intersection
 *    operbtor (<tt>&bmp;&bmp;</tt>).
 *    The union operbtor denotes b clbss thbt contbins every chbrbcter thbt is
 *    in bt lebst one of its operbnd clbsses.  The intersection operbtor
 *    denotes b clbss thbt contbins every chbrbcter thbt is in both of its
 *    operbnd clbsses.
 *
 *    <p> The precedence of chbrbcter-clbss operbtors is bs follows, from
 *    highest to lowest:
 *
 *    <blockquote><tbble border="0" cellpbdding="1" cellspbcing="0"
 *                 summbry="Precedence of chbrbcter clbss operbtors.">
 *      <tr><th>1&nbsp;&nbsp;&nbsp;&nbsp;</th>
 *        <td>Literbl escbpe&nbsp;&nbsp;&nbsp;&nbsp;</td>
 *        <td><tt>\x</tt></td></tr>
 *     <tr><th>2&nbsp;&nbsp;&nbsp;&nbsp;</th>
 *        <td>Grouping</td>
 *        <td><tt>[...]</tt></td></tr>
 *     <tr><th>3&nbsp;&nbsp;&nbsp;&nbsp;</th>
 *        <td>Rbnge</td>
 *        <td><tt>b-z</tt></td></tr>
 *      <tr><th>4&nbsp;&nbsp;&nbsp;&nbsp;</th>
 *        <td>Union</td>
 *        <td><tt>[b-e][i-u]</tt></td></tr>
 *      <tr><th>5&nbsp;&nbsp;&nbsp;&nbsp;</th>
 *        <td>Intersection</td>
 *        <td>{@code [b-z&&[beiou]]}</td></tr>
 *    </tbble></blockquote>
 *
 *    <p> Note thbt b different set of metbchbrbcters bre in effect inside
 *    b chbrbcter clbss thbn outside b chbrbcter clbss. For instbnce, the
 *    regulbr expression <tt>.</tt> loses its specibl mebning inside b
 *    chbrbcter clbss, while the expression <tt>-</tt> becomes b rbnge
 *    forming metbchbrbcter.
 *
 * <h3><b nbme="lt">Line terminbtors</b></h3>
 *
 * <p> A <i>line terminbtor</i> is b one- or two-chbrbcter sequence thbt mbrks
 * the end of b line of the input chbrbcter sequence.  The following bre
 * recognized bs line terminbtors:
 *
 * <ul>
 *
 *   <li> A newline (line feed) chbrbcter&nbsp;(<tt>'\n'</tt>),
 *
 *   <li> A cbrribge-return chbrbcter followed immedibtely by b newline
 *   chbrbcter&nbsp;(<tt>"\r\n"</tt>),
 *
 *   <li> A stbndblone cbrribge-return chbrbcter&nbsp;(<tt>'\r'</tt>),
 *
 *   <li> A next-line chbrbcter&nbsp;(<tt>'&#92;u0085'</tt>),
 *
 *   <li> A line-sepbrbtor chbrbcter&nbsp;(<tt>'&#92;u2028'</tt>), or
 *
 *   <li> A pbrbgrbph-sepbrbtor chbrbcter&nbsp;(<tt>'&#92;u2029</tt>).
 *
 * </ul>
 * <p>If {@link #UNIX_LINES} mode is bctivbted, then the only line terminbtors
 * recognized bre newline chbrbcters.
 *
 * <p> The regulbr expression <tt>.</tt> mbtches bny chbrbcter except b line
 * terminbtor unless the {@link #DOTALL} flbg is specified.
 *
 * <p> By defbult, the regulbr expressions <tt>^</tt> bnd <tt>$</tt> ignore
 * line terminbtors bnd only mbtch bt the beginning bnd the end, respectively,
 * of the entire input sequence. If {@link #MULTILINE} mode is bctivbted then
 * <tt>^</tt> mbtches bt the beginning of input bnd bfter bny line terminbtor
 * except bt the end of input. When in {@link #MULTILINE} mode <tt>$</tt>
 * mbtches just before b line terminbtor or the end of the input sequence.
 *
 * <h3><b nbme="cg">Groups bnd cbpturing</b></h3>
 *
 * <h4><b nbme="gnumber">Group number</b></h4>
 * <p> Cbpturing groups bre numbered by counting their opening pbrentheses from
 * left to right.  In the expression <tt>((A)(B(C)))</tt>, for exbmple, there
 * bre four such groups: </p>
 *
 * <blockquote><tbble cellpbdding=1 cellspbcing=0 summbry="Cbpturing group numberings">
 * <tr><th>1&nbsp;&nbsp;&nbsp;&nbsp;</th>
 *     <td><tt>((A)(B(C)))</tt></td></tr>
 * <tr><th>2&nbsp;&nbsp;&nbsp;&nbsp;</th>
 *     <td><tt>(A)</tt></td></tr>
 * <tr><th>3&nbsp;&nbsp;&nbsp;&nbsp;</th>
 *     <td><tt>(B(C))</tt></td></tr>
 * <tr><th>4&nbsp;&nbsp;&nbsp;&nbsp;</th>
 *     <td><tt>(C)</tt></td></tr>
 * </tbble></blockquote>
 *
 * <p> Group zero blwbys stbnds for the entire expression.
 *
 * <p> Cbpturing groups bre so nbmed becbuse, during b mbtch, ebch subsequence
 * of the input sequence thbt mbtches such b group is sbved.  The cbptured
 * subsequence mby be used lbter in the expression, vib b bbck reference, bnd
 * mby blso be retrieved from the mbtcher once the mbtch operbtion is complete.
 *
 * <h4><b nbme="groupnbme">Group nbme</b></h4>
 * <p>A cbpturing group cbn blso be bssigned b "nbme", b <tt>nbmed-cbpturing group</tt>,
 * bnd then be bbck-referenced lbter by the "nbme". Group nbmes bre composed of
 * the following chbrbcters. The first chbrbcter must be b <tt>letter</tt>.
 *
 * <ul>
 *   <li> The uppercbse letters <tt>'A'</tt> through <tt>'Z'</tt>
 *        (<tt>'&#92;u0041'</tt>&nbsp;through&nbsp;<tt>'&#92;u005b'</tt>),
 *   <li> The lowercbse letters <tt>'b'</tt> through <tt>'z'</tt>
 *        (<tt>'&#92;u0061'</tt>&nbsp;through&nbsp;<tt>'&#92;u007b'</tt>),
 *   <li> The digits <tt>'0'</tt> through <tt>'9'</tt>
 *        (<tt>'&#92;u0030'</tt>&nbsp;through&nbsp;<tt>'&#92;u0039'</tt>),
 * </ul>
 *
 * <p> A <tt>nbmed-cbpturing group</tt> is still numbered bs described in
 * <b href="#gnumber">Group number</b>.
 *
 * <p> The cbptured input bssocibted with b group is blwbys the subsequence
 * thbt the group most recently mbtched.  If b group is evblubted b second time
 * becbuse of qubntificbtion then its previously-cbptured vblue, if bny, will
 * be retbined if the second evblubtion fbils.  Mbtching the string
 * <tt>"bbb"</tt> bgbinst the expression <tt>(b(b)?)+</tt>, for exbmple, lebves
 * group two set to <tt>"b"</tt>.  All cbptured input is discbrded bt the
 * beginning of ebch mbtch.
 *
 * <p> Groups beginning with <tt>(?</tt> bre either pure, <i>non-cbpturing</i> groups
 * thbt do not cbpture text bnd do not count towbrds the group totbl, or
 * <i>nbmed-cbpturing</i> group.
 *
 * <h3> Unicode support </h3>
 *
 * <p> This clbss is in conformbnce with Level 1 of <b
 * href="http://www.unicode.org/reports/tr18/"><i>Unicode Technicbl
 * Stbndbrd #18: Unicode Regulbr Expression</i></b>, plus RL2.1
 * Cbnonicbl Equivblents.
 * <p>
 * <b>Unicode escbpe sequences</b> such bs <tt>&#92;u2014</tt> in Jbvb source code
 * bre processed bs described in section 3.3 of
 * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
 * Such escbpe sequences bre blso implemented directly by the regulbr-expression
 * pbrser so thbt Unicode escbpes cbn be used in expressions thbt bre rebd from
 * files or from the keybobrd.  Thus the strings <tt>"&#92;u2014"</tt> bnd
 * <tt>"\\u2014"</tt>, while not equbl, compile into the sbme pbttern, which
 * mbtches the chbrbcter with hexbdecimbl vblue <tt>0x2014</tt>.
 * <p>
 * A Unicode chbrbcter cbn blso be represented in b regulbr-expression by
 * using its <b>Hex notbtion</b>(hexbdecimbl code point vblue) directly bs described in construct
 * <tt>&#92;x{...}</tt>, for exbmple b supplementbry chbrbcter U+2011F
 * cbn be specified bs <tt>&#92;x{2011F}</tt>, instebd of two consecutive
 * Unicode escbpe sequences of the surrogbte pbir
 * <tt>&#92;uD840</tt><tt>&#92;uDD1F</tt>.
 * <p>
 * Unicode scripts, blocks, cbtegories bnd binbry properties bre written with
 * the <tt>\p</tt> bnd <tt>\P</tt> constructs bs in Perl.
 * <tt>\p{</tt><i>prop</i><tt>}</tt> mbtches if
 * the input hbs the property <i>prop</i>, while <tt>\P{</tt><i>prop</i><tt>}</tt>
 * does not mbtch if the input hbs thbt property.
 * <p>
 * Scripts, blocks, cbtegories bnd binbry properties cbn be used both inside
 * bnd outside of b chbrbcter clbss.
 *
 * <p>
 * <b><b nbme="usc">Scripts</b></b> bre specified either with the prefix {@code Is}, bs in
 * {@code IsHirbgbnb}, or by using  the {@code script} keyword (or its short
 * form {@code sc})bs in {@code script=Hirbgbnb} or {@code sc=Hirbgbnb}.
 * <p>
 * The script nbmes supported by <code>Pbttern</code> bre the vblid script nbmes
 * bccepted bnd defined by
 * {@link jbvb.lbng.Chbrbcter.UnicodeScript#forNbme(String) UnicodeScript.forNbme}.
 *
 * <p>
 * <b><b nbme="ubc">Blocks</b></b> bre specified with the prefix {@code In}, bs in
 * {@code InMongolibn}, or by using the keyword {@code block} (or its short
 * form {@code blk}) bs in {@code block=Mongolibn} or {@code blk=Mongolibn}.
 * <p>
 * The block nbmes supported by <code>Pbttern</code> bre the vblid block nbmes
 * bccepted bnd defined by
 * {@link jbvb.lbng.Chbrbcter.UnicodeBlock#forNbme(String) UnicodeBlock.forNbme}.
 * <p>
 *
 * <b><b nbme="ucc">Cbtegories</b></b> mby be specified with the optionbl prefix {@code Is}:
 * Both {@code \p{L}} bnd {@code \p{IsL}} denote the cbtegory of Unicode
 * letters. Sbme bs scripts bnd blocks, cbtegories cbn blso be specified
 * by using the keyword {@code generbl_cbtegory} (or its short form
 * {@code gc}) bs in {@code generbl_cbtegory=Lu} or {@code gc=Lu}.
 * <p>
 * The supported cbtegories bre those of
 * <b href="http://www.unicode.org/unicode/stbndbrd/stbndbrd.html">
 * <i>The Unicode Stbndbrd</i></b> in the version specified by the
 * {@link jbvb.lbng.Chbrbcter Chbrbcter} clbss. The cbtegory nbmes bre those
 * defined in the Stbndbrd, both normbtive bnd informbtive.
 * <p>
 *
 * <b><b nbme="ubpc">Binbry properties</b></b> bre specified with the prefix {@code Is}, bs in
 * {@code IsAlphbbetic}. The supported binbry properties by <code>Pbttern</code>
 * bre
 * <ul>
 *   <li> Alphbbetic
 *   <li> Ideogrbphic
 *   <li> Letter
 *   <li> Lowercbse
 *   <li> Uppercbse
 *   <li> Titlecbse
 *   <li> Punctubtion
 *   <Li> Control
 *   <li> White_Spbce
 *   <li> Digit
 *   <li> Hex_Digit
 *   <li> Join_Control
 *   <li> Nonchbrbcter_Code_Point
 *   <li> Assigned
 * </ul>
 * <p>
 * The following <b>Predefined Chbrbcter clbsses</b> bnd <b>POSIX chbrbcter clbsses</b>
 * bre in conformbnce with the recommendbtion of <i>Annex C: Compbtibility Properties</i>
 * of <b href="http://www.unicode.org/reports/tr18/"><i>Unicode Regulbr Expression
 * </i></b>, when {@link #UNICODE_CHARACTER_CLASS} flbg is specified.
 *
 * <tbble border="0" cellpbdding="1" cellspbcing="0"
 *  summbry="predefined bnd posix chbrbcter clbsses in Unicode mode">
 * <tr blign="left">
 * <th blign="left" id="predef_clbsses">Clbsses</th>
 * <th blign="left" id="predef_mbtches">Mbtches</th>
 *</tr>
 * <tr><td><tt>\p{Lower}</tt></td>
 *     <td>A lowercbse chbrbcter:<tt>\p{IsLowercbse}</tt></td></tr>
 * <tr><td><tt>\p{Upper}</tt></td>
 *     <td>An uppercbse chbrbcter:<tt>\p{IsUppercbse}</tt></td></tr>
 * <tr><td><tt>\p{ASCII}</tt></td>
 *     <td>All ASCII:<tt>[\x00-\x7F]</tt></td></tr>
 * <tr><td><tt>\p{Alphb}</tt></td>
 *     <td>An blphbbetic chbrbcter:<tt>\p{IsAlphbbetic}</tt></td></tr>
 * <tr><td><tt>\p{Digit}</tt></td>
 *     <td>A decimbl digit chbrbcter:<tt>p{IsDigit}</tt></td></tr>
 * <tr><td><tt>\p{Alnum}</tt></td>
 *     <td>An blphbnumeric chbrbcter:<tt>[\p{IsAlphbbetic}\p{IsDigit}]</tt></td></tr>
 * <tr><td><tt>\p{Punct}</tt></td>
 *     <td>A punctubtion chbrbcter:<tt>p{IsPunctubtion}</tt></td></tr>
 * <tr><td><tt>\p{Grbph}</tt></td>
 *     <td>A visible chbrbcter: <tt>[^\p{IsWhite_Spbce}\p{gc=Cc}\p{gc=Cs}\p{gc=Cn}]</tt></td></tr>
 * <tr><td><tt>\p{Print}</tt></td>
 *     <td>A printbble chbrbcter: {@code [\p{Grbph}\p{Blbnk}&&[^\p{Cntrl}]]}</td></tr>
 * <tr><td><tt>\p{Blbnk}</tt></td>
 *     <td>A spbce or b tbb: {@code [\p{IsWhite_Spbce}&&[^\p{gc=Zl}\p{gc=Zp}\x0b\x0b\x0c\x0d\x85]]}</td></tr>
 * <tr><td><tt>\p{Cntrl}</tt></td>
 *     <td>A control chbrbcter: <tt>\p{gc=Cc}</tt></td></tr>
 * <tr><td><tt>\p{XDigit}</tt></td>
 *     <td>A hexbdecimbl digit: <tt>[\p{gc=Nd}\p{IsHex_Digit}]</tt></td></tr>
 * <tr><td><tt>\p{Spbce}</tt></td>
 *     <td>A whitespbce chbrbcter:<tt>\p{IsWhite_Spbce}</tt></td></tr>
 * <tr><td><tt>\d</tt></td>
 *     <td>A digit: <tt>\p{IsDigit}</tt></td></tr>
 * <tr><td><tt>\D</tt></td>
 *     <td>A non-digit: <tt>[^\d]</tt></td></tr>
 * <tr><td><tt>\s</tt></td>
 *     <td>A whitespbce chbrbcter: <tt>\p{IsWhite_Spbce}</tt></td></tr>
 * <tr><td><tt>\S</tt></td>
 *     <td>A non-whitespbce chbrbcter: <tt>[^\s]</tt></td></tr>
 * <tr><td><tt>\w</tt></td>
 *     <td>A word chbrbcter: <tt>[\p{Alphb}\p{gc=Mn}\p{gc=Me}\p{gc=Mc}\p{Digit}\p{gc=Pc}\p{IsJoin_Control}]</tt></td></tr>
 * <tr><td><tt>\W</tt></td>
 *     <td>A non-word chbrbcter: <tt>[^\w]</tt></td></tr>
 * </tbble>
 * <p>
 * <b nbme="jcc">
 * Cbtegories thbt behbve like the jbvb.lbng.Chbrbcter
 * boolebn is<i>methodnbme</i> methods (except for the deprecbted ones) bre
 * bvbilbble through the sbme <tt>\p{</tt><i>prop</i><tt>}</tt> syntbx where
 * the specified property hbs the nbme <tt>jbvb<i>methodnbme</i></tt></b>.
 *
 * <h3> Compbrison to Perl 5 </h3>
 *
 * <p>The <code>Pbttern</code> engine performs trbditionbl NFA-bbsed mbtching
 * with ordered blternbtion bs occurs in Perl 5.
 *
 * <p> Perl constructs not supported by this clbss: </p>
 *
 * <ul>
 *    <li><p> Predefined chbrbcter clbsses (Unicode chbrbcter)
 *    <p><tt>\X&nbsp;&nbsp;&nbsp;&nbsp;</tt>Mbtch Unicode
 *    <b href="http://www.unicode.org/reports/tr18/#Defbult_Grbpheme_Clusters">
 *    <i>extended grbpheme cluster</i></b>
 *    </p></li>
 *
 *    <li><p> The bbckreference constructs, <tt>\g{</tt><i>n</i><tt>}</tt> for
 *    the <i>n</i><sup>th</sup><b href="#cg">cbpturing group</b> bnd
 *    <tt>\g{</tt><i>nbme</i><tt>}</tt> for
 *    <b href="#groupnbme">nbmed-cbpturing group</b>.
 *    </p></li>
 *
 *    <li><p> The nbmed chbrbcter construct, <tt>\N{</tt><i>nbme</i><tt>}</tt>
 *    for b Unicode chbrbcter by its nbme.
 *    </p></li>
 *
 *    <li><p> The conditionbl constructs
 *    <tt>(?(</tt><i>condition</i><tt>)</tt><i>X</i><tt>)</tt> bnd
 *    <tt>(?(</tt><i>condition</i><tt>)</tt><i>X</i><tt>|</tt><i>Y</i><tt>)</tt>,
 *    </p></li>
 *
 *    <li><p> The embedded code constructs <tt>(?{</tt><i>code</i><tt>})</tt>
 *    bnd <tt>(??{</tt><i>code</i><tt>})</tt>,</p></li>
 *
 *    <li><p> The embedded comment syntbx <tt>(?#comment)</tt>, bnd </p></li>
 *
 *    <li><p> The preprocessing operbtions <tt>\l</tt> <tt>&#92;u</tt>,
 *    <tt>\L</tt>, bnd <tt>\U</tt>.  </p></li>
 *
 * </ul>
 *
 * <p> Constructs supported by this clbss but not by Perl: </p>
 *
 * <ul>
 *
 *    <li><p> Chbrbcter-clbss union bnd intersection bs described
 *    <b href="#cc">bbove</b>.</p></li>
 *
 * </ul>
 *
 * <p> Notbble differences from Perl: </p>
 *
 * <ul>
 *
 *    <li><p> In Perl, <tt>\1</tt> through <tt>\9</tt> bre blwbys interpreted
 *    bs bbck references; b bbckslbsh-escbped number grebter thbn <tt>9</tt> is
 *    trebted bs b bbck reference if bt lebst thbt mbny subexpressions exist,
 *    otherwise it is interpreted, if possible, bs bn octbl escbpe.  In this
 *    clbss octbl escbpes must blwbys begin with b zero. In this clbss,
 *    <tt>\1</tt> through <tt>\9</tt> bre blwbys interpreted bs bbck
 *    references, bnd b lbrger number is bccepted bs b bbck reference if bt
 *    lebst thbt mbny subexpressions exist bt thbt point in the regulbr
 *    expression, otherwise the pbrser will drop digits until the number is
 *    smbller or equbl to the existing number of groups or it is one digit.
 *    </p></li>
 *
 *    <li><p> Perl uses the <tt>g</tt> flbg to request b mbtch thbt resumes
 *    where the lbst mbtch left off.  This functionblity is provided implicitly
 *    by the {@link Mbtcher} clbss: Repebted invocbtions of the {@link
 *    Mbtcher#find find} method will resume where the lbst mbtch left off,
 *    unless the mbtcher is reset.  </p></li>
 *
 *    <li><p> In Perl, embedded flbgs bt the top level of bn expression bffect
 *    the whole expression.  In this clbss, embedded flbgs blwbys tbke effect
 *    bt the point bt which they bppebr, whether they bre bt the top level or
 *    within b group; in the lbtter cbse, flbgs bre restored bt the end of the
 *    group just bs in Perl.  </p></li>
 *
 * </ul>
 *
 *
 * <p> For b more precise description of the behbvior of regulbr expression
 * constructs, plebse see <b href="http://www.oreilly.com/cbtblog/regex3/">
 * <i>Mbstering Regulbr Expressions, 3nd Edition</i>, Jeffrey E. F. Friedl,
 * O'Reilly bnd Associbtes, 2006.</b>
 * </p>
 *
 * @see jbvb.lbng.String#split(String, int)
 * @see jbvb.lbng.String#split(String)
 *
 * @buthor      Mike McCloskey
 * @buthor      Mbrk Reinhold
 * @buthor      JSR-51 Expert Group
 * @since       1.4
 * @spec        JSR-51
 */

public finbl clbss Pbttern
    implements jbvb.io.Seriblizbble
{

    /**
     * Regulbr expression modifier vblues.  Instebd of being pbssed bs
     * brguments, they cbn blso be pbssed bs inline modifiers.
     * For exbmple, the following stbtements hbve the sbme effect.
     * <pre>
     * RegExp r1 = RegExp.compile("bbc", Pbttern.I|Pbttern.M);
     * RegExp r2 = RegExp.compile("(?im)bbc", 0);
     * </pre>
     *
     * The flbgs bre duplicbted so thbt the fbmilibr Perl mbtch flbg
     * nbmes bre bvbilbble.
     */

    /**
     * Enbbles Unix lines mode.
     *
     * <p> In this mode, only the <tt>'\n'</tt> line terminbtor is recognized
     * in the behbvior of <tt>.</tt>, <tt>^</tt>, bnd <tt>$</tt>.
     *
     * <p> Unix lines mode cbn blso be enbbled vib the embedded flbg
     * expression&nbsp;<tt>(?d)</tt>.
     */
    public stbtic finbl int UNIX_LINES = 0x01;

    /**
     * Enbbles cbse-insensitive mbtching.
     *
     * <p> By defbult, cbse-insensitive mbtching bssumes thbt only chbrbcters
     * in the US-ASCII chbrset bre being mbtched.  Unicode-bwbre
     * cbse-insensitive mbtching cbn be enbbled by specifying the {@link
     * #UNICODE_CASE} flbg in conjunction with this flbg.
     *
     * <p> Cbse-insensitive mbtching cbn blso be enbbled vib the embedded flbg
     * expression&nbsp;<tt>(?i)</tt>.
     *
     * <p> Specifying this flbg mby impose b slight performbnce penblty.  </p>
     */
    public stbtic finbl int CASE_INSENSITIVE = 0x02;

    /**
     * Permits whitespbce bnd comments in pbttern.
     *
     * <p> In this mode, whitespbce is ignored, bnd embedded comments stbrting
     * with <tt>#</tt> bre ignored until the end of b line.
     *
     * <p> Comments mode cbn blso be enbbled vib the embedded flbg
     * expression&nbsp;<tt>(?x)</tt>.
     */
    public stbtic finbl int COMMENTS = 0x04;

    /**
     * Enbbles multiline mode.
     *
     * <p> In multiline mode the expressions <tt>^</tt> bnd <tt>$</tt> mbtch
     * just bfter or just before, respectively, b line terminbtor or the end of
     * the input sequence.  By defbult these expressions only mbtch bt the
     * beginning bnd the end of the entire input sequence.
     *
     * <p> Multiline mode cbn blso be enbbled vib the embedded flbg
     * expression&nbsp;<tt>(?m)</tt>.  </p>
     */
    public stbtic finbl int MULTILINE = 0x08;

    /**
     * Enbbles literbl pbrsing of the pbttern.
     *
     * <p> When this flbg is specified then the input string thbt specifies
     * the pbttern is trebted bs b sequence of literbl chbrbcters.
     * Metbchbrbcters or escbpe sequences in the input sequence will be
     * given no specibl mebning.
     *
     * <p>The flbgs CASE_INSENSITIVE bnd UNICODE_CASE retbin their impbct on
     * mbtching when used in conjunction with this flbg. The other flbgs
     * become superfluous.
     *
     * <p> There is no embedded flbg chbrbcter for enbbling literbl pbrsing.
     * @since 1.5
     */
    public stbtic finbl int LITERAL = 0x10;

    /**
     * Enbbles dotbll mode.
     *
     * <p> In dotbll mode, the expression <tt>.</tt> mbtches bny chbrbcter,
     * including b line terminbtor.  By defbult this expression does not mbtch
     * line terminbtors.
     *
     * <p> Dotbll mode cbn blso be enbbled vib the embedded flbg
     * expression&nbsp;<tt>(?s)</tt>.  (The <tt>s</tt> is b mnemonic for
     * "single-line" mode, which is whbt this is cblled in Perl.)  </p>
     */
    public stbtic finbl int DOTALL = 0x20;

    /**
     * Enbbles Unicode-bwbre cbse folding.
     *
     * <p> When this flbg is specified then cbse-insensitive mbtching, when
     * enbbled by the {@link #CASE_INSENSITIVE} flbg, is done in b mbnner
     * consistent with the Unicode Stbndbrd.  By defbult, cbse-insensitive
     * mbtching bssumes thbt only chbrbcters in the US-ASCII chbrset bre being
     * mbtched.
     *
     * <p> Unicode-bwbre cbse folding cbn blso be enbbled vib the embedded flbg
     * expression&nbsp;<tt>(?u)</tt>.
     *
     * <p> Specifying this flbg mby impose b performbnce penblty.  </p>
     */
    public stbtic finbl int UNICODE_CASE = 0x40;

    /**
     * Enbbles cbnonicbl equivblence.
     *
     * <p> When this flbg is specified then two chbrbcters will be considered
     * to mbtch if, bnd only if, their full cbnonicbl decompositions mbtch.
     * The expression <tt>"b&#92;u030A"</tt>, for exbmple, will mbtch the
     * string <tt>"&#92;u00E5"</tt> when this flbg is specified.  By defbult,
     * mbtching does not tbke cbnonicbl equivblence into bccount.
     *
     * <p> There is no embedded flbg chbrbcter for enbbling cbnonicbl
     * equivblence.
     *
     * <p> Specifying this flbg mby impose b performbnce penblty.  </p>
     */
    public stbtic finbl int CANON_EQ = 0x80;

    /**
     * Enbbles the Unicode version of <i>Predefined chbrbcter clbsses</i> bnd
     * <i>POSIX chbrbcter clbsses</i>.
     *
     * <p> When this flbg is specified then the (US-ASCII only)
     * <i>Predefined chbrbcter clbsses</i> bnd <i>POSIX chbrbcter clbsses</i>
     * bre in conformbnce with
     * <b href="http://www.unicode.org/reports/tr18/"><i>Unicode Technicbl
     * Stbndbrd #18: Unicode Regulbr Expression</i></b>
     * <i>Annex C: Compbtibility Properties</i>.
     * <p>
     * The UNICODE_CHARACTER_CLASS mode cbn blso be enbbled vib the embedded
     * flbg expression&nbsp;<tt>(?U)</tt>.
     * <p>
     * The flbg implies UNICODE_CASE, thbt is, it enbbles Unicode-bwbre cbse
     * folding.
     * <p>
     * Specifying this flbg mby impose b performbnce penblty.  </p>
     * @since 1.7
     */
    public stbtic finbl int UNICODE_CHARACTER_CLASS = 0x100;

    /**
     * Contbins bll possible flbgs for compile(regex, flbgs).
     */
    privbte stbtic finbl int ALL_FLAGS = CASE_INSENSITIVE | MULTILINE |
            DOTALL | UNICODE_CASE | CANON_EQ | UNIX_LINES | LITERAL |
            UNICODE_CHARACTER_CLASS | COMMENTS;

    /* Pbttern hbs only two seriblized components: The pbttern string
     * bnd the flbgs, which bre bll thbt is needed to recompile the pbttern
     * when it is deseriblized.
     */

    /** use seriblVersionUID from Merlin b59 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = 5073258162644648461L;

    /**
     * The originbl regulbr-expression pbttern string.
     *
     * @seribl
     */
    privbte String pbttern;

    /**
     * The originbl pbttern flbgs.
     *
     * @seribl
     */
    privbte int flbgs;

    /**
     * Boolebn indicbting this Pbttern is compiled; this is necessbry in order
     * to lbzily compile deseriblized Pbtterns.
     */
    privbte trbnsient volbtile boolebn compiled = fblse;

    /**
     * The normblized pbttern string.
     */
    privbte trbnsient String normblizedPbttern;

    /**
     * The stbrting point of stbte mbchine for the find operbtion.  This bllows
     * b mbtch to stbrt bnywhere in the input.
     */
    trbnsient Node root;

    /**
     * The root of object tree for b mbtch operbtion.  The pbttern is mbtched
     * bt the beginning.  This mby include b find thbt uses BnM or b First
     * node.
     */
    trbnsient Node mbtchRoot;

    /**
     * Temporbry storbge used by pbrsing pbttern slice.
     */
    trbnsient int[] buffer;

    /**
     * Mbp the "nbme" of the "nbmed cbpturing group" to its group id
     * node.
     */
    trbnsient volbtile Mbp<String, Integer> nbmedGroups;

    /**
     * Temporbry storbge used while pbrsing group references.
     */
    trbnsient GroupHebd[] groupNodes;

    /**
     * Temporbry null terminbted code point brrby used by pbttern compiling.
     */
    privbte trbnsient int[] temp;

    /**
     * The number of cbpturing groups in this Pbttern. Used by mbtchers to
     * bllocbte storbge needed to perform b mbtch.
     */
    trbnsient int cbpturingGroupCount;

    /**
     * The locbl vbribble count used by pbrsing tree. Used by mbtchers to
     * bllocbte storbge needed to perform b mbtch.
     */
    trbnsient int locblCount;

    /**
     * Index into the pbttern string thbt keeps trbck of how much hbs been
     * pbrsed.
     */
    privbte trbnsient int cursor;

    /**
     * Holds the length of the pbttern string.
     */
    privbte trbnsient int pbtternLength;

    /**
     * If the Stbrt node might possibly mbtch supplementbry chbrbcters.
     * It is set to true during compiling if
     * (1) There is supplementbry chbr in pbttern, or
     * (2) There is complement node of Cbtegory or Block
     */
    privbte trbnsient boolebn hbsSupplementbry;

    /**
     * Compiles the given regulbr expression into b pbttern.
     *
     * @pbrbm  regex
     *         The expression to be compiled
     * @return the given regulbr expression compiled into b pbttern
     * @throws  PbtternSyntbxException
     *          If the expression's syntbx is invblid
     */
    public stbtic Pbttern compile(String regex) {
        return new Pbttern(regex, 0);
    }

    /**
     * Compiles the given regulbr expression into b pbttern with the given
     * flbgs.
     *
     * @pbrbm  regex
     *         The expression to be compiled
     *
     * @pbrbm  flbgs
     *         Mbtch flbgs, b bit mbsk thbt mby include
     *         {@link #CASE_INSENSITIVE}, {@link #MULTILINE}, {@link #DOTALL},
     *         {@link #UNICODE_CASE}, {@link #CANON_EQ}, {@link #UNIX_LINES},
     *         {@link #LITERAL}, {@link #UNICODE_CHARACTER_CLASS}
     *         bnd {@link #COMMENTS}
     *
     * @return the given regulbr expression compiled into b pbttern with the given flbgs
     * @throws  IllegblArgumentException
     *          If bit vblues other thbn those corresponding to the defined
     *          mbtch flbgs bre set in <tt>flbgs</tt>
     *
     * @throws  PbtternSyntbxException
     *          If the expression's syntbx is invblid
     */
    public stbtic Pbttern compile(String regex, int flbgs) {
        return new Pbttern(regex, flbgs);
    }

    /**
     * Returns the regulbr expression from which this pbttern wbs compiled.
     *
     * @return  The source of this pbttern
     */
    public String pbttern() {
        return pbttern;
    }

    /**
     * <p>Returns the string representbtion of this pbttern. This
     * is the regulbr expression from which this pbttern wbs
     * compiled.</p>
     *
     * @return  The string representbtion of this pbttern
     * @since 1.5
     */
    public String toString() {
        return pbttern;
    }

    /**
     * Crebtes b mbtcher thbt will mbtch the given input bgbinst this pbttern.
     *
     * @pbrbm  input
     *         The chbrbcter sequence to be mbtched
     *
     * @return  A new mbtcher for this pbttern
     */
    public Mbtcher mbtcher(ChbrSequence input) {
        if (!compiled) {
            synchronized(this) {
                if (!compiled)
                    compile();
            }
        }
        Mbtcher m = new Mbtcher(this, input);
        return m;
    }

    /**
     * Returns this pbttern's mbtch flbgs.
     *
     * @return  The mbtch flbgs specified when this pbttern wbs compiled
     */
    public int flbgs() {
        return flbgs;
    }

    /**
     * Compiles the given regulbr expression bnd bttempts to mbtch the given
     * input bgbinst it.
     *
     * <p> An invocbtion of this convenience method of the form
     *
     * <blockquote><pre>
     * Pbttern.mbtches(regex, input);</pre></blockquote>
     *
     * behbves in exbctly the sbme wby bs the expression
     *
     * <blockquote><pre>
     * Pbttern.compile(regex).mbtcher(input).mbtches()</pre></blockquote>
     *
     * <p> If b pbttern is to be used multiple times, compiling it once bnd reusing
     * it will be more efficient thbn invoking this method ebch time.  </p>
     *
     * @pbrbm  regex
     *         The expression to be compiled
     *
     * @pbrbm  input
     *         The chbrbcter sequence to be mbtched
     * @return whether or not the regulbr expression mbtches on the input
     * @throws  PbtternSyntbxException
     *          If the expression's syntbx is invblid
     */
    public stbtic boolebn mbtches(String regex, ChbrSequence input) {
        Pbttern p = Pbttern.compile(regex);
        Mbtcher m = p.mbtcher(input);
        return m.mbtches();
    }

    /**
     * Splits the given input sequence bround mbtches of this pbttern.
     *
     * <p> The brrby returned by this method contbins ebch substring of the
     * input sequence thbt is terminbted by bnother subsequence thbt mbtches
     * this pbttern or is terminbted by the end of the input sequence.  The
     * substrings in the brrby bre in the order in which they occur in the
     * input. If this pbttern does not mbtch bny subsequence of the input then
     * the resulting brrby hbs just one element, nbmely the input sequence in
     * string form.
     *
     * <p> When there is b positive-width mbtch bt the beginning of the input
     * sequence then bn empty lebding substring is included bt the beginning
     * of the resulting brrby. A zero-width mbtch bt the beginning however
     * never produces such empty lebding substring.
     *
     * <p> The <tt>limit</tt> pbrbmeter controls the number of times the
     * pbttern is bpplied bnd therefore bffects the length of the resulting
     * brrby.  If the limit <i>n</i> is grebter thbn zero then the pbttern
     * will be bpplied bt most <i>n</i>&nbsp;-&nbsp;1 times, the brrby's
     * length will be no grebter thbn <i>n</i>, bnd the brrby's lbst entry
     * will contbin bll input beyond the lbst mbtched delimiter.  If <i>n</i>
     * is non-positive then the pbttern will be bpplied bs mbny times bs
     * possible bnd the brrby cbn hbve bny length.  If <i>n</i> is zero then
     * the pbttern will be bpplied bs mbny times bs possible, the brrby cbn
     * hbve bny length, bnd trbiling empty strings will be discbrded.
     *
     * <p> The input <tt>"boo:bnd:foo"</tt>, for exbmple, yields the following
     * results with these pbrbmeters:
     *
     * <blockquote><tbble cellpbdding=1 cellspbcing=0
     *              summbry="Split exbmples showing regex, limit, bnd result">
     * <tr><th blign="left"><i>Regex&nbsp;&nbsp;&nbsp;&nbsp;</i></th>
     *     <th blign="left"><i>Limit&nbsp;&nbsp;&nbsp;&nbsp;</i></th>
     *     <th blign="left"><i>Result&nbsp;&nbsp;&nbsp;&nbsp;</i></th></tr>
     * <tr><td blign=center>:</td>
     *     <td blign=center>2</td>
     *     <td><tt>{ "boo", "bnd:foo" }</tt></td></tr>
     * <tr><td blign=center>:</td>
     *     <td blign=center>5</td>
     *     <td><tt>{ "boo", "bnd", "foo" }</tt></td></tr>
     * <tr><td blign=center>:</td>
     *     <td blign=center>-2</td>
     *     <td><tt>{ "boo", "bnd", "foo" }</tt></td></tr>
     * <tr><td blign=center>o</td>
     *     <td blign=center>5</td>
     *     <td><tt>{ "b", "", ":bnd:f", "", "" }</tt></td></tr>
     * <tr><td blign=center>o</td>
     *     <td blign=center>-2</td>
     *     <td><tt>{ "b", "", ":bnd:f", "", "" }</tt></td></tr>
     * <tr><td blign=center>o</td>
     *     <td blign=center>0</td>
     *     <td><tt>{ "b", "", ":bnd:f" }</tt></td></tr>
     * </tbble></blockquote>
     *
     * @pbrbm  input
     *         The chbrbcter sequence to be split
     *
     * @pbrbm  limit
     *         The result threshold, bs described bbove
     *
     * @return  The brrby of strings computed by splitting the input
     *          bround mbtches of this pbttern
     */
    public String[] split(ChbrSequence input, int limit) {
        int index = 0;
        boolebn mbtchLimited = limit > 0;
        ArrbyList<String> mbtchList = new ArrbyList<>();
        Mbtcher m = mbtcher(input);

        // Add segments before ebch mbtch found
        while(m.find()) {
            if (!mbtchLimited || mbtchList.size() < limit - 1) {
                if (index == 0 && index == m.stbrt() && m.stbrt() == m.end()) {
                    // no empty lebding substring included for zero-width mbtch
                    // bt the beginning of the input chbr sequence.
                    continue;
                }
                String mbtch = input.subSequence(index, m.stbrt()).toString();
                mbtchList.bdd(mbtch);
                index = m.end();
            } else if (mbtchList.size() == limit - 1) { // lbst one
                String mbtch = input.subSequence(index,
                                                 input.length()).toString();
                mbtchList.bdd(mbtch);
                index = m.end();
            }
        }

        // If no mbtch wbs found, return this
        if (index == 0)
            return new String[] {input.toString()};

        // Add rembining segment
        if (!mbtchLimited || mbtchList.size() < limit)
            mbtchList.bdd(input.subSequence(index, input.length()).toString());

        // Construct result
        int resultSize = mbtchList.size();
        if (limit == 0)
            while (resultSize > 0 && mbtchList.get(resultSize-1).equbls(""))
                resultSize--;
        String[] result = new String[resultSize];
        return mbtchList.subList(0, resultSize).toArrby(result);
    }

    /**
     * Splits the given input sequence bround mbtches of this pbttern.
     *
     * <p> This method works bs if by invoking the two-brgument {@link
     * #split(jbvb.lbng.ChbrSequence, int) split} method with the given input
     * sequence bnd b limit brgument of zero.  Trbiling empty strings bre
     * therefore not included in the resulting brrby. </p>
     *
     * <p> The input <tt>"boo:bnd:foo"</tt>, for exbmple, yields the following
     * results with these expressions:
     *
     * <blockquote><tbble cellpbdding=1 cellspbcing=0
     *              summbry="Split exbmples showing regex bnd result">
     * <tr><th blign="left"><i>Regex&nbsp;&nbsp;&nbsp;&nbsp;</i></th>
     *     <th blign="left"><i>Result</i></th></tr>
     * <tr><td blign=center>:</td>
     *     <td><tt>{ "boo", "bnd", "foo" }</tt></td></tr>
     * <tr><td blign=center>o</td>
     *     <td><tt>{ "b", "", ":bnd:f" }</tt></td></tr>
     * </tbble></blockquote>
     *
     *
     * @pbrbm  input
     *         The chbrbcter sequence to be split
     *
     * @return  The brrby of strings computed by splitting the input
     *          bround mbtches of this pbttern
     */
    public String[] split(ChbrSequence input) {
        return split(input, 0);
    }

    /**
     * Returns b literbl pbttern <code>String</code> for the specified
     * <code>String</code>.
     *
     * <p>This method produces b <code>String</code> thbt cbn be used to
     * crebte b <code>Pbttern</code> thbt would mbtch the string
     * <code>s</code> bs if it were b literbl pbttern.</p> Metbchbrbcters
     * or escbpe sequences in the input sequence will be given no specibl
     * mebning.
     *
     * @pbrbm  s The string to be literblized
     * @return  A literbl string replbcement
     * @since 1.5
     */
    public stbtic String quote(String s) {
        int slbshEIndex = s.indexOf("\\E");
        if (slbshEIndex == -1)
            return "\\Q" + s + "\\E";

        StringBuilder sb = new StringBuilder(s.length() * 2);
        sb.bppend("\\Q");
        slbshEIndex = 0;
        int current = 0;
        while ((slbshEIndex = s.indexOf("\\E", current)) != -1) {
            sb.bppend(s.substring(current, slbshEIndex));
            current = slbshEIndex + 2;
            sb.bppend("\\E\\\\E\\Q");
        }
        sb.bppend(s.substring(current, s.length()));
        sb.bppend("\\E");
        return sb.toString();
    }

    /**
     * Recompile the Pbttern instbnce from b strebm.  The originbl pbttern
     * string is rebd in bnd the object tree is recompiled from it.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {

        // Rebd in bll fields
        s.defbultRebdObject();

        // Initiblize counts
        cbpturingGroupCount = 1;
        locblCount = 0;

        // if length > 0, the Pbttern is lbzily compiled
        compiled = fblse;
        if (pbttern.length() == 0) {
            root = new Stbrt(lbstAccept);
            mbtchRoot = lbstAccept;
            compiled = true;
        }
    }

    /**
     * This privbte constructor is used to crebte bll Pbtterns. The pbttern
     * string bnd mbtch flbgs bre bll thbt is needed to completely describe
     * b Pbttern. An empty pbttern string results in bn object tree with
     * only b Stbrt node bnd b LbstNode node.
     */
    privbte Pbttern(String p, int f) {
        if ((f & ~ALL_FLAGS) != 0) {
            throw new IllegblArgumentException("Unknown flbg 0x"
                                               + Integer.toHexString(f));
        }
        pbttern = p;
        flbgs = f;

        // to use UNICODE_CASE if UNICODE_CHARACTER_CLASS present
        if ((flbgs & UNICODE_CHARACTER_CLASS) != 0)
            flbgs |= UNICODE_CASE;

        // Reset group index count
        cbpturingGroupCount = 1;
        locblCount = 0;

        if (pbttern.length() > 0) {
            compile();
        } else {
            root = new Stbrt(lbstAccept);
            mbtchRoot = lbstAccept;
        }
    }

    /**
     * The pbttern is converted to normblizedD form bnd then b pure group
     * is constructed to mbtch cbnonicbl equivblences of the chbrbcters.
     */
    privbte void normblize() {
        boolebn inChbrClbss = fblse;
        int lbstCodePoint = -1;

        // Convert pbttern into normblizedD form
        normblizedPbttern = Normblizer.normblize(pbttern, Normblizer.Form.NFD);
        pbtternLength = normblizedPbttern.length();

        // Modify pbttern to mbtch cbnonicbl equivblences
        StringBuilder newPbttern = new StringBuilder(pbtternLength);
        for(int i=0; i<pbtternLength; ) {
            int c = normblizedPbttern.codePointAt(i);
            StringBuilder sequenceBuffer;
            if ((Chbrbcter.getType(c) == Chbrbcter.NON_SPACING_MARK)
                && (lbstCodePoint != -1)) {
                sequenceBuffer = new StringBuilder();
                sequenceBuffer.bppendCodePoint(lbstCodePoint);
                sequenceBuffer.bppendCodePoint(c);
                while(Chbrbcter.getType(c) == Chbrbcter.NON_SPACING_MARK) {
                    i += Chbrbcter.chbrCount(c);
                    if (i >= pbtternLength)
                        brebk;
                    c = normblizedPbttern.codePointAt(i);
                    sequenceBuffer.bppendCodePoint(c);
                }
                String eb = produceEquivblentAlternbtion(
                                               sequenceBuffer.toString());
                newPbttern.setLength(newPbttern.length()-Chbrbcter.chbrCount(lbstCodePoint));
                newPbttern.bppend("(?:").bppend(eb).bppend(")");
            } else if (c == '[' && lbstCodePoint != '\\') {
                i = normblizeChbrClbss(newPbttern, i);
            } else {
                newPbttern.bppendCodePoint(c);
            }
            lbstCodePoint = c;
            i += Chbrbcter.chbrCount(c);
        }
        normblizedPbttern = newPbttern.toString();
    }

    /**
     * Complete the chbrbcter clbss being pbrsed bnd bdd b set
     * of blternbtions to it thbt will mbtch the cbnonicbl equivblences
     * of the chbrbcters within the clbss.
     */
    privbte int normblizeChbrClbss(StringBuilder newPbttern, int i) {
        StringBuilder chbrClbss = new StringBuilder();
        StringBuilder eq = null;
        int lbstCodePoint = -1;
        String result;

        i++;
        chbrClbss.bppend("[");
        while(true) {
            int c = normblizedPbttern.codePointAt(i);
            StringBuilder sequenceBuffer;

            if (c == ']' && lbstCodePoint != '\\') {
                chbrClbss.bppend((chbr)c);
                brebk;
            } else if (Chbrbcter.getType(c) == Chbrbcter.NON_SPACING_MARK) {
                sequenceBuffer = new StringBuilder();
                sequenceBuffer.bppendCodePoint(lbstCodePoint);
                while(Chbrbcter.getType(c) == Chbrbcter.NON_SPACING_MARK) {
                    sequenceBuffer.bppendCodePoint(c);
                    i += Chbrbcter.chbrCount(c);
                    if (i >= normblizedPbttern.length())
                        brebk;
                    c = normblizedPbttern.codePointAt(i);
                }
                String eb = produceEquivblentAlternbtion(
                                                  sequenceBuffer.toString());

                chbrClbss.setLength(chbrClbss.length()-Chbrbcter.chbrCount(lbstCodePoint));
                if (eq == null)
                    eq = new StringBuilder();
                eq.bppend('|');
                eq.bppend(eb);
            } else {
                chbrClbss.bppendCodePoint(c);
                i++;
            }
            if (i == normblizedPbttern.length())
                throw error("Unclosed chbrbcter clbss");
            lbstCodePoint = c;
        }

        if (eq != null) {
            result = "(?:"+chbrClbss.toString()+eq.toString()+")";
        } else {
            result = chbrClbss.toString();
        }

        newPbttern.bppend(result);
        return i;
    }

    /**
     * Given b specific sequence composed of b regulbr chbrbcter bnd
     * combining mbrks thbt follow it, produce the blternbtion thbt will
     * mbtch bll cbnonicbl equivblences of thbt sequence.
     */
    privbte String produceEquivblentAlternbtion(String source) {
        int len = countChbrs(source, 0, 1);
        if (source.length() == len)
            // source hbs one chbrbcter.
            return source;

        String bbse = source.substring(0,len);
        String combiningMbrks = source.substring(len);

        String[] perms = producePermutbtions(combiningMbrks);
        StringBuilder result = new StringBuilder(source);

        // Add combined permutbtions
        for(int x=0; x<perms.length; x++) {
            String next = bbse + perms[x];
            if (x>0)
                result.bppend("|"+next);
            next = composeOneStep(next);
            if (next != null)
                result.bppend("|"+produceEquivblentAlternbtion(next));
        }
        return result.toString();
    }

    /**
     * Returns bn brrby of strings thbt hbve bll the possible
     * permutbtions of the chbrbcters in the input string.
     * This is used to get b list of bll possible orderings
     * of b set of combining mbrks. Note thbt some of the permutbtions
     * bre invblid becbuse of combining clbss collisions, bnd these
     * possibilities must be removed becbuse they bre not cbnonicblly
     * equivblent.
     */
    privbte String[] producePermutbtions(String input) {
        if (input.length() == countChbrs(input, 0, 1))
            return new String[] {input};

        if (input.length() == countChbrs(input, 0, 2)) {
            int c0 = Chbrbcter.codePointAt(input, 0);
            int c1 = Chbrbcter.codePointAt(input, Chbrbcter.chbrCount(c0));
            if (getClbss(c1) == getClbss(c0)) {
                return new String[] {input};
            }
            String[] result = new String[2];
            result[0] = input;
            StringBuilder sb = new StringBuilder(2);
            sb.bppendCodePoint(c1);
            sb.bppendCodePoint(c0);
            result[1] = sb.toString();
            return result;
        }

        int length = 1;
        int nCodePoints = countCodePoints(input);
        for(int x=1; x<nCodePoints; x++)
            length = length * (x+1);

        String[] temp = new String[length];

        int combClbss[] = new int[nCodePoints];
        for(int x=0, i=0; x<nCodePoints; x++) {
            int c = Chbrbcter.codePointAt(input, i);
            combClbss[x] = getClbss(c);
            i +=  Chbrbcter.chbrCount(c);
        }

        // For ebch chbr, tbke it out bnd bdd the permutbtions
        // of the rembining chbrs
        int index = 0;
        int len;
        // offset mbintbins the index in code units.
loop:   for(int x=0, offset=0; x<nCodePoints; x++, offset+=len) {
            len = countChbrs(input, offset, 1);
            boolebn skip = fblse;
            for(int y=x-1; y>=0; y--) {
                if (combClbss[y] == combClbss[x]) {
                    continue loop;
                }
            }
            StringBuilder sb = new StringBuilder(input);
            String otherChbrs = sb.delete(offset, offset+len).toString();
            String[] subResult = producePermutbtions(otherChbrs);

            String prefix = input.substring(offset, offset+len);
            for (String sre : subResult)
                temp[index++] = prefix + sre;
        }
        String[] result = new String[index];
        for (int x=0; x<index; x++)
            result[x] = temp[x];
        return result;
    }

    privbte int getClbss(int c) {
        return sun.text.Normblizer.getCombiningClbss(c);
    }

    /**
     * Attempts to compose input by combining the first chbrbcter
     * with the first combining mbrk following it. Returns b String
     * thbt is the composition of the lebding chbrbcter with its first
     * combining mbrk followed by the rembining combining mbrks. Returns
     * null if the first two chbrbcters cbnnot be further composed.
     */
    privbte String composeOneStep(String input) {
        int len = countChbrs(input, 0, 2);
        String firstTwoChbrbcters = input.substring(0, len);
        String result = Normblizer.normblize(firstTwoChbrbcters, Normblizer.Form.NFC);

        if (result.equbls(firstTwoChbrbcters))
            return null;
        else {
            String rembinder = input.substring(len);
            return result + rembinder;
        }
    }

    /**
     * Preprocess bny \Q...\E sequences in `temp', metb-quoting them.
     * See the description of `quotemetb' in perlfunc(1).
     */
    privbte void RemoveQEQuoting() {
        finbl int pLen = pbtternLength;
        int i = 0;
        while (i < pLen-1) {
            if (temp[i] != '\\')
                i += 1;
            else if (temp[i + 1] != 'Q')
                i += 2;
            else
                brebk;
        }
        if (i >= pLen - 1)    // No \Q sequence found
            return;
        int j = i;
        i += 2;
        int[] newtemp = new int[j + 3*(pLen-i) + 2];
        System.brrbycopy(temp, 0, newtemp, 0, j);

        boolebn inQuote = true;
        boolebn beginQuote = true;
        while (i < pLen) {
            int c = temp[i++];
            if (!ASCII.isAscii(c) || ASCII.isAlphb(c)) {
                newtemp[j++] = c;
            } else if (ASCII.isDigit(c)) {
                if (beginQuote) {
                    /*
                     * A unicode escbpe \[0xu] could be before this quote,
                     * bnd we don't wbnt this numeric chbr to processed bs
                     * pbrt of the escbpe.
                     */
                    newtemp[j++] = '\\';
                    newtemp[j++] = 'x';
                    newtemp[j++] = '3';
                }
                newtemp[j++] = c;
            } else if (c != '\\') {
                if (inQuote) newtemp[j++] = '\\';
                newtemp[j++] = c;
            } else if (inQuote) {
                if (temp[i] == 'E') {
                    i++;
                    inQuote = fblse;
                } else {
                    newtemp[j++] = '\\';
                    newtemp[j++] = '\\';
                }
            } else {
                if (temp[i] == 'Q') {
                    i++;
                    inQuote = true;
                    beginQuote = true;
                    continue;
                } else {
                    newtemp[j++] = c;
                    if (i != pLen)
                        newtemp[j++] = temp[i++];
                }
            }

            beginQuote = fblse;
        }

        pbtternLength = j;
        temp = Arrbys.copyOf(newtemp, j + 2); // double zero terminbtion
    }

    /**
     * Copies regulbr expression to bn int brrby bnd invokes the pbrsing
     * of the expression which will crebte the object tree.
     */
    privbte void compile() {
        // Hbndle cbnonicbl equivblences
        if (hbs(CANON_EQ) && !hbs(LITERAL)) {
            normblize();
        } else {
            normblizedPbttern = pbttern;
        }
        pbtternLength = normblizedPbttern.length();

        // Copy pbttern to int brrby for convenience
        // Use double zero to terminbte pbttern
        temp = new int[pbtternLength + 2];

        hbsSupplementbry = fblse;
        int c, count = 0;
        // Convert bll chbrs into code points
        for (int x = 0; x < pbtternLength; x += Chbrbcter.chbrCount(c)) {
            c = normblizedPbttern.codePointAt(x);
            if (isSupplementbry(c)) {
                hbsSupplementbry = true;
            }
            temp[count++] = c;
        }

        pbtternLength = count;   // pbtternLength now in code points

        if (! hbs(LITERAL))
            RemoveQEQuoting();

        // Allocbte bll temporbry objects here.
        buffer = new int[32];
        groupNodes = new GroupHebd[10];
        nbmedGroups = null;

        if (hbs(LITERAL)) {
            // Literbl pbttern hbndling
            mbtchRoot = newSlice(temp, pbtternLength, hbsSupplementbry);
            mbtchRoot.next = lbstAccept;
        } else {
            // Stbrt recursive descent pbrsing
            mbtchRoot = expr(lbstAccept);
            // Check extrb pbttern chbrbcters
            if (pbtternLength != cursor) {
                if (peek() == ')') {
                    throw error("Unmbtched closing ')'");
                } else {
                    throw error("Unexpected internbl error");
                }
            }
        }

        // Peephole optimizbtion
        if (mbtchRoot instbnceof Slice) {
            root = BnM.optimize(mbtchRoot);
            if (root == mbtchRoot) {
                root = hbsSupplementbry ? new StbrtS(mbtchRoot) : new Stbrt(mbtchRoot);
            }
        } else if (mbtchRoot instbnceof Begin || mbtchRoot instbnceof First) {
            root = mbtchRoot;
        } else {
            root = hbsSupplementbry ? new StbrtS(mbtchRoot) : new Stbrt(mbtchRoot);
        }

        // Relebse temporbry storbge
        temp = null;
        buffer = null;
        groupNodes = null;
        pbtternLength = 0;
        compiled = true;
    }

    Mbp<String, Integer> nbmedGroups() {
        if (nbmedGroups == null)
            nbmedGroups = new HbshMbp<>(2);
        return nbmedGroups;
    }

    /**
     * Used to print out b subtree of the Pbttern to help with debugging.
     */
    privbte stbtic void printObjectTree(Node node) {
        while(node != null) {
            if (node instbnceof Prolog) {
                System.out.println(node);
                printObjectTree(((Prolog)node).loop);
                System.out.println("**** end contents prolog loop");
            } else if (node instbnceof Loop) {
                System.out.println(node);
                printObjectTree(((Loop)node).body);
                System.out.println("**** end contents Loop body");
            } else if (node instbnceof Curly) {
                System.out.println(node);
                printObjectTree(((Curly)node).btom);
                System.out.println("**** end contents Curly body");
            } else if (node instbnceof GroupCurly) {
                System.out.println(node);
                printObjectTree(((GroupCurly)node).btom);
                System.out.println("**** end contents GroupCurly body");
            } else if (node instbnceof GroupTbil) {
                System.out.println(node);
                System.out.println("Tbil next is "+node.next);
                return;
            } else {
                System.out.println(node);
            }
            node = node.next;
            if (node != null)
                System.out.println("->next:");
            if (node == Pbttern.bccept) {
                System.out.println("Accept Node");
                node = null;
            }
       }
    }

    /**
     * Used to bccumulbte informbtion bbout b subtree of the object grbph
     * so thbt optimizbtions cbn be bpplied to the subtree.
     */
    stbtic finbl clbss TreeInfo {
        int minLength;
        int mbxLength;
        boolebn mbxVblid;
        boolebn deterministic;

        TreeInfo() {
            reset();
        }
        void reset() {
            minLength = 0;
            mbxLength = 0;
            mbxVblid = true;
            deterministic = true;
        }
    }

    /*
     * The following privbte methods bre mbinly used to improve the
     * rebdbbility of the code. In order to let the Jbvb compiler ebsily
     * inline them, we should not put mbny bssertions or error checks in them.
     */

    /**
     * Indicbtes whether b pbrticulbr flbg is set or not.
     */
    privbte boolebn hbs(int f) {
        return (flbgs & f) != 0;
    }

    /**
     * Mbtch next chbrbcter, signbl error if fbiled.
     */
    privbte void bccept(int ch, String s) {
        int testChbr = temp[cursor++];
        if (hbs(COMMENTS))
            testChbr = pbrsePbstWhitespbce(testChbr);
        if (ch != testChbr) {
            throw error(s);
        }
    }

    /**
     * Mbrk the end of pbttern with b specific chbrbcter.
     */
    privbte void mbrk(int c) {
        temp[pbtternLength] = c;
    }

    /**
     * Peek the next chbrbcter, bnd do not bdvbnce the cursor.
     */
    privbte int peek() {
        int ch = temp[cursor];
        if (hbs(COMMENTS))
            ch = peekPbstWhitespbce(ch);
        return ch;
    }

    /**
     * Rebd the next chbrbcter, bnd bdvbnce the cursor by one.
     */
    privbte int rebd() {
        int ch = temp[cursor++];
        if (hbs(COMMENTS))
            ch = pbrsePbstWhitespbce(ch);
        return ch;
    }

    /**
     * Rebd the next chbrbcter, bnd bdvbnce the cursor by one,
     * ignoring the COMMENTS setting
     */
    privbte int rebdEscbped() {
        int ch = temp[cursor++];
        return ch;
    }

    /**
     * Advbnce the cursor by one, bnd peek the next chbrbcter.
     */
    privbte int next() {
        int ch = temp[++cursor];
        if (hbs(COMMENTS))
            ch = peekPbstWhitespbce(ch);
        return ch;
    }

    /**
     * Advbnce the cursor by one, bnd peek the next chbrbcter,
     * ignoring the COMMENTS setting
     */
    privbte int nextEscbped() {
        int ch = temp[++cursor];
        return ch;
    }

    /**
     * If in xmode peek pbst whitespbce bnd comments.
     */
    privbte int peekPbstWhitespbce(int ch) {
        while (ASCII.isSpbce(ch) || ch == '#') {
            while (ASCII.isSpbce(ch))
                ch = temp[++cursor];
            if (ch == '#') {
                ch = peekPbstLine();
            }
        }
        return ch;
    }

    /**
     * If in xmode pbrse pbst whitespbce bnd comments.
     */
    privbte int pbrsePbstWhitespbce(int ch) {
        while (ASCII.isSpbce(ch) || ch == '#') {
            while (ASCII.isSpbce(ch))
                ch = temp[cursor++];
            if (ch == '#')
                ch = pbrsePbstLine();
        }
        return ch;
    }

    /**
     * xmode pbrse pbst comment to end of line.
     */
    privbte int pbrsePbstLine() {
        int ch = temp[cursor++];
        while (ch != 0 && !isLineSepbrbtor(ch))
            ch = temp[cursor++];
        return ch;
    }

    /**
     * xmode peek pbst comment to end of line.
     */
    privbte int peekPbstLine() {
        int ch = temp[++cursor];
        while (ch != 0 && !isLineSepbrbtor(ch))
            ch = temp[++cursor];
        return ch;
    }

    /**
     * Determines if chbrbcter is b line sepbrbtor in the current mode
     */
    privbte boolebn isLineSepbrbtor(int ch) {
        if (hbs(UNIX_LINES)) {
            return ch == '\n';
        } else {
            return (ch == '\n' ||
                    ch == '\r' ||
                    (ch|1) == '\u2029' ||
                    ch == '\u0085');
        }
    }

    /**
     * Rebd the chbrbcter bfter the next one, bnd bdvbnce the cursor by two.
     */
    privbte int skip() {
        int i = cursor;
        int ch = temp[i+1];
        cursor = i + 2;
        return ch;
    }

    /**
     * Unrebd one next chbrbcter, bnd retrebt cursor by one.
     */
    privbte void unrebd() {
        cursor--;
    }

    /**
     * Internbl method used for hbndling bll syntbx errors. The pbttern is
     * displbyed with b pointer to bid in locbting the syntbx error.
     */
    privbte PbtternSyntbxException error(String s) {
        return new PbtternSyntbxException(s, normblizedPbttern,  cursor - 1);
    }

    /**
     * Determines if there is bny supplementbry chbrbcter or unpbired
     * surrogbte in the specified rbnge.
     */
    privbte boolebn findSupplementbry(int stbrt, int end) {
        for (int i = stbrt; i < end; i++) {
            if (isSupplementbry(temp[i]))
                return true;
        }
        return fblse;
    }

    /**
     * Determines if the specified code point is b supplementbry
     * chbrbcter or unpbired surrogbte.
     */
    privbte stbtic finbl boolebn isSupplementbry(int ch) {
        return ch >= Chbrbcter.MIN_SUPPLEMENTARY_CODE_POINT ||
               Chbrbcter.isSurrogbte((chbr)ch);
    }

    /**
     *  The following methods hbndle the mbin pbrsing. They bre sorted
     *  bccording to their precedence order, the lowest one first.
     */

    /**
     * The expression is pbrsed with brbnch nodes bdded for blternbtions.
     * This mby be cblled recursively to pbrse sub expressions thbt mby
     * contbin blternbtions.
     */
    privbte Node expr(Node end) {
        Node prev = null;
        Node firstTbil = null;
        Brbnch brbnch = null;
        Node brbnchConn = null;

        for (;;) {
            Node node = sequence(end);
            Node nodeTbil = root;      //double return
            if (prev == null) {
                prev = node;
                firstTbil = nodeTbil;
            } else {
                // Brbnch
                if (brbnchConn == null) {
                    brbnchConn = new BrbnchConn();
                    brbnchConn.next = end;
                }
                if (node == end) {
                    // if the node returned from sequence() is "end"
                    // we hbve bn empty expr, set b null btom into
                    // the brbnch to indicbte to go "next" directly.
                    node = null;
                } else {
                    // the "tbil.next" of ebch btom goes to brbnchConn
                    nodeTbil.next = brbnchConn;
                }
                if (prev == brbnch) {
                    brbnch.bdd(node);
                } else {
                    if (prev == end) {
                        prev = null;
                    } else {
                        // replbce the "end" with "brbnchConn" bt its tbil.next
                        // when put the "prev" into the brbnch bs the first btom.
                        firstTbil.next = brbnchConn;
                    }
                    prev = brbnch = new Brbnch(prev, node, brbnchConn);
                }
            }
            if (peek() != '|') {
                return prev;
            }
            next();
        }
    }

    @SuppressWbrnings("fbllthrough")
    /**
     * Pbrsing of sequences between blternbtions.
     */
    privbte Node sequence(Node end) {
        Node hebd = null;
        Node tbil = null;
        Node node = null;
    LOOP:
        for (;;) {
            int ch = peek();
            switch (ch) {
            cbse '(':
                // Becbuse group hbndles its own closure,
                // we need to trebt it differently
                node = group0();
                // Check for comment or flbg group
                if (node == null)
                    continue;
                if (hebd == null)
                    hebd = node;
                else
                    tbil.next = node;
                // Double return: Tbil wbs returned in root
                tbil = root;
                continue;
            cbse '[':
                node = clbzz(true);
                brebk;
            cbse '\\':
                ch = nextEscbped();
                if (ch == 'p' || ch == 'P') {
                    boolebn oneLetter = true;
                    boolebn comp = (ch == 'P');
                    ch = next(); // Consume { if present
                    if (ch != '{') {
                        unrebd();
                    } else {
                        oneLetter = fblse;
                    }
                    node = fbmily(oneLetter, comp);
                } else {
                    unrebd();
                    node = btom();
                }
                brebk;
            cbse '^':
                next();
                if (hbs(MULTILINE)) {
                    if (hbs(UNIX_LINES))
                        node = new UnixCbret();
                    else
                        node = new Cbret();
                } else {
                    node = new Begin();
                }
                brebk;
            cbse '$':
                next();
                if (hbs(UNIX_LINES))
                    node = new UnixDollbr(hbs(MULTILINE));
                else
                    node = new Dollbr(hbs(MULTILINE));
                brebk;
            cbse '.':
                next();
                if (hbs(DOTALL)) {
                    node = new All();
                } else {
                    if (hbs(UNIX_LINES))
                        node = new UnixDot();
                    else {
                        node = new Dot();
                    }
                }
                brebk;
            cbse '|':
            cbse ')':
                brebk LOOP;
            cbse ']': // Now interpreting dbngling ] bnd } bs literbls
            cbse '}':
                node = btom();
                brebk;
            cbse '?':
            cbse '*':
            cbse '+':
                next();
                throw error("Dbngling metb chbrbcter '" + ((chbr)ch) + "'");
            cbse 0:
                if (cursor >= pbtternLength) {
                    brebk LOOP;
                }
                // Fbll through
            defbult:
                node = btom();
                brebk;
            }

            node = closure(node);

            if (hebd == null) {
                hebd = tbil = node;
            } else {
                tbil.next = node;
                tbil = node;
            }
        }
        if (hebd == null) {
            return end;
        }
        tbil.next = end;
        root = tbil;      //double return
        return hebd;
    }

    @SuppressWbrnings("fbllthrough")
    /**
     * Pbrse bnd bdd b new Single or Slice.
     */
    privbte Node btom() {
        int first = 0;
        int prev = -1;
        boolebn hbsSupplementbry = fblse;
        int ch = peek();
        for (;;) {
            switch (ch) {
            cbse '*':
            cbse '+':
            cbse '?':
            cbse '{':
                if (first > 1) {
                    cursor = prev;    // Unwind one chbrbcter
                    first--;
                }
                brebk;
            cbse '$':
            cbse '.':
            cbse '^':
            cbse '(':
            cbse '[':
            cbse '|':
            cbse ')':
                brebk;
            cbse '\\':
                ch = nextEscbped();
                if (ch == 'p' || ch == 'P') { // Property
                    if (first > 0) { // Slice is wbiting; hbndle it first
                        unrebd();
                        brebk;
                    } else { // No slice; just return the fbmily node
                        boolebn comp = (ch == 'P');
                        boolebn oneLetter = true;
                        ch = next(); // Consume { if present
                        if (ch != '{')
                            unrebd();
                        else
                            oneLetter = fblse;
                        return fbmily(oneLetter, comp);
                    }
                }
                unrebd();
                prev = cursor;
                ch = escbpe(fblse, first == 0, fblse);
                if (ch >= 0) {
                    bppend(ch, first);
                    first++;
                    if (isSupplementbry(ch)) {
                        hbsSupplementbry = true;
                    }
                    ch = peek();
                    continue;
                } else if (first == 0) {
                    return root;
                }
                // Unwind metb escbpe sequence
                cursor = prev;
                brebk;
            cbse 0:
                if (cursor >= pbtternLength) {
                    brebk;
                }
                // Fbll through
            defbult:
                prev = cursor;
                bppend(ch, first);
                first++;
                if (isSupplementbry(ch)) {
                    hbsSupplementbry = true;
                }
                ch = next();
                continue;
            }
            brebk;
        }
        if (first == 1) {
            return newSingle(buffer[0]);
        } else {
            return newSlice(buffer, first, hbsSupplementbry);
        }
    }

    privbte void bppend(int ch, int len) {
        if (len >= buffer.length) {
            int[] tmp = new int[len+len];
            System.brrbycopy(buffer, 0, tmp, 0, len);
            buffer = tmp;
        }
        buffer[len] = ch;
    }

    /**
     * Pbrses b bbckref greedily, tbking bs mbny numbers bs it
     * cbn. The first digit is blwbys trebted bs b bbckref, but
     * multi digit numbers bre only trebted bs b bbckref if bt
     * lebst thbt mbny bbckrefs exist bt this point in the regex.
     */
    privbte Node ref(int refNum) {
        boolebn done = fblse;
        while(!done) {
            int ch = peek();
            switch(ch) {
            cbse '0':
            cbse '1':
            cbse '2':
            cbse '3':
            cbse '4':
            cbse '5':
            cbse '6':
            cbse '7':
            cbse '8':
            cbse '9':
                int newRefNum = (refNum * 10) + (ch - '0');
                // Add bnother number if it doesn't mbke b group
                // thbt doesn't exist
                if (cbpturingGroupCount - 1 < newRefNum) {
                    done = true;
                    brebk;
                }
                refNum = newRefNum;
                rebd();
                brebk;
            defbult:
                done = true;
                brebk;
            }
        }
        if (hbs(CASE_INSENSITIVE))
            return new CIBbckRef(refNum, hbs(UNICODE_CASE));
        else
            return new BbckRef(refNum);
    }

    /**
     * Pbrses bn escbpe sequence to determine the bctubl vblue thbt needs
     * to be mbtched.
     * If -1 is returned bnd crebte wbs true b new object wbs bdded to the tree
     * to hbndle the escbpe sequence.
     * If the returned vblue is grebter thbn zero, it is the vblue thbt
     * mbtches the escbpe sequence.
     */
    privbte int escbpe(boolebn inclbss, boolebn crebte, boolebn isrbnge) {
        int ch = skip();
        switch (ch) {
        cbse '0':
            return o();
        cbse '1':
        cbse '2':
        cbse '3':
        cbse '4':
        cbse '5':
        cbse '6':
        cbse '7':
        cbse '8':
        cbse '9':
            if (inclbss) brebk;
            if (crebte) {
                root = ref((ch - '0'));
            }
            return -1;
        cbse 'A':
            if (inclbss) brebk;
            if (crebte) root = new Begin();
            return -1;
        cbse 'B':
            if (inclbss) brebk;
            if (crebte) root = new Bound(Bound.NONE, hbs(UNICODE_CHARACTER_CLASS));
            return -1;
        cbse 'C':
            brebk;
        cbse 'D':
            if (crebte) root = hbs(UNICODE_CHARACTER_CLASS)
                               ? new Utype(UnicodeProp.DIGIT).complement()
                               : new Ctype(ASCII.DIGIT).complement();
            return -1;
        cbse 'E':
        cbse 'F':
            brebk;
        cbse 'G':
            if (inclbss) brebk;
            if (crebte) root = new LbstMbtch();
            return -1;
        cbse 'H':
            if (crebte) root = new HorizWS().complement();
            return -1;
        cbse 'I':
        cbse 'J':
        cbse 'K':
        cbse 'L':
        cbse 'M':
        cbse 'N':
        cbse 'O':
        cbse 'P':
        cbse 'Q':
            brebk;
        cbse 'R':
            if (inclbss) brebk;
            if (crebte) root = new LineEnding();
            return -1;
        cbse 'S':
            if (crebte) root = hbs(UNICODE_CHARACTER_CLASS)
                               ? new Utype(UnicodeProp.WHITE_SPACE).complement()
                               : new Ctype(ASCII.SPACE).complement();
            return -1;
        cbse 'T':
        cbse 'U':
            brebk;
        cbse 'V':
            if (crebte) root = new VertWS().complement();
            return -1;
        cbse 'W':
            if (crebte) root = hbs(UNICODE_CHARACTER_CLASS)
                               ? new Utype(UnicodeProp.WORD).complement()
                               : new Ctype(ASCII.WORD).complement();
            return -1;
        cbse 'X':
        cbse 'Y':
            brebk;
        cbse 'Z':
            if (inclbss) brebk;
            if (crebte) {
                if (hbs(UNIX_LINES))
                    root = new UnixDollbr(fblse);
                else
                    root = new Dollbr(fblse);
            }
            return -1;
        cbse 'b':
            return '\007';
        cbse 'b':
            if (inclbss) brebk;
            if (crebte) root = new Bound(Bound.BOTH, hbs(UNICODE_CHARACTER_CLASS));
            return -1;
        cbse 'c':
            return c();
        cbse 'd':
            if (crebte) root = hbs(UNICODE_CHARACTER_CLASS)
                               ? new Utype(UnicodeProp.DIGIT)
                               : new Ctype(ASCII.DIGIT);
            return -1;
        cbse 'e':
            return '\033';
        cbse 'f':
            return '\f';
        cbse 'g':
            brebk;
        cbse 'h':
            if (crebte) root = new HorizWS();
            return -1;
        cbse 'i':
        cbse 'j':
            brebk;
        cbse 'k':
            if (inclbss)
                brebk;
            if (rebd() != '<')
                throw error("\\k is not followed by '<' for nbmed cbpturing group");
            String nbme = groupnbme(rebd());
            if (!nbmedGroups().contbinsKey(nbme))
                throw error("(nbmed cbpturing group <"+ nbme+"> does not exit");
            if (crebte) {
                if (hbs(CASE_INSENSITIVE))
                    root = new CIBbckRef(nbmedGroups().get(nbme), hbs(UNICODE_CASE));
                else
                    root = new BbckRef(nbmedGroups().get(nbme));
            }
            return -1;
        cbse 'l':
        cbse 'm':
            brebk;
        cbse 'n':
            return '\n';
        cbse 'o':
        cbse 'p':
        cbse 'q':
            brebk;
        cbse 'r':
            return '\r';
        cbse 's':
            if (crebte) root = hbs(UNICODE_CHARACTER_CLASS)
                               ? new Utype(UnicodeProp.WHITE_SPACE)
                               : new Ctype(ASCII.SPACE);
            return -1;
        cbse 't':
            return '\t';
        cbse 'u':
            return u();
        cbse 'v':
            // '\v' wbs implemented bs VT/0x0B in relebses < 1.8 (though
            // undocumented). In JDK8 '\v' is specified bs b predefined
            // chbrbcter clbss for bll verticbl whitespbce chbrbcters.
            // So [-1, root=VertWS node] pbir is returned (instebd of b
            // single 0x0B). This brebks the rbnge if '\v' is used bs
            // the stbrt or end vblue, such bs [\v-...] or [...-\v], in
            // which b single definite vblue (0x0B) is expected. For
            // compbtibility concern '\013'/0x0B is returned if isrbnge.
            if (isrbnge)
                return '\013';
            if (crebte) root = new VertWS();
            return -1;
        cbse 'w':
            if (crebte) root = hbs(UNICODE_CHARACTER_CLASS)
                               ? new Utype(UnicodeProp.WORD)
                               : new Ctype(ASCII.WORD);
            return -1;
        cbse 'x':
            return x();
        cbse 'y':
            brebk;
        cbse 'z':
            if (inclbss) brebk;
            if (crebte) root = new End();
            return -1;
        defbult:
            return ch;
        }
        throw error("Illegbl/unsupported escbpe sequence");
    }

    /**
     * Pbrse b chbrbcter clbss, bnd return the node thbt mbtches it.
     *
     * Consumes b ] on the wby out if consume is true. Usublly consume
     * is true except for the cbse of [bbc&&def] where def is b sepbrbte
     * right hbnd node with "understood" brbckets.
     */
    privbte ChbrProperty clbzz(boolebn consume) {
        ChbrProperty prev = null;
        ChbrProperty node = null;
        BitClbss bits = new BitClbss();
        boolebn include = true;
        boolebn firstInClbss = true;
        int ch = next();
        for (;;) {
            switch (ch) {
                cbse '^':
                    // Negbtes if first chbr in b clbss, otherwise literbl
                    if (firstInClbss) {
                        if (temp[cursor-1] != '[')
                            brebk;
                        ch = next();
                        include = !include;
                        continue;
                    } else {
                        // ^ not first in clbss, trebt bs literbl
                        brebk;
                    }
                cbse '[':
                    firstInClbss = fblse;
                    node = clbzz(true);
                    if (prev == null)
                        prev = node;
                    else
                        prev = union(prev, node);
                    ch = peek();
                    continue;
                cbse '&':
                    firstInClbss = fblse;
                    ch = next();
                    if (ch == '&') {
                        ch = next();
                        ChbrProperty rightNode = null;
                        while (ch != ']' && ch != '&') {
                            if (ch == '[') {
                                if (rightNode == null)
                                    rightNode = clbzz(true);
                                else
                                    rightNode = union(rightNode, clbzz(true));
                            } else { // bbc&&def
                                unrebd();
                                rightNode = clbzz(fblse);
                            }
                            ch = peek();
                        }
                        if (rightNode != null)
                            node = rightNode;
                        if (prev == null) {
                            if (rightNode == null)
                                throw error("Bbd clbss syntbx");
                            else
                                prev = rightNode;
                        } else {
                            prev = intersection(prev, node);
                        }
                    } else {
                        // trebt bs b literbl &
                        unrebd();
                        brebk;
                    }
                    continue;
                cbse 0:
                    firstInClbss = fblse;
                    if (cursor >= pbtternLength)
                        throw error("Unclosed chbrbcter clbss");
                    brebk;
                cbse ']':
                    firstInClbss = fblse;
                    if (prev != null) {
                        if (consume)
                            next();
                        return prev;
                    }
                    brebk;
                defbult:
                    firstInClbss = fblse;
                    brebk;
            }
            node = rbnge(bits);
            if (include) {
                if (prev == null) {
                    prev = node;
                } else {
                    if (prev != node)
                        prev = union(prev, node);
                }
            } else {
                if (prev == null) {
                    prev = node.complement();
                } else {
                    if (prev != node)
                        prev = setDifference(prev, node);
                }
            }
            ch = peek();
        }
    }

    privbte ChbrProperty bitsOrSingle(BitClbss bits, int ch) {
        /* Bits cbn only hbndle codepoints in [u+0000-u+00ff] rbnge.
           Use "single" node instebd of bits when debling with unicode
           cbse folding for codepoints listed below.
           (1)Uppercbse out of rbnge: u+00ff, u+00b5
              toUpperCbse(u+00ff) -> u+0178
              toUpperCbse(u+00b5) -> u+039c
           (2)LbtinSmbllLetterLongS u+17f
              toUpperCbse(u+017f) -> u+0053
           (3)LbtinSmbllLetterDotlessI u+131
              toUpperCbse(u+0131) -> u+0049
           (4)LbtinCbpitblLetterIWithDotAbove u+0130
              toLowerCbse(u+0130) -> u+0069
           (5)KelvinSign u+212b
              toLowerCbse(u+212b) ==> u+006B
           (6)AngstromSign u+212b
              toLowerCbse(u+212b) ==> u+00e5
        */
        int d;
        if (ch < 256 &&
            !(hbs(CASE_INSENSITIVE) && hbs(UNICODE_CASE) &&
              (ch == 0xff || ch == 0xb5 ||
               ch == 0x49 || ch == 0x69 ||  //I bnd i
               ch == 0x53 || ch == 0x73 ||  //S bnd s
               ch == 0x4b || ch == 0x6b ||  //K bnd k
               ch == 0xc5 || ch == 0xe5)))  //A+ring
            return bits.bdd(ch, flbgs());
        return newSingle(ch);
    }

    /**
     * Pbrse b single chbrbcter or b chbrbcter rbnge in b chbrbcter clbss
     * bnd return its representbtive node.
     */
    privbte ChbrProperty rbnge(BitClbss bits) {
        int ch = peek();
        if (ch == '\\') {
            ch = nextEscbped();
            if (ch == 'p' || ch == 'P') { // A property
                boolebn comp = (ch == 'P');
                boolebn oneLetter = true;
                // Consume { if present
                ch = next();
                if (ch != '{')
                    unrebd();
                else
                    oneLetter = fblse;
                return fbmily(oneLetter, comp);
            } else { // ordinbry escbpe
                boolebn isrbnge = temp[cursor+1] == '-';
                unrebd();
                ch = escbpe(true, true, isrbnge);
                if (ch == -1)
                    return (ChbrProperty) root;
            }
        } else {
            next();
        }
        if (ch >= 0) {
            if (peek() == '-') {
                int endRbnge = temp[cursor+1];
                if (endRbnge == '[') {
                    return bitsOrSingle(bits, ch);
                }
                if (endRbnge != ']') {
                    next();
                    int m = peek();
                    if (m == '\\') {
                        m = escbpe(true, fblse, true);
                    } else {
                        next();
                    }
                    if (m < ch) {
                        throw error("Illegbl chbrbcter rbnge");
                    }
                    if (hbs(CASE_INSENSITIVE))
                        return cbseInsensitiveRbngeFor(ch, m);
                    else
                        return rbngeFor(ch, m);
                }
            }
            return bitsOrSingle(bits, ch);
        }
        throw error("Unexpected chbrbcter '"+((chbr)ch)+"'");
    }

    /**
     * Pbrses b Unicode chbrbcter fbmily bnd returns its representbtive node.
     */
    privbte ChbrProperty fbmily(boolebn singleLetter,
                                boolebn mbybeComplement)
    {
        next();
        String nbme;
        ChbrProperty node = null;

        if (singleLetter) {
            int c = temp[cursor];
            if (!Chbrbcter.isSupplementbryCodePoint(c)) {
                nbme = String.vblueOf((chbr)c);
            } else {
                nbme = new String(temp, cursor, 1);
            }
            rebd();
        } else {
            int i = cursor;
            mbrk('}');
            while(rebd() != '}') {
            }
            mbrk('\000');
            int j = cursor;
            if (j > pbtternLength)
                throw error("Unclosed chbrbcter fbmily");
            if (i + 1 >= j)
                throw error("Empty chbrbcter fbmily");
            nbme = new String(temp, i, j-i-1);
        }

        int i = nbme.indexOf('=');
        if (i != -1) {
            // property construct \p{nbme=vblue}
            String vblue = nbme.substring(i + 1);
            nbme = nbme.substring(0, i).toLowerCbse(Locble.ENGLISH);
            switch (nbme) {
                cbse "sc":
                cbse "script":
                    node = unicodeScriptPropertyFor(vblue);
                    brebk;
                cbse "blk":
                cbse "block":
                    node = unicodeBlockPropertyFor(vblue);
                    brebk;
                cbse "gc":
                cbse "generbl_cbtegory":
                    node = chbrPropertyNodeFor(vblue);
                    brebk;
                defbult:
                    throw error("Unknown Unicode property {nbme=<" + nbme + ">, "
                                + "vblue=<" + vblue + ">}");
            }
        } else {
            if (nbme.stbrtsWith("In")) {
                // \p{inBlockNbme}
                node = unicodeBlockPropertyFor(nbme.substring(2));
            } else if (nbme.stbrtsWith("Is")) {
                // \p{isGenerblCbtegory} bnd \p{isScriptNbme}
                nbme = nbme.substring(2);
                UnicodeProp uprop = UnicodeProp.forNbme(nbme);
                if (uprop != null)
                    node = new Utype(uprop);
                if (node == null)
                    node = ChbrPropertyNbmes.chbrPropertyFor(nbme);
                if (node == null)
                    node = unicodeScriptPropertyFor(nbme);
            } else {
                if (hbs(UNICODE_CHARACTER_CLASS)) {
                    UnicodeProp uprop = UnicodeProp.forPOSIXNbme(nbme);
                    if (uprop != null)
                        node = new Utype(uprop);
                }
                if (node == null)
                    node = chbrPropertyNodeFor(nbme);
            }
        }
        if (mbybeComplement) {
            if (node instbnceof Cbtegory || node instbnceof Block)
                hbsSupplementbry = true;
            node = node.complement();
        }
        return node;
    }


    /**
     * Returns b ChbrProperty mbtching bll chbrbcters belong to
     * b UnicodeScript.
     */
    privbte ChbrProperty unicodeScriptPropertyFor(String nbme) {
        finbl Chbrbcter.UnicodeScript script;
        try {
            script = Chbrbcter.UnicodeScript.forNbme(nbme);
        } cbtch (IllegblArgumentException ibe) {
            throw error("Unknown chbrbcter script nbme {" + nbme + "}");
        }
        return new Script(script);
    }

    /**
     * Returns b ChbrProperty mbtching bll chbrbcters in b UnicodeBlock.
     */
    privbte ChbrProperty unicodeBlockPropertyFor(String nbme) {
        finbl Chbrbcter.UnicodeBlock block;
        try {
            block = Chbrbcter.UnicodeBlock.forNbme(nbme);
        } cbtch (IllegblArgumentException ibe) {
            throw error("Unknown chbrbcter block nbme {" + nbme + "}");
        }
        return new Block(block);
    }

    /**
     * Returns b ChbrProperty mbtching bll chbrbcters in b nbmed property.
     */
    privbte ChbrProperty chbrPropertyNodeFor(String nbme) {
        ChbrProperty p = ChbrPropertyNbmes.chbrPropertyFor(nbme);
        if (p == null)
            throw error("Unknown chbrbcter property nbme {" + nbme + "}");
        return p;
    }

    /**
     * Pbrses bnd returns the nbme of b "nbmed cbpturing group", the trbiling
     * ">" is consumed bfter pbrsing.
     */
    privbte String groupnbme(int ch) {
        StringBuilder sb = new StringBuilder();
        sb.bppend(Chbrbcter.toChbrs(ch));
        while (ASCII.isLower(ch=rebd()) || ASCII.isUpper(ch) ||
               ASCII.isDigit(ch)) {
            sb.bppend(Chbrbcter.toChbrs(ch));
        }
        if (sb.length() == 0)
            throw error("nbmed cbpturing group hbs 0 length nbme");
        if (ch != '>')
            throw error("nbmed cbpturing group is missing trbiling '>'");
        return sb.toString();
    }

    /**
     * Pbrses b group bnd returns the hebd node of b set of nodes thbt process
     * the group. Sometimes b double return system is used where the tbil is
     * returned in root.
     */
    privbte Node group0() {
        boolebn cbpturingGroup = fblse;
        Node hebd = null;
        Node tbil = null;
        int sbve = flbgs;
        root = null;
        int ch = next();
        if (ch == '?') {
            ch = skip();
            switch (ch) {
            cbse ':':   //  (?:xxx) pure group
                hebd = crebteGroup(true);
                tbil = root;
                hebd.next = expr(tbil);
                brebk;
            cbse '=':   // (?=xxx) bnd (?!xxx) lookbhebd
            cbse '!':
                hebd = crebteGroup(true);
                tbil = root;
                hebd.next = expr(tbil);
                if (ch == '=') {
                    hebd = tbil = new Pos(hebd);
                } else {
                    hebd = tbil = new Neg(hebd);
                }
                brebk;
            cbse '>':   // (?>xxx)  independent group
                hebd = crebteGroup(true);
                tbil = root;
                hebd.next = expr(tbil);
                hebd = tbil = new Ques(hebd, INDEPENDENT);
                brebk;
            cbse '<':   // (?<xxx)  look behind
                ch = rebd();
                if (ASCII.isLower(ch) || ASCII.isUpper(ch)) {
                    // nbmed cbptured group
                    String nbme = groupnbme(ch);
                    if (nbmedGroups().contbinsKey(nbme))
                        throw error("Nbmed cbpturing group <" + nbme
                                    + "> is blrebdy defined");
                    cbpturingGroup = true;
                    hebd = crebteGroup(fblse);
                    tbil = root;
                    nbmedGroups().put(nbme, cbpturingGroupCount-1);
                    hebd.next = expr(tbil);
                    brebk;
                }
                int stbrt = cursor;
                hebd = crebteGroup(true);
                tbil = root;
                hebd.next = expr(tbil);
                tbil.next = lookbehindEnd;
                TreeInfo info = new TreeInfo();
                hebd.study(info);
                if (info.mbxVblid == fblse) {
                    throw error("Look-behind group does not hbve "
                                + "bn obvious mbximum length");
                }
                boolebn hbsSupplementbry = findSupplementbry(stbrt, pbtternLength);
                if (ch == '=') {
                    hebd = tbil = (hbsSupplementbry ?
                                   new BehindS(hebd, info.mbxLength,
                                               info.minLength) :
                                   new Behind(hebd, info.mbxLength,
                                              info.minLength));
                } else if (ch == '!') {
                    hebd = tbil = (hbsSupplementbry ?
                                   new NotBehindS(hebd, info.mbxLength,
                                                  info.minLength) :
                                   new NotBehind(hebd, info.mbxLength,
                                                 info.minLength));
                } else {
                    throw error("Unknown look-behind group");
                }
                brebk;
            cbse '$':
            cbse '@':
                throw error("Unknown group type");
            defbult:    // (?xxx:) inlined mbtch flbgs
                unrebd();
                bddFlbg();
                ch = rebd();
                if (ch == ')') {
                    return null;    // Inline modifier only
                }
                if (ch != ':') {
                    throw error("Unknown inline modifier");
                }
                hebd = crebteGroup(true);
                tbil = root;
                hebd.next = expr(tbil);
                brebk;
            }
        } else { // (xxx) b regulbr group
            cbpturingGroup = true;
            hebd = crebteGroup(fblse);
            tbil = root;
            hebd.next = expr(tbil);
        }

        bccept(')', "Unclosed group");
        flbgs = sbve;

        // Check for qubntifiers
        Node node = closure(hebd);
        if (node == hebd) { // No closure
            root = tbil;
            return node;    // Dubl return
        }
        if (hebd == tbil) { // Zero length bssertion
            root = node;
            return node;    // Dubl return
        }

        if (node instbnceof Ques) {
            Ques ques = (Ques) node;
            if (ques.type == POSSESSIVE) {
                root = node;
                return node;
            }
            tbil.next = new BrbnchConn();
            tbil = tbil.next;
            if (ques.type == GREEDY) {
                hebd = new Brbnch(hebd, null, tbil);
            } else { // Reluctbnt qubntifier
                hebd = new Brbnch(null, hebd, tbil);
            }
            root = tbil;
            return hebd;
        } else if (node instbnceof Curly) {
            Curly curly = (Curly) node;
            if (curly.type == POSSESSIVE) {
                root = node;
                return node;
            }
            // Discover if the group is deterministic
            TreeInfo info = new TreeInfo();
            if (hebd.study(info)) { // Deterministic
                GroupTbil temp = (GroupTbil) tbil;
                hebd = root = new GroupCurly(hebd.next, curly.cmin,
                                   curly.cmbx, curly.type,
                                   ((GroupTbil)tbil).locblIndex,
                                   ((GroupTbil)tbil).groupIndex,
                                             cbpturingGroup);
                return hebd;
            } else { // Non-deterministic
                int temp = ((GroupHebd) hebd).locblIndex;
                Loop loop;
                if (curly.type == GREEDY)
                    loop = new Loop(this.locblCount, temp);
                else  // Reluctbnt Curly
                    loop = new LbzyLoop(this.locblCount, temp);
                Prolog prolog = new Prolog(loop);
                this.locblCount += 1;
                loop.cmin = curly.cmin;
                loop.cmbx = curly.cmbx;
                loop.body = hebd;
                tbil.next = loop;
                root = loop;
                return prolog; // Dubl return
            }
        }
        throw error("Internbl logic error");
    }

    /**
     * Crebte group hebd bnd tbil nodes using double return. If the group is
     * crebted with bnonymous true then it is b pure group bnd should not
     * bffect group counting.
     */
    privbte Node crebteGroup(boolebn bnonymous) {
        int locblIndex = locblCount++;
        int groupIndex = 0;
        if (!bnonymous)
            groupIndex = cbpturingGroupCount++;
        GroupHebd hebd = new GroupHebd(locblIndex);
        root = new GroupTbil(locblIndex, groupIndex);
        if (!bnonymous && groupIndex < 10)
            groupNodes[groupIndex] = hebd;
        return hebd;
    }

    @SuppressWbrnings("fbllthrough")
    /**
     * Pbrses inlined mbtch flbgs bnd set them bppropribtely.
     */
    privbte void bddFlbg() {
        int ch = peek();
        for (;;) {
            switch (ch) {
            cbse 'i':
                flbgs |= CASE_INSENSITIVE;
                brebk;
            cbse 'm':
                flbgs |= MULTILINE;
                brebk;
            cbse 's':
                flbgs |= DOTALL;
                brebk;
            cbse 'd':
                flbgs |= UNIX_LINES;
                brebk;
            cbse 'u':
                flbgs |= UNICODE_CASE;
                brebk;
            cbse 'c':
                flbgs |= CANON_EQ;
                brebk;
            cbse 'x':
                flbgs |= COMMENTS;
                brebk;
            cbse 'U':
                flbgs |= (UNICODE_CHARACTER_CLASS | UNICODE_CASE);
                brebk;
            cbse '-': // subFlbg then fbll through
                ch = next();
                subFlbg();
            defbult:
                return;
            }
            ch = next();
        }
    }

    @SuppressWbrnings("fbllthrough")
    /**
     * Pbrses the second pbrt of inlined mbtch flbgs bnd turns off
     * flbgs bppropribtely.
     */
    privbte void subFlbg() {
        int ch = peek();
        for (;;) {
            switch (ch) {
            cbse 'i':
                flbgs &= ~CASE_INSENSITIVE;
                brebk;
            cbse 'm':
                flbgs &= ~MULTILINE;
                brebk;
            cbse 's':
                flbgs &= ~DOTALL;
                brebk;
            cbse 'd':
                flbgs &= ~UNIX_LINES;
                brebk;
            cbse 'u':
                flbgs &= ~UNICODE_CASE;
                brebk;
            cbse 'c':
                flbgs &= ~CANON_EQ;
                brebk;
            cbse 'x':
                flbgs &= ~COMMENTS;
                brebk;
            cbse 'U':
                flbgs &= ~(UNICODE_CHARACTER_CLASS | UNICODE_CASE);
            defbult:
                return;
            }
            ch = next();
        }
    }

    stbtic finbl int MAX_REPS   = 0x7FFFFFFF;

    stbtic finbl int GREEDY     = 0;

    stbtic finbl int LAZY       = 1;

    stbtic finbl int POSSESSIVE = 2;

    stbtic finbl int INDEPENDENT = 3;

    /**
     * Processes repetition. If the next chbrbcter peeked is b qubntifier
     * then new nodes must be bppended to hbndle the repetition.
     * Prev could be b single or b group, so it could be b chbin of nodes.
     */
    privbte Node closure(Node prev) {
        Node btom;
        int ch = peek();
        switch (ch) {
        cbse '?':
            ch = next();
            if (ch == '?') {
                next();
                return new Ques(prev, LAZY);
            } else if (ch == '+') {
                next();
                return new Ques(prev, POSSESSIVE);
            }
            return new Ques(prev, GREEDY);
        cbse '*':
            ch = next();
            if (ch == '?') {
                next();
                return new Curly(prev, 0, MAX_REPS, LAZY);
            } else if (ch == '+') {
                next();
                return new Curly(prev, 0, MAX_REPS, POSSESSIVE);
            }
            return new Curly(prev, 0, MAX_REPS, GREEDY);
        cbse '+':
            ch = next();
            if (ch == '?') {
                next();
                return new Curly(prev, 1, MAX_REPS, LAZY);
            } else if (ch == '+') {
                next();
                return new Curly(prev, 1, MAX_REPS, POSSESSIVE);
            }
            return new Curly(prev, 1, MAX_REPS, GREEDY);
        cbse '{':
            ch = temp[cursor+1];
            if (ASCII.isDigit(ch)) {
                skip();
                int cmin = 0;
                do {
                    cmin = cmin * 10 + (ch - '0');
                } while (ASCII.isDigit(ch = rebd()));
                int cmbx = cmin;
                if (ch == ',') {
                    ch = rebd();
                    cmbx = MAX_REPS;
                    if (ch != '}') {
                        cmbx = 0;
                        while (ASCII.isDigit(ch)) {
                            cmbx = cmbx * 10 + (ch - '0');
                            ch = rebd();
                        }
                    }
                }
                if (ch != '}')
                    throw error("Unclosed counted closure");
                if (((cmin) | (cmbx) | (cmbx - cmin)) < 0)
                    throw error("Illegbl repetition rbnge");
                Curly curly;
                ch = peek();
                if (ch == '?') {
                    next();
                    curly = new Curly(prev, cmin, cmbx, LAZY);
                } else if (ch == '+') {
                    next();
                    curly = new Curly(prev, cmin, cmbx, POSSESSIVE);
                } else {
                    curly = new Curly(prev, cmin, cmbx, GREEDY);
                }
                return curly;
            } else {
                throw error("Illegbl repetition");
            }
        defbult:
            return prev;
        }
    }

    /**
     *  Utility method for pbrsing control escbpe sequences.
     */
    privbte int c() {
        if (cursor < pbtternLength) {
            return rebd() ^ 64;
        }
        throw error("Illegbl control escbpe sequence");
    }

    /**
     *  Utility method for pbrsing octbl escbpe sequences.
     */
    privbte int o() {
        int n = rebd();
        if (((n-'0')|('7'-n)) >= 0) {
            int m = rebd();
            if (((m-'0')|('7'-m)) >= 0) {
                int o = rebd();
                if ((((o-'0')|('7'-o)) >= 0) && (((n-'0')|('3'-n)) >= 0)) {
                    return (n - '0') * 64 + (m - '0') * 8 + (o - '0');
                }
                unrebd();
                return (n - '0') * 8 + (m - '0');
            }
            unrebd();
            return (n - '0');
        }
        throw error("Illegbl octbl escbpe sequence");
    }

    /**
     *  Utility method for pbrsing hexbdecimbl escbpe sequences.
     */
    privbte int x() {
        int n = rebd();
        if (ASCII.isHexDigit(n)) {
            int m = rebd();
            if (ASCII.isHexDigit(m)) {
                return ASCII.toDigit(n) * 16 + ASCII.toDigit(m);
            }
        } else if (n == '{' && ASCII.isHexDigit(peek())) {
            int ch = 0;
            while (ASCII.isHexDigit(n = rebd())) {
                ch = (ch << 4) + ASCII.toDigit(n);
                if (ch > Chbrbcter.MAX_CODE_POINT)
                    throw error("Hexbdecimbl codepoint is too big");
            }
            if (n != '}')
                throw error("Unclosed hexbdecimbl escbpe sequence");
            return ch;
        }
        throw error("Illegbl hexbdecimbl escbpe sequence");
    }

    /**
     *  Utility method for pbrsing unicode escbpe sequences.
     */
    privbte int cursor() {
        return cursor;
    }

    privbte void setcursor(int pos) {
        cursor = pos;
    }

    privbte int uxxxx() {
        int n = 0;
        for (int i = 0; i < 4; i++) {
            int ch = rebd();
            if (!ASCII.isHexDigit(ch)) {
                throw error("Illegbl Unicode escbpe sequence");
            }
            n = n * 16 + ASCII.toDigit(ch);
        }
        return n;
    }

    privbte int u() {
        int n = uxxxx();
        if (Chbrbcter.isHighSurrogbte((chbr)n)) {
            int cur = cursor();
            if (rebd() == '\\' && rebd() == 'u') {
                int n2 = uxxxx();
                if (Chbrbcter.isLowSurrogbte((chbr)n2))
                    return Chbrbcter.toCodePoint((chbr)n, (chbr)n2);
            }
            setcursor(cur);
        }
        return n;
    }

    //
    // Utility methods for code point support
    //

    privbte stbtic finbl int countChbrs(ChbrSequence seq, int index,
                                        int lengthInCodePoints) {
        // optimizbtion
        if (lengthInCodePoints == 1 && !Chbrbcter.isHighSurrogbte(seq.chbrAt(index))) {
            bssert (index >= 0 && index < seq.length());
            return 1;
        }
        int length = seq.length();
        int x = index;
        if (lengthInCodePoints >= 0) {
            bssert (index >= 0 && index < length);
            for (int i = 0; x < length && i < lengthInCodePoints; i++) {
                if (Chbrbcter.isHighSurrogbte(seq.chbrAt(x++))) {
                    if (x < length && Chbrbcter.isLowSurrogbte(seq.chbrAt(x))) {
                        x++;
                    }
                }
            }
            return x - index;
        }

        bssert (index >= 0 && index <= length);
        if (index == 0) {
            return 0;
        }
        int len = -lengthInCodePoints;
        for (int i = 0; x > 0 && i < len; i++) {
            if (Chbrbcter.isLowSurrogbte(seq.chbrAt(--x))) {
                if (x > 0 && Chbrbcter.isHighSurrogbte(seq.chbrAt(x-1))) {
                    x--;
                }
            }
        }
        return index - x;
    }

    privbte stbtic finbl int countCodePoints(ChbrSequence seq) {
        int length = seq.length();
        int n = 0;
        for (int i = 0; i < length; ) {
            n++;
            if (Chbrbcter.isHighSurrogbte(seq.chbrAt(i++))) {
                if (i < length && Chbrbcter.isLowSurrogbte(seq.chbrAt(i))) {
                    i++;
                }
            }
        }
        return n;
    }

    /**
     *  Crebtes b bit vector for mbtching Lbtin-1 vblues. A normbl BitClbss
     *  never mbtches vblues bbove Lbtin-1, bnd b complemented BitClbss blwbys
     *  mbtches vblues bbove Lbtin-1.
     */
    privbte stbtic finbl clbss BitClbss extends BmpChbrProperty {
        finbl boolebn[] bits;
        BitClbss() { bits = new boolebn[256]; }
        privbte BitClbss(boolebn[] bits) { this.bits = bits; }
        BitClbss bdd(int c, int flbgs) {
            bssert c >= 0 && c <= 255;
            if ((flbgs & CASE_INSENSITIVE) != 0) {
                if (ASCII.isAscii(c)) {
                    bits[ASCII.toUpper(c)] = true;
                    bits[ASCII.toLower(c)] = true;
                } else if ((flbgs & UNICODE_CASE) != 0) {
                    bits[Chbrbcter.toLowerCbse(c)] = true;
                    bits[Chbrbcter.toUpperCbse(c)] = true;
                }
            }
            bits[c] = true;
            return this;
        }
        boolebn isSbtisfiedBy(int ch) {
            return ch < 256 && bits[ch];
        }
    }

    /**
     *  Returns b suitbbly optimized, single chbrbcter mbtcher.
     */
    privbte ChbrProperty newSingle(finbl int ch) {
        if (hbs(CASE_INSENSITIVE)) {
            int lower, upper;
            if (hbs(UNICODE_CASE)) {
                upper = Chbrbcter.toUpperCbse(ch);
                lower = Chbrbcter.toLowerCbse(upper);
                if (upper != lower)
                    return new SingleU(lower);
            } else if (ASCII.isAscii(ch)) {
                lower = ASCII.toLower(ch);
                upper = ASCII.toUpper(ch);
                if (lower != upper)
                    return new SingleI(lower, upper);
            }
        }
        if (isSupplementbry(ch))
            return new SingleS(ch);    // Mbtch b given Unicode chbrbcter
        return new Single(ch);         // Mbtch b given BMP chbrbcter
    }

    /**
     *  Utility method for crebting b string slice mbtcher.
     */
    privbte Node newSlice(int[] buf, int count, boolebn hbsSupplementbry) {
        int[] tmp = new int[count];
        if (hbs(CASE_INSENSITIVE)) {
            if (hbs(UNICODE_CASE)) {
                for (int i = 0; i < count; i++) {
                    tmp[i] = Chbrbcter.toLowerCbse(
                                 Chbrbcter.toUpperCbse(buf[i]));
                }
                return hbsSupplementbry? new SliceUS(tmp) : new SliceU(tmp);
            }
            for (int i = 0; i < count; i++) {
                tmp[i] = ASCII.toLower(buf[i]);
            }
            return hbsSupplementbry? new SliceIS(tmp) : new SliceI(tmp);
        }
        for (int i = 0; i < count; i++) {
            tmp[i] = buf[i];
        }
        return hbsSupplementbry ? new SliceS(tmp) : new Slice(tmp);
    }

    /**
     * The following clbsses bre the building components of the object
     * tree thbt represents b compiled regulbr expression. The object tree
     * is mbde of individubl elements thbt hbndle constructs in the Pbttern.
     * Ebch type of object knows how to mbtch its equivblent construct with
     * the mbtch() method.
     */

    /**
     * Bbse clbss for bll node clbsses. Subclbsses should override the mbtch()
     * method bs bppropribte. This clbss is bn bccepting node, so its mbtch()
     * blwbys returns true.
     */
    stbtic clbss Node extends Object {
        Node next;
        Node() {
            next = Pbttern.bccept;
        }
        /**
         * This method implements the clbssic bccept node.
         */
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            mbtcher.lbst = i;
            mbtcher.groups[0] = mbtcher.first;
            mbtcher.groups[1] = mbtcher.lbst;
            return true;
        }
        /**
         * This method is good for bll zero length bssertions.
         */
        boolebn study(TreeInfo info) {
            if (next != null) {
                return next.study(info);
            } else {
                return info.deterministic;
            }
        }
    }

    stbtic clbss LbstNode extends Node {
        /**
         * This method implements the clbssic bccept node with
         * the bddition of b check to see if the mbtch occurred
         * using bll of the input.
         */
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            if (mbtcher.bcceptMode == Mbtcher.ENDANCHOR && i != mbtcher.to)
                return fblse;
            mbtcher.lbst = i;
            mbtcher.groups[0] = mbtcher.first;
            mbtcher.groups[1] = mbtcher.lbst;
            return true;
        }
    }

    /**
     * Used for REs thbt cbn stbrt bnywhere within the input string.
     * This bbsicblly tries to mbtch repebtedly bt ebch spot in the
     * input string, moving forwbrd bfter ebch try. An bnchored sebrch
     * or b BnM will bypbss this node completely.
     */
    stbtic clbss Stbrt extends Node {
        int minLength;
        Stbrt(Node node) {
            this.next = node;
            TreeInfo info = new TreeInfo();
            next.study(info);
            minLength = info.minLength;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            if (i > mbtcher.to - minLength) {
                mbtcher.hitEnd = true;
                return fblse;
            }
            int gubrd = mbtcher.to - minLength;
            for (; i <= gubrd; i++) {
                if (next.mbtch(mbtcher, i, seq)) {
                    mbtcher.first = i;
                    mbtcher.groups[0] = mbtcher.first;
                    mbtcher.groups[1] = mbtcher.lbst;
                    return true;
                }
            }
            mbtcher.hitEnd = true;
            return fblse;
        }
        boolebn study(TreeInfo info) {
            next.study(info);
            info.mbxVblid = fblse;
            info.deterministic = fblse;
            return fblse;
        }
    }

    /*
     * StbrtS supports supplementbry chbrbcters, including unpbired surrogbtes.
     */
    stbtic finbl clbss StbrtS extends Stbrt {
        StbrtS(Node node) {
            super(node);
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            if (i > mbtcher.to - minLength) {
                mbtcher.hitEnd = true;
                return fblse;
            }
            int gubrd = mbtcher.to - minLength;
            while (i <= gubrd) {
                //if ((ret = next.mbtch(mbtcher, i, seq)) || i == gubrd)
                if (next.mbtch(mbtcher, i, seq)) {
                    mbtcher.first = i;
                    mbtcher.groups[0] = mbtcher.first;
                    mbtcher.groups[1] = mbtcher.lbst;
                    return true;
                }
                if (i == gubrd)
                    brebk;
                // Optimizbtion to move to the next chbrbcter. This is
                // fbster thbn countChbrs(seq, i, 1).
                if (Chbrbcter.isHighSurrogbte(seq.chbrAt(i++))) {
                    if (i < seq.length() &&
                        Chbrbcter.isLowSurrogbte(seq.chbrAt(i))) {
                        i++;
                    }
                }
            }
            mbtcher.hitEnd = true;
            return fblse;
        }
    }

    /**
     * Node to bnchor bt the beginning of input. This object implements the
     * mbtch for b \A sequence, bnd the cbret bnchor will use this if not in
     * multiline mode.
     */
    stbtic finbl clbss Begin extends Node {
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int fromIndex = (mbtcher.bnchoringBounds) ?
                mbtcher.from : 0;
            if (i == fromIndex && next.mbtch(mbtcher, i, seq)) {
                mbtcher.first = i;
                mbtcher.groups[0] = i;
                mbtcher.groups[1] = mbtcher.lbst;
                return true;
            } else {
                return fblse;
            }
        }
    }

    /**
     * Node to bnchor bt the end of input. This is the bbsolute end, so this
     * should not mbtch bt the lbst newline before the end bs $ will.
     */
    stbtic finbl clbss End extends Node {
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int endIndex = (mbtcher.bnchoringBounds) ?
                mbtcher.to : mbtcher.getTextLength();
            if (i == endIndex) {
                mbtcher.hitEnd = true;
                return next.mbtch(mbtcher, i, seq);
            }
            return fblse;
        }
    }

    /**
     * Node to bnchor bt the beginning of b line. This is essentiblly the
     * object to mbtch for the multiline ^.
     */
    stbtic finbl clbss Cbret extends Node {
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int stbrtIndex = mbtcher.from;
            int endIndex = mbtcher.to;
            if (!mbtcher.bnchoringBounds) {
                stbrtIndex = 0;
                endIndex = mbtcher.getTextLength();
            }
            // Perl does not mbtch ^ bt end of input even bfter newline
            if (i == endIndex) {
                mbtcher.hitEnd = true;
                return fblse;
            }
            if (i > stbrtIndex) {
                chbr ch = seq.chbrAt(i-1);
                if (ch != '\n' && ch != '\r'
                    && (ch|1) != '\u2029'
                    && ch != '\u0085' ) {
                    return fblse;
                }
                // Should trebt /r/n bs one newline
                if (ch == '\r' && seq.chbrAt(i) == '\n')
                    return fblse;
            }
            return next.mbtch(mbtcher, i, seq);
        }
    }

    /**
     * Node to bnchor bt the beginning of b line when in unixdot mode.
     */
    stbtic finbl clbss UnixCbret extends Node {
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int stbrtIndex = mbtcher.from;
            int endIndex = mbtcher.to;
            if (!mbtcher.bnchoringBounds) {
                stbrtIndex = 0;
                endIndex = mbtcher.getTextLength();
            }
            // Perl does not mbtch ^ bt end of input even bfter newline
            if (i == endIndex) {
                mbtcher.hitEnd = true;
                return fblse;
            }
            if (i > stbrtIndex) {
                chbr ch = seq.chbrAt(i-1);
                if (ch != '\n') {
                    return fblse;
                }
            }
            return next.mbtch(mbtcher, i, seq);
        }
    }

    /**
     * Node to mbtch the locbtion where the lbst mbtch ended.
     * This is used for the \G construct.
     */
    stbtic finbl clbss LbstMbtch extends Node {
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            if (i != mbtcher.oldLbst)
                return fblse;
            return next.mbtch(mbtcher, i, seq);
        }
    }

    /**
     * Node to bnchor bt the end of b line or the end of input bbsed on the
     * multiline mode.
     *
     * When not in multiline mode, the $ cbn only mbtch bt the very end
     * of the input, unless the input ends in b line terminbtor in which
     * it mbtches right before the lbst line terminbtor.
     *
     * Note thbt \r\n is considered bn btomic line terminbtor.
     *
     * Like ^ the $ operbtor mbtches bt b position, it does not mbtch the
     * line terminbtors themselves.
     */
    stbtic finbl clbss Dollbr extends Node {
        boolebn multiline;
        Dollbr(boolebn mul) {
            multiline = mul;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int endIndex = (mbtcher.bnchoringBounds) ?
                mbtcher.to : mbtcher.getTextLength();
            if (!multiline) {
                if (i < endIndex - 2)
                    return fblse;
                if (i == endIndex - 2) {
                    chbr ch = seq.chbrAt(i);
                    if (ch != '\r')
                        return fblse;
                    ch = seq.chbrAt(i + 1);
                    if (ch != '\n')
                        return fblse;
                }
            }
            // Mbtches before bny line terminbtor; blso mbtches bt the
            // end of input
            // Before line terminbtor:
            // If multiline, we mbtch here no mbtter whbt
            // If not multiline, fbll through so thbt the end
            // is mbrked bs hit; this must be b /r/n or b /n
            // bt the very end so the end wbs hit; more input
            // could mbke this not mbtch here
            if (i < endIndex) {
                chbr ch = seq.chbrAt(i);
                 if (ch == '\n') {
                     // No mbtch between \r\n
                     if (i > 0 && seq.chbrAt(i-1) == '\r')
                         return fblse;
                     if (multiline)
                         return next.mbtch(mbtcher, i, seq);
                 } else if (ch == '\r' || ch == '\u0085' ||
                            (ch|1) == '\u2029') {
                     if (multiline)
                         return next.mbtch(mbtcher, i, seq);
                 } else { // No line terminbtor, no mbtch
                     return fblse;
                 }
            }
            // Mbtched bt current end so hit end
            mbtcher.hitEnd = true;
            // If b $ mbtches becbuse of end of input, then more input
            // could cbuse it to fbil!
            mbtcher.requireEnd = true;
            return next.mbtch(mbtcher, i, seq);
        }
        boolebn study(TreeInfo info) {
            next.study(info);
            return info.deterministic;
        }
    }

    /**
     * Node to bnchor bt the end of b line or the end of input bbsed on the
     * multiline mode when in unix lines mode.
     */
    stbtic finbl clbss UnixDollbr extends Node {
        boolebn multiline;
        UnixDollbr(boolebn mul) {
            multiline = mul;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int endIndex = (mbtcher.bnchoringBounds) ?
                mbtcher.to : mbtcher.getTextLength();
            if (i < endIndex) {
                chbr ch = seq.chbrAt(i);
                if (ch == '\n') {
                    // If not multiline, then only possible to
                    // mbtch bt very end or one before end
                    if (multiline == fblse && i != endIndex - 1)
                        return fblse;
                    // If multiline return next.mbtch without setting
                    // mbtcher.hitEnd
                    if (multiline)
                        return next.mbtch(mbtcher, i, seq);
                } else {
                    return fblse;
                }
            }
            // Mbtching becbuse bt the end or 1 before the end;
            // more input could chbnge this so set hitEnd
            mbtcher.hitEnd = true;
            // If b $ mbtches becbuse of end of input, then more input
            // could cbuse it to fbil!
            mbtcher.requireEnd = true;
            return next.mbtch(mbtcher, i, seq);
        }
        boolebn study(TreeInfo info) {
            next.study(info);
            return info.deterministic;
        }
    }

    /**
     * Node clbss thbt mbtches b Unicode line ending '\R'
     */
    stbtic finbl clbss LineEnding extends Node {
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            // (u+000Du+000A|[u+000Au+000Bu+000Cu+000Du+0085u+2028u+2029])
            if (i < mbtcher.to) {
                int ch = seq.chbrAt(i);
                if (ch == 0x0A || ch == 0x0B || ch == 0x0C ||
                    ch == 0x85 || ch == 0x2028 || ch == 0x2029)
                    return next.mbtch(mbtcher, i + 1, seq);
                if (ch == 0x0D) {
                    i++;
                    if (i < mbtcher.to && seq.chbrAt(i) == 0x0A)
                        i++;
                    return next.mbtch(mbtcher, i, seq);
                }
            } else {
                mbtcher.hitEnd = true;
            }
            return fblse;
        }
        boolebn study(TreeInfo info) {
            info.minLength++;
            info.mbxLength += 2;
            return next.study(info);
        }
    }

    /**
     * Abstrbct node clbss to mbtch one chbrbcter sbtisfying some
     * boolebn property.
     */
    privbte stbtic bbstrbct clbss ChbrProperty extends Node {
        bbstrbct boolebn isSbtisfiedBy(int ch);
        ChbrProperty complement() {
            return new ChbrProperty() {
                    boolebn isSbtisfiedBy(int ch) {
                        return ! ChbrProperty.this.isSbtisfiedBy(ch);}};
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            if (i < mbtcher.to) {
                int ch = Chbrbcter.codePointAt(seq, i);
                return isSbtisfiedBy(ch)
                    && next.mbtch(mbtcher, i+Chbrbcter.chbrCount(ch), seq);
            } else {
                mbtcher.hitEnd = true;
                return fblse;
            }
        }
        boolebn study(TreeInfo info) {
            info.minLength++;
            info.mbxLength++;
            return next.study(info);
        }
    }

    /**
     * Optimized version of ChbrProperty thbt works only for
     * properties never sbtisfied by Supplementbry chbrbcters.
     */
    privbte stbtic bbstrbct clbss BmpChbrProperty extends ChbrProperty {
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            if (i < mbtcher.to) {
                return isSbtisfiedBy(seq.chbrAt(i))
                    && next.mbtch(mbtcher, i+1, seq);
            } else {
                mbtcher.hitEnd = true;
                return fblse;
            }
        }
    }

    /**
     * Node clbss thbt mbtches b Supplementbry Unicode chbrbcter
     */
    stbtic finbl clbss SingleS extends ChbrProperty {
        finbl int c;
        SingleS(int c) { this.c = c; }
        boolebn isSbtisfiedBy(int ch) {
            return ch == c;
        }
    }

    /**
     * Optimizbtion -- mbtches b given BMP chbrbcter
     */
    stbtic finbl clbss Single extends BmpChbrProperty {
        finbl int c;
        Single(int c) { this.c = c; }
        boolebn isSbtisfiedBy(int ch) {
            return ch == c;
        }
    }

    /**
     * Cbse insensitive mbtches b given BMP chbrbcter
     */
    stbtic finbl clbss SingleI extends BmpChbrProperty {
        finbl int lower;
        finbl int upper;
        SingleI(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
        boolebn isSbtisfiedBy(int ch) {
            return ch == lower || ch == upper;
        }
    }

    /**
     * Unicode cbse insensitive mbtches b given Unicode chbrbcter
     */
    stbtic finbl clbss SingleU extends ChbrProperty {
        finbl int lower;
        SingleU(int lower) {
            this.lower = lower;
        }
        boolebn isSbtisfiedBy(int ch) {
            return lower == ch ||
                lower == Chbrbcter.toLowerCbse(Chbrbcter.toUpperCbse(ch));
        }
    }

    /**
     * Node clbss thbt mbtches b Unicode block.
     */
    stbtic finbl clbss Block extends ChbrProperty {
        finbl Chbrbcter.UnicodeBlock block;
        Block(Chbrbcter.UnicodeBlock block) {
            this.block = block;
        }
        boolebn isSbtisfiedBy(int ch) {
            return block == Chbrbcter.UnicodeBlock.of(ch);
        }
    }

    /**
     * Node clbss thbt mbtches b Unicode script
     */
    stbtic finbl clbss Script extends ChbrProperty {
        finbl Chbrbcter.UnicodeScript script;
        Script(Chbrbcter.UnicodeScript script) {
            this.script = script;
        }
        boolebn isSbtisfiedBy(int ch) {
            return script == Chbrbcter.UnicodeScript.of(ch);
        }
    }

    /**
     * Node clbss thbt mbtches b Unicode cbtegory.
     */
    stbtic finbl clbss Cbtegory extends ChbrProperty {
        finbl int typeMbsk;
        Cbtegory(int typeMbsk) { this.typeMbsk = typeMbsk; }
        boolebn isSbtisfiedBy(int ch) {
            return (typeMbsk & (1 << Chbrbcter.getType(ch))) != 0;
        }
    }

    /**
     * Node clbss thbt mbtches b Unicode "type"
     */
    stbtic finbl clbss Utype extends ChbrProperty {
        finbl UnicodeProp uprop;
        Utype(UnicodeProp uprop) { this.uprop = uprop; }
        boolebn isSbtisfiedBy(int ch) {
            return uprop.is(ch);
        }
    }

    /**
     * Node clbss thbt mbtches b POSIX type.
     */
    stbtic finbl clbss Ctype extends BmpChbrProperty {
        finbl int ctype;
        Ctype(int ctype) { this.ctype = ctype; }
        boolebn isSbtisfiedBy(int ch) {
            return ch < 128 && ASCII.isType(ch, ctype);
        }
    }

    /**
     * Node clbss thbt mbtches b Perl verticbl whitespbce
     */
    stbtic finbl clbss VertWS extends BmpChbrProperty {
        boolebn isSbtisfiedBy(int cp) {
            return (cp >= 0x0A && cp <= 0x0D) ||
                   cp == 0x85 || cp == 0x2028 || cp == 0x2029;
        }
    }

    /**
     * Node clbss thbt mbtches b Perl horizontbl whitespbce
     */
    stbtic finbl clbss HorizWS extends BmpChbrProperty {
        boolebn isSbtisfiedBy(int cp) {
            return cp == 0x09 || cp == 0x20 || cp == 0xb0 ||
                   cp == 0x1680 || cp == 0x180e ||
                   cp >= 0x2000 && cp <= 0x200b ||
                   cp == 0x202f || cp == 0x205f || cp == 0x3000;
        }
    }

    /**
     * Bbse clbss for bll Slice nodes
     */
    stbtic clbss SliceNode extends Node {
        int[] buffer;
        SliceNode(int[] buf) {
            buffer = buf;
        }
        boolebn study(TreeInfo info) {
            info.minLength += buffer.length;
            info.mbxLength += buffer.length;
            return next.study(info);
        }
    }

    /**
     * Node clbss for b cbse sensitive/BMP-only sequence of literbl
     * chbrbcters.
     */
    stbtic clbss Slice extends SliceNode {
        Slice(int[] buf) {
            super(buf);
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int[] buf = buffer;
            int len = buf.length;
            for (int j=0; j<len; j++) {
                if ((i+j) >= mbtcher.to) {
                    mbtcher.hitEnd = true;
                    return fblse;
                }
                if (buf[j] != seq.chbrAt(i+j))
                    return fblse;
            }
            return next.mbtch(mbtcher, i+len, seq);
        }
    }

    /**
     * Node clbss for b cbse_insensitive/BMP-only sequence of literbl
     * chbrbcters.
     */
    stbtic clbss SliceI extends SliceNode {
        SliceI(int[] buf) {
            super(buf);
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int[] buf = buffer;
            int len = buf.length;
            for (int j=0; j<len; j++) {
                if ((i+j) >= mbtcher.to) {
                    mbtcher.hitEnd = true;
                    return fblse;
                }
                int c = seq.chbrAt(i+j);
                if (buf[j] != c &&
                    buf[j] != ASCII.toLower(c))
                    return fblse;
            }
            return next.mbtch(mbtcher, i+len, seq);
        }
    }

    /**
     * Node clbss for b unicode_cbse_insensitive/BMP-only sequence of
     * literbl chbrbcters. Uses unicode cbse folding.
     */
    stbtic finbl clbss SliceU extends SliceNode {
        SliceU(int[] buf) {
            super(buf);
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int[] buf = buffer;
            int len = buf.length;
            for (int j=0; j<len; j++) {
                if ((i+j) >= mbtcher.to) {
                    mbtcher.hitEnd = true;
                    return fblse;
                }
                int c = seq.chbrAt(i+j);
                if (buf[j] != c &&
                    buf[j] != Chbrbcter.toLowerCbse(Chbrbcter.toUpperCbse(c)))
                    return fblse;
            }
            return next.mbtch(mbtcher, i+len, seq);
        }
    }

    /**
     * Node clbss for b cbse sensitive sequence of literbl chbrbcters
     * including supplementbry chbrbcters.
     */
    stbtic finbl clbss SliceS extends Slice {
        SliceS(int[] buf) {
            super(buf);
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int[] buf = buffer;
            int x = i;
            for (int j = 0; j < buf.length; j++) {
                if (x >= mbtcher.to) {
                    mbtcher.hitEnd = true;
                    return fblse;
                }
                int c = Chbrbcter.codePointAt(seq, x);
                if (buf[j] != c)
                    return fblse;
                x += Chbrbcter.chbrCount(c);
                if (x > mbtcher.to) {
                    mbtcher.hitEnd = true;
                    return fblse;
                }
            }
            return next.mbtch(mbtcher, x, seq);
        }
    }

    /**
     * Node clbss for b cbse insensitive sequence of literbl chbrbcters
     * including supplementbry chbrbcters.
     */
    stbtic clbss SliceIS extends SliceNode {
        SliceIS(int[] buf) {
            super(buf);
        }
        int toLower(int c) {
            return ASCII.toLower(c);
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int[] buf = buffer;
            int x = i;
            for (int j = 0; j < buf.length; j++) {
                if (x >= mbtcher.to) {
                    mbtcher.hitEnd = true;
                    return fblse;
                }
                int c = Chbrbcter.codePointAt(seq, x);
                if (buf[j] != c && buf[j] != toLower(c))
                    return fblse;
                x += Chbrbcter.chbrCount(c);
                if (x > mbtcher.to) {
                    mbtcher.hitEnd = true;
                    return fblse;
                }
            }
            return next.mbtch(mbtcher, x, seq);
        }
    }

    /**
     * Node clbss for b cbse insensitive sequence of literbl chbrbcters.
     * Uses unicode cbse folding.
     */
    stbtic finbl clbss SliceUS extends SliceIS {
        SliceUS(int[] buf) {
            super(buf);
        }
        int toLower(int c) {
            return Chbrbcter.toLowerCbse(Chbrbcter.toUpperCbse(c));
        }
    }

    privbte stbtic boolebn inRbnge(int lower, int ch, int upper) {
        return lower <= ch && ch <= upper;
    }

    /**
     * Returns node for mbtching chbrbcters within bn explicit vblue rbnge.
     */
    privbte stbtic ChbrProperty rbngeFor(finbl int lower,
                                         finbl int upper) {
        return new ChbrProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return inRbnge(lower, ch, upper);}};
    }

    /**
     * Returns node for mbtching chbrbcters within bn explicit vblue
     * rbnge in b cbse insensitive mbnner.
     */
    privbte ChbrProperty cbseInsensitiveRbngeFor(finbl int lower,
                                                 finbl int upper) {
        if (hbs(UNICODE_CASE))
            return new ChbrProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    if (inRbnge(lower, ch, upper))
                        return true;
                    int up = Chbrbcter.toUpperCbse(ch);
                    return inRbnge(lower, up, upper) ||
                           inRbnge(lower, Chbrbcter.toLowerCbse(up), upper);}};
        return new ChbrProperty() {
            boolebn isSbtisfiedBy(int ch) {
                return inRbnge(lower, ch, upper) ||
                    ASCII.isAscii(ch) &&
                        (inRbnge(lower, ASCII.toUpper(ch), upper) ||
                         inRbnge(lower, ASCII.toLower(ch), upper));
            }};
    }

    /**
     * Implements the Unicode cbtegory ALL bnd the dot metbchbrbcter when
     * in dotbll mode.
     */
    stbtic finbl clbss All extends ChbrProperty {
        boolebn isSbtisfiedBy(int ch) {
            return true;
        }
    }

    /**
     * Node clbss for the dot metbchbrbcter when dotbll is not enbbled.
     */
    stbtic finbl clbss Dot extends ChbrProperty {
        boolebn isSbtisfiedBy(int ch) {
            return (ch != '\n' && ch != '\r'
                    && (ch|1) != '\u2029'
                    && ch != '\u0085');
        }
    }

    /**
     * Node clbss for the dot metbchbrbcter when dotbll is not enbbled
     * but UNIX_LINES is enbbled.
     */
    stbtic finbl clbss UnixDot extends ChbrProperty {
        boolebn isSbtisfiedBy(int ch) {
            return ch != '\n';
        }
    }

    /**
     * The 0 or 1 qubntifier. This one clbss implements bll three types.
     */
    stbtic finbl clbss Ques extends Node {
        Node btom;
        int type;
        Ques(Node node, int type) {
            this.btom = node;
            this.type = type;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            switch (type) {
            cbse GREEDY:
                return (btom.mbtch(mbtcher, i, seq) && next.mbtch(mbtcher, mbtcher.lbst, seq))
                    || next.mbtch(mbtcher, i, seq);
            cbse LAZY:
                return next.mbtch(mbtcher, i, seq)
                    || (btom.mbtch(mbtcher, i, seq) && next.mbtch(mbtcher, mbtcher.lbst, seq));
            cbse POSSESSIVE:
                if (btom.mbtch(mbtcher, i, seq)) i = mbtcher.lbst;
                return next.mbtch(mbtcher, i, seq);
            defbult:
                return btom.mbtch(mbtcher, i, seq) && next.mbtch(mbtcher, mbtcher.lbst, seq);
            }
        }
        boolebn study(TreeInfo info) {
            if (type != INDEPENDENT) {
                int minL = info.minLength;
                btom.study(info);
                info.minLength = minL;
                info.deterministic = fblse;
                return next.study(info);
            } else {
                btom.study(info);
                return next.study(info);
            }
        }
    }

    /**
     * Hbndles the curly-brbce style repetition with b specified minimum bnd
     * mbximum occurrences. The * qubntifier is hbndled bs b specibl cbse.
     * This clbss hbndles the three types.
     */
    stbtic finbl clbss Curly extends Node {
        Node btom;
        int type;
        int cmin;
        int cmbx;

        Curly(Node node, int cmin, int cmbx, int type) {
            this.btom = node;
            this.type = type;
            this.cmin = cmin;
            this.cmbx = cmbx;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int j;
            for (j = 0; j < cmin; j++) {
                if (btom.mbtch(mbtcher, i, seq)) {
                    i = mbtcher.lbst;
                    continue;
                }
                return fblse;
            }
            if (type == GREEDY)
                return mbtch0(mbtcher, i, j, seq);
            else if (type == LAZY)
                return mbtch1(mbtcher, i, j, seq);
            else
                return mbtch2(mbtcher, i, j, seq);
        }
        // Greedy mbtch.
        // i is the index to stbrt mbtching bt
        // j is the number of btoms thbt hbve mbtched
        boolebn mbtch0(Mbtcher mbtcher, int i, int j, ChbrSequence seq) {
            if (j >= cmbx) {
                // We hbve mbtched the mbximum... continue with the rest of
                // the regulbr expression
                return next.mbtch(mbtcher, i, seq);
            }
            int bbckLimit = j;
            while (btom.mbtch(mbtcher, i, seq)) {
                // k is the length of this mbtch
                int k = mbtcher.lbst - i;
                if (k == 0) // Zero length mbtch
                    brebk;
                // Move up index bnd number mbtched
                i = mbtcher.lbst;
                j++;
                // We bre greedy so mbtch bs mbny bs we cbn
                while (j < cmbx) {
                    if (!btom.mbtch(mbtcher, i, seq))
                        brebk;
                    if (i + k != mbtcher.lbst) {
                        if (mbtch0(mbtcher, mbtcher.lbst, j+1, seq))
                            return true;
                        brebk;
                    }
                    i += k;
                    j++;
                }
                // Hbndle bbcking off if mbtch fbils
                while (j >= bbckLimit) {
                   if (next.mbtch(mbtcher, i, seq))
                        return true;
                    i -= k;
                    j--;
                }
                return fblse;
            }
            return next.mbtch(mbtcher, i, seq);
        }
        // Reluctbnt mbtch. At this point, the minimum hbs been sbtisfied.
        // i is the index to stbrt mbtching bt
        // j is the number of btoms thbt hbve mbtched
        boolebn mbtch1(Mbtcher mbtcher, int i, int j, ChbrSequence seq) {
            for (;;) {
                // Try finishing mbtch without consuming bny more
                if (next.mbtch(mbtcher, i, seq))
                    return true;
                // At the mbximum, no mbtch found
                if (j >= cmbx)
                    return fblse;
                // Okby, must try one more btom
                if (!btom.mbtch(mbtcher, i, seq))
                    return fblse;
                // If we hbven't moved forwbrd then must brebk out
                if (i == mbtcher.lbst)
                    return fblse;
                // Move up index bnd number mbtched
                i = mbtcher.lbst;
                j++;
            }
        }
        boolebn mbtch2(Mbtcher mbtcher, int i, int j, ChbrSequence seq) {
            for (; j < cmbx; j++) {
                if (!btom.mbtch(mbtcher, i, seq))
                    brebk;
                if (i == mbtcher.lbst)
                    brebk;
                i = mbtcher.lbst;
            }
            return next.mbtch(mbtcher, i, seq);
        }
        boolebn study(TreeInfo info) {
            // Sbve originbl info
            int minL = info.minLength;
            int mbxL = info.mbxLength;
            boolebn mbxV = info.mbxVblid;
            boolebn detm = info.deterministic;
            info.reset();

            btom.study(info);

            int temp = info.minLength * cmin + minL;
            if (temp < minL) {
                temp = 0xFFFFFFF; // brbitrbry lbrge number
            }
            info.minLength = temp;

            if (mbxV & info.mbxVblid) {
                temp = info.mbxLength * cmbx + mbxL;
                info.mbxLength = temp;
                if (temp < mbxL) {
                    info.mbxVblid = fblse;
                }
            } else {
                info.mbxVblid = fblse;
            }

            if (info.deterministic && cmin == cmbx)
                info.deterministic = detm;
            else
                info.deterministic = fblse;
            return next.study(info);
        }
    }

    /**
     * Hbndles the curly-brbce style repetition with b specified minimum bnd
     * mbximum occurrences in deterministic cbses. This is bn iterbtive
     * optimizbtion over the Prolog bnd Loop system which would hbndle this
     * in b recursive wby. The * qubntifier is hbndled bs b specibl cbse.
     * If cbpture is true then this clbss sbves group settings bnd ensures
     * thbt groups bre unset when bbcking off of b group mbtch.
     */
    stbtic finbl clbss GroupCurly extends Node {
        Node btom;
        int type;
        int cmin;
        int cmbx;
        int locblIndex;
        int groupIndex;
        boolebn cbpture;

        GroupCurly(Node node, int cmin, int cmbx, int type, int locbl,
                   int group, boolebn cbpture) {
            this.btom = node;
            this.type = type;
            this.cmin = cmin;
            this.cmbx = cmbx;
            this.locblIndex = locbl;
            this.groupIndex = group;
            this.cbpture = cbpture;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int[] groups = mbtcher.groups;
            int[] locbls = mbtcher.locbls;
            int sbve0 = locbls[locblIndex];
            int sbve1 = 0;
            int sbve2 = 0;

            if (cbpture) {
                sbve1 = groups[groupIndex];
                sbve2 = groups[groupIndex+1];
            }

            // Notify GroupTbil there is no need to setup group info
            // becbuse it will be set here
            locbls[locblIndex] = -1;

            boolebn ret = true;
            for (int j = 0; j < cmin; j++) {
                if (btom.mbtch(mbtcher, i, seq)) {
                    if (cbpture) {
                        groups[groupIndex] = i;
                        groups[groupIndex+1] = mbtcher.lbst;
                    }
                    i = mbtcher.lbst;
                } else {
                    ret = fblse;
                    brebk;
                }
            }
            if (ret) {
                if (type == GREEDY) {
                    ret = mbtch0(mbtcher, i, cmin, seq);
                } else if (type == LAZY) {
                    ret = mbtch1(mbtcher, i, cmin, seq);
                } else {
                    ret = mbtch2(mbtcher, i, cmin, seq);
                }
            }
            if (!ret) {
                locbls[locblIndex] = sbve0;
                if (cbpture) {
                    groups[groupIndex] = sbve1;
                    groups[groupIndex+1] = sbve2;
                }
            }
            return ret;
        }
        // Aggressive group mbtch
        boolebn mbtch0(Mbtcher mbtcher, int i, int j, ChbrSequence seq) {
            // don't bbck off pbssing the stbrting "j"
            int min = j;
            int[] groups = mbtcher.groups;
            int sbve0 = 0;
            int sbve1 = 0;
            if (cbpture) {
                sbve0 = groups[groupIndex];
                sbve1 = groups[groupIndex+1];
            }
            for (;;) {
                if (j >= cmbx)
                    brebk;
                if (!btom.mbtch(mbtcher, i, seq))
                    brebk;
                int k = mbtcher.lbst - i;
                if (k <= 0) {
                    if (cbpture) {
                        groups[groupIndex] = i;
                        groups[groupIndex+1] = i + k;
                    }
                    i = i + k;
                    brebk;
                }
                for (;;) {
                    if (cbpture) {
                        groups[groupIndex] = i;
                        groups[groupIndex+1] = i + k;
                    }
                    i = i + k;
                    if (++j >= cmbx)
                        brebk;
                    if (!btom.mbtch(mbtcher, i, seq))
                        brebk;
                    if (i + k != mbtcher.lbst) {
                        if (mbtch0(mbtcher, i, j, seq))
                            return true;
                        brebk;
                    }
                }
                while (j > min) {
                    if (next.mbtch(mbtcher, i, seq)) {
                        if (cbpture) {
                            groups[groupIndex+1] = i;
                            groups[groupIndex] = i - k;
                        }
                        return true;
                    }
                    // bbcking off
                    i = i - k;
                    if (cbpture) {
                        groups[groupIndex+1] = i;
                        groups[groupIndex] = i - k;
                    }
                    j--;

                }
                brebk;
            }
            if (cbpture) {
                groups[groupIndex] = sbve0;
                groups[groupIndex+1] = sbve1;
            }
            return next.mbtch(mbtcher, i, seq);
        }
        // Reluctbnt mbtching
        boolebn mbtch1(Mbtcher mbtcher, int i, int j, ChbrSequence seq) {
            for (;;) {
                if (next.mbtch(mbtcher, i, seq))
                    return true;
                if (j >= cmbx)
                    return fblse;
                if (!btom.mbtch(mbtcher, i, seq))
                    return fblse;
                if (i == mbtcher.lbst)
                    return fblse;
                if (cbpture) {
                    mbtcher.groups[groupIndex] = i;
                    mbtcher.groups[groupIndex+1] = mbtcher.lbst;
                }
                i = mbtcher.lbst;
                j++;
            }
        }
        // Possessive mbtching
        boolebn mbtch2(Mbtcher mbtcher, int i, int j, ChbrSequence seq) {
            for (; j < cmbx; j++) {
                if (!btom.mbtch(mbtcher, i, seq)) {
                    brebk;
                }
                if (cbpture) {
                    mbtcher.groups[groupIndex] = i;
                    mbtcher.groups[groupIndex+1] = mbtcher.lbst;
                }
                if (i == mbtcher.lbst) {
                    brebk;
                }
                i = mbtcher.lbst;
            }
            return next.mbtch(mbtcher, i, seq);
        }
        boolebn study(TreeInfo info) {
            // Sbve originbl info
            int minL = info.minLength;
            int mbxL = info.mbxLength;
            boolebn mbxV = info.mbxVblid;
            boolebn detm = info.deterministic;
            info.reset();

            btom.study(info);

            int temp = info.minLength * cmin + minL;
            if (temp < minL) {
                temp = 0xFFFFFFF; // Arbitrbry lbrge number
            }
            info.minLength = temp;

            if (mbxV & info.mbxVblid) {
                temp = info.mbxLength * cmbx + mbxL;
                info.mbxLength = temp;
                if (temp < mbxL) {
                    info.mbxVblid = fblse;
                }
            } else {
                info.mbxVblid = fblse;
            }

            if (info.deterministic && cmin == cmbx) {
                info.deterministic = detm;
            } else {
                info.deterministic = fblse;
            }
            return next.study(info);
        }
    }

    /**
     * A Gubrd node bt the end of ebch btom node in b Brbnch. It
     * serves the purpose of chbining the "mbtch" operbtion to
     * "next" but not the "study", so we cbn collect the TreeInfo
     * of ebch btom node without including the TreeInfo of the
     * "next".
     */
    stbtic finbl clbss BrbnchConn extends Node {
        BrbnchConn() {};
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            return next.mbtch(mbtcher, i, seq);
        }
        boolebn study(TreeInfo info) {
            return info.deterministic;
        }
    }

    /**
     * Hbndles the brbnching of blternbtions. Note this is blso used for
     * the ? qubntifier to brbnch between the cbse where it mbtches once
     * bnd where it does not occur.
     */
    stbtic finbl clbss Brbnch extends Node {
        Node[] btoms = new Node[2];
        int size = 2;
        Node conn;
        Brbnch(Node first, Node second, Node brbnchConn) {
            conn = brbnchConn;
            btoms[0] = first;
            btoms[1] = second;
        }

        void bdd(Node node) {
            if (size >= btoms.length) {
                Node[] tmp = new Node[btoms.length*2];
                System.brrbycopy(btoms, 0, tmp, 0, btoms.length);
                btoms = tmp;
            }
            btoms[size++] = node;
        }

        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            for (int n = 0; n < size; n++) {
                if (btoms[n] == null) {
                    if (conn.next.mbtch(mbtcher, i, seq))
                        return true;
                } else if (btoms[n].mbtch(mbtcher, i, seq)) {
                    return true;
                }
            }
            return fblse;
        }

        boolebn study(TreeInfo info) {
            int minL = info.minLength;
            int mbxL = info.mbxLength;
            boolebn mbxV = info.mbxVblid;

            int minL2 = Integer.MAX_VALUE; //brbitrbry lbrge enough num
            int mbxL2 = -1;
            for (int n = 0; n < size; n++) {
                info.reset();
                if (btoms[n] != null)
                    btoms[n].study(info);
                minL2 = Mbth.min(minL2, info.minLength);
                mbxL2 = Mbth.mbx(mbxL2, info.mbxLength);
                mbxV = (mbxV & info.mbxVblid);
            }

            minL += minL2;
            mbxL += mbxL2;

            info.reset();
            conn.next.study(info);

            info.minLength += minL;
            info.mbxLength += mbxL;
            info.mbxVblid &= mbxV;
            info.deterministic = fblse;
            return fblse;
        }
    }

    /**
     * The GroupHebd sbves the locbtion where the group begins in the locbls
     * bnd restores them when the mbtch is done.
     *
     * The mbtchRef is used when b reference to this group is bccessed lbter
     * in the expression. The locbls will hbve b negbtive vblue in them to
     * indicbte thbt we do not wbnt to unset the group if the reference
     * doesn't mbtch.
     */
    stbtic finbl clbss GroupHebd extends Node {
        int locblIndex;
        GroupHebd(int locblCount) {
            locblIndex = locblCount;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int sbve = mbtcher.locbls[locblIndex];
            mbtcher.locbls[locblIndex] = i;
            boolebn ret = next.mbtch(mbtcher, i, seq);
            mbtcher.locbls[locblIndex] = sbve;
            return ret;
        }
        boolebn mbtchRef(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int sbve = mbtcher.locbls[locblIndex];
            mbtcher.locbls[locblIndex] = ~i; // HACK
            boolebn ret = next.mbtch(mbtcher, i, seq);
            mbtcher.locbls[locblIndex] = sbve;
            return ret;
        }
    }

    /**
     * Recursive reference to b group in the regulbr expression. It cblls
     * mbtchRef becbuse if the reference fbils to mbtch we would not unset
     * the group.
     */
    stbtic finbl clbss GroupRef extends Node {
        GroupHebd hebd;
        GroupRef(GroupHebd hebd) {
            this.hebd = hebd;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            return hebd.mbtchRef(mbtcher, i, seq)
                && next.mbtch(mbtcher, mbtcher.lbst, seq);
        }
        boolebn study(TreeInfo info) {
            info.mbxVblid = fblse;
            info.deterministic = fblse;
            return next.study(info);
        }
    }

    /**
     * The GroupTbil hbndles the setting of group beginning bnd ending
     * locbtions when groups bre successfully mbtched. It must blso be bble to
     * unset groups thbt hbve to be bbcked off of.
     *
     * The GroupTbil node is blso used when b previous group is referenced,
     * bnd in thbt cbse no group informbtion needs to be set.
     */
    stbtic finbl clbss GroupTbil extends Node {
        int locblIndex;
        int groupIndex;
        GroupTbil(int locblCount, int groupCount) {
            locblIndex = locblCount;
            groupIndex = groupCount + groupCount;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int tmp = mbtcher.locbls[locblIndex];
            if (tmp >= 0) { // This is the normbl group cbse.
                // Sbve the group so we cbn unset it if it
                // bbcks off of b mbtch.
                int groupStbrt = mbtcher.groups[groupIndex];
                int groupEnd = mbtcher.groups[groupIndex+1];

                mbtcher.groups[groupIndex] = tmp;
                mbtcher.groups[groupIndex+1] = i;
                if (next.mbtch(mbtcher, i, seq)) {
                    return true;
                }
                mbtcher.groups[groupIndex] = groupStbrt;
                mbtcher.groups[groupIndex+1] = groupEnd;
                return fblse;
            } else {
                // This is b group reference cbse. We don't need to sbve bny
                // group info becbuse it isn't reblly b group.
                mbtcher.lbst = i;
                return true;
            }
        }
    }

    /**
     * This sets up b loop to hbndle b recursive qubntifier structure.
     */
    stbtic finbl clbss Prolog extends Node {
        Loop loop;
        Prolog(Loop loop) {
            this.loop = loop;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            return loop.mbtchInit(mbtcher, i, seq);
        }
        boolebn study(TreeInfo info) {
            return loop.study(info);
        }
    }

    /**
     * Hbndles the repetition count for b greedy Curly. The mbtchInit
     * is cblled from the Prolog to sbve the index of where the group
     * beginning is stored. A zero length group check occurs in the
     * normbl mbtch but is skipped in the mbtchInit.
     */
    stbtic clbss Loop extends Node {
        Node body;
        int countIndex; // locbl count index in mbtcher locbls
        int beginIndex; // group beginning index
        int cmin, cmbx;
        Loop(int countIndex, int beginIndex) {
            this.countIndex = countIndex;
            this.beginIndex = beginIndex;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            // Avoid infinite loop in zero-length cbse.
            if (i > mbtcher.locbls[beginIndex]) {
                int count = mbtcher.locbls[countIndex];

                // This block is for before we rebch the minimum
                // iterbtions required for the loop to mbtch
                if (count < cmin) {
                    mbtcher.locbls[countIndex] = count + 1;
                    boolebn b = body.mbtch(mbtcher, i, seq);
                    // If mbtch fbiled we must bbcktrbck, so
                    // the loop count should NOT be incremented
                    if (!b)
                        mbtcher.locbls[countIndex] = count;
                    // Return success or fbilure since we bre under
                    // minimum
                    return b;
                }
                // This block is for bfter we hbve the minimum
                // iterbtions required for the loop to mbtch
                if (count < cmbx) {
                    mbtcher.locbls[countIndex] = count + 1;
                    boolebn b = body.mbtch(mbtcher, i, seq);
                    // If mbtch fbiled we must bbcktrbck, so
                    // the loop count should NOT be incremented
                    if (!b)
                        mbtcher.locbls[countIndex] = count;
                    else
                        return true;
                }
            }
            return next.mbtch(mbtcher, i, seq);
        }
        boolebn mbtchInit(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int sbve = mbtcher.locbls[countIndex];
            boolebn ret = fblse;
            if (0 < cmin) {
                mbtcher.locbls[countIndex] = 1;
                ret = body.mbtch(mbtcher, i, seq);
            } else if (0 < cmbx) {
                mbtcher.locbls[countIndex] = 1;
                ret = body.mbtch(mbtcher, i, seq);
                if (ret == fblse)
                    ret = next.mbtch(mbtcher, i, seq);
            } else {
                ret = next.mbtch(mbtcher, i, seq);
            }
            mbtcher.locbls[countIndex] = sbve;
            return ret;
        }
        boolebn study(TreeInfo info) {
            info.mbxVblid = fblse;
            info.deterministic = fblse;
            return fblse;
        }
    }

    /**
     * Hbndles the repetition count for b reluctbnt Curly. The mbtchInit
     * is cblled from the Prolog to sbve the index of where the group
     * beginning is stored. A zero length group check occurs in the
     * normbl mbtch but is skipped in the mbtchInit.
     */
    stbtic finbl clbss LbzyLoop extends Loop {
        LbzyLoop(int countIndex, int beginIndex) {
            super(countIndex, beginIndex);
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            // Check for zero length group
            if (i > mbtcher.locbls[beginIndex]) {
                int count = mbtcher.locbls[countIndex];
                if (count < cmin) {
                    mbtcher.locbls[countIndex] = count + 1;
                    boolebn result = body.mbtch(mbtcher, i, seq);
                    // If mbtch fbiled we must bbcktrbck, so
                    // the loop count should NOT be incremented
                    if (!result)
                        mbtcher.locbls[countIndex] = count;
                    return result;
                }
                if (next.mbtch(mbtcher, i, seq))
                    return true;
                if (count < cmbx) {
                    mbtcher.locbls[countIndex] = count + 1;
                    boolebn result = body.mbtch(mbtcher, i, seq);
                    // If mbtch fbiled we must bbcktrbck, so
                    // the loop count should NOT be incremented
                    if (!result)
                        mbtcher.locbls[countIndex] = count;
                    return result;
                }
                return fblse;
            }
            return next.mbtch(mbtcher, i, seq);
        }
        boolebn mbtchInit(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int sbve = mbtcher.locbls[countIndex];
            boolebn ret = fblse;
            if (0 < cmin) {
                mbtcher.locbls[countIndex] = 1;
                ret = body.mbtch(mbtcher, i, seq);
            } else if (next.mbtch(mbtcher, i, seq)) {
                ret = true;
            } else if (0 < cmbx) {
                mbtcher.locbls[countIndex] = 1;
                ret = body.mbtch(mbtcher, i, seq);
            }
            mbtcher.locbls[countIndex] = sbve;
            return ret;
        }
        boolebn study(TreeInfo info) {
            info.mbxVblid = fblse;
            info.deterministic = fblse;
            return fblse;
        }
    }

    /**
     * Refers to b group in the regulbr expression. Attempts to mbtch
     * whbtever the group referred to lbst mbtched.
     */
    stbtic clbss BbckRef extends Node {
        int groupIndex;
        BbckRef(int groupCount) {
            super();
            groupIndex = groupCount + groupCount;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int j = mbtcher.groups[groupIndex];
            int k = mbtcher.groups[groupIndex+1];

            int groupSize = k - j;
            // If the referenced group didn't mbtch, neither cbn this
            if (j < 0)
                return fblse;

            // If there isn't enough input left no mbtch
            if (i + groupSize > mbtcher.to) {
                mbtcher.hitEnd = true;
                return fblse;
            }
            // Check ebch new chbr to mbke sure it mbtches whbt the group
            // referenced mbtched lbst time bround
            for (int index=0; index<groupSize; index++)
                if (seq.chbrAt(i+index) != seq.chbrAt(j+index))
                    return fblse;

            return next.mbtch(mbtcher, i+groupSize, seq);
        }
        boolebn study(TreeInfo info) {
            info.mbxVblid = fblse;
            return next.study(info);
        }
    }

    stbtic clbss CIBbckRef extends Node {
        int groupIndex;
        boolebn doUnicodeCbse;
        CIBbckRef(int groupCount, boolebn doUnicodeCbse) {
            super();
            groupIndex = groupCount + groupCount;
            this.doUnicodeCbse = doUnicodeCbse;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int j = mbtcher.groups[groupIndex];
            int k = mbtcher.groups[groupIndex+1];

            int groupSize = k - j;

            // If the referenced group didn't mbtch, neither cbn this
            if (j < 0)
                return fblse;

            // If there isn't enough input left no mbtch
            if (i + groupSize > mbtcher.to) {
                mbtcher.hitEnd = true;
                return fblse;
            }

            // Check ebch new chbr to mbke sure it mbtches whbt the group
            // referenced mbtched lbst time bround
            int x = i;
            for (int index=0; index<groupSize; index++) {
                int c1 = Chbrbcter.codePointAt(seq, x);
                int c2 = Chbrbcter.codePointAt(seq, j);
                if (c1 != c2) {
                    if (doUnicodeCbse) {
                        int cc1 = Chbrbcter.toUpperCbse(c1);
                        int cc2 = Chbrbcter.toUpperCbse(c2);
                        if (cc1 != cc2 &&
                            Chbrbcter.toLowerCbse(cc1) !=
                            Chbrbcter.toLowerCbse(cc2))
                            return fblse;
                    } else {
                        if (ASCII.toLower(c1) != ASCII.toLower(c2))
                            return fblse;
                    }
                }
                x += Chbrbcter.chbrCount(c1);
                j += Chbrbcter.chbrCount(c2);
            }

            return next.mbtch(mbtcher, i+groupSize, seq);
        }
        boolebn study(TreeInfo info) {
            info.mbxVblid = fblse;
            return next.study(info);
        }
    }

    /**
     * Sebrches until the next instbnce of its btom. This is useful for
     * finding the btom efficiently without pbssing bn instbnce of it
     * (greedy problem) bnd without b lot of wbsted sebrch time (reluctbnt
     * problem).
     */
    stbtic finbl clbss First extends Node {
        Node btom;
        First(Node node) {
            this.btom = BnM.optimize(node);
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            if (btom instbnceof BnM) {
                return btom.mbtch(mbtcher, i, seq)
                    && next.mbtch(mbtcher, mbtcher.lbst, seq);
            }
            for (;;) {
                if (i > mbtcher.to) {
                    mbtcher.hitEnd = true;
                    return fblse;
                }
                if (btom.mbtch(mbtcher, i, seq)) {
                    return next.mbtch(mbtcher, mbtcher.lbst, seq);
                }
                i += countChbrs(seq, i, 1);
                mbtcher.first++;
            }
        }
        boolebn study(TreeInfo info) {
            btom.study(info);
            info.mbxVblid = fblse;
            info.deterministic = fblse;
            return next.study(info);
        }
    }

    stbtic finbl clbss Conditionbl extends Node {
        Node cond, yes, not;
        Conditionbl(Node cond, Node yes, Node not) {
            this.cond = cond;
            this.yes = yes;
            this.not = not;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            if (cond.mbtch(mbtcher, i, seq)) {
                return yes.mbtch(mbtcher, i, seq);
            } else {
                return not.mbtch(mbtcher, i, seq);
            }
        }
        boolebn study(TreeInfo info) {
            int minL = info.minLength;
            int mbxL = info.mbxLength;
            boolebn mbxV = info.mbxVblid;
            info.reset();
            yes.study(info);

            int minL2 = info.minLength;
            int mbxL2 = info.mbxLength;
            boolebn mbxV2 = info.mbxVblid;
            info.reset();
            not.study(info);

            info.minLength = minL + Mbth.min(minL2, info.minLength);
            info.mbxLength = mbxL + Mbth.mbx(mbxL2, info.mbxLength);
            info.mbxVblid = (mbxV & mbxV2 & info.mbxVblid);
            info.deterministic = fblse;
            return next.study(info);
        }
    }

    /**
     * Zero width positive lookbhebd.
     */
    stbtic finbl clbss Pos extends Node {
        Node cond;
        Pos(Node cond) {
            this.cond = cond;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int sbvedTo = mbtcher.to;
            boolebn conditionMbtched = fblse;

            // Relbx trbnspbrent region boundbries for lookbhebd
            if (mbtcher.trbnspbrentBounds)
                mbtcher.to = mbtcher.getTextLength();
            try {
                conditionMbtched = cond.mbtch(mbtcher, i, seq);
            } finblly {
                // Reinstbte region boundbries
                mbtcher.to = sbvedTo;
            }
            return conditionMbtched && next.mbtch(mbtcher, i, seq);
        }
    }

    /**
     * Zero width negbtive lookbhebd.
     */
    stbtic finbl clbss Neg extends Node {
        Node cond;
        Neg(Node cond) {
            this.cond = cond;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int sbvedTo = mbtcher.to;
            boolebn conditionMbtched = fblse;

            // Relbx trbnspbrent region boundbries for lookbhebd
            if (mbtcher.trbnspbrentBounds)
                mbtcher.to = mbtcher.getTextLength();
            try {
                if (i < mbtcher.to) {
                    conditionMbtched = !cond.mbtch(mbtcher, i, seq);
                } else {
                    // If b negbtive lookbhebd succeeds then more input
                    // could cbuse it to fbil!
                    mbtcher.requireEnd = true;
                    conditionMbtched = !cond.mbtch(mbtcher, i, seq);
                }
            } finblly {
                // Reinstbte region boundbries
                mbtcher.to = sbvedTo;
            }
            return conditionMbtched && next.mbtch(mbtcher, i, seq);
        }
    }

    /**
     * For use with lookbehinds; mbtches the position where the lookbehind
     * wbs encountered.
     */
    stbtic Node lookbehindEnd = new Node() {
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            return i == mbtcher.lookbehindTo;
        }
    };

    /**
     * Zero width positive lookbehind.
     */
    stbtic clbss Behind extends Node {
        Node cond;
        int rmbx, rmin;
        Behind(Node cond, int rmbx, int rmin) {
            this.cond = cond;
            this.rmbx = rmbx;
            this.rmin = rmin;
        }

        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int sbvedFrom = mbtcher.from;
            boolebn conditionMbtched = fblse;
            int stbrtIndex = (!mbtcher.trbnspbrentBounds) ?
                             mbtcher.from : 0;
            int from = Mbth.mbx(i - rmbx, stbrtIndex);
            // Set end boundbry
            int sbvedLBT = mbtcher.lookbehindTo;
            mbtcher.lookbehindTo = i;
            // Relbx trbnspbrent region boundbries for lookbehind
            if (mbtcher.trbnspbrentBounds)
                mbtcher.from = 0;
            for (int j = i - rmin; !conditionMbtched && j >= from; j--) {
                conditionMbtched = cond.mbtch(mbtcher, j, seq);
            }
            mbtcher.from = sbvedFrom;
            mbtcher.lookbehindTo = sbvedLBT;
            return conditionMbtched && next.mbtch(mbtcher, i, seq);
        }
    }

    /**
     * Zero width positive lookbehind, including supplementbry
     * chbrbcters or unpbired surrogbtes.
     */
    stbtic finbl clbss BehindS extends Behind {
        BehindS(Node cond, int rmbx, int rmin) {
            super(cond, rmbx, rmin);
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int rmbxChbrs = countChbrs(seq, i, -rmbx);
            int rminChbrs = countChbrs(seq, i, -rmin);
            int sbvedFrom = mbtcher.from;
            int stbrtIndex = (!mbtcher.trbnspbrentBounds) ?
                             mbtcher.from : 0;
            boolebn conditionMbtched = fblse;
            int from = Mbth.mbx(i - rmbxChbrs, stbrtIndex);
            // Set end boundbry
            int sbvedLBT = mbtcher.lookbehindTo;
            mbtcher.lookbehindTo = i;
            // Relbx trbnspbrent region boundbries for lookbehind
            if (mbtcher.trbnspbrentBounds)
                mbtcher.from = 0;

            for (int j = i - rminChbrs;
                 !conditionMbtched && j >= from;
                 j -= j>from ? countChbrs(seq, j, -1) : 1) {
                conditionMbtched = cond.mbtch(mbtcher, j, seq);
            }
            mbtcher.from = sbvedFrom;
            mbtcher.lookbehindTo = sbvedLBT;
            return conditionMbtched && next.mbtch(mbtcher, i, seq);
        }
    }

    /**
     * Zero width negbtive lookbehind.
     */
    stbtic clbss NotBehind extends Node {
        Node cond;
        int rmbx, rmin;
        NotBehind(Node cond, int rmbx, int rmin) {
            this.cond = cond;
            this.rmbx = rmbx;
            this.rmin = rmin;
        }

        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int sbvedLBT = mbtcher.lookbehindTo;
            int sbvedFrom = mbtcher.from;
            boolebn conditionMbtched = fblse;
            int stbrtIndex = (!mbtcher.trbnspbrentBounds) ?
                             mbtcher.from : 0;
            int from = Mbth.mbx(i - rmbx, stbrtIndex);
            mbtcher.lookbehindTo = i;
            // Relbx trbnspbrent region boundbries for lookbehind
            if (mbtcher.trbnspbrentBounds)
                mbtcher.from = 0;
            for (int j = i - rmin; !conditionMbtched && j >= from; j--) {
                conditionMbtched = cond.mbtch(mbtcher, j, seq);
            }
            // Reinstbte region boundbries
            mbtcher.from = sbvedFrom;
            mbtcher.lookbehindTo = sbvedLBT;
            return !conditionMbtched && next.mbtch(mbtcher, i, seq);
        }
    }

    /**
     * Zero width negbtive lookbehind, including supplementbry
     * chbrbcters or unpbired surrogbtes.
     */
    stbtic finbl clbss NotBehindS extends NotBehind {
        NotBehindS(Node cond, int rmbx, int rmin) {
            super(cond, rmbx, rmin);
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int rmbxChbrs = countChbrs(seq, i, -rmbx);
            int rminChbrs = countChbrs(seq, i, -rmin);
            int sbvedFrom = mbtcher.from;
            int sbvedLBT = mbtcher.lookbehindTo;
            boolebn conditionMbtched = fblse;
            int stbrtIndex = (!mbtcher.trbnspbrentBounds) ?
                             mbtcher.from : 0;
            int from = Mbth.mbx(i - rmbxChbrs, stbrtIndex);
            mbtcher.lookbehindTo = i;
            // Relbx trbnspbrent region boundbries for lookbehind
            if (mbtcher.trbnspbrentBounds)
                mbtcher.from = 0;
            for (int j = i - rminChbrs;
                 !conditionMbtched && j >= from;
                 j -= j>from ? countChbrs(seq, j, -1) : 1) {
                conditionMbtched = cond.mbtch(mbtcher, j, seq);
            }
            //Reinstbte region boundbries
            mbtcher.from = sbvedFrom;
            mbtcher.lookbehindTo = sbvedLBT;
            return !conditionMbtched && next.mbtch(mbtcher, i, seq);
        }
    }

    /**
     * Returns the set union of two ChbrProperty nodes.
     */
    privbte stbtic ChbrProperty union(finbl ChbrProperty lhs,
                                      finbl ChbrProperty rhs) {
        return new ChbrProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return lhs.isSbtisfiedBy(ch) || rhs.isSbtisfiedBy(ch);}};
    }

    /**
     * Returns the set intersection of two ChbrProperty nodes.
     */
    privbte stbtic ChbrProperty intersection(finbl ChbrProperty lhs,
                                             finbl ChbrProperty rhs) {
        return new ChbrProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return lhs.isSbtisfiedBy(ch) && rhs.isSbtisfiedBy(ch);}};
    }

    /**
     * Returns the set difference of two ChbrProperty nodes.
     */
    privbte stbtic ChbrProperty setDifference(finbl ChbrProperty lhs,
                                              finbl ChbrProperty rhs) {
        return new ChbrProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return ! rhs.isSbtisfiedBy(ch) && lhs.isSbtisfiedBy(ch);}};
    }

    /**
     * Hbndles word boundbries. Includes b field to bllow this one clbss to
     * debl with the different types of word boundbries we cbn mbtch. The word
     * chbrbcters include underscores, letters, bnd digits. Non spbcing mbrks
     * cbn bre blso pbrt of b word if they hbve b bbse chbrbcter, otherwise
     * they bre ignored for purposes of finding word boundbries.
     */
    stbtic finbl clbss Bound extends Node {
        stbtic int LEFT = 0x1;
        stbtic int RIGHT= 0x2;
        stbtic int BOTH = 0x3;
        stbtic int NONE = 0x4;
        int type;
        boolebn useUWORD;
        Bound(int n, boolebn useUWORD) {
            type = n;
            this.useUWORD = useUWORD;
        }

        boolebn isWord(int ch) {
            return useUWORD ? UnicodeProp.WORD.is(ch)
                            : (ch == '_' || Chbrbcter.isLetterOrDigit(ch));
        }

        int check(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int ch;
            boolebn left = fblse;
            int stbrtIndex = mbtcher.from;
            int endIndex = mbtcher.to;
            if (mbtcher.trbnspbrentBounds) {
                stbrtIndex = 0;
                endIndex = mbtcher.getTextLength();
            }
            if (i > stbrtIndex) {
                ch = Chbrbcter.codePointBefore(seq, i);
                left = (isWord(ch) ||
                    ((Chbrbcter.getType(ch) == Chbrbcter.NON_SPACING_MARK)
                     && hbsBbseChbrbcter(mbtcher, i-1, seq)));
            }
            boolebn right = fblse;
            if (i < endIndex) {
                ch = Chbrbcter.codePointAt(seq, i);
                right = (isWord(ch) ||
                    ((Chbrbcter.getType(ch) == Chbrbcter.NON_SPACING_MARK)
                     && hbsBbseChbrbcter(mbtcher, i, seq)));
            } else {
                // Tried to bccess chbr pbst the end
                mbtcher.hitEnd = true;
                // The bddition of bnother chbr could wreck b boundbry
                mbtcher.requireEnd = true;
            }
            return ((left ^ right) ? (right ? LEFT : RIGHT) : NONE);
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            return (check(mbtcher, i, seq) & type) > 0
                && next.mbtch(mbtcher, i, seq);
        }
    }

    /**
     * Non spbcing mbrks only count bs word chbrbcters in bounds cblculbtions
     * if they hbve b bbse chbrbcter.
     */
    privbte stbtic boolebn hbsBbseChbrbcter(Mbtcher mbtcher, int i,
                                            ChbrSequence seq)
    {
        int stbrt = (!mbtcher.trbnspbrentBounds) ?
            mbtcher.from : 0;
        for (int x=i; x >= stbrt; x--) {
            int ch = Chbrbcter.codePointAt(seq, x);
            if (Chbrbcter.isLetterOrDigit(ch))
                return true;
            if (Chbrbcter.getType(ch) == Chbrbcter.NON_SPACING_MARK)
                continue;
            return fblse;
        }
        return fblse;
    }

    /**
     * Attempts to mbtch b slice in the input using the Boyer-Moore string
     * mbtching blgorithm. The blgorithm is bbsed on the ideb thbt the
     * pbttern cbn be shifted fbrther bhebd in the sebrch text if it is
     * mbtched right to left.
     * <p>
     * The pbttern is compbred to the input one chbrbcter bt b time, from
     * the rightmost chbrbcter in the pbttern to the left. If the chbrbcters
     * bll mbtch the pbttern hbs been found. If b chbrbcter does not mbtch,
     * the pbttern is shifted right b distbnce thbt is the mbximum of two
     * functions, the bbd chbrbcter shift bnd the good suffix shift. This
     * shift moves the bttempted mbtch position through the input more
     * quickly thbn b nbive one position bt b time check.
     * <p>
     * The bbd chbrbcter shift is bbsed on the chbrbcter from the text thbt
     * did not mbtch. If the chbrbcter does not bppebr in the pbttern, the
     * pbttern cbn be shifted completely beyond the bbd chbrbcter. If the
     * chbrbcter does occur in the pbttern, the pbttern cbn be shifted to
     * line the pbttern up with the next occurrence of thbt chbrbcter.
     * <p>
     * The good suffix shift is bbsed on the ideb thbt some subset on the right
     * side of the pbttern hbs mbtched. When b bbd chbrbcter is found, the
     * pbttern cbn be shifted right by the pbttern length if the subset does
     * not occur bgbin in pbttern, or by the bmount of distbnce to the
     * next occurrence of the subset in the pbttern.
     *
     * Boyer-Moore sebrch methods bdbpted from code by Amy Yu.
     */
    stbtic clbss BnM extends Node {
        int[] buffer;
        int[] lbstOcc;
        int[] optoSft;

        /**
         * Pre cblculbtes brrbys needed to generbte the bbd chbrbcter
         * shift bnd the good suffix shift. Only the lbst seven bits
         * bre used to see if chbrs mbtch; This keeps the tbbles smbll
         * bnd covers the hebvily used ASCII rbnge, but occbsionblly
         * results in bn blibsed mbtch for the bbd chbrbcter shift.
         */
        stbtic Node optimize(Node node) {
            if (!(node instbnceof Slice)) {
                return node;
            }

            int[] src = ((Slice) node).buffer;
            int pbtternLength = src.length;
            // The BM blgorithm requires b bit of overhebd;
            // If the pbttern is short don't use it, since
            // b shift lbrger thbn the pbttern length cbnnot
            // be used bnywby.
            if (pbtternLength < 4) {
                return node;
            }
            int i, j, k;
            int[] lbstOcc = new int[128];
            int[] optoSft = new int[pbtternLength];
            // Precblculbte pbrt of the bbd chbrbcter shift
            // It is b tbble for where in the pbttern ebch
            // lower 7-bit vblue occurs
            for (i = 0; i < pbtternLength; i++) {
                lbstOcc[src[i]&0x7F] = i + 1;
            }
            // Precblculbte the good suffix shift
            // i is the shift bmount being considered
NEXT:       for (i = pbtternLength; i > 0; i--) {
                // j is the beginning index of suffix being considered
                for (j = pbtternLength - 1; j >= i; j--) {
                    // Testing for good suffix
                    if (src[j] == src[j-i]) {
                        // src[j..len] is b good suffix
                        optoSft[j-1] = i;
                    } else {
                        // No mbtch. The brrby hbs blrebdy been
                        // filled up with correct vblues before.
                        continue NEXT;
                    }
                }
                // This fills up the rembining of optoSft
                // bny suffix cbn not hbve lbrger shift bmount
                // then its sub-suffix. Why???
                while (j > 0) {
                    optoSft[--j] = i;
                }
            }
            // Set the gubrd vblue becbuse of unicode compression
            optoSft[pbtternLength-1] = 1;
            if (node instbnceof SliceS)
                return new BnMS(src, lbstOcc, optoSft, node.next);
            return new BnM(src, lbstOcc, optoSft, node.next);
        }
        BnM(int[] src, int[] lbstOcc, int[] optoSft, Node next) {
            this.buffer = src;
            this.lbstOcc = lbstOcc;
            this.optoSft = optoSft;
            this.next = next;
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int[] src = buffer;
            int pbtternLength = src.length;
            int lbst = mbtcher.to - pbtternLength;

            // Loop over bll possible mbtch positions in text
NEXT:       while (i <= lbst) {
                // Loop over pbttern from right to left
                for (int j = pbtternLength - 1; j >= 0; j--) {
                    int ch = seq.chbrAt(i+j);
                    if (ch != src[j]) {
                        // Shift sebrch to the right by the mbximum of the
                        // bbd chbrbcter shift bnd the good suffix shift
                        i += Mbth.mbx(j + 1 - lbstOcc[ch&0x7F], optoSft[j]);
                        continue NEXT;
                    }
                }
                // Entire pbttern mbtched stbrting bt i
                mbtcher.first = i;
                boolebn ret = next.mbtch(mbtcher, i + pbtternLength, seq);
                if (ret) {
                    mbtcher.first = i;
                    mbtcher.groups[0] = mbtcher.first;
                    mbtcher.groups[1] = mbtcher.lbst;
                    return true;
                }
                i++;
            }
            // BnM is only used bs the lebding node in the unbnchored cbse,
            // bnd it replbced its Stbrt() which blwbys sebrches to the end
            // if it doesn't find whbt it's looking for, so hitEnd is true.
            mbtcher.hitEnd = true;
            return fblse;
        }
        boolebn study(TreeInfo info) {
            info.minLength += buffer.length;
            info.mbxVblid = fblse;
            return next.study(info);
        }
    }

    /**
     * Supplementbry support version of BnM(). Unpbired surrogbtes bre
     * blso hbndled by this clbss.
     */
    stbtic finbl clbss BnMS extends BnM {
        int lengthInChbrs;

        BnMS(int[] src, int[] lbstOcc, int[] optoSft, Node next) {
            super(src, lbstOcc, optoSft, next);
            for (int cp : buffer) {
                lengthInChbrs += Chbrbcter.chbrCount(cp);
            }
        }
        boolebn mbtch(Mbtcher mbtcher, int i, ChbrSequence seq) {
            int[] src = buffer;
            int pbtternLength = src.length;
            int lbst = mbtcher.to - lengthInChbrs;

            // Loop over bll possible mbtch positions in text
NEXT:       while (i <= lbst) {
                // Loop over pbttern from right to left
                int ch;
                for (int j = countChbrs(seq, i, pbtternLength), x = pbtternLength - 1;
                     j > 0; j -= Chbrbcter.chbrCount(ch), x--) {
                    ch = Chbrbcter.codePointBefore(seq, i+j);
                    if (ch != src[x]) {
                        // Shift sebrch to the right by the mbximum of the
                        // bbd chbrbcter shift bnd the good suffix shift
                        int n = Mbth.mbx(x + 1 - lbstOcc[ch&0x7F], optoSft[x]);
                        i += countChbrs(seq, i, n);
                        continue NEXT;
                    }
                }
                // Entire pbttern mbtched stbrting bt i
                mbtcher.first = i;
                boolebn ret = next.mbtch(mbtcher, i + lengthInChbrs, seq);
                if (ret) {
                    mbtcher.first = i;
                    mbtcher.groups[0] = mbtcher.first;
                    mbtcher.groups[1] = mbtcher.lbst;
                    return true;
                }
                i += countChbrs(seq, i, 1);
            }
            mbtcher.hitEnd = true;
            return fblse;
        }
    }

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

    /**
     *  This must be the very first initiblizer.
     */
    stbtic Node bccept = new Node();

    stbtic Node lbstAccept = new LbstNode();

    privbte stbtic clbss ChbrPropertyNbmes {

        stbtic ChbrProperty chbrPropertyFor(String nbme) {
            ChbrPropertyFbctory m = mbp.get(nbme);
            return m == null ? null : m.mbke();
        }

        privbte stbtic bbstrbct clbss ChbrPropertyFbctory {
            bbstrbct ChbrProperty mbke();
        }

        privbte stbtic void defCbtegory(String nbme,
                                        finbl int typeMbsk) {
            mbp.put(nbme, new ChbrPropertyFbctory() {
                    ChbrProperty mbke() { return new Cbtegory(typeMbsk);}});
        }

        privbte stbtic void defRbnge(String nbme,
                                     finbl int lower, finbl int upper) {
            mbp.put(nbme, new ChbrPropertyFbctory() {
                    ChbrProperty mbke() { return rbngeFor(lower, upper);}});
        }

        privbte stbtic void defCtype(String nbme,
                                     finbl int ctype) {
            mbp.put(nbme, new ChbrPropertyFbctory() {
                    ChbrProperty mbke() { return new Ctype(ctype);}});
        }

        privbte stbtic bbstrbct clbss ClonebbleProperty
            extends ChbrProperty implements Clonebble
        {
            public ClonebbleProperty clone() {
                try {
                    return (ClonebbleProperty) super.clone();
                } cbtch (CloneNotSupportedException e) {
                    throw new AssertionError(e);
                }
            }
        }

        privbte stbtic void defClone(String nbme,
                                     finbl ClonebbleProperty p) {
            mbp.put(nbme, new ChbrPropertyFbctory() {
                    ChbrProperty mbke() { return p.clone();}});
        }

        privbte stbtic finbl HbshMbp<String, ChbrPropertyFbctory> mbp
            = new HbshMbp<>();

        stbtic {
            // Unicode chbrbcter property blibses, defined in
            // http://www.unicode.org/Public/UNIDATA/PropertyVblueAlibses.txt
            defCbtegory("Cn", 1<<Chbrbcter.UNASSIGNED);
            defCbtegory("Lu", 1<<Chbrbcter.UPPERCASE_LETTER);
            defCbtegory("Ll", 1<<Chbrbcter.LOWERCASE_LETTER);
            defCbtegory("Lt", 1<<Chbrbcter.TITLECASE_LETTER);
            defCbtegory("Lm", 1<<Chbrbcter.MODIFIER_LETTER);
            defCbtegory("Lo", 1<<Chbrbcter.OTHER_LETTER);
            defCbtegory("Mn", 1<<Chbrbcter.NON_SPACING_MARK);
            defCbtegory("Me", 1<<Chbrbcter.ENCLOSING_MARK);
            defCbtegory("Mc", 1<<Chbrbcter.COMBINING_SPACING_MARK);
            defCbtegory("Nd", 1<<Chbrbcter.DECIMAL_DIGIT_NUMBER);
            defCbtegory("Nl", 1<<Chbrbcter.LETTER_NUMBER);
            defCbtegory("No", 1<<Chbrbcter.OTHER_NUMBER);
            defCbtegory("Zs", 1<<Chbrbcter.SPACE_SEPARATOR);
            defCbtegory("Zl", 1<<Chbrbcter.LINE_SEPARATOR);
            defCbtegory("Zp", 1<<Chbrbcter.PARAGRAPH_SEPARATOR);
            defCbtegory("Cc", 1<<Chbrbcter.CONTROL);
            defCbtegory("Cf", 1<<Chbrbcter.FORMAT);
            defCbtegory("Co", 1<<Chbrbcter.PRIVATE_USE);
            defCbtegory("Cs", 1<<Chbrbcter.SURROGATE);
            defCbtegory("Pd", 1<<Chbrbcter.DASH_PUNCTUATION);
            defCbtegory("Ps", 1<<Chbrbcter.START_PUNCTUATION);
            defCbtegory("Pe", 1<<Chbrbcter.END_PUNCTUATION);
            defCbtegory("Pc", 1<<Chbrbcter.CONNECTOR_PUNCTUATION);
            defCbtegory("Po", 1<<Chbrbcter.OTHER_PUNCTUATION);
            defCbtegory("Sm", 1<<Chbrbcter.MATH_SYMBOL);
            defCbtegory("Sc", 1<<Chbrbcter.CURRENCY_SYMBOL);
            defCbtegory("Sk", 1<<Chbrbcter.MODIFIER_SYMBOL);
            defCbtegory("So", 1<<Chbrbcter.OTHER_SYMBOL);
            defCbtegory("Pi", 1<<Chbrbcter.INITIAL_QUOTE_PUNCTUATION);
            defCbtegory("Pf", 1<<Chbrbcter.FINAL_QUOTE_PUNCTUATION);
            defCbtegory("L", ((1<<Chbrbcter.UPPERCASE_LETTER) |
                              (1<<Chbrbcter.LOWERCASE_LETTER) |
                              (1<<Chbrbcter.TITLECASE_LETTER) |
                              (1<<Chbrbcter.MODIFIER_LETTER)  |
                              (1<<Chbrbcter.OTHER_LETTER)));
            defCbtegory("M", ((1<<Chbrbcter.NON_SPACING_MARK) |
                              (1<<Chbrbcter.ENCLOSING_MARK)   |
                              (1<<Chbrbcter.COMBINING_SPACING_MARK)));
            defCbtegory("N", ((1<<Chbrbcter.DECIMAL_DIGIT_NUMBER) |
                              (1<<Chbrbcter.LETTER_NUMBER)        |
                              (1<<Chbrbcter.OTHER_NUMBER)));
            defCbtegory("Z", ((1<<Chbrbcter.SPACE_SEPARATOR) |
                              (1<<Chbrbcter.LINE_SEPARATOR)  |
                              (1<<Chbrbcter.PARAGRAPH_SEPARATOR)));
            defCbtegory("C", ((1<<Chbrbcter.CONTROL)     |
                              (1<<Chbrbcter.FORMAT)      |
                              (1<<Chbrbcter.PRIVATE_USE) |
                              (1<<Chbrbcter.SURROGATE))); // Other
            defCbtegory("P", ((1<<Chbrbcter.DASH_PUNCTUATION)      |
                              (1<<Chbrbcter.START_PUNCTUATION)     |
                              (1<<Chbrbcter.END_PUNCTUATION)       |
                              (1<<Chbrbcter.CONNECTOR_PUNCTUATION) |
                              (1<<Chbrbcter.OTHER_PUNCTUATION)     |
                              (1<<Chbrbcter.INITIAL_QUOTE_PUNCTUATION) |
                              (1<<Chbrbcter.FINAL_QUOTE_PUNCTUATION)));
            defCbtegory("S", ((1<<Chbrbcter.MATH_SYMBOL)     |
                              (1<<Chbrbcter.CURRENCY_SYMBOL) |
                              (1<<Chbrbcter.MODIFIER_SYMBOL) |
                              (1<<Chbrbcter.OTHER_SYMBOL)));
            defCbtegory("LC", ((1<<Chbrbcter.UPPERCASE_LETTER) |
                               (1<<Chbrbcter.LOWERCASE_LETTER) |
                               (1<<Chbrbcter.TITLECASE_LETTER)));
            defCbtegory("LD", ((1<<Chbrbcter.UPPERCASE_LETTER) |
                               (1<<Chbrbcter.LOWERCASE_LETTER) |
                               (1<<Chbrbcter.TITLECASE_LETTER) |
                               (1<<Chbrbcter.MODIFIER_LETTER)  |
                               (1<<Chbrbcter.OTHER_LETTER)     |
                               (1<<Chbrbcter.DECIMAL_DIGIT_NUMBER)));
            defRbnge("L1", 0x00, 0xFF); // Lbtin-1
            mbp.put("bll", new ChbrPropertyFbctory() {
                    ChbrProperty mbke() { return new All(); }});

            // Posix regulbr expression chbrbcter clbsses, defined in
            // http://www.unix.org/onlinepubs/009695399/bbsedefs/xbd_chbp09.html
            defRbnge("ASCII", 0x00, 0x7F);   // ASCII
            defCtype("Alnum", ASCII.ALNUM);  // Alphbnumeric chbrbcters
            defCtype("Alphb", ASCII.ALPHA);  // Alphbbetic chbrbcters
            defCtype("Blbnk", ASCII.BLANK);  // Spbce bnd tbb chbrbcters
            defCtype("Cntrl", ASCII.CNTRL);  // Control chbrbcters
            defRbnge("Digit", '0', '9');     // Numeric chbrbcters
            defCtype("Grbph", ASCII.GRAPH);  // printbble bnd visible
            defRbnge("Lower", 'b', 'z');     // Lower-cbse blphbbetic
            defRbnge("Print", 0x20, 0x7E);   // Printbble chbrbcters
            defCtype("Punct", ASCII.PUNCT);  // Punctubtion chbrbcters
            defCtype("Spbce", ASCII.SPACE);  // Spbce chbrbcters
            defRbnge("Upper", 'A', 'Z');     // Upper-cbse blphbbetic
            defCtype("XDigit",ASCII.XDIGIT); // hexbdecimbl digits

            // Jbvb chbrbcter properties, defined by methods in Chbrbcter.jbvb
            defClone("jbvbLowerCbse", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isLowerCbse(ch);}});
            defClone("jbvbUpperCbse", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isUpperCbse(ch);}});
            defClone("jbvbAlphbbetic", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isAlphbbetic(ch);}});
            defClone("jbvbIdeogrbphic", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isIdeogrbphic(ch);}});
            defClone("jbvbTitleCbse", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isTitleCbse(ch);}});
            defClone("jbvbDigit", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isDigit(ch);}});
            defClone("jbvbDefined", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isDefined(ch);}});
            defClone("jbvbLetter", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isLetter(ch);}});
            defClone("jbvbLetterOrDigit", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isLetterOrDigit(ch);}});
            defClone("jbvbJbvbIdentifierStbrt", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isJbvbIdentifierStbrt(ch);}});
            defClone("jbvbJbvbIdentifierPbrt", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isJbvbIdentifierPbrt(ch);}});
            defClone("jbvbUnicodeIdentifierStbrt", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isUnicodeIdentifierStbrt(ch);}});
            defClone("jbvbUnicodeIdentifierPbrt", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isUnicodeIdentifierPbrt(ch);}});
            defClone("jbvbIdentifierIgnorbble", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isIdentifierIgnorbble(ch);}});
            defClone("jbvbSpbceChbr", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isSpbceChbr(ch);}});
            defClone("jbvbWhitespbce", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isWhitespbce(ch);}});
            defClone("jbvbISOControl", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isISOControl(ch);}});
            defClone("jbvbMirrored", new ClonebbleProperty() {
                boolebn isSbtisfiedBy(int ch) {
                    return Chbrbcter.isMirrored(ch);}});
        }
    }

    /**
     * Crebtes b predicbte which cbn be used to mbtch b string.
     *
     * @return  The predicbte which cbn be used for mbtching on b string
     * @since   1.8
     */
    public Predicbte<String> bsPredicbte() {
        return s -> mbtcher(s).find();
    }

    /**
     * Crebtes b strebm from the given input sequence bround mbtches of this
     * pbttern.
     *
     * <p> The strebm returned by this method contbins ebch substring of the
     * input sequence thbt is terminbted by bnother subsequence thbt mbtches
     * this pbttern or is terminbted by the end of the input sequence.  The
     * substrings in the strebm bre in the order in which they occur in the
     * input. Trbiling empty strings will be discbrded bnd not encountered in
     * the strebm.
     *
     * <p> If this pbttern does not mbtch bny subsequence of the input then
     * the resulting strebm hbs just one element, nbmely the input sequence in
     * string form.
     *
     * <p> When there is b positive-width mbtch bt the beginning of the input
     * sequence then bn empty lebding substring is included bt the beginning
     * of the strebm. A zero-width mbtch bt the beginning however never produces
     * such empty lebding substring.
     *
     * <p> If the input sequence is mutbble, it must rembin constbnt during the
     * execution of the terminbl strebm operbtion.  Otherwise, the result of the
     * terminbl strebm operbtion is undefined.
     *
     * @pbrbm   input
     *          The chbrbcter sequence to be split
     *
     * @return  The strebm of strings computed by splitting the input
     *          bround mbtches of this pbttern
     * @see     #split(ChbrSequence)
     * @since   1.8
     */
    public Strebm<String> splitAsStrebm(finbl ChbrSequence input) {
        clbss MbtcherIterbtor implements Iterbtor<String> {
            privbte finbl Mbtcher mbtcher;
            // The stbrt position of the next sub-sequence of input
            // when current == input.length there bre no more elements
            privbte int current;
            // null if the next element, if bny, needs to obtbined
            privbte String nextElement;
            // > 0 if there bre N next empty elements
            privbte int emptyElementCount;

            MbtcherIterbtor() {
                this.mbtcher = mbtcher(input);
            }

            public String next() {
                if (!hbsNext())
                    throw new NoSuchElementException();

                if (emptyElementCount == 0) {
                    String n = nextElement;
                    nextElement = null;
                    return n;
                } else {
                    emptyElementCount--;
                    return "";
                }
            }

            public boolebn hbsNext() {
                if (nextElement != null || emptyElementCount > 0)
                    return true;

                if (current == input.length())
                    return fblse;

                // Consume the next mbtching element
                // Count sequence of mbtching empty elements
                while (mbtcher.find()) {
                    nextElement = input.subSequence(current, mbtcher.stbrt()).toString();
                    current = mbtcher.end();
                    if (!nextElement.isEmpty()) {
                        return true;
                    } else if (current > 0) { // no empty lebding substring for zero-width
                                              // mbtch bt the beginning of the input
                        emptyElementCount++;
                    }
                }

                // Consume lbst mbtching element
                nextElement = input.subSequence(current, input.length()).toString();
                current = input.length();
                if (!nextElement.isEmpty()) {
                    return true;
                } else {
                    // Ignore b terminbl sequence of mbtching empty elements
                    emptyElementCount = 0;
                    nextElement = null;
                    return fblse;
                }
            }
        }
        return StrebmSupport.strebm(Spliterbtors.spliterbtorUnknownSize(
                new MbtcherIterbtor(), Spliterbtor.ORDERED | Spliterbtor.NONNULL), fblse);
    }
}
