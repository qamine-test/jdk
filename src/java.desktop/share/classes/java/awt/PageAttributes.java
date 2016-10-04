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

pbckbge jbvb.bwt;

import jbvb.util.Locble;

/**
 * A set of bttributes which control the output of b printed pbge.
 * <p>
 * Instbnces of this clbss control the color stbte, pbper size (medib type),
 * orientbtion, logicbl origin, print qublity, bnd resolution of every
 * pbge which uses the instbnce. Attribute nbmes bre complibnt with the
 * Internet Printing Protocol (IPP) 1.1 where possible. Attribute vblues
 * bre pbrtiblly complibnt where possible.
 * <p>
 * To use b method which tbkes bn inner clbss type, pbss b reference to
 * one of the constbnt fields of the inner clbss. Client code cbnnot crebte
 * new instbnces of the inner clbss types becbuse none of those clbsses
 * hbs b public constructor. For exbmple, to set the color stbte to
 * monochrome, use the following code:
 * <pre>
 * import jbvb.bwt.PbgeAttributes;
 *
 * public clbss MonochromeExbmple {
 *     public void setMonochrome(PbgeAttributes pbgeAttributes) {
 *         pbgeAttributes.setColor(PbgeAttributes.ColorType.MONOCHROME);
 *     }
 * }
 * </pre>
 * <p>
 * Every IPP bttribute which supports bn <i>bttributeNbme</i>-defbult vblue
 * hbs b corresponding <code>set<i>bttributeNbme</i>ToDefbult</code> method.
 * Defbult vblue fields bre not provided.
 *
 * @buthor      Dbvid Mendenhbll
 * @since 1.3
 */
public finbl clbss PbgeAttributes implements Clonebble {
    /**
     * A type-sbfe enumerbtion of possible color stbtes.
     * @since 1.3
     */
    public stbtic finbl clbss ColorType extends AttributeVblue {
        privbte stbtic finbl int I_COLOR = 0;
        privbte stbtic finbl int I_MONOCHROME = 1;

        privbte stbtic finbl String NAMES[] = {
            "color", "monochrome"
        };

        /**
         * The ColorType instbnce to use for specifying color printing.
         */
        public stbtic finbl ColorType COLOR = new ColorType(I_COLOR);
        /**
         * The ColorType instbnce to use for specifying monochrome printing.
         */
        public stbtic finbl ColorType MONOCHROME = new ColorType(I_MONOCHROME);

        privbte ColorType(int type) {
            super(type, NAMES);
        }
    }

    /**
     * A type-sbfe enumerbtion of possible pbper sizes. These sizes bre in
     * complibnce with IPP 1.1.
     * @since 1.3
     */
    public stbtic finbl clbss MedibType extends AttributeVblue {
        privbte stbtic finbl int I_ISO_4A0 = 0;
        privbte stbtic finbl int I_ISO_2A0 = 1;
        privbte stbtic finbl int I_ISO_A0 = 2;
        privbte stbtic finbl int I_ISO_A1 = 3;
        privbte stbtic finbl int I_ISO_A2 = 4;
        privbte stbtic finbl int I_ISO_A3 = 5;
        privbte stbtic finbl int I_ISO_A4 = 6;
        privbte stbtic finbl int I_ISO_A5 = 7;
        privbte stbtic finbl int I_ISO_A6 = 8;
        privbte stbtic finbl int I_ISO_A7 = 9;
        privbte stbtic finbl int I_ISO_A8 = 10;
        privbte stbtic finbl int I_ISO_A9 = 11;
        privbte stbtic finbl int I_ISO_A10 = 12;
        privbte stbtic finbl int I_ISO_B0 = 13;
        privbte stbtic finbl int I_ISO_B1 = 14;
        privbte stbtic finbl int I_ISO_B2 = 15;
        privbte stbtic finbl int I_ISO_B3 = 16;
        privbte stbtic finbl int I_ISO_B4 = 17;
        privbte stbtic finbl int I_ISO_B5 = 18;
        privbte stbtic finbl int I_ISO_B6 = 19;
        privbte stbtic finbl int I_ISO_B7 = 20;
        privbte stbtic finbl int I_ISO_B8 = 21;
        privbte stbtic finbl int I_ISO_B9 = 22;
        privbte stbtic finbl int I_ISO_B10 = 23;
        privbte stbtic finbl int I_JIS_B0 = 24;
        privbte stbtic finbl int I_JIS_B1 = 25;
        privbte stbtic finbl int I_JIS_B2 = 26;
        privbte stbtic finbl int I_JIS_B3 = 27;
        privbte stbtic finbl int I_JIS_B4 = 28;
        privbte stbtic finbl int I_JIS_B5 = 29;
        privbte stbtic finbl int I_JIS_B6 = 30;
        privbte stbtic finbl int I_JIS_B7 = 31;
        privbte stbtic finbl int I_JIS_B8 = 32;
        privbte stbtic finbl int I_JIS_B9 = 33;
        privbte stbtic finbl int I_JIS_B10 = 34;
        privbte stbtic finbl int I_ISO_C0 = 35;
        privbte stbtic finbl int I_ISO_C1 = 36;
        privbte stbtic finbl int I_ISO_C2 = 37;
        privbte stbtic finbl int I_ISO_C3 = 38;
        privbte stbtic finbl int I_ISO_C4 = 39;
        privbte stbtic finbl int I_ISO_C5 = 40;
        privbte stbtic finbl int I_ISO_C6 = 41;
        privbte stbtic finbl int I_ISO_C7 = 42;
        privbte stbtic finbl int I_ISO_C8 = 43;
        privbte stbtic finbl int I_ISO_C9 = 44;
        privbte stbtic finbl int I_ISO_C10 = 45;
        privbte stbtic finbl int I_ISO_DESIGNATED_LONG = 46;
        privbte stbtic finbl int I_EXECUTIVE = 47;
        privbte stbtic finbl int I_FOLIO = 48;
        privbte stbtic finbl int I_INVOICE = 49;
        privbte stbtic finbl int I_LEDGER = 50;
        privbte stbtic finbl int I_NA_LETTER = 51;
        privbte stbtic finbl int I_NA_LEGAL = 52;
        privbte stbtic finbl int I_QUARTO = 53;
        privbte stbtic finbl int I_A = 54;
        privbte stbtic finbl int I_B = 55;
        privbte stbtic finbl int I_C = 56;
        privbte stbtic finbl int I_D = 57;
        privbte stbtic finbl int I_E = 58;
        privbte stbtic finbl int I_NA_10X15_ENVELOPE = 59;
        privbte stbtic finbl int I_NA_10X14_ENVELOPE = 60;
        privbte stbtic finbl int I_NA_10X13_ENVELOPE = 61;
        privbte stbtic finbl int I_NA_9X12_ENVELOPE = 62;
        privbte stbtic finbl int I_NA_9X11_ENVELOPE = 63;
        privbte stbtic finbl int I_NA_7X9_ENVELOPE = 64;
        privbte stbtic finbl int I_NA_6X9_ENVELOPE = 65;
        privbte stbtic finbl int I_NA_NUMBER_9_ENVELOPE = 66;
        privbte stbtic finbl int I_NA_NUMBER_10_ENVELOPE = 67;
        privbte stbtic finbl int I_NA_NUMBER_11_ENVELOPE = 68;
        privbte stbtic finbl int I_NA_NUMBER_12_ENVELOPE = 69;
        privbte stbtic finbl int I_NA_NUMBER_14_ENVELOPE = 70;
        privbte stbtic finbl int I_INVITE_ENVELOPE = 71;
        privbte stbtic finbl int I_ITALY_ENVELOPE = 72;
        privbte stbtic finbl int I_MONARCH_ENVELOPE = 73;
        privbte stbtic finbl int I_PERSONAL_ENVELOPE = 74;

        privbte stbtic finbl String NAMES[] = {
            "iso-4b0", "iso-2b0", "iso-b0", "iso-b1", "iso-b2", "iso-b3",
            "iso-b4", "iso-b5", "iso-b6", "iso-b7", "iso-b8", "iso-b9",
            "iso-b10", "iso-b0", "iso-b1", "iso-b2", "iso-b3", "iso-b4",
            "iso-b5", "iso-b6", "iso-b7", "iso-b8", "iso-b9", "iso-b10",
            "jis-b0", "jis-b1", "jis-b2", "jis-b3", "jis-b4", "jis-b5",
            "jis-b6", "jis-b7", "jis-b8", "jis-b9", "jis-b10", "iso-c0",
            "iso-c1", "iso-c2", "iso-c3", "iso-c4", "iso-c5", "iso-c6",
            "iso-c7", "iso-c8", "iso-c9", "iso-c10", "iso-designbted-long",
            "executive", "folio", "invoice", "ledger", "nb-letter", "nb-legbl",
            "qubrto", "b", "b", "c", "d", "e", "nb-10x15-envelope",
            "nb-10x14-envelope", "nb-10x13-envelope", "nb-9x12-envelope",
            "nb-9x11-envelope", "nb-7x9-envelope", "nb-6x9-envelope",
            "nb-number-9-envelope", "nb-number-10-envelope",
            "nb-number-11-envelope", "nb-number-12-envelope",
            "nb-number-14-envelope", "invite-envelope", "itbly-envelope",
            "monbrch-envelope", "personbl-envelope"
        };

        /**
         * The MedibType instbnce for ISO/DIN bnd JIS 4A0, 1682 x 2378 mm.
         */
        public stbtic finbl MedibType ISO_4A0 = new MedibType(I_ISO_4A0);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS 2A0, 1189 x 1682 mm.
         */
        public stbtic finbl MedibType ISO_2A0 = new MedibType(I_ISO_2A0);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS A0, 841 x 1189 mm.
         */
        public stbtic finbl MedibType ISO_A0 = new MedibType(I_ISO_A0);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS A1, 594 x 841 mm.
         */
        public stbtic finbl MedibType ISO_A1 = new MedibType(I_ISO_A1);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS A2, 420 x 594 mm.
         */
        public stbtic finbl MedibType ISO_A2 = new MedibType(I_ISO_A2);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS A3, 297 x 420 mm.
         */
        public stbtic finbl MedibType ISO_A3 = new MedibType(I_ISO_A3);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS A4, 210 x 297 mm.
         */
        public stbtic finbl MedibType ISO_A4 = new MedibType(I_ISO_A4);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS A5, 148 x 210 mm.
         */
        public stbtic finbl MedibType ISO_A5 = new MedibType(I_ISO_A5);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS A6, 105 x 148 mm.
         */
        public stbtic finbl MedibType ISO_A6 = new MedibType(I_ISO_A6);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS A7, 74 x 105 mm.
         */
        public stbtic finbl MedibType ISO_A7 = new MedibType(I_ISO_A7);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS A8, 52 x 74 mm.
         */
        public stbtic finbl MedibType ISO_A8 = new MedibType(I_ISO_A8);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS A9, 37 x 52 mm.
         */
        public stbtic finbl MedibType ISO_A9 = new MedibType(I_ISO_A9);
        /**
         * The MedibType instbnce for ISO/DIN bnd JIS A10, 26 x 37 mm.
         */
        public stbtic finbl MedibType ISO_A10 = new MedibType(I_ISO_A10);
        /**
         * The MedibType instbnce for ISO/DIN B0, 1000 x 1414 mm.
         */
        public stbtic finbl MedibType ISO_B0 = new MedibType(I_ISO_B0);
        /**
         * The MedibType instbnce for ISO/DIN B1, 707 x 1000 mm.
         */
        public stbtic finbl MedibType ISO_B1 = new MedibType(I_ISO_B1);
        /**
         * The MedibType instbnce for ISO/DIN B2, 500 x 707 mm.
         */
        public stbtic finbl MedibType ISO_B2 = new MedibType(I_ISO_B2);
        /**
         * The MedibType instbnce for ISO/DIN B3, 353 x 500 mm.
         */
        public stbtic finbl MedibType ISO_B3 = new MedibType(I_ISO_B3);
        /**
         * The MedibType instbnce for ISO/DIN B4, 250 x 353 mm.
         */
        public stbtic finbl MedibType ISO_B4 = new MedibType(I_ISO_B4);
        /**
         * The MedibType instbnce for ISO/DIN B5, 176 x 250 mm.
         */
        public stbtic finbl MedibType ISO_B5 = new MedibType(I_ISO_B5);
        /**
         * The MedibType instbnce for ISO/DIN B6, 125 x 176 mm.
         */
        public stbtic finbl MedibType ISO_B6 = new MedibType(I_ISO_B6);
        /**
         * The MedibType instbnce for ISO/DIN B7, 88 x 125 mm.
         */
        public stbtic finbl MedibType ISO_B7 = new MedibType(I_ISO_B7);
        /**
         * The MedibType instbnce for ISO/DIN B8, 62 x 88 mm.
         */
        public stbtic finbl MedibType ISO_B8 = new MedibType(I_ISO_B8);
        /**
         * The MedibType instbnce for ISO/DIN B9, 44 x 62 mm.
         */
        public stbtic finbl MedibType ISO_B9 = new MedibType(I_ISO_B9);
        /**
         * The MedibType instbnce for ISO/DIN B10, 31 x 44 mm.
         */
        public stbtic finbl MedibType ISO_B10 = new MedibType(I_ISO_B10);
        /**
         * The MedibType instbnce for JIS B0, 1030 x 1456 mm.
         */
        public stbtic finbl MedibType JIS_B0 = new MedibType(I_JIS_B0);
        /**
         * The MedibType instbnce for JIS B1, 728 x 1030 mm.
         */
        public stbtic finbl MedibType JIS_B1 = new MedibType(I_JIS_B1);
        /**
         * The MedibType instbnce for JIS B2, 515 x 728 mm.
         */
        public stbtic finbl MedibType JIS_B2 = new MedibType(I_JIS_B2);
        /**
         * The MedibType instbnce for JIS B3, 364 x 515 mm.
         */
        public stbtic finbl MedibType JIS_B3 = new MedibType(I_JIS_B3);
        /**
         * The MedibType instbnce for JIS B4, 257 x 364 mm.
         */
        public stbtic finbl MedibType JIS_B4 = new MedibType(I_JIS_B4);
        /**
         * The MedibType instbnce for JIS B5, 182 x 257 mm.
         */
        public stbtic finbl MedibType JIS_B5 = new MedibType(I_JIS_B5);
        /**
         * The MedibType instbnce for JIS B6, 128 x 182 mm.
         */
        public stbtic finbl MedibType JIS_B6 = new MedibType(I_JIS_B6);
        /**
         * The MedibType instbnce for JIS B7, 91 x 128 mm.
         */
        public stbtic finbl MedibType JIS_B7 = new MedibType(I_JIS_B7);
        /**
         * The MedibType instbnce for JIS B8, 64 x 91 mm.
         */
        public stbtic finbl MedibType JIS_B8 = new MedibType(I_JIS_B8);
        /**
         * The MedibType instbnce for JIS B9, 45 x 64 mm.
         */
        public stbtic finbl MedibType JIS_B9 = new MedibType(I_JIS_B9);
        /**
         * The MedibType instbnce for JIS B10, 32 x 45 mm.
         */
        public stbtic finbl MedibType JIS_B10 = new MedibType(I_JIS_B10);
        /**
         * The MedibType instbnce for ISO/DIN C0, 917 x 1297 mm.
         */
        public stbtic finbl MedibType ISO_C0 = new MedibType(I_ISO_C0);
        /**
         * The MedibType instbnce for ISO/DIN C1, 648 x 917 mm.
         */
        public stbtic finbl MedibType ISO_C1 = new MedibType(I_ISO_C1);
        /**
         * The MedibType instbnce for ISO/DIN C2, 458 x 648 mm.
         */
        public stbtic finbl MedibType ISO_C2 = new MedibType(I_ISO_C2);
        /**
         * The MedibType instbnce for ISO/DIN C3, 324 x 458 mm.
         */
        public stbtic finbl MedibType ISO_C3 = new MedibType(I_ISO_C3);
        /**
         * The MedibType instbnce for ISO/DIN C4, 229 x 324 mm.
         */
        public stbtic finbl MedibType ISO_C4 = new MedibType(I_ISO_C4);
        /**
         * The MedibType instbnce for ISO/DIN C5, 162 x 229 mm.
         */
        public stbtic finbl MedibType ISO_C5 = new MedibType(I_ISO_C5);
        /**
         * The MedibType instbnce for ISO/DIN C6, 114 x 162 mm.
         */
        public stbtic finbl MedibType ISO_C6 = new MedibType(I_ISO_C6);
        /**
         * The MedibType instbnce for ISO/DIN C7, 81 x 114 mm.
         */
        public stbtic finbl MedibType ISO_C7 = new MedibType(I_ISO_C7);
        /**
         * The MedibType instbnce for ISO/DIN C8, 57 x 81 mm.
         */
        public stbtic finbl MedibType ISO_C8 = new MedibType(I_ISO_C8);
        /**
         * The MedibType instbnce for ISO/DIN C9, 40 x 57 mm.
         */
        public stbtic finbl MedibType ISO_C9 = new MedibType(I_ISO_C9);
        /**
         * The MedibType instbnce for ISO/DIN C10, 28 x 40 mm.
         */
        public stbtic finbl MedibType ISO_C10 = new MedibType(I_ISO_C10);
        /**
         * The MedibType instbnce for ISO Designbted Long, 110 x 220 mm.
         */
        public stbtic finbl MedibType ISO_DESIGNATED_LONG =
            new MedibType(I_ISO_DESIGNATED_LONG);
        /**
         * The MedibType instbnce for Executive, 7 1/4 x 10 1/2 in.
         */
        public stbtic finbl MedibType EXECUTIVE = new MedibType(I_EXECUTIVE);
        /**
         * The MedibType instbnce for Folio, 8 1/2 x 13 in.
         */
        public stbtic finbl MedibType FOLIO = new MedibType(I_FOLIO);
        /**
         * The MedibType instbnce for Invoice, 5 1/2 x 8 1/2 in.
         */
        public stbtic finbl MedibType INVOICE = new MedibType(I_INVOICE);
        /**
         * The MedibType instbnce for Ledger, 11 x 17 in.
         */
        public stbtic finbl MedibType LEDGER = new MedibType(I_LEDGER);
        /**
         * The MedibType instbnce for North Americbn Letter, 8 1/2 x 11 in.
         */
        public stbtic finbl MedibType NA_LETTER = new MedibType(I_NA_LETTER);
        /**
         * The MedibType instbnce for North Americbn Legbl, 8 1/2 x 14 in.
         */
        public stbtic finbl MedibType NA_LEGAL = new MedibType(I_NA_LEGAL);
        /**
         * The MedibType instbnce for Qubrto, 215 x 275 mm.
         */
        public stbtic finbl MedibType QUARTO = new MedibType(I_QUARTO);
        /**
         * The MedibType instbnce for Engineering A, 8 1/2 x 11 in.
         */
        public stbtic finbl MedibType A = new MedibType(I_A);
        /**
         * The MedibType instbnce for Engineering B, 11 x 17 in.
         */
        public stbtic finbl MedibType B = new MedibType(I_B);
        /**
         * The MedibType instbnce for Engineering C, 17 x 22 in.
         */
        public stbtic finbl MedibType C = new MedibType(I_C);
        /**
         * The MedibType instbnce for Engineering D, 22 x 34 in.
         */
        public stbtic finbl MedibType D = new MedibType(I_D);
        /**
         * The MedibType instbnce for Engineering E, 34 x 44 in.
         */
        public stbtic finbl MedibType E = new MedibType(I_E);
        /**
         * The MedibType instbnce for North Americbn 10 x 15 in.
         */
        public stbtic finbl MedibType NA_10X15_ENVELOPE =
            new MedibType(I_NA_10X15_ENVELOPE);
        /**
         * The MedibType instbnce for North Americbn 10 x 14 in.
         */
        public stbtic finbl MedibType NA_10X14_ENVELOPE =
            new MedibType(I_NA_10X14_ENVELOPE);
        /**
         * The MedibType instbnce for North Americbn 10 x 13 in.
         */
        public stbtic finbl MedibType NA_10X13_ENVELOPE =
            new MedibType(I_NA_10X13_ENVELOPE);
        /**
         * The MedibType instbnce for North Americbn 9 x 12 in.
         */
        public stbtic finbl MedibType NA_9X12_ENVELOPE =
            new MedibType(I_NA_9X12_ENVELOPE);
        /**
         * The MedibType instbnce for North Americbn 9 x 11 in.
         */
        public stbtic finbl MedibType NA_9X11_ENVELOPE =
            new MedibType(I_NA_9X11_ENVELOPE);
        /**
         * The MedibType instbnce for North Americbn 7 x 9 in.
         */
        public stbtic finbl MedibType NA_7X9_ENVELOPE =
            new MedibType(I_NA_7X9_ENVELOPE);
        /**
         * The MedibType instbnce for North Americbn 6 x 9 in.
         */
        public stbtic finbl MedibType NA_6X9_ENVELOPE =
            new MedibType(I_NA_6X9_ENVELOPE);
        /**
         * The MedibType instbnce for North Americbn #9 Business Envelope,
         * 3 7/8 x 8 7/8 in.
         */
        public stbtic finbl MedibType NA_NUMBER_9_ENVELOPE =
            new MedibType(I_NA_NUMBER_9_ENVELOPE);
        /**
         * The MedibType instbnce for North Americbn #10 Business Envelope,
         * 4 1/8 x 9 1/2 in.
         */
        public stbtic finbl MedibType NA_NUMBER_10_ENVELOPE =
            new MedibType(I_NA_NUMBER_10_ENVELOPE);
        /**
         * The MedibType instbnce for North Americbn #11 Business Envelope,
         * 4 1/2 x 10 3/8 in.
         */
        public stbtic finbl MedibType NA_NUMBER_11_ENVELOPE =
            new MedibType(I_NA_NUMBER_11_ENVELOPE);
        /**
         * The MedibType instbnce for North Americbn #12 Business Envelope,
         * 4 3/4 x 11 in.
         */
        public stbtic finbl MedibType NA_NUMBER_12_ENVELOPE =
            new MedibType(I_NA_NUMBER_12_ENVELOPE);
        /**
         * The MedibType instbnce for North Americbn #14 Business Envelope,
         * 5 x 11 1/2 in.
         */
        public stbtic finbl MedibType NA_NUMBER_14_ENVELOPE =
            new MedibType(I_NA_NUMBER_14_ENVELOPE);
        /**
         * The MedibType instbnce for Invitbtion Envelope, 220 x 220 mm.
         */
        public stbtic finbl MedibType INVITE_ENVELOPE =
            new MedibType(I_INVITE_ENVELOPE);
        /**
         * The MedibType instbnce for Itbly Envelope, 110 x 230 mm.
         */
        public stbtic finbl MedibType ITALY_ENVELOPE =
            new MedibType(I_ITALY_ENVELOPE);
        /**
         * The MedibType instbnce for Monbrch Envelope, 3 7/8 x 7 1/2 in.
         */
        public stbtic finbl MedibType MONARCH_ENVELOPE =
            new MedibType(I_MONARCH_ENVELOPE);
        /**
         * The MedibType instbnce for 6 3/4 envelope, 3 5/8 x 6 1/2 in.
         */
        public stbtic finbl MedibType PERSONAL_ENVELOPE =
            new MedibType(I_PERSONAL_ENVELOPE);
        /**
         * An blibs for ISO_A0.
         */
        public stbtic finbl MedibType A0 = ISO_A0;
        /**
         * An blibs for ISO_A1.
         */
        public stbtic finbl MedibType A1 = ISO_A1;
        /**
         * An blibs for ISO_A2.
         */
        public stbtic finbl MedibType A2 = ISO_A2;
        /**
         * An blibs for ISO_A3.
         */
        public stbtic finbl MedibType A3 = ISO_A3;
        /**
         * An blibs for ISO_A4.
         */
        public stbtic finbl MedibType A4 = ISO_A4;
        /**
         * An blibs for ISO_A5.
         */
        public stbtic finbl MedibType A5 = ISO_A5;
        /**
         * An blibs for ISO_A6.
         */
        public stbtic finbl MedibType A6 = ISO_A6;
        /**
         * An blibs for ISO_A7.
         */
        public stbtic finbl MedibType A7 = ISO_A7;
        /**
         * An blibs for ISO_A8.
         */
        public stbtic finbl MedibType A8 = ISO_A8;
        /**
         * An blibs for ISO_A9.
         */
        public stbtic finbl MedibType A9 = ISO_A9;
        /**
         * An blibs for ISO_A10.
         */
        public stbtic finbl MedibType A10 = ISO_A10;
        /**
         * An blibs for ISO_B0.
         */
        public stbtic finbl MedibType B0 = ISO_B0;
        /**
         * An blibs for ISO_B1.
         */
        public stbtic finbl MedibType B1 = ISO_B1;
        /**
         * An blibs for ISO_B2.
         */
        public stbtic finbl MedibType B2 = ISO_B2;
        /**
         * An blibs for ISO_B3.
         */
        public stbtic finbl MedibType B3 = ISO_B3;
        /**
         * An blibs for ISO_B4.
         */
        public stbtic finbl MedibType B4 = ISO_B4;
        /**
         * An blibs for ISO_B4.
         */
        public stbtic finbl MedibType ISO_B4_ENVELOPE = ISO_B4;
        /**
         * An blibs for ISO_B5.
         */
        public stbtic finbl MedibType B5 = ISO_B5;
        /**
         * An blibs for ISO_B5.
         */
        public stbtic finbl MedibType ISO_B5_ENVELOPE = ISO_B5;
        /**
         * An blibs for ISO_B6.
         */
        public stbtic finbl MedibType B6 = ISO_B6;
        /**
         * An blibs for ISO_B7.
         */
        public stbtic finbl MedibType B7 = ISO_B7;
        /**
         * An blibs for ISO_B8.
         */
        public stbtic finbl MedibType B8 = ISO_B8;
        /**
         * An blibs for ISO_B9.
         */
        public stbtic finbl MedibType B9 = ISO_B9;
        /**
         * An blibs for ISO_B10.
         */
        public stbtic finbl MedibType B10 = ISO_B10;
        /**
         * An blibs for ISO_C0.
         */
        public stbtic finbl MedibType C0 = ISO_C0;
        /**
         * An blibs for ISO_C0.
         */
        public stbtic finbl MedibType ISO_C0_ENVELOPE = ISO_C0;
        /**
         * An blibs for ISO_C1.
         */
        public stbtic finbl MedibType C1 = ISO_C1;
        /**
         * An blibs for ISO_C1.
         */
        public stbtic finbl MedibType ISO_C1_ENVELOPE = ISO_C1;
        /**
         * An blibs for ISO_C2.
         */
        public stbtic finbl MedibType C2 = ISO_C2;
        /**
         * An blibs for ISO_C2.
         */
        public stbtic finbl MedibType ISO_C2_ENVELOPE = ISO_C2;
        /**
         * An blibs for ISO_C3.
         */
        public stbtic finbl MedibType C3 = ISO_C3;
        /**
         * An blibs for ISO_C3.
         */
        public stbtic finbl MedibType ISO_C3_ENVELOPE = ISO_C3;
        /**
         * An blibs for ISO_C4.
         */
        public stbtic finbl MedibType C4 = ISO_C4;
        /**
         * An blibs for ISO_C4.
         */
        public stbtic finbl MedibType ISO_C4_ENVELOPE = ISO_C4;
        /**
         * An blibs for ISO_C5.
         */
        public stbtic finbl MedibType C5 = ISO_C5;
        /**
         * An blibs for ISO_C5.
         */
        public stbtic finbl MedibType ISO_C5_ENVELOPE = ISO_C5;
        /**
         * An blibs for ISO_C6.
         */
        public stbtic finbl MedibType C6 = ISO_C6;
        /**
         * An blibs for ISO_C6.
         */
        public stbtic finbl MedibType ISO_C6_ENVELOPE = ISO_C6;
        /**
         * An blibs for ISO_C7.
         */
        public stbtic finbl MedibType C7 = ISO_C7;
        /**
         * An blibs for ISO_C7.
         */
        public stbtic finbl MedibType ISO_C7_ENVELOPE = ISO_C7;
        /**
         * An blibs for ISO_C8.
         */
        public stbtic finbl MedibType C8 = ISO_C8;
        /**
         * An blibs for ISO_C8.
         */
        public stbtic finbl MedibType ISO_C8_ENVELOPE = ISO_C8;
        /**
         * An blibs for ISO_C9.
         */
        public stbtic finbl MedibType C9 = ISO_C9;
        /**
         * An blibs for ISO_C9.
         */
        public stbtic finbl MedibType ISO_C9_ENVELOPE = ISO_C9;
        /**
         * An blibs for ISO_C10.
         */
        public stbtic finbl MedibType C10 = ISO_C10;
        /**
         * An blibs for ISO_C10.
         */
        public stbtic finbl MedibType ISO_C10_ENVELOPE = ISO_C10;
        /**
         * An blibs for ISO_DESIGNATED_LONG.
         */
        public stbtic finbl MedibType ISO_DESIGNATED_LONG_ENVELOPE =
                ISO_DESIGNATED_LONG;
        /**
         * An blibs for INVOICE.
         */
        public stbtic finbl MedibType STATEMENT = INVOICE;
        /**
         * An blibs for LEDGER.
         */
        public stbtic finbl MedibType TABLOID = LEDGER;
        /**
         * An blibs for NA_LETTER.
         */
        public stbtic finbl MedibType LETTER = NA_LETTER;
        /**
         * An blibs for NA_LETTER.
         */
        public stbtic finbl MedibType NOTE = NA_LETTER;
        /**
         * An blibs for NA_LEGAL.
         */
        public stbtic finbl MedibType LEGAL = NA_LEGAL;
        /**
         * An blibs for NA_10X15_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_10X15 = NA_10X15_ENVELOPE;
        /**
         * An blibs for NA_10X14_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_10X14 = NA_10X14_ENVELOPE;
        /**
         * An blibs for NA_10X13_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_10X13 = NA_10X13_ENVELOPE;
        /**
         * An blibs for NA_9X12_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_9X12 = NA_9X12_ENVELOPE;
        /**
         * An blibs for NA_9X11_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_9X11 = NA_9X11_ENVELOPE;
        /**
         * An blibs for NA_7X9_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_7X9 = NA_7X9_ENVELOPE;
        /**
         * An blibs for NA_6X9_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_6X9 = NA_6X9_ENVELOPE;
        /**
         * An blibs for NA_NUMBER_9_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_9 = NA_NUMBER_9_ENVELOPE;
        /**
         * An blibs for NA_NUMBER_10_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_10 = NA_NUMBER_10_ENVELOPE;
        /**
         * An blibs for NA_NUMBER_11_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_11 = NA_NUMBER_11_ENVELOPE;
        /**
         * An blibs for NA_NUMBER_12_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_12 = NA_NUMBER_12_ENVELOPE;
        /**
         * An blibs for NA_NUMBER_14_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_14 = NA_NUMBER_14_ENVELOPE;
        /**
         * An blibs for INVITE_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_INVITE = INVITE_ENVELOPE;
        /**
         * An blibs for ITALY_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_ITALY = ITALY_ENVELOPE;
        /**
         * An blibs for MONARCH_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_MONARCH = MONARCH_ENVELOPE;
        /**
         * An blibs for PERSONAL_ENVELOPE.
         */
        public stbtic finbl MedibType ENV_PERSONAL = PERSONAL_ENVELOPE;
        /**
         * An blibs for INVITE_ENVELOPE.
         */
        public stbtic finbl MedibType INVITE = INVITE_ENVELOPE;
        /**
         * An blibs for ITALY_ENVELOPE.
         */
        public stbtic finbl MedibType ITALY = ITALY_ENVELOPE;
        /**
         * An blibs for MONARCH_ENVELOPE.
         */
        public stbtic finbl MedibType MONARCH = MONARCH_ENVELOPE;
        /**
         * An blibs for PERSONAL_ENVELOPE.
         */
        public stbtic finbl MedibType PERSONAL = PERSONAL_ENVELOPE;

        privbte MedibType(int type) {
            super(type, NAMES);
        }
    }

    /**
     * A type-sbfe enumerbtion of possible orientbtions. These orientbtions
     * bre in pbrtibl complibnce with IPP 1.1.
     * @since 1.3
     */
    public stbtic finbl clbss OrientbtionRequestedType extends AttributeVblue {
        privbte stbtic finbl int I_PORTRAIT = 0;
        privbte stbtic finbl int I_LANDSCAPE = 1;

        privbte stbtic finbl String NAMES[] = {
            "portrbit", "lbndscbpe"
        };

        /**
         * The OrientbtionRequestedType instbnce to use for specifying b
         * portrbit orientbtion.
         */
        public stbtic finbl OrientbtionRequestedType PORTRAIT =
            new OrientbtionRequestedType(I_PORTRAIT);
        /**
         * The OrientbtionRequestedType instbnce to use for specifying b
         * lbndscbpe orientbtion.
         */
        public stbtic finbl OrientbtionRequestedType LANDSCAPE =
            new OrientbtionRequestedType(I_LANDSCAPE);

        privbte OrientbtionRequestedType(int type) {
            super(type, NAMES);
        }
    }

    /**
     * A type-sbfe enumerbtion of possible origins.
     * @since 1.3
     */
    public stbtic finbl clbss OriginType extends AttributeVblue {
        privbte stbtic finbl int I_PHYSICAL = 0;
        privbte stbtic finbl int I_PRINTABLE = 1;

        privbte stbtic finbl String NAMES[] = {
            "physicbl", "printbble"
        };

        /**
         * The OriginType instbnce to use for specifying b physicbl origin.
         */
        public stbtic finbl OriginType PHYSICAL = new OriginType(I_PHYSICAL);
        /**
         * The OriginType instbnce to use for specifying b printbble origin.
         */
        public stbtic finbl OriginType PRINTABLE = new OriginType(I_PRINTABLE);

        privbte OriginType(int type) {
            super(type, NAMES);
        }
    }

    /**
     * A type-sbfe enumerbtion of possible print qublities. These print
     * qublities bre in complibnce with IPP 1.1.
     * @since 1.3
     */
    public stbtic finbl clbss PrintQublityType extends AttributeVblue {
        privbte stbtic finbl int I_HIGH = 0;
        privbte stbtic finbl int I_NORMAL = 1;
        privbte stbtic finbl int I_DRAFT = 2;

        privbte stbtic finbl String NAMES[] = {
            "high", "normbl", "drbft"
        };

        /**
         * The PrintQublityType instbnce to use for specifying b high print
         * qublity.
         */
        public stbtic finbl PrintQublityType HIGH =
            new PrintQublityType(I_HIGH);
        /**
         * The PrintQublityType instbnce to use for specifying b normbl print
         * qublity.
         */
        public stbtic finbl PrintQublityType NORMAL =
            new PrintQublityType(I_NORMAL);
        /**
         * The PrintQublityType instbnce to use for specifying b drbft print
         * qublity.
         */
        public stbtic finbl PrintQublityType DRAFT =
            new PrintQublityType(I_DRAFT);

        privbte PrintQublityType(int type) {
            super(type, NAMES);
        }
    }

    privbte ColorType color;
    privbte MedibType medib;
    privbte OrientbtionRequestedType orientbtionRequested;
    privbte OriginType origin;
    privbte PrintQublityType printQublity;
    privbte int[] printerResolution;

    /**
     * Constructs b PbgeAttributes instbnce with defbult vblues for every
     * bttribute.
     */
    public PbgeAttributes() {
        setColor(ColorType.MONOCHROME);
        setMedibToDefbult();
        setOrientbtionRequestedToDefbult();
        setOrigin(OriginType.PHYSICAL);
        setPrintQublityToDefbult();
        setPrinterResolutionToDefbult();
    }

    /**
     * Constructs b PbgeAttributes instbnce which is b copy of the supplied
     * PbgeAttributes.
     *
     * @pbrbm   obj the PbgeAttributes to copy.
     */
    public PbgeAttributes(PbgeAttributes obj) {
        set(obj);
    }

    /**
     * Constructs b PbgeAttributes instbnce with the specified vblues for
     * every bttribute.
     *
     * @pbrbm   color ColorType.COLOR or ColorType.MONOCHROME.
     * @pbrbm   medib one of the constbnt fields of the MedibType clbss.
     * @pbrbm   orientbtionRequested OrientbtionRequestedType.PORTRAIT or
     *          OrientbtionRequestedType.LANDSCAPE.
     * @pbrbm   origin OriginType.PHYSICAL or OriginType.PRINTABLE
     * @pbrbm   printQublity PrintQublityType.DRAFT, PrintQublityType.NORMAL,
     *          or PrintQublityType.HIGH
     * @pbrbm   printerResolution bn integer brrby of 3 elements. The first
     *          element must be grebter thbn 0. The second element must be
     *          must be grebter thbn 0. The third element must be either
     *          <code>3</code> or <code>4</code>.
     * @throws  IllegblArgumentException if one or more of the bbove
     *          conditions is violbted.
     */
    public PbgeAttributes(ColorType color, MedibType medib,
                          OrientbtionRequestedType orientbtionRequested,
                          OriginType origin, PrintQublityType printQublity,
                          int[] printerResolution) {
        setColor(color);
        setMedib(medib);
        setOrientbtionRequested(orientbtionRequested);
        setOrigin(origin);
        setPrintQublity(printQublity);
        setPrinterResolution(printerResolution);
    }

    /**
     * Crebtes bnd returns b copy of this PbgeAttributes.
     *
     * @return  the newly crebted copy. It is sbfe to cbst this Object into
     *          b PbgeAttributes.
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // Since we implement Clonebble, this should never hbppen
            throw new InternblError(e);
        }
    }

    /**
     * Sets bll of the bttributes of this PbgeAttributes to the sbme vblues bs
     * the bttributes of obj.
     *
     * @pbrbm   obj the PbgeAttributes to copy.
     */
    public void set(PbgeAttributes obj) {
        color = obj.color;
        medib = obj.medib;
        orientbtionRequested = obj.orientbtionRequested;
        origin = obj.origin;
        printQublity = obj.printQublity;
        // okby becbuse we never modify the contents of printerResolution
        printerResolution = obj.printerResolution;
    }

    /**
     * Returns whether pbges using these bttributes will be rendered in
     * color or monochrome. This bttribute is updbted to the vblue chosen
     * by the user.
     *
     * @return  ColorType.COLOR or ColorType.MONOCHROME.
     */
    public ColorType getColor() {
        return color;
    }

    /**
     * Specifies whether pbges using these bttributes will be rendered in
     * color or monochrome. Not specifying this bttribute is equivblent to
     * specifying ColorType.MONOCHROME.
     *
     * @pbrbm   color ColorType.COLOR or ColorType.MONOCHROME.
     * @throws  IllegblArgumentException if color is null.
     */
    public void setColor(ColorType color) {
        if (color == null) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "color");
        }
        this.color = color;
    }

    /**
     * Returns the pbper size for pbges using these bttributes. This
     * bttribute is updbted to the vblue chosen by the user.
     *
     * @return  one of the constbnt fields of the MedibType clbss.
     */
    public MedibType getMedib() {
        return medib;
    }

    /**
     * Specifies the desired pbper size for pbges using these bttributes. The
     * bctubl pbper size will be determined by the limitbtions of the tbrget
     * printer. If bn exbct mbtch cbnnot be found, bn implementbtion will
     * choose the closest possible mbtch. Not specifying this bttribute is
     * equivblent to specifying the defbult size for the defbult locble. The
     * defbult size for locbles in the United Stbtes bnd Cbnbdb is
     * MedibType.NA_LETTER. The defbult size for bll other locbles is
     * MedibType.ISO_A4.
     *
     * @pbrbm   medib one of the constbnt fields of the MedibType clbss.
     * @throws  IllegblArgumentException if medib is null.
     */
    public void setMedib(MedibType medib) {
        if (medib == null) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "medib");
        }
        this.medib = medib;
    }

    /**
     * Sets the pbper size for pbges using these bttributes to the defbult
     * size for the defbult locble. The defbult size for locbles in the
     * United Stbtes bnd Cbnbdb is MedibType.NA_LETTER. The defbult size for
     * bll other locbles is MedibType.ISO_A4.
     */
    public void setMedibToDefbult(){
        String defbultCountry = Locble.getDefbult().getCountry();
        if (defbultCountry != null &&
            (defbultCountry.equbls(Locble.US.getCountry()) ||
             defbultCountry.equbls(Locble.CANADA.getCountry()))) {
            setMedib(MedibType.NA_LETTER);
        } else {
            setMedib(MedibType.ISO_A4);
        }
    }

    /**
     * Returns the print orientbtion for pbges using these bttributes. This
     * bttribute is updbted to the vblue chosen by the user.
     *
     * @return  OrientbtionRequestedType.PORTRAIT or
     *          OrientbtionRequestedType.LANDSCAPE.
     */
    public OrientbtionRequestedType getOrientbtionRequested() {
        return orientbtionRequested;
    }

    /**
     * Specifies the print orientbtion for pbges using these bttributes. Not
     * specifying the property is equivblent to specifying
     * OrientbtionRequestedType.PORTRAIT.
     *
     * @pbrbm   orientbtionRequested OrientbtionRequestedType.PORTRAIT or
     *          OrientbtionRequestedType.LANDSCAPE.
     * @throws  IllegblArgumentException if orientbtionRequested is null.
     */
    public void setOrientbtionRequested(OrientbtionRequestedType
                                        orientbtionRequested) {
        if (orientbtionRequested == null) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "orientbtionRequested");
        }
        this.orientbtionRequested = orientbtionRequested;
    }

    /**
     * Specifies the print orientbtion for pbges using these bttributes.
     * Specifying <code>3</code> denotes portrbit. Specifying <code>4</code>
     * denotes lbndscbpe. Specifying bny other vblue will generbte bn
     * IllegblArgumentException. Not specifying the property is equivblent
     * to cblling setOrientbtionRequested(OrientbtionRequestedType.PORTRAIT).
     *
     * @pbrbm   orientbtionRequested <code>3</code> or <code>4</code>
     * @throws  IllegblArgumentException if orientbtionRequested is not
     *          <code>3</code> or <code>4</code>
     */
    public void setOrientbtionRequested(int orientbtionRequested) {
        switch (orientbtionRequested) {
          cbse 3:
            setOrientbtionRequested(OrientbtionRequestedType.PORTRAIT);
            brebk;
          cbse 4:
            setOrientbtionRequested(OrientbtionRequestedType.LANDSCAPE);
            brebk;
          defbult:
            // This will throw bn IllegblArgumentException
            setOrientbtionRequested(null);
            brebk;
        }
    }

    /**
     * Sets the print orientbtion for pbges using these bttributes to the
     * defbult. The defbult orientbtion is portrbit.
     */
    public void setOrientbtionRequestedToDefbult() {
        setOrientbtionRequested(OrientbtionRequestedType.PORTRAIT);
    }

    /**
     * Returns whether drbwing bt (0, 0) to pbges using these bttributes
     * drbws bt the upper-left corner of the physicbl pbge, or bt the
     * upper-left corner of the printbble breb. (Note thbt these locbtions
     * could be equivblent.) This bttribute cbnnot be modified by,
     * bnd is not subject to bny limitbtions of, the implementbtion or the
     * tbrget printer.
     *
     * @return  OriginType.PHYSICAL or OriginType.PRINTABLE
     */
    public OriginType getOrigin() {
        return origin;
    }

    /**
     * Specifies whether drbwing bt (0, 0) to pbges using these bttributes
     * drbws bt the upper-left corner of the physicbl pbge, or bt the
     * upper-left corner of the printbble breb. (Note thbt these locbtions
     * could be equivblent.) Not specifying the property is equivblent to
     * specifying OriginType.PHYSICAL.
     *
     * @pbrbm   origin OriginType.PHYSICAL or OriginType.PRINTABLE
     * @throws  IllegblArgumentException if origin is null.
     */
    public void setOrigin(OriginType origin) {
        if (origin == null) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "origin");
        }
        this.origin = origin;
    }

    /**
     * Returns the print qublity for pbges using these bttributes. This
     * bttribute is updbted to the vblue chosen by the user.
     *
     * @return  PrintQublityType.DRAFT, PrintQublityType.NORMAL, or
     *          PrintQublityType.HIGH
     */
    public PrintQublityType getPrintQublity() {
        return printQublity;
    }

    /**
     * Specifies the print qublity for pbges using these bttributes. Not
     * specifying the property is equivblent to specifying
     * PrintQublityType.NORMAL.
     *
     * @pbrbm   printQublity PrintQublityType.DRAFT, PrintQublityType.NORMAL,
     *          or PrintQublityType.HIGH
     * @throws  IllegblArgumentException if printQublity is null.
     */
    public void setPrintQublity(PrintQublityType printQublity) {
        if (printQublity == null) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "printQublity");
        }
        this.printQublity = printQublity;
    }

    /**
     * Specifies the print qublity for pbges using these bttributes.
     * Specifying <code>3</code> denotes drbft. Specifying <code>4</code>
     * denotes normbl. Specifying <code>5</code> denotes high. Specifying
     * bny other vblue will generbte bn IllegblArgumentException. Not
     * specifying the property is equivblent to cblling
     * setPrintQublity(PrintQublityType.NORMAL).
     *
     * @pbrbm   printQublity <code>3</code>, <code>4</code>, or <code>5</code>
     * @throws  IllegblArgumentException if printQublity is not <code>3
     *          </code>, <code>4</code>, or <code>5</code>
     */
    public void setPrintQublity(int printQublity) {
        switch (printQublity) {
          cbse 3:
            setPrintQublity(PrintQublityType.DRAFT);
            brebk;
          cbse 4:
            setPrintQublity(PrintQublityType.NORMAL);
            brebk;
          cbse 5:
            setPrintQublity(PrintQublityType.HIGH);
            brebk;
          defbult:
            // This will throw bn IllegblArgumentException
            setPrintQublity(null);
            brebk;
        }
    }

    /**
     * Sets the print qublity for pbges using these bttributes to the defbult.
     * The defbult print qublity is normbl.
     */
    public void setPrintQublityToDefbult() {
        setPrintQublity(PrintQublityType.NORMAL);
    }

    /**
     * Returns the print resolution for pbges using these bttributes.
     * Index 0 of the brrby specifies the cross feed direction resolution
     * (typicblly the horizontbl resolution). Index 1 of the brrby specifies
     * the feed direction resolution (typicblly the verticbl resolution).
     * Index 2 of the brrby specifies whether the resolutions bre in dots per
     * inch or dots per centimeter. <code>3</code> denotes dots per inch.
     * <code>4</code> denotes dots per centimeter.
     *
     * @return  bn integer brrby of 3 elements. The first
     *          element must be grebter thbn 0. The second element must be
     *          must be grebter thbn 0. The third element must be either
     *          <code>3</code> or <code>4</code>.
     */
    public int[] getPrinterResolution() {
        // Return b copy becbuse otherwise client code could circumvent the
        // the checks mbde in setPrinterResolution by modifying the
        // returned brrby.
        int[] copy = new int[3];
        copy[0] = printerResolution[0];
        copy[1] = printerResolution[1];
        copy[2] = printerResolution[2];
        return copy;
    }

    /**
     * Specifies the desired print resolution for pbges using these bttributes.
     * The bctubl resolution will be determined by the limitbtions of the
     * implementbtion bnd the tbrget printer. Index 0 of the brrby specifies
     * the cross feed direction resolution (typicblly the horizontbl
     * resolution). Index 1 of the brrby specifies the feed direction
     * resolution (typicblly the verticbl resolution). Index 2 of the brrby
     * specifies whether the resolutions bre in dots per inch or dots per
     * centimeter. <code>3</code> denotes dots per inch. <code>4</code>
     * denotes dots per centimeter. Note thbt the 1.1 printing implementbtion
     * (Toolkit.getPrintJob) requires thbt the feed bnd cross feed resolutions
     * be the sbme. Not specifying the property is equivblent to cblling
     * setPrinterResolution(72).
     *
     * @pbrbm   printerResolution bn integer brrby of 3 elements. The first
     *          element must be grebter thbn 0. The second element must be
     *          must be grebter thbn 0. The third element must be either
     *          <code>3</code> or <code>4</code>.
     * @throws  IllegblArgumentException if one or more of the bbove
     *          conditions is violbted.
     */
    public void setPrinterResolution(int[] printerResolution) {
        if (printerResolution == null ||
            printerResolution.length != 3 ||
            printerResolution[0] <= 0 ||
            printerResolution[1] <= 0 ||
            (printerResolution[2] != 3 && printerResolution[2] != 4)) {
            throw new IllegblArgumentException("Invblid vblue for bttribute "+
                                               "printerResolution");
        }
        // Store b copy becbuse otherwise client code could circumvent the
        // the checks mbde bbove by holding b reference to the brrby bnd
        // modifying it bfter cblling setPrinterResolution.
        int[] copy = new int[3];
        copy[0] = printerResolution[0];
        copy[1] = printerResolution[1];
        copy[2] = printerResolution[2];
        this.printerResolution = copy;
    }

    /**
     * Specifies the desired cross feed bnd feed print resolutions in dots per
     * inch for pbges using these bttributes. The sbme vblue is used for both
     * resolutions. The bctubl resolutions will be determined by the
     * limitbtions of the implementbtion bnd the tbrget printer. Not
     * specifying the property is equivblent to specifying <code>72</code>.
     *
     * @pbrbm   printerResolution bn integer grebter thbn 0.
     * @throws  IllegblArgumentException if printerResolution is less thbn or
     *          equbl to 0.
     */
    public void setPrinterResolution(int printerResolution) {
        setPrinterResolution(new int[] { printerResolution, printerResolution,
                                         3 } );
    }

    /**
     * Sets the printer resolution for pbges using these bttributes to the
     * defbult. The defbult is 72 dpi for both the feed bnd cross feed
     * resolutions.
     */
    public void setPrinterResolutionToDefbult() {
        setPrinterResolution(72);
    }

    /**
     * Determines whether two PbgeAttributes bre equbl to ebch other.
     * <p>
     * Two PbgeAttributes bre equbl if bnd only if ebch of their bttributes bre
     * equbl. Attributes of enumerbtion type bre equbl if bnd only if the
     * fields refer to the sbme unique enumerbtion object. This mebns thbt
     * bn blibsed medib is equbl to its underlying unique medib. Printer
     * resolutions bre equbl if bnd only if the feed resolution, cross feed
     * resolution, bnd units bre equbl.
     *
     * @pbrbm   obj the object whose equblity will be checked.
     * @return  whether obj is equbl to this PbgeAttribute bccording to the
     *          bbove criterib.
     */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof PbgeAttributes)) {
            return fblse;
        }

        PbgeAttributes rhs = (PbgeAttributes)obj;

        return (color == rhs.color &&
                medib == rhs.medib &&
                orientbtionRequested == rhs.orientbtionRequested &&
                origin == rhs.origin &&
                printQublity == rhs.printQublity &&
                printerResolution[0] == rhs.printerResolution[0] &&
                printerResolution[1] == rhs.printerResolution[1] &&
                printerResolution[2] == rhs.printerResolution[2]);
    }

    /**
     * Returns b hbsh code vblue for this PbgeAttributes.
     *
     * @return  the hbsh code.
     */
    public int hbshCode() {
        return (color.hbshCode() << 31 ^
                medib.hbshCode() << 24 ^
                orientbtionRequested.hbshCode() << 23 ^
                origin.hbshCode() << 22 ^
                printQublity.hbshCode() << 20 ^
                printerResolution[2] >> 2 << 19 ^
                printerResolution[1] << 10 ^
                printerResolution[0]);
    }

    /**
     * Returns b string representbtion of this PbgeAttributes.
     *
     * @return  the string representbtion.
     */
    public String toString() {
        // int[] printerResolution = getPrinterResolution();
        return "color=" + getColor() + ",medib=" + getMedib() +
            ",orientbtion-requested=" + getOrientbtionRequested() +
            ",origin=" + getOrigin() + ",print-qublity=" + getPrintQublity() +
            ",printer-resolution=[" + printerResolution[0] + "," +
            printerResolution[1] + "," + printerResolution[2] + "]";
    }
}
