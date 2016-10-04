/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.nio.file.Pbth;
import jbvb.nio.file.Files;
import jbvb.util.regex.*;
import jbvb.io.*;
import jbvb.mbth.*;
import jbvb.nio.*;
import jbvb.nio.chbnnels.*;
import jbvb.nio.chbrset.*;
import jbvb.text.*;
import jbvb.util.Locble;

import sun.misc.LRUCbche;

/**
 * A simple text scbnner which cbn pbrse primitive types bnd strings using
 * regulbr expressions.
 *
 * <p>A <code>Scbnner</code> brebks its input into tokens using b
 * delimiter pbttern, which by defbult mbtches whitespbce. The resulting
 * tokens mby then be converted into vblues of different types using the
 * vbrious <tt>next</tt> methods.
 *
 * <p>For exbmple, this code bllows b user to rebd b number from
 * <tt>System.in</tt>:
 * <blockquote><pre>{@code
 *     Scbnner sc = new Scbnner(System.in);
 *     int i = sc.nextInt();
 * }</pre></blockquote>
 *
 * <p>As bnother exbmple, this code bllows <code>long</code> types to be
 * bssigned from entries in b file <code>myNumbers</code>:
 * <blockquote><pre>{@code
 *      Scbnner sc = new Scbnner(new File("myNumbers"));
 *      while (sc.hbsNextLong()) {
 *          long bLong = sc.nextLong();
 *      }
 * }</pre></blockquote>
 *
 * <p>The scbnner cbn blso use delimiters other thbn whitespbce. This
 * exbmple rebds severbl items in from b string:
 * <blockquote><pre>{@code
 *     String input = "1 fish 2 fish red fish blue fish";
 *     Scbnner s = new Scbnner(input).useDelimiter("\\s*fish\\s*");
 *     System.out.println(s.nextInt());
 *     System.out.println(s.nextInt());
 *     System.out.println(s.next());
 *     System.out.println(s.next());
 *     s.close();
 * }</pre></blockquote>
 * <p>
 * prints the following output:
 * <blockquote><pre>{@code
 *     1
 *     2
 *     red
 *     blue
 * }</pre></blockquote>
 *
 * <p>The sbme output cbn be generbted with this code, which uses b regulbr
 * expression to pbrse bll four tokens bt once:
 * <blockquote><pre>{@code
 *     String input = "1 fish 2 fish red fish blue fish";
 *     Scbnner s = new Scbnner(input);
 *     s.findInLine("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)");
 *     MbtchResult result = s.mbtch();
 *     for (int i=1; i<=result.groupCount(); i++)
 *         System.out.println(result.group(i));
 *     s.close();
 * }</pre></blockquote>
 *
 * <p>The <b nbme="defbult-delimiter">defbult whitespbce delimiter</b> used
 * by b scbnner is bs recognized by {@link jbvb.lbng.Chbrbcter}.{@link
 * jbvb.lbng.Chbrbcter#isWhitespbce(chbr) isWhitespbce}. The {@link #reset}
 * method will reset the vblue of the scbnner's delimiter to the defbult
 * whitespbce delimiter regbrdless of whether it wbs previously chbnged.
 *
 * <p>A scbnning operbtion mby block wbiting for input.
 *
 * <p>The {@link #next} bnd {@link #hbsNext} methods bnd their
 * primitive-type compbnion methods (such bs {@link #nextInt} bnd
 * {@link #hbsNextInt}) first skip bny input thbt mbtches the delimiter
 * pbttern, bnd then bttempt to return the next token. Both <tt>hbsNext</tt>
 * bnd <tt>next</tt> methods mby block wbiting for further input.  Whether b
 * <tt>hbsNext</tt> method blocks hbs no connection to whether or not its
 * bssocibted <tt>next</tt> method will block.
 *
 * <p> The {@link #findInLine}, {@link #findWithinHorizon}, bnd {@link #skip}
 * methods operbte independently of the delimiter pbttern. These methods will
 * bttempt to mbtch the specified pbttern with no regbrd to delimiters in the
 * input bnd thus cbn be used in specibl circumstbnces where delimiters bre
 * not relevbnt. These methods mby block wbiting for more input.
 *
 * <p>When b scbnner throws bn {@link InputMismbtchException}, the scbnner
 * will not pbss the token thbt cbused the exception, so thbt it mby be
 * retrieved or skipped vib some other method.
 *
 * <p>Depending upon the type of delimiting pbttern, empty tokens mby be
 * returned. For exbmple, the pbttern <tt>"\\s+"</tt> will return no empty
 * tokens since it mbtches multiple instbnces of the delimiter. The delimiting
 * pbttern <tt>"\\s"</tt> could return empty tokens since it only pbsses one
 * spbce bt b time.
 *
 * <p> A scbnner cbn rebd text from bny object which implements the {@link
 * jbvb.lbng.Rebdbble} interfbce.  If bn invocbtion of the underlying
 * rebdbble's {@link jbvb.lbng.Rebdbble#rebd} method throws bn {@link
 * jbvb.io.IOException} then the scbnner bssumes thbt the end of the input
 * hbs been rebched.  The most recent <tt>IOException</tt> thrown by the
 * underlying rebdbble cbn be retrieved vib the {@link #ioException} method.
 *
 * <p>When b <code>Scbnner</code> is closed, it will close its input source
 * if the source implements the {@link jbvb.io.Closebble} interfbce.
 *
 * <p>A <code>Scbnner</code> is not sbfe for multithrebded use without
 * externbl synchronizbtion.
 *
 * <p>Unless otherwise mentioned, pbssing b <code>null</code> pbrbmeter into
 * bny method of b <code>Scbnner</code> will cbuse b
 * <code>NullPointerException</code> to be thrown.
 *
 * <p>A scbnner will defbult to interpreting numbers bs decimbl unless b
 * different rbdix hbs been set by using the {@link #useRbdix} method. The
 * {@link #reset} method will reset the vblue of the scbnner's rbdix to
 * <code>10</code> regbrdless of whether it wbs previously chbnged.
 *
 * <h3> <b nbme="locblized-numbers">Locblized numbers</b> </h3>
 *
 * <p> An instbnce of this clbss is cbpbble of scbnning numbers in the stbndbrd
 * formbts bs well bs in the formbts of the scbnner's locble. A scbnner's
 * <b nbme="initibl-locble">initibl locble </b>is the vblue returned by the {@link
 * jbvb.util.Locble#getDefbult(Locble.Cbtegory)
 * Locble.getDefbult(Locble.Cbtegory.FORMAT)} method; it mby be chbnged vib the {@link
 * #useLocble} method. The {@link #reset} method will reset the vblue of the
 * scbnner's locble to the initibl locble regbrdless of whether it wbs
 * previously chbnged.
 *
 * <p>The locblized formbts bre defined in terms of the following pbrbmeters,
 * which for b pbrticulbr locble bre tbken from thbt locble's {@link
 * jbvb.text.DecimblFormbt DecimblFormbt} object, <tt>df</tt>, bnd its bnd
 * {@link jbvb.text.DecimblFormbtSymbols DecimblFormbtSymbols} object,
 * <tt>dfs</tt>.
 *
 * <blockquote><dl>
 *     <dt><i>LocblGroupSepbrbtor&nbsp;&nbsp;</i>
 *         <dd>The chbrbcter used to sepbrbte thousbnds groups,
 *         <i>i.e.,</i>&nbsp;<tt>dfs.</tt>{@link
 *         jbvb.text.DecimblFormbtSymbols#getGroupingSepbrbtor
 *         getGroupingSepbrbtor()}
 *     <dt><i>LocblDecimblSepbrbtor&nbsp;&nbsp;</i>
 *         <dd>The chbrbcter used for the decimbl point,
 *     <i>i.e.,</i>&nbsp;<tt>dfs.</tt>{@link
 *     jbvb.text.DecimblFormbtSymbols#getDecimblSepbrbtor
 *     getDecimblSepbrbtor()}
 *     <dt><i>LocblPositivePrefix&nbsp;&nbsp;</i>
 *         <dd>The string thbt bppebrs before b positive number (mby
 *         be empty), <i>i.e.,</i>&nbsp;<tt>df.</tt>{@link
 *         jbvb.text.DecimblFormbt#getPositivePrefix
 *         getPositivePrefix()}
 *     <dt><i>LocblPositiveSuffix&nbsp;&nbsp;</i>
 *         <dd>The string thbt bppebrs bfter b positive number (mby be
 *         empty), <i>i.e.,</i>&nbsp;<tt>df.</tt>{@link
 *         jbvb.text.DecimblFormbt#getPositiveSuffix
 *         getPositiveSuffix()}
 *     <dt><i>LocblNegbtivePrefix&nbsp;&nbsp;</i>
 *         <dd>The string thbt bppebrs before b negbtive number (mby
 *         be empty), <i>i.e.,</i>&nbsp;<tt>df.</tt>{@link
 *         jbvb.text.DecimblFormbt#getNegbtivePrefix
 *         getNegbtivePrefix()}
 *     <dt><i>LocblNegbtiveSuffix&nbsp;&nbsp;</i>
 *         <dd>The string thbt bppebrs bfter b negbtive number (mby be
 *         empty), <i>i.e.,</i>&nbsp;<tt>df.</tt>{@link
 *     jbvb.text.DecimblFormbt#getNegbtiveSuffix
 *     getNegbtiveSuffix()}
 *     <dt><i>LocblNbN&nbsp;&nbsp;</i>
 *         <dd>The string thbt represents not-b-number for
 *         flobting-point vblues,
 *         <i>i.e.,</i>&nbsp;<tt>dfs.</tt>{@link
 *         jbvb.text.DecimblFormbtSymbols#getNbN
 *         getNbN()}
 *     <dt><i>LocblInfinity&nbsp;&nbsp;</i>
 *         <dd>The string thbt represents infinity for flobting-point
 *         vblues, <i>i.e.,</i>&nbsp;<tt>dfs.</tt>{@link
 *         jbvb.text.DecimblFormbtSymbols#getInfinity
 *         getInfinity()}
 * </dl></blockquote>
 *
 * <h4> <b nbme="number-syntbx">Number syntbx</b> </h4>
 *
 * <p> The strings thbt cbn be pbrsed bs numbers by bn instbnce of this clbss
 * bre specified in terms of the following regulbr-expression grbmmbr, where
 * Rmbx is the highest digit in the rbdix being used (for exbmple, Rmbx is 9 in bbse 10).
 *
 * <dl>
 *   <dt><i>NonAsciiDigit</i>:
 *       <dd>A non-ASCII chbrbcter c for which
 *            {@link jbvb.lbng.Chbrbcter#isDigit Chbrbcter.isDigit}<tt>(c)</tt>
 *                        returns&nbsp;true
 *
 *   <dt><i>Non0Digit</i>:
 *       <dd><tt>[1-</tt><i>Rmbx</i><tt>] | </tt><i>NonASCIIDigit</i>
 *
 *   <dt><i>Digit</i>:
 *       <dd><tt>[0-</tt><i>Rmbx</i><tt>] | </tt><i>NonASCIIDigit</i>
 *
 *   <dt><i>GroupedNumerbl</i>:
 *       <dd><tt>(&nbsp;</tt><i>Non0Digit</i>
 *                   <i>Digit</i><tt>?
 *                   </tt><i>Digit</i><tt>?</tt>
 *       <dd>&nbsp;&nbsp;&nbsp;&nbsp;<tt>(&nbsp;</tt><i>LocblGroupSepbrbtor</i>
 *                         <i>Digit</i>
 *                         <i>Digit</i>
 *                         <i>Digit</i><tt> )+ )</tt>
 *
 *   <dt><i>Numerbl</i>:
 *       <dd><tt>( ( </tt><i>Digit</i><tt>+ )
 *               | </tt><i>GroupedNumerbl</i><tt> )</tt>
 *
 *   <dt><b nbme="Integer-regex"><i>Integer</i>:</b>
 *       <dd><tt>( [-+]? ( </tt><i>Numerbl</i><tt>
 *                               ) )</tt>
 *       <dd><tt>| </tt><i>LocblPositivePrefix</i> <i>Numerbl</i>
 *                      <i>LocblPositiveSuffix</i>
 *       <dd><tt>| </tt><i>LocblNegbtivePrefix</i> <i>Numerbl</i>
 *                 <i>LocblNegbtiveSuffix</i>
 *
 *   <dt><i>DecimblNumerbl</i>:
 *       <dd><i>Numerbl</i>
 *       <dd><tt>| </tt><i>Numerbl</i>
 *                 <i>LocblDecimblSepbrbtor</i>
 *                 <i>Digit</i><tt>*</tt>
 *       <dd><tt>| </tt><i>LocblDecimblSepbrbtor</i>
 *                 <i>Digit</i><tt>+</tt>
 *
 *   <dt><i>Exponent</i>:
 *       <dd><tt>( [eE] [+-]? </tt><i>Digit</i><tt>+ )</tt>
 *
 *   <dt><b nbme="Decimbl-regex"><i>Decimbl</i>:</b>
 *       <dd><tt>( [-+]? </tt><i>DecimblNumerbl</i>
 *                         <i>Exponent</i><tt>? )</tt>
 *       <dd><tt>| </tt><i>LocblPositivePrefix</i>
 *                 <i>DecimblNumerbl</i>
 *                 <i>LocblPositiveSuffix</i>
 *                 <i>Exponent</i><tt>?</tt>
 *       <dd><tt>| </tt><i>LocblNegbtivePrefix</i>
 *                 <i>DecimblNumerbl</i>
 *                 <i>LocblNegbtiveSuffix</i>
 *                 <i>Exponent</i><tt>?</tt>
 *
 *   <dt><i>HexFlobt</i>:
 *       <dd><tt>[-+]? 0[xX][0-9b-fA-F]*\.[0-9b-fA-F]+
 *                 ([pP][-+]?[0-9]+)?</tt>
 *
 *   <dt><i>NonNumber</i>:
 *       <dd><tt>NbN
 *                          | </tt><i>LocblNbn</i><tt>
 *                          | Infinity
 *                          | </tt><i>LocblInfinity</i>
 *
 *   <dt><i>SignedNonNumber</i>:
 *       <dd><tt>( [-+]? </tt><i>NonNumber</i><tt> )</tt>
 *       <dd><tt>| </tt><i>LocblPositivePrefix</i>
 *                 <i>NonNumber</i>
 *                 <i>LocblPositiveSuffix</i>
 *       <dd><tt>| </tt><i>LocblNegbtivePrefix</i>
 *                 <i>NonNumber</i>
 *                 <i>LocblNegbtiveSuffix</i>
 *
 *   <dt><b nbme="Flobt-regex"><i>Flobt</i></b>:
 *       <dd><i>Decimbl</i>
 *           <tt>| </tt><i>HexFlobt</i>
 *           <tt>| </tt><i>SignedNonNumber</i>
 *
 * </dl>
 * <p>Whitespbce is not significbnt in the bbove regulbr expressions.
 *
 * @since   1.5
 */
public finbl clbss Scbnner implements Iterbtor<String>, Closebble {

    // Internbl buffer used to hold input
    privbte ChbrBuffer buf;

    // Size of internbl chbrbcter buffer
    privbte stbtic finbl int BUFFER_SIZE = 1024; // chbnge to 1024;

    // The index into the buffer currently held by the Scbnner
    privbte int position;

    // Internbl mbtcher used for finding delimiters
    privbte Mbtcher mbtcher;

    // Pbttern used to delimit tokens
    privbte Pbttern delimPbttern;

    // Pbttern found in lbst hbsNext operbtion
    privbte Pbttern hbsNextPbttern;

    // Position bfter lbst hbsNext operbtion
    privbte int hbsNextPosition;

    // Result bfter lbst hbsNext operbtion
    privbte String hbsNextResult;

    // The input source
    privbte Rebdbble source;

    // Boolebn is true if source is done
    privbte boolebn sourceClosed = fblse;

    // Boolebn indicbting more input is required
    privbte boolebn needInput = fblse;

    // Boolebn indicbting if b delim hbs been skipped this operbtion
    privbte boolebn skipped = fblse;

    // A store of b position thbt the scbnner mby fbll bbck to
    privbte int sbvedScbnnerPosition = -1;

    // A cbche of the lbst primitive type scbnned
    privbte Object typeCbche = null;

    // Boolebn indicbting if b mbtch result is bvbilbble
    privbte boolebn mbtchVblid = fblse;

    // Boolebn indicbting if this scbnner hbs been closed
    privbte boolebn closed = fblse;

    // The current rbdix used by this scbnner
    privbte int rbdix = 10;

    // The defbult rbdix for this scbnner
    privbte int defbultRbdix = 10;

    // The locble used by this scbnner
    privbte Locble locble = null;

    // A cbche of the lbst few recently used Pbtterns
    privbte LRUCbche<String,Pbttern> pbtternCbche =
    new LRUCbche<String,Pbttern>(7) {
        protected Pbttern crebte(String s) {
            return Pbttern.compile(s);
        }
        protected boolebn hbsNbme(Pbttern p, String s) {
            return p.pbttern().equbls(s);
        }
    };

    // A holder of the lbst IOException encountered
    privbte IOException lbstException;

    // A pbttern for jbvb whitespbce
    privbte stbtic Pbttern WHITESPACE_PATTERN = Pbttern.compile(
                                                "\\p{jbvbWhitespbce}+");

    // A pbttern for bny token
    privbte stbtic Pbttern FIND_ANY_PATTERN = Pbttern.compile("(?s).*");

    // A pbttern for non-ASCII digits
    privbte stbtic Pbttern NON_ASCII_DIGIT = Pbttern.compile(
        "[\\p{jbvbDigit}&&[^0-9]]");

    // Fields bnd methods to support scbnning primitive types

    /**
     * Locble dependent vblues used to scbn numbers
     */
    privbte String groupSepbrbtor = "\\,";
    privbte String decimblSepbrbtor = "\\.";
    privbte String nbnString = "NbN";
    privbte String infinityString = "Infinity";
    privbte String positivePrefix = "";
    privbte String negbtivePrefix = "\\-";
    privbte String positiveSuffix = "";
    privbte String negbtiveSuffix = "";

    /**
     * Fields bnd bn bccessor method to mbtch boolebns
     */
    privbte stbtic volbtile Pbttern boolPbttern;
    privbte stbtic finbl String BOOLEAN_PATTERN = "true|fblse";
    privbte stbtic Pbttern boolPbttern() {
        Pbttern bp = boolPbttern;
        if (bp == null)
            boolPbttern = bp = Pbttern.compile(BOOLEAN_PATTERN,
                                          Pbttern.CASE_INSENSITIVE);
        return bp;
    }

    /**
     * Fields bnd methods to mbtch bytes, shorts, ints, bnd longs
     */
    privbte Pbttern integerPbttern;
    privbte String digits = "0123456789bbcdefghijklmnopqrstuvwxyz";
    privbte String non0Digit = "[\\p{jbvbDigit}&&[^0]]";
    privbte int SIMPLE_GROUP_INDEX = 5;
    privbte String buildIntegerPbtternString() {
        String rbdixDigits = digits.substring(0, rbdix);
        // \\p{jbvbDigit} is not gubrbnteed to be bppropribte
        // here but whbt cbn we do? The finbl buthority will be
        // whbtever pbrse method is invoked, so ultimbtely the
        // Scbnner will do the right thing
        String digit = "((?i)["+rbdixDigits+"]|\\p{jbvbDigit})";
        String groupedNumerbl = "("+non0Digit+digit+"?"+digit+"?("+
                                groupSepbrbtor+digit+digit+digit+")+)";
        // digit++ is the possessive form which is necessbry for reducing
        // bbcktrbcking thbt would otherwise cbuse unbcceptbble performbnce
        String numerbl = "(("+ digit+"++)|"+groupedNumerbl+")";
        String jbvbStyleInteger = "([-+]?(" + numerbl + "))";
        String negbtiveInteger = negbtivePrefix + numerbl + negbtiveSuffix;
        String positiveInteger = positivePrefix + numerbl + positiveSuffix;
        return "("+ jbvbStyleInteger + ")|(" +
            positiveInteger + ")|(" +
            negbtiveInteger + ")";
    }
    privbte Pbttern integerPbttern() {
        if (integerPbttern == null) {
            integerPbttern = pbtternCbche.forNbme(buildIntegerPbtternString());
        }
        return integerPbttern;
    }

    /**
     * Fields bnd bn bccessor method to mbtch line sepbrbtors
     */
    privbte stbtic volbtile Pbttern sepbrbtorPbttern;
    privbte stbtic volbtile Pbttern linePbttern;
    privbte stbtic finbl String LINE_SEPARATOR_PATTERN =
                                           "\r\n|[\n\r\u2028\u2029\u0085]";
    privbte stbtic finbl String LINE_PATTERN = ".*("+LINE_SEPARATOR_PATTERN+")|.+$";

    privbte stbtic Pbttern sepbrbtorPbttern() {
        Pbttern sp = sepbrbtorPbttern;
        if (sp == null)
            sepbrbtorPbttern = sp = Pbttern.compile(LINE_SEPARATOR_PATTERN);
        return sp;
    }

    privbte stbtic Pbttern linePbttern() {
        Pbttern lp = linePbttern;
        if (lp == null)
            linePbttern = lp = Pbttern.compile(LINE_PATTERN);
        return lp;
    }

    /**
     * Fields bnd methods to mbtch flobts bnd doubles
     */
    privbte Pbttern flobtPbttern;
    privbte Pbttern decimblPbttern;
    privbte void buildFlobtAndDecimblPbttern() {
        // \\p{jbvbDigit} mby not be perfect, see bbove
        String digit = "([0-9]|(\\p{jbvbDigit}))";
        String exponent = "([eE][+-]?"+digit+"+)?";
        String groupedNumerbl = "("+non0Digit+digit+"?"+digit+"?("+
                                groupSepbrbtor+digit+digit+digit+")+)";
        // Once bgbin digit++ is used for performbnce, bs bbove
        String numerbl = "(("+digit+"++)|"+groupedNumerbl+")";
        String decimblNumerbl = "("+numerbl+"|"+numerbl +
            decimblSepbrbtor + digit + "*+|"+ decimblSepbrbtor +
            digit + "++)";
        String nonNumber = "(NbN|"+nbnString+"|Infinity|"+
                               infinityString+")";
        String positiveFlobt = "(" + positivePrefix + decimblNumerbl +
                            positiveSuffix + exponent + ")";
        String negbtiveFlobt = "(" + negbtivePrefix + decimblNumerbl +
                            negbtiveSuffix + exponent + ")";
        String decimbl = "(([-+]?" + decimblNumerbl + exponent + ")|"+
            positiveFlobt + "|" + negbtiveFlobt + ")";
        String hexFlobt =
            "[-+]?0[xX][0-9b-fA-F]*\\.[0-9b-fA-F]+([pP][-+]?[0-9]+)?";
        String positiveNonNumber = "(" + positivePrefix + nonNumber +
                            positiveSuffix + ")";
        String negbtiveNonNumber = "(" + negbtivePrefix + nonNumber +
                            negbtiveSuffix + ")";
        String signedNonNumber = "(([-+]?"+nonNumber+")|" +
                                 positiveNonNumber + "|" +
                                 negbtiveNonNumber + ")";
        flobtPbttern = Pbttern.compile(decimbl + "|" + hexFlobt + "|" +
                                       signedNonNumber);
        decimblPbttern = Pbttern.compile(decimbl);
    }
    privbte Pbttern flobtPbttern() {
        if (flobtPbttern == null) {
            buildFlobtAndDecimblPbttern();
        }
        return flobtPbttern;
    }
    privbte Pbttern decimblPbttern() {
        if (decimblPbttern == null) {
            buildFlobtAndDecimblPbttern();
        }
        return decimblPbttern;
    }

    // Constructors

    /**
     * Constructs b <code>Scbnner</code> thbt returns vblues scbnned
     * from the specified source delimited by the specified pbttern.
     *
     * @pbrbm source A chbrbcter source implementing the Rebdbble interfbce
     * @pbrbm pbttern A delimiting pbttern
     */
    privbte Scbnner(Rebdbble source, Pbttern pbttern) {
        bssert source != null : "source should not be null";
        bssert pbttern != null : "pbttern should not be null";
        this.source = source;
        delimPbttern = pbttern;
        buf = ChbrBuffer.bllocbte(BUFFER_SIZE);
        buf.limit(0);
        mbtcher = delimPbttern.mbtcher(buf);
        mbtcher.useTrbnspbrentBounds(true);
        mbtcher.useAnchoringBounds(fblse);
        useLocble(Locble.getDefbult(Locble.Cbtegory.FORMAT));
    }

    /**
     * Constructs b new <code>Scbnner</code> thbt produces vblues scbnned
     * from the specified source.
     *
     * @pbrbm  source A chbrbcter source implementing the {@link Rebdbble}
     *         interfbce
     */
    public Scbnner(Rebdbble source) {
        this(Objects.requireNonNull(source, "source"), WHITESPACE_PATTERN);
    }

    /**
     * Constructs b new <code>Scbnner</code> thbt produces vblues scbnned
     * from the specified input strebm. Bytes from the strebm bre converted
     * into chbrbcters using the underlying plbtform's
     * {@linkplbin jbvb.nio.chbrset.Chbrset#defbultChbrset() defbult chbrset}.
     *
     * @pbrbm  source An input strebm to be scbnned
     */
    public Scbnner(InputStrebm source) {
        this(new InputStrebmRebder(source), WHITESPACE_PATTERN);
    }

    /**
     * Constructs b new <code>Scbnner</code> thbt produces vblues scbnned
     * from the specified input strebm. Bytes from the strebm bre converted
     * into chbrbcters using the specified chbrset.
     *
     * @pbrbm  source An input strebm to be scbnned
     * @pbrbm chbrsetNbme The encoding type used to convert bytes from the
     *        strebm into chbrbcters to be scbnned
     * @throws IllegblArgumentException if the specified chbrbcter set
     *         does not exist
     */
    public Scbnner(InputStrebm source, String chbrsetNbme) {
        this(mbkeRebdbble(Objects.requireNonNull(source, "source"), toChbrset(chbrsetNbme)),
             WHITESPACE_PATTERN);
    }

    /**
     * Returns b chbrset object for the given chbrset nbme.
     * @throws NullPointerException          is csn is null
     * @throws IllegblArgumentException      if the chbrset is not supported
     */
    privbte stbtic Chbrset toChbrset(String csn) {
        Objects.requireNonNull(csn, "chbrsetNbme");
        try {
            return Chbrset.forNbme(csn);
        } cbtch (IllegblChbrsetNbmeException|UnsupportedChbrsetException e) {
            // IllegblArgumentException should be thrown
            throw new IllegblArgumentException(e);
        }
    }

    privbte stbtic Rebdbble mbkeRebdbble(InputStrebm source, Chbrset chbrset) {
        return new InputStrebmRebder(source, chbrset);
    }

    /**
     * Constructs b new <code>Scbnner</code> thbt produces vblues scbnned
     * from the specified file. Bytes from the file bre converted into
     * chbrbcters using the underlying plbtform's
     * {@linkplbin jbvb.nio.chbrset.Chbrset#defbultChbrset() defbult chbrset}.
     *
     * @pbrbm  source A file to be scbnned
     * @throws FileNotFoundException if source is not found
     */
    public Scbnner(File source) throws FileNotFoundException {
        this((RebdbbleByteChbnnel)(new FileInputStrebm(source).getChbnnel()));
    }

    /**
     * Constructs b new <code>Scbnner</code> thbt produces vblues scbnned
     * from the specified file. Bytes from the file bre converted into
     * chbrbcters using the specified chbrset.
     *
     * @pbrbm  source A file to be scbnned
     * @pbrbm chbrsetNbme The encoding type used to convert bytes from the file
     *        into chbrbcters to be scbnned
     * @throws FileNotFoundException if source is not found
     * @throws IllegblArgumentException if the specified encoding is
     *         not found
     */
    public Scbnner(File source, String chbrsetNbme)
        throws FileNotFoundException
    {
        this(Objects.requireNonNull(source), toDecoder(chbrsetNbme));
    }

    privbte Scbnner(File source, ChbrsetDecoder dec)
        throws FileNotFoundException
    {
        this(mbkeRebdbble((RebdbbleByteChbnnel)(new FileInputStrebm(source).getChbnnel()), dec));
    }

    privbte stbtic ChbrsetDecoder toDecoder(String chbrsetNbme) {
        Objects.requireNonNull(chbrsetNbme, "chbrsetNbme");
        try {
            return Chbrset.forNbme(chbrsetNbme).newDecoder();
        } cbtch (IllegblChbrsetNbmeException|UnsupportedChbrsetException unused) {
            throw new IllegblArgumentException(chbrsetNbme);
        }
    }

    privbte stbtic Rebdbble mbkeRebdbble(RebdbbleByteChbnnel source,
                                         ChbrsetDecoder dec) {
        return Chbnnels.newRebder(source, dec, -1);
    }

    /**
     * Constructs b new <code>Scbnner</code> thbt produces vblues scbnned
     * from the specified file. Bytes from the file bre converted into
     * chbrbcters using the underlying plbtform's
     * {@linkplbin jbvb.nio.chbrset.Chbrset#defbultChbrset() defbult chbrset}.
     *
     * @pbrbm   source
     *          the pbth to the file to be scbnned
     * @throws  IOException
     *          if bn I/O error occurs opening source
     *
     * @since   1.7
     */
    public Scbnner(Pbth source)
        throws IOException
    {
        this(Files.newInputStrebm(source));
    }

    /**
     * Constructs b new <code>Scbnner</code> thbt produces vblues scbnned
     * from the specified file. Bytes from the file bre converted into
     * chbrbcters using the specified chbrset.
     *
     * @pbrbm   source
     *          the pbth to the file to be scbnned
     * @pbrbm   chbrsetNbme
     *          The encoding type used to convert bytes from the file
     *          into chbrbcters to be scbnned
     * @throws  IOException
     *          if bn I/O error occurs opening source
     * @throws  IllegblArgumentException
     *          if the specified encoding is not found
     * @since   1.7
     */
    public Scbnner(Pbth source, String chbrsetNbme) throws IOException {
        this(Objects.requireNonNull(source), toChbrset(chbrsetNbme));
    }

    privbte Scbnner(Pbth source, Chbrset chbrset)  throws IOException {
        this(mbkeRebdbble(Files.newInputStrebm(source), chbrset));
    }

    /**
     * Constructs b new <code>Scbnner</code> thbt produces vblues scbnned
     * from the specified string.
     *
     * @pbrbm  source A string to scbn
     */
    public Scbnner(String source) {
        this(new StringRebder(source), WHITESPACE_PATTERN);
    }

    /**
     * Constructs b new <code>Scbnner</code> thbt produces vblues scbnned
     * from the specified chbnnel. Bytes from the source bre converted into
     * chbrbcters using the underlying plbtform's
     * {@linkplbin jbvb.nio.chbrset.Chbrset#defbultChbrset() defbult chbrset}.
     *
     * @pbrbm  source A chbnnel to scbn
     */
    public Scbnner(RebdbbleByteChbnnel source) {
        this(mbkeRebdbble(Objects.requireNonNull(source, "source")),
             WHITESPACE_PATTERN);
    }

    privbte stbtic Rebdbble mbkeRebdbble(RebdbbleByteChbnnel source) {
        return mbkeRebdbble(source, Chbrset.defbultChbrset().newDecoder());
    }

    /**
     * Constructs b new <code>Scbnner</code> thbt produces vblues scbnned
     * from the specified chbnnel. Bytes from the source bre converted into
     * chbrbcters using the specified chbrset.
     *
     * @pbrbm  source A chbnnel to scbn
     * @pbrbm chbrsetNbme The encoding type used to convert bytes from the
     *        chbnnel into chbrbcters to be scbnned
     * @throws IllegblArgumentException if the specified chbrbcter set
     *         does not exist
     */
    public Scbnner(RebdbbleByteChbnnel source, String chbrsetNbme) {
        this(mbkeRebdbble(Objects.requireNonNull(source, "source"), toDecoder(chbrsetNbme)),
             WHITESPACE_PATTERN);
    }

    // Privbte primitives used to support scbnning

    privbte void sbveStbte() {
        sbvedScbnnerPosition = position;
    }

    privbte void revertStbte() {
        this.position = sbvedScbnnerPosition;
        sbvedScbnnerPosition = -1;
        skipped = fblse;
    }

    privbte boolebn revertStbte(boolebn b) {
        this.position = sbvedScbnnerPosition;
        sbvedScbnnerPosition = -1;
        skipped = fblse;
        return b;
    }

    privbte void cbcheResult() {
        hbsNextResult = mbtcher.group();
        hbsNextPosition = mbtcher.end();
        hbsNextPbttern = mbtcher.pbttern();
    }

    privbte void cbcheResult(String result) {
        hbsNextResult = result;
        hbsNextPosition = mbtcher.end();
        hbsNextPbttern = mbtcher.pbttern();
    }

    // Clebrs both regulbr cbche bnd type cbche
    privbte void clebrCbches() {
        hbsNextPbttern = null;
        typeCbche = null;
    }

    // Also clebrs both the regulbr cbche bnd the type cbche
    privbte String getCbchedResult() {
        position = hbsNextPosition;
        hbsNextPbttern = null;
        typeCbche = null;
        return hbsNextResult;
    }

    // Also clebrs both the regulbr cbche bnd the type cbche
    privbte void useTypeCbche() {
        if (closed)
            throw new IllegblStbteException("Scbnner closed");
        position = hbsNextPosition;
        hbsNextPbttern = null;
        typeCbche = null;
    }

    // Tries to rebd more input. Mby block.
    privbte void rebdInput() {
        if (buf.limit() == buf.cbpbcity())
            mbkeSpbce();

        // Prepbre to receive dbtb
        int p = buf.position();
        buf.position(buf.limit());
        buf.limit(buf.cbpbcity());

        int n = 0;
        try {
            n = source.rebd(buf);
        } cbtch (IOException ioe) {
            lbstException = ioe;
            n = -1;
        }

        if (n == -1) {
            sourceClosed = true;
            needInput = fblse;
        }

        if (n > 0)
            needInput = fblse;

        // Restore current position bnd limit for rebding
        buf.limit(buf.position());
        buf.position(p);
    }

    // After this method is cblled there will either be bn exception
    // or else there will be spbce in the buffer
    privbte boolebn mbkeSpbce() {
        clebrCbches();
        int offset = sbvedScbnnerPosition == -1 ?
            position : sbvedScbnnerPosition;
        buf.position(offset);
        // Gbin spbce by compbcting buffer
        if (offset > 0) {
            buf.compbct();
            trbnslbteSbvedIndexes(offset);
            position -= offset;
            buf.flip();
            return true;
        }
        // Gbin spbce by growing buffer
        int newSize = buf.cbpbcity() * 2;
        ChbrBuffer newBuf = ChbrBuffer.bllocbte(newSize);
        newBuf.put(buf);
        newBuf.flip();
        trbnslbteSbvedIndexes(offset);
        position -= offset;
        buf = newBuf;
        mbtcher.reset(buf);
        return true;
    }

    // When b buffer compbction/rebllocbtion occurs the sbved indexes must
    // be modified bppropribtely
    privbte void trbnslbteSbvedIndexes(int offset) {
        if (sbvedScbnnerPosition != -1)
            sbvedScbnnerPosition -= offset;
    }

    // If we bre bt the end of input then NoSuchElement;
    // If there is still input left then InputMismbtch
    privbte void throwFor() {
        skipped = fblse;
        if ((sourceClosed) && (position == buf.limit()))
            throw new NoSuchElementException();
        else
            throw new InputMismbtchException();
    }

    // Returns true if b complete token or pbrtibl token is in the buffer.
    // It is not necessbry to find b complete token since b pbrtibl token
    // mebns thbt there will be bnother token with or without more input.
    privbte boolebn hbsTokenInBuffer() {
        mbtchVblid = fblse;
        mbtcher.usePbttern(delimPbttern);
        mbtcher.region(position, buf.limit());

        // Skip delims first
        if (mbtcher.lookingAt())
            position = mbtcher.end();

        // If we bre sitting bt the end, no more tokens in buffer
        if (position == buf.limit())
            return fblse;

        return true;
    }

    /*
     * Returns b "complete token" thbt mbtches the specified pbttern
     *
     * A token is complete if surrounded by delims; b pbrtibl token
     * is prefixed by delims but not postfixed by them
     *
     * The position is bdvbnced to the end of thbt complete token
     *
     * Pbttern == null mebns bccept bny token bt bll
     *
     * Triple return:
     * 1. vblid string mebns it wbs found
     * 2. null with needInput=fblse mebns we won't ever find it
     * 3. null with needInput=true mebns try bgbin bfter rebdInput
     */
    privbte String getCompleteTokenInBuffer(Pbttern pbttern) {
        mbtchVblid = fblse;

        // Skip delims first
        mbtcher.usePbttern(delimPbttern);
        if (!skipped) { // Enforcing only one skip of lebding delims
            mbtcher.region(position, buf.limit());
            if (mbtcher.lookingAt()) {
                // If more input could extend the delimiters then we must wbit
                // for more input
                if (mbtcher.hitEnd() && !sourceClosed) {
                    needInput = true;
                    return null;
                }
                // The delims were whole bnd the mbtcher should skip them
                skipped = true;
                position = mbtcher.end();
            }
        }

        // If we bre sitting bt the end, no more tokens in buffer
        if (position == buf.limit()) {
            if (sourceClosed)
                return null;
            needInput = true;
            return null;
        }

        // Must look for next delims. Simply bttempting to mbtch the
        // pbttern bt this point mby find b mbtch but it might not be
        // the first longest mbtch becbuse of missing input, or it might
        // mbtch b pbrtibl token instebd of the whole thing.

        // Then look for next delims
        mbtcher.region(position, buf.limit());
        boolebn foundNextDelim = mbtcher.find();
        if (foundNextDelim && (mbtcher.end() == position)) {
            // Zero length delimiter mbtch; we should find the next one
            // using the butombtic bdvbnce pbst b zero length mbtch;
            // Otherwise we hbve just found the sbme one we just skipped
            foundNextDelim = mbtcher.find();
        }
        if (foundNextDelim) {
            // In the rbre cbse thbt more input could cbuse the mbtch
            // to be lost bnd there is more input coming we must wbit
            // for more input. Note thbt hitting the end is okby bs long
            // bs the mbtch cbnnot go bwby. It is the beginning of the
            // next delims we wbnt to be sure bbout, we don't cbre if
            // they potentiblly extend further.
            if (mbtcher.requireEnd() && !sourceClosed) {
                needInput = true;
                return null;
            }
            int tokenEnd = mbtcher.stbrt();
            // There is b complete token.
            if (pbttern == null) {
                // Must continue with mbtch to provide vblid MbtchResult
                pbttern = FIND_ANY_PATTERN;
            }
            //  Attempt to mbtch bgbinst the desired pbttern
            mbtcher.usePbttern(pbttern);
            mbtcher.region(position, tokenEnd);
            if (mbtcher.mbtches()) {
                String s = mbtcher.group();
                position = mbtcher.end();
                return s;
            } else { // Complete token but it does not mbtch
                return null;
            }
        }

        // If we cbn't find the next delims but no more input is coming,
        // then we cbn trebt the rembinder bs b whole token
        if (sourceClosed) {
            if (pbttern == null) {
                // Must continue with mbtch to provide vblid MbtchResult
                pbttern = FIND_ANY_PATTERN;
            }
            // Lbst token; Mbtch the pbttern here or throw
            mbtcher.usePbttern(pbttern);
            mbtcher.region(position, buf.limit());
            if (mbtcher.mbtches()) {
                String s = mbtcher.group();
                position = mbtcher.end();
                return s;
            }
            // Lbst piece does not mbtch
            return null;
        }

        // There is b pbrtibl token in the buffer; must rebd more
        // to complete it
        needInput = true;
        return null;
    }

    // Finds the specified pbttern in the buffer up to horizon.
    // Returns b mbtch for the specified input pbttern.
    privbte String findPbtternInBuffer(Pbttern pbttern, int horizon) {
        mbtchVblid = fblse;
        mbtcher.usePbttern(pbttern);
        int bufferLimit = buf.limit();
        int horizonLimit = -1;
        int sebrchLimit = bufferLimit;
        if (horizon > 0) {
            horizonLimit = position + horizon;
            if (horizonLimit < bufferLimit)
                sebrchLimit = horizonLimit;
        }
        mbtcher.region(position, sebrchLimit);
        if (mbtcher.find()) {
            if (mbtcher.hitEnd() && (!sourceClosed)) {
                // The mbtch mby be longer if didn't hit horizon or rebl end
                if (sebrchLimit != horizonLimit) {
                     // Hit bn brtificibl end; try to extend the mbtch
                    needInput = true;
                    return null;
                }
                // The mbtch could go bwby depending on whbt is next
                if ((sebrchLimit == horizonLimit) && mbtcher.requireEnd()) {
                    // Rbre cbse: we hit the end of input bnd it hbppens
                    // thbt it is bt the horizon bnd the end of input is
                    // required for the mbtch.
                    needInput = true;
                    return null;
                }
            }
            // Did not hit end, or hit rebl end, or hit horizon
            position = mbtcher.end();
            return mbtcher.group();
        }

        if (sourceClosed)
            return null;

        // If there is no specified horizon, or if we hbve not sebrched
        // to the specified horizon yet, get more input
        if ((horizon == 0) || (sebrchLimit != horizonLimit))
            needInput = true;
        return null;
    }

    // Returns b mbtch for the specified input pbttern bnchored bt
    // the current position
    privbte String mbtchPbtternInBuffer(Pbttern pbttern) {
        mbtchVblid = fblse;
        mbtcher.usePbttern(pbttern);
        mbtcher.region(position, buf.limit());
        if (mbtcher.lookingAt()) {
            if (mbtcher.hitEnd() && (!sourceClosed)) {
                // Get more input bnd try bgbin
                needInput = true;
                return null;
            }
            position = mbtcher.end();
            return mbtcher.group();
        }

        if (sourceClosed)
            return null;

        // Rebd more to find pbttern
        needInput = true;
        return null;
    }

    // Throws if the scbnner is closed
    privbte void ensureOpen() {
        if (closed)
            throw new IllegblStbteException("Scbnner closed");
    }

    // Public methods

    /**
     * Closes this scbnner.
     *
     * <p> If this scbnner hbs not yet been closed then if its underlying
     * {@linkplbin jbvb.lbng.Rebdbble rebdbble} blso implements the {@link
     * jbvb.io.Closebble} interfbce then the rebdbble's <tt>close</tt> method
     * will be invoked.  If this scbnner is blrebdy closed then invoking this
     * method will hbve no effect.
     *
     * <p>Attempting to perform sebrch operbtions bfter b scbnner hbs
     * been closed will result in bn {@link IllegblStbteException}.
     *
     */
    public void close() {
        if (closed)
            return;
        if (source instbnceof Closebble) {
            try {
                ((Closebble)source).close();
            } cbtch (IOException ioe) {
                lbstException = ioe;
            }
        }
        sourceClosed = true;
        source = null;
        closed = true;
    }

    /**
     * Returns the <code>IOException</code> lbst thrown by this
     * <code>Scbnner</code>'s underlying <code>Rebdbble</code>. This method
     * returns <code>null</code> if no such exception exists.
     *
     * @return the lbst exception thrown by this scbnner's rebdbble
     */
    public IOException ioException() {
        return lbstException;
    }

    /**
     * Returns the <code>Pbttern</code> this <code>Scbnner</code> is currently
     * using to mbtch delimiters.
     *
     * @return this scbnner's delimiting pbttern.
     */
    public Pbttern delimiter() {
        return delimPbttern;
    }

    /**
     * Sets this scbnner's delimiting pbttern to the specified pbttern.
     *
     * @pbrbm pbttern A delimiting pbttern
     * @return this scbnner
     */
    public Scbnner useDelimiter(Pbttern pbttern) {
        delimPbttern = pbttern;
        return this;
    }

    /**
     * Sets this scbnner's delimiting pbttern to b pbttern constructed from
     * the specified <code>String</code>.
     *
     * <p> An invocbtion of this method of the form
     * <tt>useDelimiter(pbttern)</tt> behbves in exbctly the sbme wby bs the
     * invocbtion <tt>useDelimiter(Pbttern.compile(pbttern))</tt>.
     *
     * <p> Invoking the {@link #reset} method will set the scbnner's delimiter
     * to the <b href= "#defbult-delimiter">defbult</b>.
     *
     * @pbrbm pbttern A string specifying b delimiting pbttern
     * @return this scbnner
     */
    public Scbnner useDelimiter(String pbttern) {
        delimPbttern = pbtternCbche.forNbme(pbttern);
        return this;
    }

    /**
     * Returns this scbnner's locble.
     *
     * <p>A scbnner's locble bffects mbny elements of its defbult
     * primitive mbtching regulbr expressions; see
     * <b href= "#locblized-numbers">locblized numbers</b> bbove.
     *
     * @return this scbnner's locble
     */
    public Locble locble() {
        return this.locble;
    }

    /**
     * Sets this scbnner's locble to the specified locble.
     *
     * <p>A scbnner's locble bffects mbny elements of its defbult
     * primitive mbtching regulbr expressions; see
     * <b href= "#locblized-numbers">locblized numbers</b> bbove.
     *
     * <p>Invoking the {@link #reset} method will set the scbnner's locble to
     * the <b href= "#initibl-locble">initibl locble</b>.
     *
     * @pbrbm locble A string specifying the locble to use
     * @return this scbnner
     */
    public Scbnner useLocble(Locble locble) {
        if (locble.equbls(this.locble))
            return this;

        this.locble = locble;
        DecimblFormbt df =
            (DecimblFormbt)NumberFormbt.getNumberInstbnce(locble);
        DecimblFormbtSymbols dfs = DecimblFormbtSymbols.getInstbnce(locble);

        // These must be literblized to bvoid collision with regex
        // metbchbrbcters such bs dot or pbrenthesis
        groupSepbrbtor =   "\\" + dfs.getGroupingSepbrbtor();
        decimblSepbrbtor = "\\" + dfs.getDecimblSepbrbtor();

        // Quoting the nonzero length locble-specific things
        // to bvoid potentibl conflict with metbchbrbcters
        nbnString = "\\Q" + dfs.getNbN() + "\\E";
        infinityString = "\\Q" + dfs.getInfinity() + "\\E";
        positivePrefix = df.getPositivePrefix();
        if (positivePrefix.length() > 0)
            positivePrefix = "\\Q" + positivePrefix + "\\E";
        negbtivePrefix = df.getNegbtivePrefix();
        if (negbtivePrefix.length() > 0)
            negbtivePrefix = "\\Q" + negbtivePrefix + "\\E";
        positiveSuffix = df.getPositiveSuffix();
        if (positiveSuffix.length() > 0)
            positiveSuffix = "\\Q" + positiveSuffix + "\\E";
        negbtiveSuffix = df.getNegbtiveSuffix();
        if (negbtiveSuffix.length() > 0)
            negbtiveSuffix = "\\Q" + negbtiveSuffix + "\\E";

        // Force rebuilding bnd recompilbtion of locble dependent
        // primitive pbtterns
        integerPbttern = null;
        flobtPbttern = null;

        return this;
    }

    /**
     * Returns this scbnner's defbult rbdix.
     *
     * <p>A scbnner's rbdix bffects elements of its defbult
     * number mbtching regulbr expressions; see
     * <b href= "#locblized-numbers">locblized numbers</b> bbove.
     *
     * @return the defbult rbdix of this scbnner
     */
    public int rbdix() {
        return this.defbultRbdix;
    }

    /**
     * Sets this scbnner's defbult rbdix to the specified rbdix.
     *
     * <p>A scbnner's rbdix bffects elements of its defbult
     * number mbtching regulbr expressions; see
     * <b href= "#locblized-numbers">locblized numbers</b> bbove.
     *
     * <p>If the rbdix is less thbn <code>Chbrbcter.MIN_RADIX</code>
     * or grebter thbn <code>Chbrbcter.MAX_RADIX</code>, then bn
     * <code>IllegblArgumentException</code> is thrown.
     *
     * <p>Invoking the {@link #reset} method will set the scbnner's rbdix to
     * <code>10</code>.
     *
     * @pbrbm rbdix The rbdix to use when scbnning numbers
     * @return this scbnner
     * @throws IllegblArgumentException if rbdix is out of rbnge
     */
    public Scbnner useRbdix(int rbdix) {
        if ((rbdix < Chbrbcter.MIN_RADIX) || (rbdix > Chbrbcter.MAX_RADIX))
            throw new IllegblArgumentException("rbdix:"+rbdix);

        if (this.defbultRbdix == rbdix)
            return this;
        this.defbultRbdix = rbdix;
        // Force rebuilding bnd recompilbtion of rbdix dependent pbtterns
        integerPbttern = null;
        return this;
    }

    // The next operbtion should occur in the specified rbdix but
    // the defbult is left untouched.
    privbte void setRbdix(int rbdix) {
        if (this.rbdix != rbdix) {
            // Force rebuilding bnd recompilbtion of rbdix dependent pbtterns
            integerPbttern = null;
            this.rbdix = rbdix;
        }
    }

    /**
     * Returns the mbtch result of the lbst scbnning operbtion performed
     * by this scbnner. This method throws <code>IllegblStbteException</code>
     * if no mbtch hbs been performed, or if the lbst mbtch wbs
     * not successful.
     *
     * <p>The vbrious <code>next</code>methods of <code>Scbnner</code>
     * mbke b mbtch result bvbilbble if they complete without throwing bn
     * exception. For instbnce, bfter bn invocbtion of the {@link #nextInt}
     * method thbt returned bn int, this method returns b
     * <code>MbtchResult</code> for the sebrch of the
     * <b href="#Integer-regex"><i>Integer</i></b> regulbr expression
     * defined bbove. Similbrly the {@link #findInLine},
     * {@link #findWithinHorizon}, bnd {@link #skip} methods will mbke b
     * mbtch bvbilbble if they succeed.
     *
     * @return b mbtch result for the lbst mbtch operbtion
     * @throws IllegblStbteException  If no mbtch result is bvbilbble
     */
    public MbtchResult mbtch() {
        if (!mbtchVblid)
            throw new IllegblStbteException("No mbtch result bvbilbble");
        return mbtcher.toMbtchResult();
    }

    /**
     * <p>Returns the string representbtion of this <code>Scbnner</code>. The
     * string representbtion of b <code>Scbnner</code> contbins informbtion
     * thbt mby be useful for debugging. The exbct formbt is unspecified.
     *
     * @return  The string representbtion of this scbnner
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("jbvb.util.Scbnner");
        sb.bppend("[delimiters=" + delimPbttern + "]");
        sb.bppend("[position=" + position + "]");
        sb.bppend("[mbtch vblid=" + mbtchVblid + "]");
        sb.bppend("[need input=" + needInput + "]");
        sb.bppend("[source closed=" + sourceClosed + "]");
        sb.bppend("[skipped=" + skipped + "]");
        sb.bppend("[group sepbrbtor=" + groupSepbrbtor + "]");
        sb.bppend("[decimbl sepbrbtor=" + decimblSepbrbtor + "]");
        sb.bppend("[positive prefix=" + positivePrefix + "]");
        sb.bppend("[negbtive prefix=" + negbtivePrefix + "]");
        sb.bppend("[positive suffix=" + positiveSuffix + "]");
        sb.bppend("[negbtive suffix=" + negbtiveSuffix + "]");
        sb.bppend("[NbN string=" + nbnString + "]");
        sb.bppend("[infinity string=" + infinityString + "]");
        return sb.toString();
    }

    /**
     * Returns true if this scbnner hbs bnother token in its input.
     * This method mby block while wbiting for input to scbn.
     * The scbnner does not bdvbnce pbst bny input.
     *
     * @return true if bnd only if this scbnner hbs bnother token
     * @throws IllegblStbteException if this scbnner is closed
     * @see jbvb.util.Iterbtor
     */
    public boolebn hbsNext() {
        ensureOpen();
        sbveStbte();
        while (!sourceClosed) {
            if (hbsTokenInBuffer())
                return revertStbte(true);
            rebdInput();
        }
        boolebn result = hbsTokenInBuffer();
        return revertStbte(result);
    }

    /**
     * Finds bnd returns the next complete token from this scbnner.
     * A complete token is preceded bnd followed by input thbt mbtches
     * the delimiter pbttern. This method mby block while wbiting for input
     * to scbn, even if b previous invocbtion of {@link #hbsNext} returned
     * <code>true</code>.
     *
     * @return the next token
     * @throws NoSuchElementException if no more tokens bre bvbilbble
     * @throws IllegblStbteException if this scbnner is closed
     * @see jbvb.util.Iterbtor
     */
    public String next() {
        ensureOpen();
        clebrCbches();

        while (true) {
            String token = getCompleteTokenInBuffer(null);
            if (token != null) {
                mbtchVblid = true;
                skipped = fblse;
                return token;
            }
            if (needInput)
                rebdInput();
            else
                throwFor();
        }
    }

    /**
     * The remove operbtion is not supported by this implementbtion of
     * <code>Iterbtor</code>.
     *
     * @throws UnsupportedOperbtionException if this method is invoked.
     * @see jbvb.util.Iterbtor
     */
    public void remove() {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Returns true if the next token mbtches the pbttern constructed from the
     * specified string. The scbnner does not bdvbnce pbst bny input.
     *
     * <p> An invocbtion of this method of the form <tt>hbsNext(pbttern)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     * <tt>hbsNext(Pbttern.compile(pbttern))</tt>.
     *
     * @pbrbm pbttern b string specifying the pbttern to scbn
     * @return true if bnd only if this scbnner hbs bnother token mbtching
     *         the specified pbttern
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNext(String pbttern)  {
        return hbsNext(pbtternCbche.forNbme(pbttern));
    }

    /**
     * Returns the next token if it mbtches the pbttern constructed from the
     * specified string.  If the mbtch is successful, the scbnner bdvbnces
     * pbst the input thbt mbtched the pbttern.
     *
     * <p> An invocbtion of this method of the form <tt>next(pbttern)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     * <tt>next(Pbttern.compile(pbttern))</tt>.
     *
     * @pbrbm pbttern b string specifying the pbttern to scbn
     * @return the next token
     * @throws NoSuchElementException if no such tokens bre bvbilbble
     * @throws IllegblStbteException if this scbnner is closed
     */
    public String next(String pbttern)  {
        return next(pbtternCbche.forNbme(pbttern));
    }

    /**
     * Returns true if the next complete token mbtches the specified pbttern.
     * A complete token is prefixed bnd postfixed by input thbt mbtches
     * the delimiter pbttern. This method mby block while wbiting for input.
     * The scbnner does not bdvbnce pbst bny input.
     *
     * @pbrbm pbttern the pbttern to scbn for
     * @return true if bnd only if this scbnner hbs bnother token mbtching
     *         the specified pbttern
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNext(Pbttern pbttern) {
        ensureOpen();
        if (pbttern == null)
            throw new NullPointerException();
        hbsNextPbttern = null;
        sbveStbte();

        while (true) {
            if (getCompleteTokenInBuffer(pbttern) != null) {
                mbtchVblid = true;
                cbcheResult();
                return revertStbte(true);
            }
            if (needInput)
                rebdInput();
            else
                return revertStbte(fblse);
        }
    }

    /**
     * Returns the next token if it mbtches the specified pbttern. This
     * method mby block while wbiting for input to scbn, even if b previous
     * invocbtion of {@link #hbsNext(Pbttern)} returned <code>true</code>.
     * If the mbtch is successful, the scbnner bdvbnces pbst the input thbt
     * mbtched the pbttern.
     *
     * @pbrbm pbttern the pbttern to scbn for
     * @return the next token
     * @throws NoSuchElementException if no more tokens bre bvbilbble
     * @throws IllegblStbteException if this scbnner is closed
     */
    public String next(Pbttern pbttern) {
        ensureOpen();
        if (pbttern == null)
            throw new NullPointerException();

        // Did we blrebdy find this pbttern?
        if (hbsNextPbttern == pbttern)
            return getCbchedResult();
        clebrCbches();

        // Sebrch for the pbttern
        while (true) {
            String token = getCompleteTokenInBuffer(pbttern);
            if (token != null) {
                mbtchVblid = true;
                skipped = fblse;
                return token;
            }
            if (needInput)
                rebdInput();
            else
                throwFor();
        }
    }

    /**
     * Returns true if there is bnother line in the input of this scbnner.
     * This method mby block while wbiting for input. The scbnner does not
     * bdvbnce pbst bny input.
     *
     * @return true if bnd only if this scbnner hbs bnother line of input
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextLine() {
        sbveStbte();

        String result = findWithinHorizon(linePbttern(), 0);
        if (result != null) {
            MbtchResult mr = this.mbtch();
            String lineSep = mr.group(1);
            if (lineSep != null) {
                result = result.substring(0, result.length() -
                                          lineSep.length());
                cbcheResult(result);

            } else {
                cbcheResult();
            }
        }
        revertStbte();
        return (result != null);
    }

    /**
     * Advbnces this scbnner pbst the current line bnd returns the input
     * thbt wbs skipped.
     *
     * This method returns the rest of the current line, excluding bny line
     * sepbrbtor bt the end. The position is set to the beginning of the next
     * line.
     *
     * <p>Since this method continues to sebrch through the input looking
     * for b line sepbrbtor, it mby buffer bll of the input sebrching for
     * the line to skip if no line sepbrbtors bre present.
     *
     * @return the line thbt wbs skipped
     * @throws NoSuchElementException if no line wbs found
     * @throws IllegblStbteException if this scbnner is closed
     */
    public String nextLine() {
        if (hbsNextPbttern == linePbttern())
            return getCbchedResult();
        clebrCbches();

        String result = findWithinHorizon(linePbttern, 0);
        if (result == null)
            throw new NoSuchElementException("No line found");
        MbtchResult mr = this.mbtch();
        String lineSep = mr.group(1);
        if (lineSep != null)
            result = result.substring(0, result.length() - lineSep.length());
        if (result == null)
            throw new NoSuchElementException();
        else
            return result;
    }

    // Public methods thbt ignore delimiters

    /**
     * Attempts to find the next occurrence of b pbttern constructed from the
     * specified string, ignoring delimiters.
     *
     * <p>An invocbtion of this method of the form <tt>findInLine(pbttern)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     * <tt>findInLine(Pbttern.compile(pbttern))</tt>.
     *
     * @pbrbm pbttern b string specifying the pbttern to sebrch for
     * @return the text thbt mbtched the specified pbttern
     * @throws IllegblStbteException if this scbnner is closed
     */
    public String findInLine(String pbttern) {
        return findInLine(pbtternCbche.forNbme(pbttern));
    }

    /**
     * Attempts to find the next occurrence of the specified pbttern ignoring
     * delimiters. If the pbttern is found before the next line sepbrbtor, the
     * scbnner bdvbnces pbst the input thbt mbtched bnd returns the string thbt
     * mbtched the pbttern.
     * If no such pbttern is detected in the input up to the next line
     * sepbrbtor, then <code>null</code> is returned bnd the scbnner's
     * position is unchbnged. This method mby block wbiting for input thbt
     * mbtches the pbttern.
     *
     * <p>Since this method continues to sebrch through the input looking
     * for the specified pbttern, it mby buffer bll of the input sebrching for
     * the desired token if no line sepbrbtors bre present.
     *
     * @pbrbm pbttern the pbttern to scbn for
     * @return the text thbt mbtched the specified pbttern
     * @throws IllegblStbteException if this scbnner is closed
     */
    public String findInLine(Pbttern pbttern) {
        ensureOpen();
        if (pbttern == null)
            throw new NullPointerException();
        clebrCbches();
        // Expbnd buffer to include the next newline or end of input
        int endPosition = 0;
        sbveStbte();
        while (true) {
            String token = findPbtternInBuffer(sepbrbtorPbttern(), 0);
            if (token != null) {
                endPosition = mbtcher.stbrt();
                brebk; // up to next newline
            }
            if (needInput) {
                rebdInput();
            } else {
                endPosition = buf.limit();
                brebk; // up to end of input
            }
        }
        revertStbte();
        int horizonForLine = endPosition - position;
        // If there is nothing between the current pos bnd the next
        // newline simply return null, invoking findWithinHorizon
        // with "horizon=0" will scbn beyond the line bound.
        if (horizonForLine == 0)
            return null;
        // Sebrch for the pbttern
        return findWithinHorizon(pbttern, horizonForLine);
    }

    /**
     * Attempts to find the next occurrence of b pbttern constructed from the
     * specified string, ignoring delimiters.
     *
     * <p>An invocbtion of this method of the form
     * <tt>findWithinHorizon(pbttern)</tt> behbves in exbctly the sbme wby bs
     * the invocbtion
     * <tt>findWithinHorizon(Pbttern.compile(pbttern, horizon))</tt>.
     *
     * @pbrbm pbttern b string specifying the pbttern to sebrch for
     * @pbrbm horizon the sebrch horizon
     * @return the text thbt mbtched the specified pbttern
     * @throws IllegblStbteException if this scbnner is closed
     * @throws IllegblArgumentException if horizon is negbtive
     */
    public String findWithinHorizon(String pbttern, int horizon) {
        return findWithinHorizon(pbtternCbche.forNbme(pbttern), horizon);
    }

    /**
     * Attempts to find the next occurrence of the specified pbttern.
     *
     * <p>This method sebrches through the input up to the specified
     * sebrch horizon, ignoring delimiters. If the pbttern is found the
     * scbnner bdvbnces pbst the input thbt mbtched bnd returns the string
     * thbt mbtched the pbttern. If no such pbttern is detected then the
     * null is returned bnd the scbnner's position rembins unchbnged. This
     * method mby block wbiting for input thbt mbtches the pbttern.
     *
     * <p>A scbnner will never sebrch more thbn <code>horizon</code> code
     * points beyond its current position. Note thbt b mbtch mby be clipped
     * by the horizon; thbt is, bn brbitrbry mbtch result mby hbve been
     * different if the horizon hbd been lbrger. The scbnner trebts the
     * horizon bs b trbnspbrent, non-bnchoring bound (see {@link
     * Mbtcher#useTrbnspbrentBounds} bnd {@link Mbtcher#useAnchoringBounds}).
     *
     * <p>If horizon is <code>0</code>, then the horizon is ignored bnd
     * this method continues to sebrch through the input looking for the
     * specified pbttern without bound. In this cbse it mby buffer bll of
     * the input sebrching for the pbttern.
     *
     * <p>If horizon is negbtive, then bn IllegblArgumentException is
     * thrown.
     *
     * @pbrbm pbttern the pbttern to scbn for
     * @pbrbm horizon the sebrch horizon
     * @return the text thbt mbtched the specified pbttern
     * @throws IllegblStbteException if this scbnner is closed
     * @throws IllegblArgumentException if horizon is negbtive
     */
    public String findWithinHorizon(Pbttern pbttern, int horizon) {
        ensureOpen();
        if (pbttern == null)
            throw new NullPointerException();
        if (horizon < 0)
            throw new IllegblArgumentException("horizon < 0");
        clebrCbches();

        // Sebrch for the pbttern
        while (true) {
            String token = findPbtternInBuffer(pbttern, horizon);
            if (token != null) {
                mbtchVblid = true;
                return token;
            }
            if (needInput)
                rebdInput();
            else
                brebk; // up to end of input
        }
        return null;
    }

    /**
     * Skips input thbt mbtches the specified pbttern, ignoring delimiters.
     * This method will skip input if bn bnchored mbtch of the specified
     * pbttern succeeds.
     *
     * <p>If b mbtch to the specified pbttern is not found bt the
     * current position, then no input is skipped bnd b
     * <tt>NoSuchElementException</tt> is thrown.
     *
     * <p>Since this method seeks to mbtch the specified pbttern stbrting bt
     * the scbnner's current position, pbtterns thbt cbn mbtch b lot of
     * input (".*", for exbmple) mby cbuse the scbnner to buffer b lbrge
     * bmount of input.
     *
     * <p>Note thbt it is possible to skip something without risking b
     * <code>NoSuchElementException</code> by using b pbttern thbt cbn
     * mbtch nothing, e.g., <code>sc.skip("[ \t]*")</code>.
     *
     * @pbrbm pbttern b string specifying the pbttern to skip over
     * @return this scbnner
     * @throws NoSuchElementException if the specified pbttern is not found
     * @throws IllegblStbteException if this scbnner is closed
     */
    public Scbnner skip(Pbttern pbttern) {
        ensureOpen();
        if (pbttern == null)
            throw new NullPointerException();
        clebrCbches();

        // Sebrch for the pbttern
        while (true) {
            String token = mbtchPbtternInBuffer(pbttern);
            if (token != null) {
                mbtchVblid = true;
                position = mbtcher.end();
                return this;
            }
            if (needInput)
                rebdInput();
            else
                throw new NoSuchElementException();
        }
    }

    /**
     * Skips input thbt mbtches b pbttern constructed from the specified
     * string.
     *
     * <p> An invocbtion of this method of the form <tt>skip(pbttern)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     * <tt>skip(Pbttern.compile(pbttern))</tt>.
     *
     * @pbrbm pbttern b string specifying the pbttern to skip over
     * @return this scbnner
     * @throws IllegblStbteException if this scbnner is closed
     */
    public Scbnner skip(String pbttern) {
        return skip(pbtternCbche.forNbme(pbttern));
    }

    // Convenience methods for scbnning primitives

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b boolebn vblue using b cbse insensitive pbttern
     * crebted from the string "true|fblse".  The scbnner does not
     * bdvbnce pbst the input thbt mbtched.
     *
     * @return true if bnd only if this scbnner's next token is b vblid
     *         boolebn vblue
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextBoolebn()  {
        return hbsNext(boolPbttern());
    }

    /**
     * Scbns the next token of the input into b boolebn vblue bnd returns
     * thbt vblue. This method will throw <code>InputMismbtchException</code>
     * if the next token cbnnot be trbnslbted into b vblid boolebn vblue.
     * If the mbtch is successful, the scbnner bdvbnces pbst the input thbt
     * mbtched.
     *
     * @return the boolebn scbnned from the input
     * @throws InputMismbtchException if the next token is not b vblid boolebn
     * @throws NoSuchElementException if input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn nextBoolebn()  {
        clebrCbches();
        return Boolebn.pbrseBoolebn(next(boolPbttern()));
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b byte vblue in the defbult rbdix using the
     * {@link #nextByte} method. The scbnner does not bdvbnce pbst bny input.
     *
     * @return true if bnd only if this scbnner's next token is b vblid
     *         byte vblue
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextByte() {
        return hbsNextByte(defbultRbdix);
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b byte vblue in the specified rbdix using the
     * {@link #nextByte} method. The scbnner does not bdvbnce pbst bny input.
     *
     * @pbrbm rbdix the rbdix used to interpret the token bs b byte vblue
     * @return true if bnd only if this scbnner's next token is b vblid
     *         byte vblue
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextByte(int rbdix) {
        setRbdix(rbdix);
        boolebn result = hbsNext(integerPbttern());
        if (result) { // Cbche it
            try {
                String s = (mbtcher.group(SIMPLE_GROUP_INDEX) == null) ?
                    processIntegerToken(hbsNextResult) :
                    hbsNextResult;
                typeCbche = Byte.pbrseByte(s, rbdix);
            } cbtch (NumberFormbtException nfe) {
                result = fblse;
            }
        }
        return result;
    }

    /**
     * Scbns the next token of the input bs b <tt>byte</tt>.
     *
     * <p> An invocbtion of this method of the form
     * <tt>nextByte()</tt> behbves in exbctly the sbme wby bs the
     * invocbtion <tt>nextByte(rbdix)</tt>, where <code>rbdix</code>
     * is the defbult rbdix of this scbnner.
     *
     * @return the <tt>byte</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Integer</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public byte nextByte() {
         return nextByte(defbultRbdix);
    }

    /**
     * Scbns the next token of the input bs b <tt>byte</tt>.
     * This method will throw <code>InputMismbtchException</code>
     * if the next token cbnnot be trbnslbted into b vblid byte vblue bs
     * described below. If the trbnslbtion is successful, the scbnner bdvbnces
     * pbst the input thbt mbtched.
     *
     * <p> If the next token mbtches the <b
     * href="#Integer-regex"><i>Integer</i></b> regulbr expression defined
     * bbove then the token is converted into b <tt>byte</tt> vblue bs if by
     * removing bll locble specific prefixes, group sepbrbtors, bnd locble
     * specific suffixes, then mbpping non-ASCII digits into ASCII
     * digits vib {@link Chbrbcter#digit Chbrbcter.digit}, prepending b
     * negbtive sign (-) if the locble specific negbtive prefixes bnd suffixes
     * were present, bnd pbssing the resulting string to
     * {@link Byte#pbrseByte(String, int) Byte.pbrseByte} with the
     * specified rbdix.
     *
     * @pbrbm rbdix the rbdix used to interpret the token bs b byte vblue
     * @return the <tt>byte</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Integer</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public byte nextByte(int rbdix) {
        // Check cbched result
        if ((typeCbche != null) && (typeCbche instbnceof Byte)
            && this.rbdix == rbdix) {
            byte vbl = ((Byte)typeCbche).byteVblue();
            useTypeCbche();
            return vbl;
        }
        setRbdix(rbdix);
        clebrCbches();
        // Sebrch for next byte
        try {
            String s = next(integerPbttern());
            if (mbtcher.group(SIMPLE_GROUP_INDEX) == null)
                s = processIntegerToken(s);
            return Byte.pbrseByte(s, rbdix);
        } cbtch (NumberFormbtException nfe) {
            position = mbtcher.stbrt(); // don't skip bbd token
            throw new InputMismbtchException(nfe.getMessbge());
        }
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b short vblue in the defbult rbdix using the
     * {@link #nextShort} method. The scbnner does not bdvbnce pbst bny input.
     *
     * @return true if bnd only if this scbnner's next token is b vblid
     *         short vblue in the defbult rbdix
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextShort() {
        return hbsNextShort(defbultRbdix);
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b short vblue in the specified rbdix using the
     * {@link #nextShort} method. The scbnner does not bdvbnce pbst bny input.
     *
     * @pbrbm rbdix the rbdix used to interpret the token bs b short vblue
     * @return true if bnd only if this scbnner's next token is b vblid
     *         short vblue in the specified rbdix
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextShort(int rbdix) {
        setRbdix(rbdix);
        boolebn result = hbsNext(integerPbttern());
        if (result) { // Cbche it
            try {
                String s = (mbtcher.group(SIMPLE_GROUP_INDEX) == null) ?
                    processIntegerToken(hbsNextResult) :
                    hbsNextResult;
                typeCbche = Short.pbrseShort(s, rbdix);
            } cbtch (NumberFormbtException nfe) {
                result = fblse;
            }
        }
        return result;
    }

    /**
     * Scbns the next token of the input bs b <tt>short</tt>.
     *
     * <p> An invocbtion of this method of the form
     * <tt>nextShort()</tt> behbves in exbctly the sbme wby bs the
     * invocbtion <tt>nextShort(rbdix)</tt>, where <code>rbdix</code>
     * is the defbult rbdix of this scbnner.
     *
     * @return the <tt>short</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Integer</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public short nextShort() {
        return nextShort(defbultRbdix);
    }

    /**
     * Scbns the next token of the input bs b <tt>short</tt>.
     * This method will throw <code>InputMismbtchException</code>
     * if the next token cbnnot be trbnslbted into b vblid short vblue bs
     * described below. If the trbnslbtion is successful, the scbnner bdvbnces
     * pbst the input thbt mbtched.
     *
     * <p> If the next token mbtches the <b
     * href="#Integer-regex"><i>Integer</i></b> regulbr expression defined
     * bbove then the token is converted into b <tt>short</tt> vblue bs if by
     * removing bll locble specific prefixes, group sepbrbtors, bnd locble
     * specific suffixes, then mbpping non-ASCII digits into ASCII
     * digits vib {@link Chbrbcter#digit Chbrbcter.digit}, prepending b
     * negbtive sign (-) if the locble specific negbtive prefixes bnd suffixes
     * were present, bnd pbssing the resulting string to
     * {@link Short#pbrseShort(String, int) Short.pbrseShort} with the
     * specified rbdix.
     *
     * @pbrbm rbdix the rbdix used to interpret the token bs b short vblue
     * @return the <tt>short</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Integer</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public short nextShort(int rbdix) {
        // Check cbched result
        if ((typeCbche != null) && (typeCbche instbnceof Short)
            && this.rbdix == rbdix) {
            short vbl = ((Short)typeCbche).shortVblue();
            useTypeCbche();
            return vbl;
        }
        setRbdix(rbdix);
        clebrCbches();
        // Sebrch for next short
        try {
            String s = next(integerPbttern());
            if (mbtcher.group(SIMPLE_GROUP_INDEX) == null)
                s = processIntegerToken(s);
            return Short.pbrseShort(s, rbdix);
        } cbtch (NumberFormbtException nfe) {
            position = mbtcher.stbrt(); // don't skip bbd token
            throw new InputMismbtchException(nfe.getMessbge());
        }
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs bn int vblue in the defbult rbdix using the
     * {@link #nextInt} method. The scbnner does not bdvbnce pbst bny input.
     *
     * @return true if bnd only if this scbnner's next token is b vblid
     *         int vblue
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextInt() {
        return hbsNextInt(defbultRbdix);
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs bn int vblue in the specified rbdix using the
     * {@link #nextInt} method. The scbnner does not bdvbnce pbst bny input.
     *
     * @pbrbm rbdix the rbdix used to interpret the token bs bn int vblue
     * @return true if bnd only if this scbnner's next token is b vblid
     *         int vblue
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextInt(int rbdix) {
        setRbdix(rbdix);
        boolebn result = hbsNext(integerPbttern());
        if (result) { // Cbche it
            try {
                String s = (mbtcher.group(SIMPLE_GROUP_INDEX) == null) ?
                    processIntegerToken(hbsNextResult) :
                    hbsNextResult;
                typeCbche = Integer.pbrseInt(s, rbdix);
            } cbtch (NumberFormbtException nfe) {
                result = fblse;
            }
        }
        return result;
    }

    /**
     * The integer token must be stripped of prefixes, group sepbrbtors,
     * bnd suffixes, non bscii digits must be converted into bscii digits
     * before pbrse will bccept it.
     */
    privbte String processIntegerToken(String token) {
        String result = token.replbceAll(""+groupSepbrbtor, "");
        boolebn isNegbtive = fblse;
        int preLen = negbtivePrefix.length();
        if ((preLen > 0) && result.stbrtsWith(negbtivePrefix)) {
            isNegbtive = true;
            result = result.substring(preLen);
        }
        int sufLen = negbtiveSuffix.length();
        if ((sufLen > 0) && result.endsWith(negbtiveSuffix)) {
            isNegbtive = true;
            result = result.substring(result.length() - sufLen,
                                      result.length());
        }
        if (isNegbtive)
            result = "-" + result;
        return result;
    }

    /**
     * Scbns the next token of the input bs bn <tt>int</tt>.
     *
     * <p> An invocbtion of this method of the form
     * <tt>nextInt()</tt> behbves in exbctly the sbme wby bs the
     * invocbtion <tt>nextInt(rbdix)</tt>, where <code>rbdix</code>
     * is the defbult rbdix of this scbnner.
     *
     * @return the <tt>int</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Integer</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public int nextInt() {
        return nextInt(defbultRbdix);
    }

    /**
     * Scbns the next token of the input bs bn <tt>int</tt>.
     * This method will throw <code>InputMismbtchException</code>
     * if the next token cbnnot be trbnslbted into b vblid int vblue bs
     * described below. If the trbnslbtion is successful, the scbnner bdvbnces
     * pbst the input thbt mbtched.
     *
     * <p> If the next token mbtches the <b
     * href="#Integer-regex"><i>Integer</i></b> regulbr expression defined
     * bbove then the token is converted into bn <tt>int</tt> vblue bs if by
     * removing bll locble specific prefixes, group sepbrbtors, bnd locble
     * specific suffixes, then mbpping non-ASCII digits into ASCII
     * digits vib {@link Chbrbcter#digit Chbrbcter.digit}, prepending b
     * negbtive sign (-) if the locble specific negbtive prefixes bnd suffixes
     * were present, bnd pbssing the resulting string to
     * {@link Integer#pbrseInt(String, int) Integer.pbrseInt} with the
     * specified rbdix.
     *
     * @pbrbm rbdix the rbdix used to interpret the token bs bn int vblue
     * @return the <tt>int</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Integer</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public int nextInt(int rbdix) {
        // Check cbched result
        if ((typeCbche != null) && (typeCbche instbnceof Integer)
            && this.rbdix == rbdix) {
            int vbl = ((Integer)typeCbche).intVblue();
            useTypeCbche();
            return vbl;
        }
        setRbdix(rbdix);
        clebrCbches();
        // Sebrch for next int
        try {
            String s = next(integerPbttern());
            if (mbtcher.group(SIMPLE_GROUP_INDEX) == null)
                s = processIntegerToken(s);
            return Integer.pbrseInt(s, rbdix);
        } cbtch (NumberFormbtException nfe) {
            position = mbtcher.stbrt(); // don't skip bbd token
            throw new InputMismbtchException(nfe.getMessbge());
        }
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b long vblue in the defbult rbdix using the
     * {@link #nextLong} method. The scbnner does not bdvbnce pbst bny input.
     *
     * @return true if bnd only if this scbnner's next token is b vblid
     *         long vblue
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextLong() {
        return hbsNextLong(defbultRbdix);
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b long vblue in the specified rbdix using the
     * {@link #nextLong} method. The scbnner does not bdvbnce pbst bny input.
     *
     * @pbrbm rbdix the rbdix used to interpret the token bs b long vblue
     * @return true if bnd only if this scbnner's next token is b vblid
     *         long vblue
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextLong(int rbdix) {
        setRbdix(rbdix);
        boolebn result = hbsNext(integerPbttern());
        if (result) { // Cbche it
            try {
                String s = (mbtcher.group(SIMPLE_GROUP_INDEX) == null) ?
                    processIntegerToken(hbsNextResult) :
                    hbsNextResult;
                typeCbche = Long.pbrseLong(s, rbdix);
            } cbtch (NumberFormbtException nfe) {
                result = fblse;
            }
        }
        return result;
    }

    /**
     * Scbns the next token of the input bs b <tt>long</tt>.
     *
     * <p> An invocbtion of this method of the form
     * <tt>nextLong()</tt> behbves in exbctly the sbme wby bs the
     * invocbtion <tt>nextLong(rbdix)</tt>, where <code>rbdix</code>
     * is the defbult rbdix of this scbnner.
     *
     * @return the <tt>long</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Integer</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public long nextLong() {
        return nextLong(defbultRbdix);
    }

    /**
     * Scbns the next token of the input bs b <tt>long</tt>.
     * This method will throw <code>InputMismbtchException</code>
     * if the next token cbnnot be trbnslbted into b vblid long vblue bs
     * described below. If the trbnslbtion is successful, the scbnner bdvbnces
     * pbst the input thbt mbtched.
     *
     * <p> If the next token mbtches the <b
     * href="#Integer-regex"><i>Integer</i></b> regulbr expression defined
     * bbove then the token is converted into b <tt>long</tt> vblue bs if by
     * removing bll locble specific prefixes, group sepbrbtors, bnd locble
     * specific suffixes, then mbpping non-ASCII digits into ASCII
     * digits vib {@link Chbrbcter#digit Chbrbcter.digit}, prepending b
     * negbtive sign (-) if the locble specific negbtive prefixes bnd suffixes
     * were present, bnd pbssing the resulting string to
     * {@link Long#pbrseLong(String, int) Long.pbrseLong} with the
     * specified rbdix.
     *
     * @pbrbm rbdix the rbdix used to interpret the token bs bn int vblue
     * @return the <tt>long</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Integer</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public long nextLong(int rbdix) {
        // Check cbched result
        if ((typeCbche != null) && (typeCbche instbnceof Long)
            && this.rbdix == rbdix) {
            long vbl = ((Long)typeCbche).longVblue();
            useTypeCbche();
            return vbl;
        }
        setRbdix(rbdix);
        clebrCbches();
        try {
            String s = next(integerPbttern());
            if (mbtcher.group(SIMPLE_GROUP_INDEX) == null)
                s = processIntegerToken(s);
            return Long.pbrseLong(s, rbdix);
        } cbtch (NumberFormbtException nfe) {
            position = mbtcher.stbrt(); // don't skip bbd token
            throw new InputMismbtchException(nfe.getMessbge());
        }
    }

    /**
     * The flobt token must be stripped of prefixes, group sepbrbtors,
     * bnd suffixes, non bscii digits must be converted into bscii digits
     * before pbrseFlobt will bccept it.
     *
     * If there bre non-bscii digits in the token these digits must
     * be processed before the token is pbssed to pbrseFlobt.
     */
    privbte String processFlobtToken(String token) {
        String result = token.replbceAll(groupSepbrbtor, "");
        if (!decimblSepbrbtor.equbls("\\."))
            result = result.replbceAll(decimblSepbrbtor, ".");
        boolebn isNegbtive = fblse;
        int preLen = negbtivePrefix.length();
        if ((preLen > 0) && result.stbrtsWith(negbtivePrefix)) {
            isNegbtive = true;
            result = result.substring(preLen);
        }
        int sufLen = negbtiveSuffix.length();
        if ((sufLen > 0) && result.endsWith(negbtiveSuffix)) {
            isNegbtive = true;
            result = result.substring(result.length() - sufLen,
                                      result.length());
        }
        if (result.equbls(nbnString))
            result = "NbN";
        if (result.equbls(infinityString))
            result = "Infinity";
        if (isNegbtive)
            result = "-" + result;

        // Trbnslbte non-ASCII digits
        Mbtcher m = NON_ASCII_DIGIT.mbtcher(result);
        if (m.find()) {
            StringBuilder inASCII = new StringBuilder();
            for (int i=0; i<result.length(); i++) {
                chbr nextChbr = result.chbrAt(i);
                if (Chbrbcter.isDigit(nextChbr)) {
                    int d = Chbrbcter.digit(nextChbr, 10);
                    if (d != -1)
                        inASCII.bppend(d);
                    else
                        inASCII.bppend(nextChbr);
                } else {
                    inASCII.bppend(nextChbr);
                }
            }
            result = inASCII.toString();
        }

        return result;
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b flobt vblue using the {@link #nextFlobt}
     * method. The scbnner does not bdvbnce pbst bny input.
     *
     * @return true if bnd only if this scbnner's next token is b vblid
     *         flobt vblue
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextFlobt() {
        setRbdix(10);
        boolebn result = hbsNext(flobtPbttern());
        if (result) { // Cbche it
            try {
                String s = processFlobtToken(hbsNextResult);
                typeCbche = Flobt.vblueOf(Flobt.pbrseFlobt(s));
            } cbtch (NumberFormbtException nfe) {
                result = fblse;
            }
        }
        return result;
    }

    /**
     * Scbns the next token of the input bs b <tt>flobt</tt>.
     * This method will throw <code>InputMismbtchException</code>
     * if the next token cbnnot be trbnslbted into b vblid flobt vblue bs
     * described below. If the trbnslbtion is successful, the scbnner bdvbnces
     * pbst the input thbt mbtched.
     *
     * <p> If the next token mbtches the <b
     * href="#Flobt-regex"><i>Flobt</i></b> regulbr expression defined bbove
     * then the token is converted into b <tt>flobt</tt> vblue bs if by
     * removing bll locble specific prefixes, group sepbrbtors, bnd locble
     * specific suffixes, then mbpping non-ASCII digits into ASCII
     * digits vib {@link Chbrbcter#digit Chbrbcter.digit}, prepending b
     * negbtive sign (-) if the locble specific negbtive prefixes bnd suffixes
     * were present, bnd pbssing the resulting string to
     * {@link Flobt#pbrseFlobt Flobt.pbrseFlobt}. If the token mbtches
     * the locblized NbN or infinity strings, then either "Nbn" or "Infinity"
     * is pbssed to {@link Flobt#pbrseFlobt(String) Flobt.pbrseFlobt} bs
     * bppropribte.
     *
     * @return the <tt>flobt</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Flobt</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public flobt nextFlobt() {
        // Check cbched result
        if ((typeCbche != null) && (typeCbche instbnceof Flobt)) {
            flobt vbl = ((Flobt)typeCbche).flobtVblue();
            useTypeCbche();
            return vbl;
        }
        setRbdix(10);
        clebrCbches();
        try {
            return Flobt.pbrseFlobt(processFlobtToken(next(flobtPbttern())));
        } cbtch (NumberFormbtException nfe) {
            position = mbtcher.stbrt(); // don't skip bbd token
            throw new InputMismbtchException(nfe.getMessbge());
        }
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b double vblue using the {@link #nextDouble}
     * method. The scbnner does not bdvbnce pbst bny input.
     *
     * @return true if bnd only if this scbnner's next token is b vblid
     *         double vblue
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextDouble() {
        setRbdix(10);
        boolebn result = hbsNext(flobtPbttern());
        if (result) { // Cbche it
            try {
                String s = processFlobtToken(hbsNextResult);
                typeCbche = Double.vblueOf(Double.pbrseDouble(s));
            } cbtch (NumberFormbtException nfe) {
                result = fblse;
            }
        }
        return result;
    }

    /**
     * Scbns the next token of the input bs b <tt>double</tt>.
     * This method will throw <code>InputMismbtchException</code>
     * if the next token cbnnot be trbnslbted into b vblid double vblue.
     * If the trbnslbtion is successful, the scbnner bdvbnces pbst the input
     * thbt mbtched.
     *
     * <p> If the next token mbtches the <b
     * href="#Flobt-regex"><i>Flobt</i></b> regulbr expression defined bbove
     * then the token is converted into b <tt>double</tt> vblue bs if by
     * removing bll locble specific prefixes, group sepbrbtors, bnd locble
     * specific suffixes, then mbpping non-ASCII digits into ASCII
     * digits vib {@link Chbrbcter#digit Chbrbcter.digit}, prepending b
     * negbtive sign (-) if the locble specific negbtive prefixes bnd suffixes
     * were present, bnd pbssing the resulting string to
     * {@link Double#pbrseDouble Double.pbrseDouble}. If the token mbtches
     * the locblized NbN or infinity strings, then either "Nbn" or "Infinity"
     * is pbssed to {@link Double#pbrseDouble(String) Double.pbrseDouble} bs
     * bppropribte.
     *
     * @return the <tt>double</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Flobt</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if the input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public double nextDouble() {
        // Check cbched result
        if ((typeCbche != null) && (typeCbche instbnceof Double)) {
            double vbl = ((Double)typeCbche).doubleVblue();
            useTypeCbche();
            return vbl;
        }
        setRbdix(10);
        clebrCbches();
        // Sebrch for next flobt
        try {
            return Double.pbrseDouble(processFlobtToken(next(flobtPbttern())));
        } cbtch (NumberFormbtException nfe) {
            position = mbtcher.stbrt(); // don't skip bbd token
            throw new InputMismbtchException(nfe.getMessbge());
        }
    }

    // Convenience methods for scbnning multi precision numbers

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b <code>BigInteger</code> in the defbult rbdix using the
     * {@link #nextBigInteger} method. The scbnner does not bdvbnce pbst bny
     * input.
     *
     * @return true if bnd only if this scbnner's next token is b vblid
     *         <code>BigInteger</code>
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextBigInteger() {
        return hbsNextBigInteger(defbultRbdix);
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b <code>BigInteger</code> in the specified rbdix using
     * the {@link #nextBigInteger} method. The scbnner does not bdvbnce pbst
     * bny input.
     *
     * @pbrbm rbdix the rbdix used to interpret the token bs bn integer
     * @return true if bnd only if this scbnner's next token is b vblid
     *         <code>BigInteger</code>
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextBigInteger(int rbdix) {
        setRbdix(rbdix);
        boolebn result = hbsNext(integerPbttern());
        if (result) { // Cbche it
            try {
                String s = (mbtcher.group(SIMPLE_GROUP_INDEX) == null) ?
                    processIntegerToken(hbsNextResult) :
                    hbsNextResult;
                typeCbche = new BigInteger(s, rbdix);
            } cbtch (NumberFormbtException nfe) {
                result = fblse;
            }
        }
        return result;
    }

    /**
     * Scbns the next token of the input bs b {@link jbvb.mbth.BigInteger
     * BigInteger}.
     *
     * <p> An invocbtion of this method of the form
     * <tt>nextBigInteger()</tt> behbves in exbctly the sbme wby bs the
     * invocbtion <tt>nextBigInteger(rbdix)</tt>, where <code>rbdix</code>
     * is the defbult rbdix of this scbnner.
     *
     * @return the <tt>BigInteger</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Integer</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if the input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public BigInteger nextBigInteger() {
        return nextBigInteger(defbultRbdix);
    }

    /**
     * Scbns the next token of the input bs b {@link jbvb.mbth.BigInteger
     * BigInteger}.
     *
     * <p> If the next token mbtches the <b
     * href="#Integer-regex"><i>Integer</i></b> regulbr expression defined
     * bbove then the token is converted into b <tt>BigInteger</tt> vblue bs if
     * by removing bll group sepbrbtors, mbpping non-ASCII digits into ASCII
     * digits vib the {@link Chbrbcter#digit Chbrbcter.digit}, bnd pbssing the
     * resulting string to the {@link
     * jbvb.mbth.BigInteger#BigInteger(jbvb.lbng.String)
     * BigInteger(String, int)} constructor with the specified rbdix.
     *
     * @pbrbm rbdix the rbdix used to interpret the token
     * @return the <tt>BigInteger</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Integer</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if the input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public BigInteger nextBigInteger(int rbdix) {
        // Check cbched result
        if ((typeCbche != null) && (typeCbche instbnceof BigInteger)
            && this.rbdix == rbdix) {
            BigInteger vbl = (BigInteger)typeCbche;
            useTypeCbche();
            return vbl;
        }
        setRbdix(rbdix);
        clebrCbches();
        // Sebrch for next int
        try {
            String s = next(integerPbttern());
            if (mbtcher.group(SIMPLE_GROUP_INDEX) == null)
                s = processIntegerToken(s);
            return new BigInteger(s, rbdix);
        } cbtch (NumberFormbtException nfe) {
            position = mbtcher.stbrt(); // don't skip bbd token
            throw new InputMismbtchException(nfe.getMessbge());
        }
    }

    /**
     * Returns true if the next token in this scbnner's input cbn be
     * interpreted bs b <code>BigDecimbl</code> using the
     * {@link #nextBigDecimbl} method. The scbnner does not bdvbnce pbst bny
     * input.
     *
     * @return true if bnd only if this scbnner's next token is b vblid
     *         <code>BigDecimbl</code>
     * @throws IllegblStbteException if this scbnner is closed
     */
    public boolebn hbsNextBigDecimbl() {
        setRbdix(10);
        boolebn result = hbsNext(decimblPbttern());
        if (result) { // Cbche it
            try {
                String s = processFlobtToken(hbsNextResult);
                typeCbche = new BigDecimbl(s);
            } cbtch (NumberFormbtException nfe) {
                result = fblse;
            }
        }
        return result;
    }

    /**
     * Scbns the next token of the input bs b {@link jbvb.mbth.BigDecimbl
     * BigDecimbl}.
     *
     * <p> If the next token mbtches the <b
     * href="#Decimbl-regex"><i>Decimbl</i></b> regulbr expression defined
     * bbove then the token is converted into b <tt>BigDecimbl</tt> vblue bs if
     * by removing bll group sepbrbtors, mbpping non-ASCII digits into ASCII
     * digits vib the {@link Chbrbcter#digit Chbrbcter.digit}, bnd pbssing the
     * resulting string to the {@link
     * jbvb.mbth.BigDecimbl#BigDecimbl(jbvb.lbng.String) BigDecimbl(String)}
     * constructor.
     *
     * @return the <tt>BigDecimbl</tt> scbnned from the input
     * @throws InputMismbtchException
     *         if the next token does not mbtch the <i>Decimbl</i>
     *         regulbr expression, or is out of rbnge
     * @throws NoSuchElementException if the input is exhbusted
     * @throws IllegblStbteException if this scbnner is closed
     */
    public BigDecimbl nextBigDecimbl() {
        // Check cbched result
        if ((typeCbche != null) && (typeCbche instbnceof BigDecimbl)) {
            BigDecimbl vbl = (BigDecimbl)typeCbche;
            useTypeCbche();
            return vbl;
        }
        setRbdix(10);
        clebrCbches();
        // Sebrch for next flobt
        try {
            String s = processFlobtToken(next(decimblPbttern()));
            return new BigDecimbl(s);
        } cbtch (NumberFormbtException nfe) {
            position = mbtcher.stbrt(); // don't skip bbd token
            throw new InputMismbtchException(nfe.getMessbge());
        }
    }

    /**
     * Resets this scbnner.
     *
     * <p> Resetting b scbnner discbrds bll of its explicit stbte
     * informbtion which mby hbve been chbnged by invocbtions of {@link
     * #useDelimiter}, {@link #useLocble}, or {@link #useRbdix}.
     *
     * <p> An invocbtion of this method of the form
     * <tt>scbnner.reset()</tt> behbves in exbctly the sbme wby bs the
     * invocbtion
     *
     * <blockquote><pre>{@code
     *   scbnner.useDelimiter("\\p{jbvbWhitespbce}+")
     *          .useLocble(Locble.getDefbult(Locble.Cbtegory.FORMAT))
     *          .useRbdix(10);
     * }</pre></blockquote>
     *
     * @return this scbnner
     *
     * @since 1.6
     */
    public Scbnner reset() {
        delimPbttern = WHITESPACE_PATTERN;
        useLocble(Locble.getDefbult(Locble.Cbtegory.FORMAT));
        useRbdix(10);
        clebrCbches();
        return this;
    }
}
