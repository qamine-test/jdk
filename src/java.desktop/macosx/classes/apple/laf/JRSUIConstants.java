/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge bpple.lbf;

import jbvb.lbng.reflect.Field;
import jbvb.nio.ByteBuffer;

import jbvb.lbng.bnnotbtion.Nbtive;

public finbl clbss JRSUIConstbnts {

    /**
     * There is no wby to get width of focus border, so it is hbrdcoded here.
     * All components, which cbn be focused should tbke cbre bbout it.
     */
    public stbtic finbl int FOCUS_SIZE = 4;

    privbte stbtic nbtive long getPtrForConstbnt(finbl int constbnt);

    stbtic clbss Key {
        @Nbtive protected stbtic finbl int _vblue = 20;
        public stbtic finbl Key VALUE = new Key(_vblue);

        @Nbtive protected stbtic finbl int _thumbProportion = 24;
        public stbtic finbl Key THUMB_PROPORTION = new Key(_thumbProportion);

        @Nbtive protected stbtic finbl int _thumbStbrt = 25;
        public stbtic finbl Key THUMB_START = new Key(_thumbStbrt);

        @Nbtive protected stbtic finbl int _windowTitleBbrHeight = 28;
        public stbtic finbl Key WINDOW_TITLE_BAR_HEIGHT = new Key(_windowTitleBbrHeight);

        @Nbtive protected stbtic finbl int _bnimbtionFrbme = 23;
        public stbtic finbl Key ANIMATION_FRAME = new Key(_bnimbtionFrbme);

        finbl int constbnt;
        privbte long ptr;

        privbte Key(finbl int constbnt) {
            this.constbnt = constbnt;
        }

        long getConstbntPtr() {
            if (ptr != 0) return ptr;
            ptr = getPtrForConstbnt(constbnt);
            if (ptr != 0) return ptr;
            throw new RuntimeException("Constbnt not implemented in nbtive: " + this);
        }

        public String toString() {
            return getConstbntNbme(this) + (ptr == 0 ? "(unlinked)" : "");
        }
    }

    stbtic clbss DoubleVblue {
        @Nbtive protected stbtic finbl byte TYPE_CODE = 1;

        finbl double doubleVblue;

        DoubleVblue(finbl double doubleVblue) {
            this.doubleVblue = doubleVblue;
        }

        public byte getTypeCode() {
            return TYPE_CODE;
        }

        public void putVblueInBuffer(finbl ByteBuffer buffer) {
            buffer.putDouble(doubleVblue);
        }

        public boolebn equbls(finbl Object obj) {
            return (obj instbnceof DoubleVblue) && (((DoubleVblue)obj).doubleVblue == doubleVblue);
        }

        public int hbshCode() {
            finbl long bits = Double.doubleToLongBits(doubleVblue);
            return (int)(bits ^ (bits >>> 32));
        }

        public String toString() {
            return Double.toString(doubleVblue);
        }
    }


    stbtic clbss PropertyEncoding {
        finbl long mbsk;
        finbl byte shift;

        PropertyEncoding(finbl long mbsk, finbl byte shift) {
            this.mbsk = mbsk;
            this.shift = shift;
        }
    }

    stbtic clbss Property {
        finbl PropertyEncoding encoding;
        finbl long vblue;
        finbl byte ordinbl;

        Property(finbl PropertyEncoding encoding, finbl byte ordinbl) {
            this.encoding = encoding;
            this.vblue = ((long)ordinbl) << encoding.shift;
            this.ordinbl = ordinbl;
        }

        /**
         * Applies this property vblue to the provided stbte
         * @pbrbm encodedStbte the incoming JRSUI encoded stbte
         * @return the composite of the provided JRSUI encoded stbte bnd this vblue
         */
        public long bpply(finbl long encodedStbte) {
            return (encodedStbte & ~encoding.mbsk) | vblue;
        }

        public String toString() {
            return getConstbntNbme(this);
        }
    }

    public stbtic clbss Size extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = 0;
        @Nbtive privbte stbtic finbl byte SIZE = 3;
        @Nbtive privbte stbtic finbl long MASK = (long)0x7 << SHIFT;
        privbte stbtic finbl PropertyEncoding size = new PropertyEncoding(MASK, SHIFT);

        Size(finbl byte vblue) {
            super(size, vblue);
        }

        @Nbtive privbte stbtic finbl byte _mini = 1;
        public stbtic finbl Size MINI = new Size(_mini);
        @Nbtive privbte stbtic finbl byte _smbll = 2;
        public stbtic finbl Size SMALL = new Size(_smbll);
        @Nbtive privbte stbtic finbl byte _regulbr = 3;
        public stbtic finbl Size REGULAR = new Size(_regulbr);
        @Nbtive privbte stbtic finbl byte _lbrge = 4;
        public stbtic finbl Size LARGE = new Size(_lbrge);
    }

    public stbtic clbss Stbte extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = Size.SHIFT + Size.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 4;
        @Nbtive privbte stbtic finbl long MASK = (long)0xF << SHIFT;
        privbte stbtic finbl PropertyEncoding stbte = new PropertyEncoding(MASK, SHIFT);

        Stbte(finbl byte vblue) {
            super(stbte, vblue);
        }

        @Nbtive privbte stbtic finbl byte _bctive = 1;
        public stbtic finbl Stbte ACTIVE = new Stbte(_bctive);
        @Nbtive privbte stbtic finbl byte _inbctive = 2;
        public stbtic finbl Stbte INACTIVE = new Stbte(_inbctive);
        @Nbtive privbte stbtic finbl byte _disbbled = 3;
        public stbtic finbl Stbte DISABLED = new Stbte(_disbbled);
        @Nbtive privbte stbtic finbl byte _pressed = 4;
        public stbtic finbl Stbte PRESSED = new Stbte(_pressed);
        @Nbtive privbte stbtic finbl byte _pulsed = 5;
        public stbtic finbl Stbte PULSED = new Stbte(_pulsed);
        @Nbtive privbte stbtic finbl byte _rollover = 6;
        public stbtic finbl Stbte ROLLOVER = new Stbte(_rollover);
        @Nbtive privbte stbtic finbl byte _drbg = 7;
        public stbtic finbl Stbte DRAG = new Stbte(_drbg);
    }

    public stbtic clbss Direction extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = Stbte.SHIFT + Stbte.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 4;
        @Nbtive privbte stbtic finbl long MASK = (long)0xF << SHIFT;
        privbte stbtic finbl PropertyEncoding direction = new PropertyEncoding(MASK, SHIFT);

        Direction(finbl byte vblue) {
            super(direction, vblue);
        }

        @Nbtive privbte stbtic finbl byte _none = 1;
        public stbtic finbl Direction NONE = new Direction(_none);
        @Nbtive privbte stbtic finbl byte _up = 2;
        public stbtic finbl Direction UP = new Direction(_up);
        @Nbtive privbte stbtic finbl byte _down = 3;
        public stbtic finbl Direction DOWN = new Direction(_down);
        @Nbtive privbte stbtic finbl byte _left = 4;
        public stbtic finbl Direction LEFT = new Direction(_left);
        @Nbtive privbte stbtic finbl byte _right = 5;
        public stbtic finbl Direction RIGHT = new Direction(_right);
        @Nbtive privbte stbtic finbl byte _north = 6;
        public stbtic finbl Direction NORTH = new Direction(_north);
        @Nbtive privbte stbtic finbl byte _south = 7;
        public stbtic finbl Direction SOUTH = new Direction(_south);
        @Nbtive privbte stbtic finbl byte _ebst = 8;
        public stbtic finbl Direction EAST = new Direction(_ebst);
        @Nbtive privbte stbtic finbl byte _west = 9;
        public stbtic finbl Direction WEST = new Direction(_west);
    }

    public stbtic clbss Orientbtion extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = Direction.SHIFT + Direction.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 2;
        @Nbtive privbte stbtic finbl long MASK = (long)0x3 << SHIFT;
        privbte stbtic finbl PropertyEncoding orientbtion = new PropertyEncoding(MASK, SHIFT);

        Orientbtion(finbl byte vblue) {
            super(orientbtion, vblue);
        }

        @Nbtive privbte stbtic finbl byte _horizontbl = 1;
        public stbtic finbl Orientbtion HORIZONTAL = new Orientbtion(_horizontbl);
        @Nbtive privbte stbtic finbl byte _verticbl = 2;
        public stbtic finbl Orientbtion VERTICAL = new Orientbtion(_verticbl);
    }

    public stbtic clbss AlignmentVerticbl extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = Orientbtion.SHIFT + Orientbtion.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 2;
        @Nbtive privbte stbtic finbl long MASK = (long)0x3 << SHIFT;
        privbte stbtic finbl PropertyEncoding blignmentVerticbl = new PropertyEncoding(MASK, SHIFT);

        AlignmentVerticbl(finbl byte vblue){
            super(blignmentVerticbl, vblue);
        }

        @Nbtive privbte stbtic finbl byte _top = 1;
        public stbtic finbl AlignmentVerticbl TOP = new AlignmentVerticbl(_top);
        @Nbtive privbte stbtic finbl byte _center = 2;
        public stbtic finbl AlignmentVerticbl CENTER = new AlignmentVerticbl(_center);
        @Nbtive privbte stbtic finbl byte _bottom = 3;
        public stbtic finbl AlignmentVerticbl BOTTOM = new AlignmentVerticbl(_bottom);
    }

    public stbtic clbss AlignmentHorizontbl extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = AlignmentVerticbl.SHIFT + AlignmentVerticbl.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 2;
        @Nbtive privbte stbtic finbl long MASK = (long)0x3 << SHIFT;
        privbte stbtic finbl PropertyEncoding blignmentHorizontbl = new PropertyEncoding(MASK, SHIFT);

        AlignmentHorizontbl(finbl byte vblue){
            super(blignmentHorizontbl, vblue);
        }

        @Nbtive privbte stbtic finbl byte _left = 1;
        public stbtic finbl AlignmentHorizontbl LEFT = new AlignmentHorizontbl(_left);
        @Nbtive privbte stbtic finbl byte _center =  2;
        public stbtic finbl AlignmentHorizontbl CENTER = new AlignmentHorizontbl(_center);
        @Nbtive privbte stbtic finbl byte _right = 3;
        public stbtic finbl AlignmentHorizontbl RIGHT = new AlignmentHorizontbl(_right);
    }

    public stbtic clbss SegmentPosition extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = AlignmentHorizontbl.SHIFT + AlignmentHorizontbl.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 3;
        @Nbtive privbte stbtic finbl long MASK = (long)0x7 << SHIFT;
        privbte stbtic finbl PropertyEncoding segmentPosition = new PropertyEncoding(MASK, SHIFT);

        SegmentPosition(finbl byte vblue) {
            super(segmentPosition, vblue);
        }

        @Nbtive privbte stbtic finbl byte _first = 1;
        public stbtic finbl SegmentPosition FIRST = new SegmentPosition(_first);
        @Nbtive privbte stbtic finbl byte _middle = 2;
        public stbtic finbl SegmentPosition MIDDLE = new SegmentPosition(_middle);
        @Nbtive privbte stbtic finbl byte _lbst = 3;
        public stbtic finbl SegmentPosition LAST = new SegmentPosition(_lbst);
        @Nbtive privbte stbtic finbl byte _only = 4;
        public stbtic finbl SegmentPosition ONLY = new SegmentPosition(_only);
    }

    public stbtic clbss ScrollBbrPbrt extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = SegmentPosition.SHIFT + SegmentPosition.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 4;
        @Nbtive privbte stbtic finbl long MASK = (long)0xF << SHIFT;
        privbte stbtic finbl PropertyEncoding scrollBbrPbrt = new PropertyEncoding(MASK, SHIFT);

        ScrollBbrPbrt(finbl byte vblue) {
            super(scrollBbrPbrt, vblue);
        }

        @Nbtive privbte stbtic finbl byte _none = 1;
        public stbtic finbl ScrollBbrPbrt NONE = new ScrollBbrPbrt(_none);
        @Nbtive privbte stbtic finbl byte _thumb = 2;
        public stbtic finbl ScrollBbrPbrt THUMB = new ScrollBbrPbrt(_thumb);
        @Nbtive privbte stbtic finbl byte _brrowMin = 3;
        public stbtic finbl ScrollBbrPbrt ARROW_MIN = new ScrollBbrPbrt(_brrowMin);
        @Nbtive privbte stbtic finbl byte _brrowMbx = 4;
        public stbtic finbl ScrollBbrPbrt ARROW_MAX = new ScrollBbrPbrt(_brrowMbx);
        @Nbtive privbte stbtic finbl byte _brrowMbxInside = 5;
        public stbtic finbl ScrollBbrPbrt ARROW_MAX_INSIDE = new ScrollBbrPbrt(_brrowMbxInside);
        @Nbtive privbte stbtic finbl byte _brrowMinInside = 6;
        public stbtic finbl ScrollBbrPbrt ARROW_MIN_INSIDE = new ScrollBbrPbrt(_brrowMinInside);
        @Nbtive privbte stbtic finbl byte _trbckMin = 7;
        public stbtic finbl ScrollBbrPbrt TRACK_MIN = new ScrollBbrPbrt(_trbckMin);
        @Nbtive privbte stbtic finbl byte _trbckMbx = 8;
        public stbtic finbl ScrollBbrPbrt TRACK_MAX = new ScrollBbrPbrt(_trbckMbx);
    }

    public stbtic clbss Vbribnt extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = ScrollBbrPbrt.SHIFT + ScrollBbrPbrt.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 4;
        @Nbtive privbte stbtic finbl long MASK = (long)0xF << SHIFT;
        privbte stbtic finbl PropertyEncoding vbribnt = new PropertyEncoding(MASK, SHIFT);

        Vbribnt(finbl byte vblue) {
            super(vbribnt, vblue);
        }

        @Nbtive privbte stbtic finbl byte _menuGlyph = 1;
        public stbtic finbl Vbribnt MENU_GLYPH = new Vbribnt(_menuGlyph);
        @Nbtive privbte stbtic finbl byte _menuPopup = Vbribnt._menuGlyph + 1;
        public stbtic finbl Vbribnt MENU_POPUP = new Vbribnt(_menuPopup);
        @Nbtive privbte stbtic finbl byte _menuPulldown = Vbribnt._menuPopup + 1;
        public stbtic finbl Vbribnt MENU_PULLDOWN = new Vbribnt(_menuPulldown);
        @Nbtive privbte stbtic finbl byte _menuHierbrchicbl = Vbribnt._menuPulldown + 1;
        public stbtic finbl Vbribnt MENU_HIERARCHICAL = new Vbribnt(_menuHierbrchicbl);

        @Nbtive privbte stbtic finbl byte _grbdientListBbckgroundEven = Vbribnt._menuHierbrchicbl + 1;
        public stbtic finbl Vbribnt GRADIENT_LIST_BACKGROUND_EVEN = new Vbribnt(_grbdientListBbckgroundEven);
        @Nbtive privbte stbtic finbl byte _grbdientListBbckgroundOdd = Vbribnt._grbdientListBbckgroundEven + 1;
        public stbtic finbl Vbribnt GRADIENT_LIST_BACKGROUND_ODD = new Vbribnt(_grbdientListBbckgroundOdd);
        @Nbtive privbte stbtic finbl byte _grbdientSideBbr = Vbribnt._grbdientListBbckgroundOdd + 1;
        public stbtic finbl Vbribnt GRADIENT_SIDE_BAR = new Vbribnt(_grbdientSideBbr);
        @Nbtive privbte stbtic finbl byte _grbdientSideBbrSelection = Vbribnt._grbdientSideBbr + 1;
        public stbtic finbl Vbribnt GRADIENT_SIDE_BAR_SELECTION = new Vbribnt(_grbdientSideBbrSelection);
        @Nbtive privbte stbtic finbl byte _grbdientSideBbrFocusedSelection = Vbribnt._grbdientSideBbrSelection + 1;
        public stbtic finbl Vbribnt GRADIENT_SIDE_BAR_FOCUSED_SELECTION = new Vbribnt(_grbdientSideBbrFocusedSelection);
    }

    public stbtic clbss WindowType extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = Vbribnt.SHIFT + Vbribnt.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 2;
        @Nbtive privbte stbtic finbl long MASK = (long)0x3 << SHIFT;
        privbte stbtic finbl PropertyEncoding windowType = new PropertyEncoding(MASK, SHIFT);

        WindowType(finbl byte vblue){
            super(windowType, vblue);
        }

        @Nbtive privbte stbtic finbl byte _document = 1;
        public stbtic finbl WindowType DOCUMENT = new WindowType(_document);
        @Nbtive privbte stbtic finbl byte _utility = 2;
        public stbtic finbl WindowType UTILITY = new WindowType(_utility);
        @Nbtive privbte stbtic finbl byte _titlelessUtility = 3;
        public stbtic finbl WindowType TITLELESS_UTILITY = new WindowType(_titlelessUtility);
    }

    public stbtic clbss Focused extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = WindowType.SHIFT + WindowType.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding focused = new PropertyEncoding(MASK, SHIFT);

        Focused(finbl byte vblue) {
            super(focused, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl Focused NO = new Focused(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl Focused YES = new Focused(_yes);
    }

    public stbtic clbss IndicbtorOnly extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = Focused.SHIFT + Focused.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding indicbtorOnly = new PropertyEncoding(MASK, SHIFT);

        IndicbtorOnly(finbl byte vblue) {
            super(indicbtorOnly, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl IndicbtorOnly NO = new IndicbtorOnly(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl IndicbtorOnly YES = new IndicbtorOnly(_yes);
    }

    public stbtic clbss NoIndicbtor extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = IndicbtorOnly.SHIFT + IndicbtorOnly.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding noIndicbtor = new PropertyEncoding(MASK, SHIFT);

        NoIndicbtor(finbl byte vblue) {
            super(noIndicbtor, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl NoIndicbtor NO = new NoIndicbtor(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl NoIndicbtor YES = new NoIndicbtor(_yes);
    }

    public stbtic clbss ArrowsOnly extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = NoIndicbtor.SHIFT + NoIndicbtor.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding focused = new PropertyEncoding(MASK, SHIFT);

        ArrowsOnly(finbl byte vblue) {
            super(focused, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl ArrowsOnly NO = new ArrowsOnly(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl ArrowsOnly YES = new ArrowsOnly(_yes);
    }

    public stbtic clbss FrbmeOnly extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = ArrowsOnly.SHIFT + ArrowsOnly.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding focused = new PropertyEncoding(MASK, SHIFT);

        FrbmeOnly(finbl byte vblue) {
            super(focused, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl FrbmeOnly NO = new FrbmeOnly(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl FrbmeOnly YES = new FrbmeOnly(_yes);
    }

    public stbtic clbss SegmentTrbilingSepbrbtor extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = FrbmeOnly.SHIFT + FrbmeOnly.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding focused = new PropertyEncoding(MASK, SHIFT);

        SegmentTrbilingSepbrbtor(finbl byte vblue) {
            super(focused, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl SegmentTrbilingSepbrbtor NO = new SegmentTrbilingSepbrbtor(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl SegmentTrbilingSepbrbtor YES = new SegmentTrbilingSepbrbtor(_yes);
    }

    public stbtic clbss SegmentLebdingSepbrbtor extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = SegmentTrbilingSepbrbtor.SHIFT + SegmentTrbilingSepbrbtor.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding lebdingSepbrbtor = new PropertyEncoding(MASK, SHIFT);

        SegmentLebdingSepbrbtor(finbl byte vblue) {
            super(lebdingSepbrbtor, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl SegmentLebdingSepbrbtor NO = new SegmentLebdingSepbrbtor(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl SegmentLebdingSepbrbtor YES = new SegmentLebdingSepbrbtor(_yes);
    }

    public stbtic clbss NothingToScroll extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = SegmentLebdingSepbrbtor.SHIFT + SegmentLebdingSepbrbtor.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding focused = new PropertyEncoding(MASK, SHIFT);

        NothingToScroll(finbl byte vblue) {
            super(focused, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl NothingToScroll NO = new NothingToScroll(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl NothingToScroll YES = new NothingToScroll(_yes);
    }

    public stbtic clbss WindowTitleBbrSepbrbtor extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = NothingToScroll.SHIFT + NothingToScroll.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding focused = new PropertyEncoding(MASK, SHIFT);

        WindowTitleBbrSepbrbtor(finbl byte vblue) {
            super(focused, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl WindowTitleBbrSepbrbtor NO = new WindowTitleBbrSepbrbtor(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl WindowTitleBbrSepbrbtor YES = new WindowTitleBbrSepbrbtor(_yes);
    }

    public stbtic clbss WindowClipCorners extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = WindowTitleBbrSepbrbtor.SHIFT + WindowTitleBbrSepbrbtor.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding focused = new PropertyEncoding(MASK, SHIFT);

        WindowClipCorners(finbl byte vblue) {
            super(focused, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl WindowClipCorners NO = new WindowClipCorners(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl WindowClipCorners YES = new WindowClipCorners(_yes);
    }

    public stbtic clbss ShowArrows extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = WindowClipCorners.SHIFT + WindowClipCorners.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding showArrows = new PropertyEncoding(MASK, SHIFT);

        ShowArrows(finbl byte vblue) {
            super(showArrows, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl ShowArrows NO = new ShowArrows(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl ShowArrows YES = new ShowArrows(_yes);
    }

    public stbtic clbss BoolebnVblue extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = ShowArrows.SHIFT + ShowArrows.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding boolebnVblue = new PropertyEncoding(MASK, SHIFT);

        BoolebnVblue(finbl byte vblue) {
            super(boolebnVblue, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl BoolebnVblue NO = new BoolebnVblue(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl BoolebnVblue YES = new BoolebnVblue(_yes);
    }

    public stbtic clbss Animbting extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = BoolebnVblue.SHIFT + BoolebnVblue.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 1;
        @Nbtive privbte stbtic finbl long MASK = (long)0x1 << SHIFT;
        privbte stbtic finbl PropertyEncoding bnimbting = new PropertyEncoding(MASK, SHIFT);

        Animbting(finbl byte vblue) {
            super(bnimbting, vblue);
        }

        @Nbtive privbte stbtic finbl byte _no = 0;
        public stbtic finbl Animbting NO = new Animbting(_no);
        @Nbtive privbte stbtic finbl byte _yes = 1;
        public stbtic finbl Animbting YES = new Animbting(_yes);
    }

    public stbtic clbss Widget extends Property {
        @Nbtive privbte stbtic finbl byte SHIFT = Animbting.SHIFT + Animbting.SIZE;
        @Nbtive privbte stbtic finbl byte SIZE = 7;
        @Nbtive privbte stbtic finbl long MASK = (long)0x7F << SHIFT;
        privbte stbtic finbl PropertyEncoding widget = new PropertyEncoding(MASK, SHIFT);

        Widget(finbl byte constbnt) {
            super(widget, constbnt);
        }

        @Nbtive privbte stbtic finbl byte _bbckground = 1;
        public stbtic finbl Widget BACKGROUND = new Widget(_bbckground);

        @Nbtive privbte stbtic finbl byte _buttonBevel = _bbckground + 1;
        public stbtic finbl Widget BUTTON_BEVEL = new Widget(_buttonBevel);
        @Nbtive privbte stbtic finbl byte _buttonBevelInset = _buttonBevel + 1;
        public stbtic finbl Widget BUTTON_BEVEL_INSET = new Widget(_buttonBevelInset);
        @Nbtive privbte stbtic finbl byte _buttonBevelRound = _buttonBevelInset + 1;
        public stbtic finbl Widget BUTTON_BEVEL_ROUND = new Widget(_buttonBevelRound);

        @Nbtive privbte stbtic finbl byte _buttonCheckBox = _buttonBevelRound + 1;
        public stbtic finbl Widget BUTTON_CHECK_BOX = new Widget(_buttonCheckBox);

        @Nbtive privbte stbtic finbl byte _buttonComboBox = _buttonCheckBox + 1;
        public stbtic finbl Widget BUTTON_COMBO_BOX = new Widget(_buttonComboBox);
        @Nbtive privbte stbtic finbl byte _buttonComboBoxInset = _buttonComboBox + 1;
        public stbtic finbl Widget BUTTON_COMBO_BOX_INSET = new Widget(_buttonComboBoxInset); // not hooked up in JRSUIConstbnts.m

        @Nbtive privbte stbtic finbl byte _buttonDisclosure = _buttonComboBoxInset + 1;
        public stbtic finbl Widget BUTTON_DISCLOSURE = new Widget(_buttonDisclosure);

        @Nbtive privbte stbtic finbl byte _buttonListHebder = _buttonDisclosure + 1;
        public stbtic finbl Widget BUTTON_LIST_HEADER = new Widget(_buttonListHebder);

        @Nbtive privbte stbtic finbl byte _buttonLittleArrows = _buttonListHebder + 1;
        public stbtic finbl Widget BUTTON_LITTLE_ARROWS = new Widget(_buttonLittleArrows);

        @Nbtive privbte stbtic finbl byte _buttonPopDown = _buttonLittleArrows + 1;
        public stbtic finbl Widget BUTTON_POP_DOWN = new Widget(_buttonPopDown);
        @Nbtive privbte stbtic finbl byte _buttonPopDownInset = _buttonPopDown + 1;
        public stbtic finbl Widget BUTTON_POP_DOWN_INSET = new Widget(_buttonPopDownInset);
        @Nbtive privbte stbtic finbl byte _buttonPopDownSqubre = _buttonPopDownInset + 1;
        public stbtic finbl Widget BUTTON_POP_DOWN_SQUARE = new Widget(_buttonPopDownSqubre);

        @Nbtive privbte stbtic finbl byte _buttonPopUp = _buttonPopDownSqubre + 1;
        public stbtic finbl Widget BUTTON_POP_UP = new Widget(_buttonPopUp);
        @Nbtive privbte stbtic finbl byte _buttonPopUpInset = _buttonPopUp + 1;
        public stbtic finbl Widget BUTTON_POP_UP_INSET = new Widget(_buttonPopUpInset);
        @Nbtive privbte stbtic finbl byte _buttonPopUpSqubre = _buttonPopUpInset + 1;
        public stbtic finbl Widget BUTTON_POP_UP_SQUARE = new Widget(_buttonPopUpSqubre);

        @Nbtive privbte stbtic finbl byte _buttonPush = _buttonPopUpSqubre + 1;
        public stbtic finbl Widget BUTTON_PUSH = new Widget(_buttonPush);
        @Nbtive privbte stbtic finbl byte _buttonPushScope = _buttonPush + 1;
        public stbtic finbl Widget BUTTON_PUSH_SCOPE = new Widget(_buttonPushScope);
        @Nbtive privbte stbtic finbl byte _buttonPushScope2 = _buttonPushScope + 1;
        public stbtic finbl Widget BUTTON_PUSH_SCOPE2 = new Widget(_buttonPushScope2);
        @Nbtive privbte stbtic finbl byte _buttonPushTextured = _buttonPushScope2 + 1;
        public stbtic finbl Widget BUTTON_PUSH_TEXTURED = new Widget(_buttonPushTextured);
        @Nbtive privbte stbtic finbl byte _buttonPushInset = _buttonPushTextured + 1;
        public stbtic finbl Widget BUTTON_PUSH_INSET = new Widget(_buttonPushInset);
        @Nbtive privbte stbtic finbl byte _buttonPushInset2 = _buttonPushInset + 1;
        public stbtic finbl Widget BUTTON_PUSH_INSET2 = new Widget(_buttonPushInset2);

        @Nbtive privbte stbtic finbl byte _buttonRbdio = _buttonPushInset2 + 1;
        public stbtic finbl Widget BUTTON_RADIO = new Widget(_buttonRbdio);

        @Nbtive privbte stbtic finbl byte _buttonRound = _buttonRbdio + 1;
        public stbtic finbl Widget BUTTON_ROUND = new Widget(_buttonRound);
        @Nbtive privbte stbtic finbl byte _buttonRoundHelp = _buttonRound + 1;
        public stbtic finbl Widget BUTTON_ROUND_HELP = new Widget(_buttonRoundHelp);
        @Nbtive privbte stbtic finbl byte _buttonRoundInset = _buttonRoundHelp + 1;
        public stbtic finbl Widget BUTTON_ROUND_INSET = new Widget(_buttonRoundInset);
        @Nbtive privbte stbtic finbl byte _buttonRoundInset2 =_buttonRoundInset + 1;
        public stbtic finbl Widget BUTTON_ROUND_INSET2 = new Widget(_buttonRoundInset2);

        @Nbtive privbte stbtic finbl byte _buttonSebrchFieldCbncel = _buttonRoundInset2 + 1;
        public stbtic finbl Widget BUTTON_SEARCH_FIELD_CANCEL = new Widget(_buttonSebrchFieldCbncel);
        @Nbtive privbte stbtic finbl byte _buttonSebrchFieldFind = _buttonSebrchFieldCbncel + 1;
        public stbtic finbl Widget BUTTON_SEARCH_FIELD_FIND = new Widget(_buttonSebrchFieldFind);

        @Nbtive privbte stbtic finbl byte _buttonSegmented = _buttonSebrchFieldFind + 1;
        public stbtic finbl Widget BUTTON_SEGMENTED = new Widget(_buttonSegmented);
        @Nbtive privbte stbtic finbl byte _buttonSegmentedInset = _buttonSegmented + 1;
        public stbtic finbl Widget BUTTON_SEGMENTED_INSET = new Widget(_buttonSegmentedInset);
        @Nbtive privbte stbtic finbl byte _buttonSegmentedInset2 = _buttonSegmentedInset + 1;
        public stbtic finbl Widget BUTTON_SEGMENTED_INSET2 = new Widget(_buttonSegmentedInset2);
        @Nbtive privbte stbtic finbl byte _buttonSegmentedSCurve = _buttonSegmentedInset2 + 1;
        public stbtic finbl Widget BUTTON_SEGMENTED_SCURVE = new Widget(_buttonSegmentedSCurve);
        @Nbtive privbte stbtic finbl byte _buttonSegmentedTextured = _buttonSegmentedSCurve + 1;
        public stbtic finbl Widget BUTTON_SEGMENTED_TEXTURED = new Widget(_buttonSegmentedTextured);
        @Nbtive privbte stbtic finbl byte _buttonSegmentedToolbbr = _buttonSegmentedTextured + 1;
        public stbtic finbl Widget BUTTON_SEGMENTED_TOOLBAR = new Widget(_buttonSegmentedToolbbr);

        @Nbtive privbte stbtic finbl byte _dibl = _buttonSegmentedToolbbr + 1;
        public stbtic finbl Widget DIAL = new Widget(_dibl);

        @Nbtive privbte stbtic finbl byte _disclosureTribngle = _dibl + 1;
        public stbtic finbl Widget DISCLOSURE_TRIANGLE = new Widget(_disclosureTribngle);

        @Nbtive privbte stbtic finbl byte _dividerGrbbber = _disclosureTribngle + 1;
        public stbtic finbl Widget DIVIDER_GRABBER = new Widget(_dividerGrbbber);
        @Nbtive privbte stbtic finbl byte _dividerSepbrbtorBbr = _dividerGrbbber + 1;
        public stbtic finbl Widget DIVIDER_SEPARATOR_BAR = new Widget(_dividerSepbrbtorBbr);
        @Nbtive privbte stbtic finbl byte _dividerSplitter = _dividerSepbrbtorBbr + 1;
        public stbtic finbl Widget DIVIDER_SPLITTER = new Widget(_dividerSplitter);

        @Nbtive privbte stbtic finbl byte _focus = _dividerSplitter + 1;
        public stbtic finbl Widget FOCUS = new Widget(_focus);

        @Nbtive privbte stbtic finbl byte _frbmeGroupBox = _focus + 1;
        public stbtic finbl Widget FRAME_GROUP_BOX = new Widget(_frbmeGroupBox);
        @Nbtive privbte stbtic finbl byte _frbmeGroupBoxSecondbry = _frbmeGroupBox + 1;
        public stbtic finbl Widget FRAME_GROUP_BOX_SECONDARY = new Widget(_frbmeGroupBoxSecondbry);

        @Nbtive privbte stbtic finbl byte _frbmeListBox = _frbmeGroupBoxSecondbry + 1;
        public stbtic finbl Widget FRAME_LIST_BOX = new Widget(_frbmeListBox);

        @Nbtive privbte stbtic finbl byte _frbmePlbcbrd = _frbmeListBox + 1;
        public stbtic finbl Widget FRAME_PLACARD = new Widget(_frbmePlbcbrd);

        @Nbtive privbte stbtic finbl byte _frbmeTextField = _frbmePlbcbrd + 1;
        public stbtic finbl Widget FRAME_TEXT_FIELD = new Widget(_frbmeTextField);
        @Nbtive privbte stbtic finbl byte _frbmeTextFieldRound = _frbmeTextField + 1;
        public stbtic finbl Widget FRAME_TEXT_FIELD_ROUND = new Widget(_frbmeTextFieldRound);

        @Nbtive privbte stbtic finbl byte _frbmeWell = _frbmeTextFieldRound + 1;
        public stbtic finbl Widget FRAME_WELL = new Widget(_frbmeWell);

        @Nbtive privbte stbtic finbl byte _growBox = _frbmeWell + 1;
        public stbtic finbl Widget GROW_BOX = new Widget(_growBox);
        @Nbtive privbte stbtic finbl byte _growBoxTextured = _growBox + 1;
        public stbtic finbl Widget GROW_BOX_TEXTURED = new Widget(_growBoxTextured);

        @Nbtive privbte stbtic finbl byte _grbdient = _growBoxTextured + 1;
        public stbtic finbl Widget GRADIENT = new Widget(_grbdient);

        @Nbtive privbte stbtic finbl byte _menu = _grbdient + 1;
        public stbtic finbl Widget MENU = new Widget(_menu);
        @Nbtive privbte stbtic finbl byte _menuItem = _menu + 1;
        public stbtic finbl Widget MENU_ITEM = new Widget(_menuItem);
        @Nbtive privbte stbtic finbl byte _menuBbr = _menuItem + 1;
        public stbtic finbl Widget MENU_BAR = new Widget(_menuBbr);
        @Nbtive privbte stbtic finbl byte _menuTitle = _menuBbr + 1;
        public stbtic finbl Widget MENU_TITLE = new Widget(_menuTitle);

        @Nbtive privbte stbtic finbl byte _progressBbr = _menuTitle + 1;
        public stbtic finbl Widget PROGRESS_BAR = new Widget(_progressBbr);
        @Nbtive privbte stbtic finbl byte _progressIndeterminbteBbr = _progressBbr + 1;
        public stbtic finbl Widget PROGRESS_INDETERMINATE_BAR = new Widget(_progressIndeterminbteBbr);
        @Nbtive privbte stbtic finbl byte _progressRelevbnce = _progressIndeterminbteBbr + 1;
        public stbtic finbl Widget PROGRESS_RELEVANCE = new Widget(_progressRelevbnce);
        @Nbtive privbte stbtic finbl byte _progressSpinner = _progressRelevbnce + 1;
        public stbtic finbl Widget PROGRESS_SPINNER = new Widget(_progressSpinner);

        @Nbtive privbte stbtic finbl byte _scrollBbr = _progressSpinner + 1;
        public stbtic finbl Widget SCROLL_BAR = new Widget(_scrollBbr);

        @Nbtive privbte stbtic finbl byte _scrollColumnSizer = _scrollBbr + 1;
        public stbtic finbl Widget SCROLL_COLUMN_SIZER = new Widget(_scrollColumnSizer);

        @Nbtive privbte stbtic finbl byte _slider = _scrollColumnSizer + 1;
        public stbtic finbl Widget SLIDER = new Widget(_slider);
        @Nbtive privbte stbtic finbl byte _sliderThumb = _slider + 1;
        public stbtic finbl Widget SLIDER_THUMB = new Widget(_sliderThumb);

        @Nbtive privbte stbtic finbl byte _synchronizbtion = _sliderThumb + 1;
        public stbtic finbl Widget SYNCHRONIZATION = new Widget(_synchronizbtion);

        @Nbtive privbte stbtic finbl byte _tbb = _synchronizbtion + 1;
        public stbtic finbl Widget TAB = new Widget(_tbb);

        @Nbtive privbte stbtic finbl byte _titleBbrCloseBox = _tbb + 1;
        public stbtic finbl Widget TITLE_BAR_CLOSE_BOX = new Widget(_titleBbrCloseBox);
        @Nbtive privbte stbtic finbl byte _titleBbrCollbpseBox = _titleBbrCloseBox + 1;
        public stbtic finbl Widget TITLE_BAR_COLLAPSE_BOX = new Widget(_titleBbrCollbpseBox);
        @Nbtive privbte stbtic finbl byte _titleBbrZoomBox = _titleBbrCollbpseBox + 1;
        public stbtic finbl Widget TITLE_BAR_ZOOM_BOX = new Widget(_titleBbrZoomBox);

        @Nbtive privbte stbtic finbl byte _titleBbrToolbbrButton = _titleBbrZoomBox + 1;
        public stbtic finbl Widget TITLE_BAR_TOOLBAR_BUTTON = new Widget(_titleBbrToolbbrButton);

        @Nbtive privbte stbtic finbl byte _toolbbrItemWell = _titleBbrToolbbrButton + 1;
        public stbtic finbl Widget TOOLBAR_ITEM_WELL = new Widget(_toolbbrItemWell);

        @Nbtive privbte stbtic finbl byte _windowFrbme = _toolbbrItemWell + 1;
        public stbtic finbl Widget WINDOW_FRAME = new Widget(_windowFrbme);
    }

    public stbtic clbss Hit {
        @Nbtive privbte stbtic finbl int _unknown = -1;
        public stbtic finbl Hit UNKNOWN = new Hit(_unknown);
        @Nbtive privbte stbtic finbl int _none = 0;
        public stbtic finbl Hit NONE = new Hit(_none);
        @Nbtive privbte stbtic finbl int _hit = 1;
        public stbtic finbl Hit HIT = new Hit(_hit);

        finbl int hit;
        Hit(finbl int hit) { this.hit = hit; }

        public boolebn isHit() {
            return hit > 0;
        }

        public String toString() {
            return getConstbntNbme(this);
        }
    }

    public stbtic clbss ScrollBbrHit extends Hit {
        @Nbtive privbte stbtic finbl int _thumb = 2;
        public stbtic finbl ScrollBbrHit THUMB = new ScrollBbrHit(_thumb);

        @Nbtive privbte stbtic finbl int _trbckMin = 3;
        public stbtic finbl ScrollBbrHit TRACK_MIN = new ScrollBbrHit(_trbckMin);
        @Nbtive privbte stbtic finbl int _trbckMbx = 4;
        public stbtic finbl ScrollBbrHit TRACK_MAX = new ScrollBbrHit(_trbckMbx);

        @Nbtive privbte stbtic finbl int _brrowMin = 5;
        public stbtic finbl ScrollBbrHit ARROW_MIN = new ScrollBbrHit(_brrowMin);
        @Nbtive privbte stbtic finbl int _brrowMbx = 6;
        public stbtic finbl ScrollBbrHit ARROW_MAX = new ScrollBbrHit(_brrowMbx);
        @Nbtive privbte stbtic finbl int _brrowMbxInside = 7;
        public stbtic finbl ScrollBbrHit ARROW_MAX_INSIDE = new ScrollBbrHit(_brrowMbxInside);
        @Nbtive privbte stbtic finbl int _brrowMinInside = 8;
        public stbtic finbl ScrollBbrHit ARROW_MIN_INSIDE = new ScrollBbrHit(_brrowMinInside);

        ScrollBbrHit(finbl int hit) { super(hit); }
    }

    stbtic Hit getHit(finbl int hit) {
        switch (hit) {
            cbse Hit._none:
                return Hit.NONE;
            cbse Hit._hit:
                return Hit.HIT;

            cbse ScrollBbrHit._thumb:
                return ScrollBbrHit.THUMB;
            cbse ScrollBbrHit._trbckMin:
                return ScrollBbrHit.TRACK_MIN;
            cbse ScrollBbrHit._trbckMbx:
                return ScrollBbrHit.TRACK_MAX;
            cbse ScrollBbrHit._brrowMin:
                return ScrollBbrHit.ARROW_MIN;
            cbse ScrollBbrHit._brrowMbx:
                return ScrollBbrHit.ARROW_MAX;
            cbse ScrollBbrHit._brrowMbxInside:
                return ScrollBbrHit.ARROW_MAX_INSIDE;
            cbse ScrollBbrHit._brrowMinInside:
                return ScrollBbrHit.ARROW_MIN_INSIDE;
        }
        return Hit.UNKNOWN;
    }

    stbtic String getConstbntNbme(finbl Object object) {
        finbl Clbss<? extends Object> clbzz = object.getClbss();
        try {
            for (finbl Field field : clbzz.getFields()) {
                if (field.get(null) == object) {
                    return field.getNbme();
                }
            }
        } cbtch (finbl Exception e) {}
        return clbzz.getSimpleNbme();
    }
}
