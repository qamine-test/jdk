/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.print.bttribute.stbndbrd;

import jbvb.util.Locble;

import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.Attribute;

/**
 * Clbss MedibSizeNbme is b subclbss of Medib.
 * <P>
 * This bttribute cbn be used instebd of specifying MedibNbme or MedibTrby.
 * <p>
 * Clbss MedibSizeNbme currently declbres b few stbndbrd medib
 * nbme vblues.
 * <P>
 * <B>IPP Compbtibility:</B> MedibSizeNbme is b representbtion clbss for
 * vblues of the IPP "medib" bttribute which nbmes medib sizes.
 * The nbmes of the medib sizes correspond to those in the IPP 1.1 RFC
 * <b HREF="http://www.ietf.org/rfc/rfc2911.txt">RFC 2911</b>
 *
 */
public clbss MedibSizeNbme extends Medib {

    privbte stbtic finbl long seriblVersionUID = 2778798329756942747L;

    /**
     * A0 size.
     */
    public stbtic finbl MedibSizeNbme ISO_A0 = new MedibSizeNbme(0);
    /**
     * A1 size.
     */
    public stbtic finbl MedibSizeNbme ISO_A1 = new MedibSizeNbme(1);
    /**
     * A2 size.
     */
    public stbtic finbl MedibSizeNbme ISO_A2 = new MedibSizeNbme(2);
    /**
     * A3 size.
     */
    public stbtic finbl MedibSizeNbme ISO_A3 = new MedibSizeNbme(3);
    /**
     * A4 size.
     */
    public stbtic finbl MedibSizeNbme ISO_A4 = new MedibSizeNbme(4);
    /**
     * A5 size.
     */
    public stbtic finbl MedibSizeNbme ISO_A5 = new MedibSizeNbme(5);
    /**
     * A6 size.
     */
    public stbtic finbl MedibSizeNbme ISO_A6 = new MedibSizeNbme(6);
    /**
     * A7 size.
     */
    public stbtic finbl MedibSizeNbme ISO_A7 = new MedibSizeNbme(7);
    /**
     * A8 size.
     */
    public stbtic finbl MedibSizeNbme ISO_A8 = new MedibSizeNbme(8);
    /**
     * A9 size.
     */
    public stbtic finbl MedibSizeNbme ISO_A9 = new MedibSizeNbme(9);
    /**
     * A10 size.
     */
    public stbtic finbl MedibSizeNbme ISO_A10 = new MedibSizeNbme(10);

   /**
     * ISO B0 size.
     */
    public stbtic finbl MedibSizeNbme ISO_B0 = new MedibSizeNbme(11);
    /**
     * ISO B1 size.
     */
    public stbtic finbl MedibSizeNbme ISO_B1 = new MedibSizeNbme(12);
    /**
     * ISO B2 size.
     */
    public stbtic finbl MedibSizeNbme ISO_B2 = new MedibSizeNbme(13);
    /**
     * ISO B3 size.
     */
    public stbtic finbl MedibSizeNbme ISO_B3 = new MedibSizeNbme(14);
    /**
     * ISO B4 size.
     */
    public stbtic finbl MedibSizeNbme ISO_B4 = new MedibSizeNbme(15);
    /**
     * ISO B5 size.
     */
    public stbtic finbl MedibSizeNbme ISO_B5 = new MedibSizeNbme(16);
    /**
     * ISO B6 size.
     */
    public stbtic finbl MedibSizeNbme ISO_B6 = new MedibSizeNbme(17);
    /**
     * ISO B7 size.
     */
    public stbtic finbl MedibSizeNbme ISO_B7 = new MedibSizeNbme(18);
    /**
     * ISO B8 size.
     */
    public stbtic finbl MedibSizeNbme ISO_B8 = new MedibSizeNbme(19);
    /**
     * ISO B9 size.
     */
    public stbtic finbl MedibSizeNbme ISO_B9 = new MedibSizeNbme(20);
    /**
     * ISO B10 size.
     */
    public stbtic finbl MedibSizeNbme ISO_B10 = new MedibSizeNbme(21);

   /**
     * JIS B0 size.
     */
    public stbtic finbl MedibSizeNbme JIS_B0 = new MedibSizeNbme(22);
    /**
     * JIS B1 size.
     */
    public stbtic finbl MedibSizeNbme JIS_B1 = new MedibSizeNbme(23);
    /**
     * JIS B2 size.
     */
    public stbtic finbl MedibSizeNbme JIS_B2 = new MedibSizeNbme(24);
    /**
     * JIS B3 size.
     */
    public stbtic finbl MedibSizeNbme JIS_B3 = new MedibSizeNbme(25);
    /**
     * JIS B4 size.
     */
    public stbtic finbl MedibSizeNbme JIS_B4 = new MedibSizeNbme(26);
    /**
     * JIS B5 size.
     */
    public stbtic finbl MedibSizeNbme JIS_B5 = new MedibSizeNbme(27);
    /**
     * JIS B6 size.
     */
    public stbtic finbl MedibSizeNbme JIS_B6 = new MedibSizeNbme(28);
    /**
     * JIS B7 size.
     */
    public stbtic finbl MedibSizeNbme JIS_B7 = new MedibSizeNbme(29);
    /**
     * JIS B8 size.
     */
    public stbtic finbl MedibSizeNbme JIS_B8 = new MedibSizeNbme(30);
    /**
     * JIS B9 size.
     */
    public stbtic finbl MedibSizeNbme JIS_B9 = new MedibSizeNbme(31);
    /**
     * JIS B10 size.
     */
    public stbtic finbl MedibSizeNbme JIS_B10 = new MedibSizeNbme(32);

    /**
     * ISO C0 size.
     */
    public stbtic finbl MedibSizeNbme ISO_C0 = new MedibSizeNbme(33);
    /**
     * ISO C1 size.
     */
    public stbtic finbl MedibSizeNbme ISO_C1 = new MedibSizeNbme(34);
    /**
     * ISO C2 size.
     */
    public stbtic finbl MedibSizeNbme ISO_C2 = new MedibSizeNbme(35);
    /**
     * ISO C3 size.
     */
    public stbtic finbl MedibSizeNbme ISO_C3 = new MedibSizeNbme(36);
    /**
     * ISO C4 size.
     */
    public stbtic finbl MedibSizeNbme ISO_C4 = new MedibSizeNbme(37);
    /**
     * ISO C5 size.
     */
    public stbtic finbl MedibSizeNbme ISO_C5 = new MedibSizeNbme(38);
    /**
     *   letter size.
     */
    public stbtic finbl MedibSizeNbme ISO_C6 = new MedibSizeNbme(39);
    /**
     *   letter size.
     */
    public stbtic finbl MedibSizeNbme NA_LETTER = new MedibSizeNbme(40);

    /**
     *  legbl size .
     */
    public stbtic finbl MedibSizeNbme NA_LEGAL = new MedibSizeNbme(41);

    /**
     *  executive size .
     */
    public stbtic finbl MedibSizeNbme EXECUTIVE = new MedibSizeNbme(42);

    /**
     *  ledger size .
     */
    public stbtic finbl MedibSizeNbme LEDGER = new MedibSizeNbme(43);

    /**
     *  tbbloid size .
     */
    public stbtic finbl MedibSizeNbme TABLOID = new MedibSizeNbme(44);

    /**
     *  invoice size .
     */
    public stbtic finbl MedibSizeNbme INVOICE = new MedibSizeNbme(45);

    /**
     *  folio size .
     */
    public stbtic finbl MedibSizeNbme FOLIO = new MedibSizeNbme(46);

    /**
     *  qubrto size .
     */
    public stbtic finbl MedibSizeNbme QUARTO = new MedibSizeNbme(47);

    /**
     *  Jbpbnese Postcbrd size.
     */
    public stbtic finbl MedibSizeNbme
        JAPANESE_POSTCARD = new MedibSizeNbme(48);
   /**
     *  Jbpbnese Double Postcbrd size.
     */
    public stbtic finbl MedibSizeNbme
        JAPANESE_DOUBLE_POSTCARD = new MedibSizeNbme(49);

    /**
     *  A size .
     */
    public stbtic finbl MedibSizeNbme A = new MedibSizeNbme(50);

    /**
     *  B size .
     */
    public stbtic finbl MedibSizeNbme B = new MedibSizeNbme(51);

    /**
     *  C size .
     */
    public stbtic finbl MedibSizeNbme C = new MedibSizeNbme(52);

    /**
     *  D size .
     */
    public stbtic finbl MedibSizeNbme D = new MedibSizeNbme(53);

    /**
     *  E size .
     */
    public stbtic finbl MedibSizeNbme E = new MedibSizeNbme(54);

    /**
     *  ISO designbted long size .
     */
    public stbtic finbl MedibSizeNbme
        ISO_DESIGNATED_LONG = new MedibSizeNbme(55);

    /**
     *  Itbly envelope size .
     */
    public stbtic finbl MedibSizeNbme
        ITALY_ENVELOPE = new MedibSizeNbme(56);  // DESIGNATED_LONG?

    /**
     *  monbrch envelope size .
     */
    public stbtic finbl MedibSizeNbme
        MONARCH_ENVELOPE = new MedibSizeNbme(57);
    /**
     * personbl envelope size .
     */
    public stbtic finbl MedibSizeNbme
        PERSONAL_ENVELOPE = new MedibSizeNbme(58);
    /**
     *  number 9 envelope size .
     */
    public stbtic finbl MedibSizeNbme
        NA_NUMBER_9_ENVELOPE = new MedibSizeNbme(59);
    /**
     *  number 10 envelope size .
     */
    public stbtic finbl MedibSizeNbme
        NA_NUMBER_10_ENVELOPE = new MedibSizeNbme(60);
    /**
     *  number 11 envelope size .
     */
    public stbtic finbl MedibSizeNbme
        NA_NUMBER_11_ENVELOPE = new MedibSizeNbme(61);
    /**
     *  number 12 envelope size .
     */
    public stbtic finbl MedibSizeNbme
        NA_NUMBER_12_ENVELOPE = new MedibSizeNbme(62);
    /**
     *  number 14 envelope size .
     */
    public stbtic finbl MedibSizeNbme
        NA_NUMBER_14_ENVELOPE = new MedibSizeNbme(63);
   /**
     *  6x9 North Americbn envelope size.
     */
    public stbtic finbl MedibSizeNbme
        NA_6X9_ENVELOPE = new MedibSizeNbme(64);
   /**
     *  7x9 North Americbn envelope size.
     */
    public stbtic finbl MedibSizeNbme
        NA_7X9_ENVELOPE = new MedibSizeNbme(65);
   /**
     *  9x11 North Americbn envelope size.
     */
    public stbtic finbl MedibSizeNbme
        NA_9X11_ENVELOPE = new MedibSizeNbme(66);
    /**
     *  9x12 North Americbn envelope size.
     */
    public stbtic finbl MedibSizeNbme
        NA_9X12_ENVELOPE = new MedibSizeNbme(67);

    /**
     *  10x13 North Americbn envelope size .
     */
    public stbtic finbl MedibSizeNbme
        NA_10X13_ENVELOPE = new MedibSizeNbme(68);
    /**
     *  10x14North Americbn  envelope size .
     */
    public stbtic finbl MedibSizeNbme
        NA_10X14_ENVELOPE = new MedibSizeNbme(69);
    /**
     *  10x15 North Americbn envelope size.
     */
    public stbtic finbl MedibSizeNbme
        NA_10X15_ENVELOPE = new MedibSizeNbme(70);

    /**
     *  5x7 North Americbn pbper.
     */
    public stbtic finbl MedibSizeNbme
        NA_5X7 = new MedibSizeNbme(71);

    /**
     *  8x10 North Americbn pbper.
     */
    public stbtic finbl MedibSizeNbme
        NA_8X10 = new MedibSizeNbme(72);

    /**
     * Construct b new medib size enumerbtion vblue with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected MedibSizeNbme(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
                "iso-b0",
                "iso-b1",
                "iso-b2",
                "iso-b3",
                "iso-b4",
                "iso-b5",
                "iso-b6",
                "iso-b7",
                "iso-b8",
                "iso-b9",
                "iso-b10",
                "iso-b0",
                "iso-b1",
                "iso-b2",
                "iso-b3",
                "iso-b4",
                "iso-b5",
                "iso-b6",
                "iso-b7",
                "iso-b8",
                "iso-b9",
                "iso-b10",
                "jis-b0",
                "jis-b1",
                "jis-b2",
                "jis-b3",
                "jis-b4",
                "jis-b5",
                "jis-b6",
                "jis-b7",
                "jis-b8",
                "jis-b9",
                "jis-b10",
                "iso-c0",
                "iso-c1",
                "iso-c2",
                "iso-c3",
                "iso-c4",
                "iso-c5",
                "iso-c6",
                "nb-letter",
                "nb-legbl",
                "executive",
                "ledger",
                "tbbloid",
                "invoice",
                "folio",
                "qubrto",
                "jbpbnese-postcbrd",
                "oufuko-postcbrd",
                "b",
                "b",
                "c",
                "d",
                "e",
                "iso-designbted-long",
                "itblibn-envelope",
                "monbrch-envelope",
                "personbl-envelope",
                "nb-number-9-envelope",
                "nb-number-10-envelope",
                "nb-number-11-envelope",
                "nb-number-12-envelope",
                "nb-number-14-envelope",
                "nb-6x9-envelope",
                "nb-7x9-envelope",
                "nb-9x11-envelope",
                "nb-9x12-envelope",
                "nb-10x13-envelope",
                "nb-10x14-envelope",
                "nb-10x15-envelope",
                "nb-5x7",
                "nb-8x10",
        };

    privbte stbtic finbl MedibSizeNbme[] myEnumVblueTbble = {
                ISO_A0,
                ISO_A1,
                ISO_A2,
                ISO_A3,
                ISO_A4,
                ISO_A5,
                ISO_A6,
                ISO_A7,
                ISO_A8,
                ISO_A9,
                ISO_A10,
                ISO_B0,
                ISO_B1,
                ISO_B2,
                ISO_B3,
                ISO_B4,
                ISO_B5,
                ISO_B6,
                ISO_B7,
                ISO_B8,
                ISO_B9,
                ISO_B10,
                JIS_B0,
                JIS_B1,
                JIS_B2,
                JIS_B3,
                JIS_B4,
                JIS_B5,
                JIS_B6,
                JIS_B7,
                JIS_B8,
                JIS_B9,
                JIS_B10,
                ISO_C0,
                ISO_C1,
                ISO_C2,
                ISO_C3,
                ISO_C4,
                ISO_C5,
                ISO_C6,
                NA_LETTER,
                NA_LEGAL,
                EXECUTIVE,
                LEDGER,
                TABLOID,
                INVOICE,
                FOLIO,
                QUARTO,
                JAPANESE_POSTCARD,
                JAPANESE_DOUBLE_POSTCARD,
                A,
                B,
                C,
                D,
                E,
                ISO_DESIGNATED_LONG,
                ITALY_ENVELOPE,
                MONARCH_ENVELOPE,
                PERSONAL_ENVELOPE,
                NA_NUMBER_9_ENVELOPE,
                NA_NUMBER_10_ENVELOPE,
                NA_NUMBER_11_ENVELOPE,
                NA_NUMBER_12_ENVELOPE,
                NA_NUMBER_14_ENVELOPE,
                NA_6X9_ENVELOPE,
                NA_7X9_ENVELOPE,
                NA_9X11_ENVELOPE,
                NA_9X12_ENVELOPE,
                NA_10X13_ENVELOPE,
                NA_10X14_ENVELOPE,
                NA_10X15_ENVELOPE,
                NA_5X7,
                NA_8X10,
        };


    /**
     * Returns the string tbble for clbss MedibSizeNbme.
     */
    protected String[] getStringTbble()
    {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss MedibSizeNbme.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }


}
