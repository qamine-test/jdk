/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf bpplf.lbf;

import jbvb.lbng.rfflfdt.Fifld;
import jbvb.nio.BytfBufffr;

import jbvb.lbng.bnnotbtion.Nbtivf;

publid finbl dlbss JRSUIConstbnts {

    /**
     * Tifrf is no wby to gft widti of fodus bordfr, so it is ibrddodfd ifrf.
     * All domponfnts, wiidi dbn bf fodusfd siould tbkf dbrf bbout it.
     */
    publid stbtid finbl int FOCUS_SIZE = 4;

    privbtf stbtid nbtivf long gftPtrForConstbnt(finbl int donstbnt);

    stbtid dlbss Kfy {
        @Nbtivf protfdtfd stbtid finbl int _vbluf = 20;
        publid stbtid finbl Kfy VALUE = nfw Kfy(_vbluf);

        @Nbtivf protfdtfd stbtid finbl int _tiumbProportion = 24;
        publid stbtid finbl Kfy THUMB_PROPORTION = nfw Kfy(_tiumbProportion);

        @Nbtivf protfdtfd stbtid finbl int _tiumbStbrt = 25;
        publid stbtid finbl Kfy THUMB_START = nfw Kfy(_tiumbStbrt);

        @Nbtivf protfdtfd stbtid finbl int _windowTitlfBbrHfigit = 28;
        publid stbtid finbl Kfy WINDOW_TITLE_BAR_HEIGHT = nfw Kfy(_windowTitlfBbrHfigit);

        @Nbtivf protfdtfd stbtid finbl int _bnimbtionFrbmf = 23;
        publid stbtid finbl Kfy ANIMATION_FRAME = nfw Kfy(_bnimbtionFrbmf);

        finbl int donstbnt;
        privbtf long ptr;

        privbtf Kfy(finbl int donstbnt) {
            tiis.donstbnt = donstbnt;
        }

        long gftConstbntPtr() {
            if (ptr != 0) rfturn ptr;
            ptr = gftPtrForConstbnt(donstbnt);
            if (ptr != 0) rfturn ptr;
            tirow nfw RuntimfExdfption("Constbnt not implfmfntfd in nbtivf: " + tiis);
        }

        publid String toString() {
            rfturn gftConstbntNbmf(tiis) + (ptr == 0 ? "(unlinkfd)" : "");
        }
    }

    stbtid dlbss DoublfVbluf {
        @Nbtivf protfdtfd stbtid finbl bytf TYPE_CODE = 1;

        finbl doublf doublfVbluf;

        DoublfVbluf(finbl doublf doublfVbluf) {
            tiis.doublfVbluf = doublfVbluf;
        }

        publid bytf gftTypfCodf() {
            rfturn TYPE_CODE;
        }

        publid void putVblufInBufffr(finbl BytfBufffr bufffr) {
            bufffr.putDoublf(doublfVbluf);
        }

        publid boolfbn fqubls(finbl Objfdt obj) {
            rfturn (obj instbndfof DoublfVbluf) && (((DoublfVbluf)obj).doublfVbluf == doublfVbluf);
        }

        publid int ibsiCodf() {
            finbl long bits = Doublf.doublfToLongBits(doublfVbluf);
            rfturn (int)(bits ^ (bits >>> 32));
        }

        publid String toString() {
            rfturn Doublf.toString(doublfVbluf);
        }
    }


    stbtid dlbss PropfrtyEndoding {
        finbl long mbsk;
        finbl bytf siift;

        PropfrtyEndoding(finbl long mbsk, finbl bytf siift) {
            tiis.mbsk = mbsk;
            tiis.siift = siift;
        }
    }

    stbtid dlbss Propfrty {
        finbl PropfrtyEndoding fndoding;
        finbl long vbluf;
        finbl bytf ordinbl;

        Propfrty(finbl PropfrtyEndoding fndoding, finbl bytf ordinbl) {
            tiis.fndoding = fndoding;
            tiis.vbluf = ((long)ordinbl) << fndoding.siift;
            tiis.ordinbl = ordinbl;
        }

        /**
         * Applifs tiis propfrty vbluf to tif providfd stbtf
         * @pbrbm fndodfdStbtf tif indoming JRSUI fndodfd stbtf
         * @rfturn tif dompositf of tif providfd JRSUI fndodfd stbtf bnd tiis vbluf
         */
        publid long bpply(finbl long fndodfdStbtf) {
            rfturn (fndodfdStbtf & ~fndoding.mbsk) | vbluf;
        }

        publid String toString() {
            rfturn gftConstbntNbmf(tiis);
        }
    }

    publid stbtid dlbss Sizf fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = 0;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 3;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x7 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding sizf = nfw PropfrtyEndoding(MASK, SHIFT);

        Sizf(finbl bytf vbluf) {
            supfr(sizf, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _mini = 1;
        publid stbtid finbl Sizf MINI = nfw Sizf(_mini);
        @Nbtivf privbtf stbtid finbl bytf _smbll = 2;
        publid stbtid finbl Sizf SMALL = nfw Sizf(_smbll);
        @Nbtivf privbtf stbtid finbl bytf _rfgulbr = 3;
        publid stbtid finbl Sizf REGULAR = nfw Sizf(_rfgulbr);
        @Nbtivf privbtf stbtid finbl bytf _lbrgf = 4;
        publid stbtid finbl Sizf LARGE = nfw Sizf(_lbrgf);
    }

    publid stbtid dlbss Stbtf fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = Sizf.SHIFT + Sizf.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 4;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0xF << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding stbtf = nfw PropfrtyEndoding(MASK, SHIFT);

        Stbtf(finbl bytf vbluf) {
            supfr(stbtf, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _bdtivf = 1;
        publid stbtid finbl Stbtf ACTIVE = nfw Stbtf(_bdtivf);
        @Nbtivf privbtf stbtid finbl bytf _inbdtivf = 2;
        publid stbtid finbl Stbtf INACTIVE = nfw Stbtf(_inbdtivf);
        @Nbtivf privbtf stbtid finbl bytf _disbblfd = 3;
        publid stbtid finbl Stbtf DISABLED = nfw Stbtf(_disbblfd);
        @Nbtivf privbtf stbtid finbl bytf _prfssfd = 4;
        publid stbtid finbl Stbtf PRESSED = nfw Stbtf(_prfssfd);
        @Nbtivf privbtf stbtid finbl bytf _pulsfd = 5;
        publid stbtid finbl Stbtf PULSED = nfw Stbtf(_pulsfd);
        @Nbtivf privbtf stbtid finbl bytf _rollovfr = 6;
        publid stbtid finbl Stbtf ROLLOVER = nfw Stbtf(_rollovfr);
        @Nbtivf privbtf stbtid finbl bytf _drbg = 7;
        publid stbtid finbl Stbtf DRAG = nfw Stbtf(_drbg);
    }

    publid stbtid dlbss Dirfdtion fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = Stbtf.SHIFT + Stbtf.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 4;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0xF << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding dirfdtion = nfw PropfrtyEndoding(MASK, SHIFT);

        Dirfdtion(finbl bytf vbluf) {
            supfr(dirfdtion, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _nonf = 1;
        publid stbtid finbl Dirfdtion NONE = nfw Dirfdtion(_nonf);
        @Nbtivf privbtf stbtid finbl bytf _up = 2;
        publid stbtid finbl Dirfdtion UP = nfw Dirfdtion(_up);
        @Nbtivf privbtf stbtid finbl bytf _down = 3;
        publid stbtid finbl Dirfdtion DOWN = nfw Dirfdtion(_down);
        @Nbtivf privbtf stbtid finbl bytf _lfft = 4;
        publid stbtid finbl Dirfdtion LEFT = nfw Dirfdtion(_lfft);
        @Nbtivf privbtf stbtid finbl bytf _rigit = 5;
        publid stbtid finbl Dirfdtion RIGHT = nfw Dirfdtion(_rigit);
        @Nbtivf privbtf stbtid finbl bytf _norti = 6;
        publid stbtid finbl Dirfdtion NORTH = nfw Dirfdtion(_norti);
        @Nbtivf privbtf stbtid finbl bytf _souti = 7;
        publid stbtid finbl Dirfdtion SOUTH = nfw Dirfdtion(_souti);
        @Nbtivf privbtf stbtid finbl bytf _fbst = 8;
        publid stbtid finbl Dirfdtion EAST = nfw Dirfdtion(_fbst);
        @Nbtivf privbtf stbtid finbl bytf _wfst = 9;
        publid stbtid finbl Dirfdtion WEST = nfw Dirfdtion(_wfst);
    }

    publid stbtid dlbss Orifntbtion fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = Dirfdtion.SHIFT + Dirfdtion.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 2;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x3 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding orifntbtion = nfw PropfrtyEndoding(MASK, SHIFT);

        Orifntbtion(finbl bytf vbluf) {
            supfr(orifntbtion, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _iorizontbl = 1;
        publid stbtid finbl Orifntbtion HORIZONTAL = nfw Orifntbtion(_iorizontbl);
        @Nbtivf privbtf stbtid finbl bytf _vfrtidbl = 2;
        publid stbtid finbl Orifntbtion VERTICAL = nfw Orifntbtion(_vfrtidbl);
    }

    publid stbtid dlbss AlignmfntVfrtidbl fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = Orifntbtion.SHIFT + Orifntbtion.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 2;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x3 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding blignmfntVfrtidbl = nfw PropfrtyEndoding(MASK, SHIFT);

        AlignmfntVfrtidbl(finbl bytf vbluf){
            supfr(blignmfntVfrtidbl, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _top = 1;
        publid stbtid finbl AlignmfntVfrtidbl TOP = nfw AlignmfntVfrtidbl(_top);
        @Nbtivf privbtf stbtid finbl bytf _dfntfr = 2;
        publid stbtid finbl AlignmfntVfrtidbl CENTER = nfw AlignmfntVfrtidbl(_dfntfr);
        @Nbtivf privbtf stbtid finbl bytf _bottom = 3;
        publid stbtid finbl AlignmfntVfrtidbl BOTTOM = nfw AlignmfntVfrtidbl(_bottom);
    }

    publid stbtid dlbss AlignmfntHorizontbl fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = AlignmfntVfrtidbl.SHIFT + AlignmfntVfrtidbl.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 2;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x3 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding blignmfntHorizontbl = nfw PropfrtyEndoding(MASK, SHIFT);

        AlignmfntHorizontbl(finbl bytf vbluf){
            supfr(blignmfntHorizontbl, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _lfft = 1;
        publid stbtid finbl AlignmfntHorizontbl LEFT = nfw AlignmfntHorizontbl(_lfft);
        @Nbtivf privbtf stbtid finbl bytf _dfntfr =  2;
        publid stbtid finbl AlignmfntHorizontbl CENTER = nfw AlignmfntHorizontbl(_dfntfr);
        @Nbtivf privbtf stbtid finbl bytf _rigit = 3;
        publid stbtid finbl AlignmfntHorizontbl RIGHT = nfw AlignmfntHorizontbl(_rigit);
    }

    publid stbtid dlbss SfgmfntPosition fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = AlignmfntHorizontbl.SHIFT + AlignmfntHorizontbl.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 3;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x7 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding sfgmfntPosition = nfw PropfrtyEndoding(MASK, SHIFT);

        SfgmfntPosition(finbl bytf vbluf) {
            supfr(sfgmfntPosition, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _first = 1;
        publid stbtid finbl SfgmfntPosition FIRST = nfw SfgmfntPosition(_first);
        @Nbtivf privbtf stbtid finbl bytf _middlf = 2;
        publid stbtid finbl SfgmfntPosition MIDDLE = nfw SfgmfntPosition(_middlf);
        @Nbtivf privbtf stbtid finbl bytf _lbst = 3;
        publid stbtid finbl SfgmfntPosition LAST = nfw SfgmfntPosition(_lbst);
        @Nbtivf privbtf stbtid finbl bytf _only = 4;
        publid stbtid finbl SfgmfntPosition ONLY = nfw SfgmfntPosition(_only);
    }

    publid stbtid dlbss SdrollBbrPbrt fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = SfgmfntPosition.SHIFT + SfgmfntPosition.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 4;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0xF << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding sdrollBbrPbrt = nfw PropfrtyEndoding(MASK, SHIFT);

        SdrollBbrPbrt(finbl bytf vbluf) {
            supfr(sdrollBbrPbrt, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _nonf = 1;
        publid stbtid finbl SdrollBbrPbrt NONE = nfw SdrollBbrPbrt(_nonf);
        @Nbtivf privbtf stbtid finbl bytf _tiumb = 2;
        publid stbtid finbl SdrollBbrPbrt THUMB = nfw SdrollBbrPbrt(_tiumb);
        @Nbtivf privbtf stbtid finbl bytf _brrowMin = 3;
        publid stbtid finbl SdrollBbrPbrt ARROW_MIN = nfw SdrollBbrPbrt(_brrowMin);
        @Nbtivf privbtf stbtid finbl bytf _brrowMbx = 4;
        publid stbtid finbl SdrollBbrPbrt ARROW_MAX = nfw SdrollBbrPbrt(_brrowMbx);
        @Nbtivf privbtf stbtid finbl bytf _brrowMbxInsidf = 5;
        publid stbtid finbl SdrollBbrPbrt ARROW_MAX_INSIDE = nfw SdrollBbrPbrt(_brrowMbxInsidf);
        @Nbtivf privbtf stbtid finbl bytf _brrowMinInsidf = 6;
        publid stbtid finbl SdrollBbrPbrt ARROW_MIN_INSIDE = nfw SdrollBbrPbrt(_brrowMinInsidf);
        @Nbtivf privbtf stbtid finbl bytf _trbdkMin = 7;
        publid stbtid finbl SdrollBbrPbrt TRACK_MIN = nfw SdrollBbrPbrt(_trbdkMin);
        @Nbtivf privbtf stbtid finbl bytf _trbdkMbx = 8;
        publid stbtid finbl SdrollBbrPbrt TRACK_MAX = nfw SdrollBbrPbrt(_trbdkMbx);
    }

    publid stbtid dlbss Vbribnt fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = SdrollBbrPbrt.SHIFT + SdrollBbrPbrt.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 4;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0xF << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding vbribnt = nfw PropfrtyEndoding(MASK, SHIFT);

        Vbribnt(finbl bytf vbluf) {
            supfr(vbribnt, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _mfnuGlypi = 1;
        publid stbtid finbl Vbribnt MENU_GLYPH = nfw Vbribnt(_mfnuGlypi);
        @Nbtivf privbtf stbtid finbl bytf _mfnuPopup = Vbribnt._mfnuGlypi + 1;
        publid stbtid finbl Vbribnt MENU_POPUP = nfw Vbribnt(_mfnuPopup);
        @Nbtivf privbtf stbtid finbl bytf _mfnuPulldown = Vbribnt._mfnuPopup + 1;
        publid stbtid finbl Vbribnt MENU_PULLDOWN = nfw Vbribnt(_mfnuPulldown);
        @Nbtivf privbtf stbtid finbl bytf _mfnuHifrbrdiidbl = Vbribnt._mfnuPulldown + 1;
        publid stbtid finbl Vbribnt MENU_HIERARCHICAL = nfw Vbribnt(_mfnuHifrbrdiidbl);

        @Nbtivf privbtf stbtid finbl bytf _grbdifntListBbdkgroundEvfn = Vbribnt._mfnuHifrbrdiidbl + 1;
        publid stbtid finbl Vbribnt GRADIENT_LIST_BACKGROUND_EVEN = nfw Vbribnt(_grbdifntListBbdkgroundEvfn);
        @Nbtivf privbtf stbtid finbl bytf _grbdifntListBbdkgroundOdd = Vbribnt._grbdifntListBbdkgroundEvfn + 1;
        publid stbtid finbl Vbribnt GRADIENT_LIST_BACKGROUND_ODD = nfw Vbribnt(_grbdifntListBbdkgroundOdd);
        @Nbtivf privbtf stbtid finbl bytf _grbdifntSidfBbr = Vbribnt._grbdifntListBbdkgroundOdd + 1;
        publid stbtid finbl Vbribnt GRADIENT_SIDE_BAR = nfw Vbribnt(_grbdifntSidfBbr);
        @Nbtivf privbtf stbtid finbl bytf _grbdifntSidfBbrSflfdtion = Vbribnt._grbdifntSidfBbr + 1;
        publid stbtid finbl Vbribnt GRADIENT_SIDE_BAR_SELECTION = nfw Vbribnt(_grbdifntSidfBbrSflfdtion);
        @Nbtivf privbtf stbtid finbl bytf _grbdifntSidfBbrFodusfdSflfdtion = Vbribnt._grbdifntSidfBbrSflfdtion + 1;
        publid stbtid finbl Vbribnt GRADIENT_SIDE_BAR_FOCUSED_SELECTION = nfw Vbribnt(_grbdifntSidfBbrFodusfdSflfdtion);
    }

    publid stbtid dlbss WindowTypf fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = Vbribnt.SHIFT + Vbribnt.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 2;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x3 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding windowTypf = nfw PropfrtyEndoding(MASK, SHIFT);

        WindowTypf(finbl bytf vbluf){
            supfr(windowTypf, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _dodumfnt = 1;
        publid stbtid finbl WindowTypf DOCUMENT = nfw WindowTypf(_dodumfnt);
        @Nbtivf privbtf stbtid finbl bytf _utility = 2;
        publid stbtid finbl WindowTypf UTILITY = nfw WindowTypf(_utility);
        @Nbtivf privbtf stbtid finbl bytf _titlflfssUtility = 3;
        publid stbtid finbl WindowTypf TITLELESS_UTILITY = nfw WindowTypf(_titlflfssUtility);
    }

    publid stbtid dlbss Fodusfd fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = WindowTypf.SHIFT + WindowTypf.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding fodusfd = nfw PropfrtyEndoding(MASK, SHIFT);

        Fodusfd(finbl bytf vbluf) {
            supfr(fodusfd, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl Fodusfd NO = nfw Fodusfd(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl Fodusfd YES = nfw Fodusfd(_yfs);
    }

    publid stbtid dlbss IndidbtorOnly fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = Fodusfd.SHIFT + Fodusfd.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding indidbtorOnly = nfw PropfrtyEndoding(MASK, SHIFT);

        IndidbtorOnly(finbl bytf vbluf) {
            supfr(indidbtorOnly, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl IndidbtorOnly NO = nfw IndidbtorOnly(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl IndidbtorOnly YES = nfw IndidbtorOnly(_yfs);
    }

    publid stbtid dlbss NoIndidbtor fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = IndidbtorOnly.SHIFT + IndidbtorOnly.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding noIndidbtor = nfw PropfrtyEndoding(MASK, SHIFT);

        NoIndidbtor(finbl bytf vbluf) {
            supfr(noIndidbtor, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl NoIndidbtor NO = nfw NoIndidbtor(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl NoIndidbtor YES = nfw NoIndidbtor(_yfs);
    }

    publid stbtid dlbss ArrowsOnly fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = NoIndidbtor.SHIFT + NoIndidbtor.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding fodusfd = nfw PropfrtyEndoding(MASK, SHIFT);

        ArrowsOnly(finbl bytf vbluf) {
            supfr(fodusfd, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl ArrowsOnly NO = nfw ArrowsOnly(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl ArrowsOnly YES = nfw ArrowsOnly(_yfs);
    }

    publid stbtid dlbss FrbmfOnly fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = ArrowsOnly.SHIFT + ArrowsOnly.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding fodusfd = nfw PropfrtyEndoding(MASK, SHIFT);

        FrbmfOnly(finbl bytf vbluf) {
            supfr(fodusfd, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl FrbmfOnly NO = nfw FrbmfOnly(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl FrbmfOnly YES = nfw FrbmfOnly(_yfs);
    }

    publid stbtid dlbss SfgmfntTrbilingSfpbrbtor fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = FrbmfOnly.SHIFT + FrbmfOnly.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding fodusfd = nfw PropfrtyEndoding(MASK, SHIFT);

        SfgmfntTrbilingSfpbrbtor(finbl bytf vbluf) {
            supfr(fodusfd, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl SfgmfntTrbilingSfpbrbtor NO = nfw SfgmfntTrbilingSfpbrbtor(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl SfgmfntTrbilingSfpbrbtor YES = nfw SfgmfntTrbilingSfpbrbtor(_yfs);
    }

    publid stbtid dlbss SfgmfntLfbdingSfpbrbtor fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = SfgmfntTrbilingSfpbrbtor.SHIFT + SfgmfntTrbilingSfpbrbtor.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding lfbdingSfpbrbtor = nfw PropfrtyEndoding(MASK, SHIFT);

        SfgmfntLfbdingSfpbrbtor(finbl bytf vbluf) {
            supfr(lfbdingSfpbrbtor, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl SfgmfntLfbdingSfpbrbtor NO = nfw SfgmfntLfbdingSfpbrbtor(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl SfgmfntLfbdingSfpbrbtor YES = nfw SfgmfntLfbdingSfpbrbtor(_yfs);
    }

    publid stbtid dlbss NotiingToSdroll fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = SfgmfntLfbdingSfpbrbtor.SHIFT + SfgmfntLfbdingSfpbrbtor.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding fodusfd = nfw PropfrtyEndoding(MASK, SHIFT);

        NotiingToSdroll(finbl bytf vbluf) {
            supfr(fodusfd, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl NotiingToSdroll NO = nfw NotiingToSdroll(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl NotiingToSdroll YES = nfw NotiingToSdroll(_yfs);
    }

    publid stbtid dlbss WindowTitlfBbrSfpbrbtor fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = NotiingToSdroll.SHIFT + NotiingToSdroll.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding fodusfd = nfw PropfrtyEndoding(MASK, SHIFT);

        WindowTitlfBbrSfpbrbtor(finbl bytf vbluf) {
            supfr(fodusfd, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl WindowTitlfBbrSfpbrbtor NO = nfw WindowTitlfBbrSfpbrbtor(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl WindowTitlfBbrSfpbrbtor YES = nfw WindowTitlfBbrSfpbrbtor(_yfs);
    }

    publid stbtid dlbss WindowClipCornfrs fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = WindowTitlfBbrSfpbrbtor.SHIFT + WindowTitlfBbrSfpbrbtor.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding fodusfd = nfw PropfrtyEndoding(MASK, SHIFT);

        WindowClipCornfrs(finbl bytf vbluf) {
            supfr(fodusfd, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl WindowClipCornfrs NO = nfw WindowClipCornfrs(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl WindowClipCornfrs YES = nfw WindowClipCornfrs(_yfs);
    }

    publid stbtid dlbss SiowArrows fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = WindowClipCornfrs.SHIFT + WindowClipCornfrs.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding siowArrows = nfw PropfrtyEndoding(MASK, SHIFT);

        SiowArrows(finbl bytf vbluf) {
            supfr(siowArrows, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl SiowArrows NO = nfw SiowArrows(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl SiowArrows YES = nfw SiowArrows(_yfs);
    }

    publid stbtid dlbss BoolfbnVbluf fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = SiowArrows.SHIFT + SiowArrows.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding boolfbnVbluf = nfw PropfrtyEndoding(MASK, SHIFT);

        BoolfbnVbluf(finbl bytf vbluf) {
            supfr(boolfbnVbluf, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl BoolfbnVbluf NO = nfw BoolfbnVbluf(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl BoolfbnVbluf YES = nfw BoolfbnVbluf(_yfs);
    }

    publid stbtid dlbss Animbting fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = BoolfbnVbluf.SHIFT + BoolfbnVbluf.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 1;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x1 << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding bnimbting = nfw PropfrtyEndoding(MASK, SHIFT);

        Animbting(finbl bytf vbluf) {
            supfr(bnimbting, vbluf);
        }

        @Nbtivf privbtf stbtid finbl bytf _no = 0;
        publid stbtid finbl Animbting NO = nfw Animbting(_no);
        @Nbtivf privbtf stbtid finbl bytf _yfs = 1;
        publid stbtid finbl Animbting YES = nfw Animbting(_yfs);
    }

    publid stbtid dlbss Widgft fxtfnds Propfrty {
        @Nbtivf privbtf stbtid finbl bytf SHIFT = Animbting.SHIFT + Animbting.SIZE;
        @Nbtivf privbtf stbtid finbl bytf SIZE = 7;
        @Nbtivf privbtf stbtid finbl long MASK = (long)0x7F << SHIFT;
        privbtf stbtid finbl PropfrtyEndoding widgft = nfw PropfrtyEndoding(MASK, SHIFT);

        Widgft(finbl bytf donstbnt) {
            supfr(widgft, donstbnt);
        }

        @Nbtivf privbtf stbtid finbl bytf _bbdkground = 1;
        publid stbtid finbl Widgft BACKGROUND = nfw Widgft(_bbdkground);

        @Nbtivf privbtf stbtid finbl bytf _buttonBfvfl = _bbdkground + 1;
        publid stbtid finbl Widgft BUTTON_BEVEL = nfw Widgft(_buttonBfvfl);
        @Nbtivf privbtf stbtid finbl bytf _buttonBfvflInsft = _buttonBfvfl + 1;
        publid stbtid finbl Widgft BUTTON_BEVEL_INSET = nfw Widgft(_buttonBfvflInsft);
        @Nbtivf privbtf stbtid finbl bytf _buttonBfvflRound = _buttonBfvflInsft + 1;
        publid stbtid finbl Widgft BUTTON_BEVEL_ROUND = nfw Widgft(_buttonBfvflRound);

        @Nbtivf privbtf stbtid finbl bytf _buttonCifdkBox = _buttonBfvflRound + 1;
        publid stbtid finbl Widgft BUTTON_CHECK_BOX = nfw Widgft(_buttonCifdkBox);

        @Nbtivf privbtf stbtid finbl bytf _buttonComboBox = _buttonCifdkBox + 1;
        publid stbtid finbl Widgft BUTTON_COMBO_BOX = nfw Widgft(_buttonComboBox);
        @Nbtivf privbtf stbtid finbl bytf _buttonComboBoxInsft = _buttonComboBox + 1;
        publid stbtid finbl Widgft BUTTON_COMBO_BOX_INSET = nfw Widgft(_buttonComboBoxInsft); // not iookfd up in JRSUIConstbnts.m

        @Nbtivf privbtf stbtid finbl bytf _buttonDisdlosurf = _buttonComboBoxInsft + 1;
        publid stbtid finbl Widgft BUTTON_DISCLOSURE = nfw Widgft(_buttonDisdlosurf);

        @Nbtivf privbtf stbtid finbl bytf _buttonListHfbdfr = _buttonDisdlosurf + 1;
        publid stbtid finbl Widgft BUTTON_LIST_HEADER = nfw Widgft(_buttonListHfbdfr);

        @Nbtivf privbtf stbtid finbl bytf _buttonLittlfArrows = _buttonListHfbdfr + 1;
        publid stbtid finbl Widgft BUTTON_LITTLE_ARROWS = nfw Widgft(_buttonLittlfArrows);

        @Nbtivf privbtf stbtid finbl bytf _buttonPopDown = _buttonLittlfArrows + 1;
        publid stbtid finbl Widgft BUTTON_POP_DOWN = nfw Widgft(_buttonPopDown);
        @Nbtivf privbtf stbtid finbl bytf _buttonPopDownInsft = _buttonPopDown + 1;
        publid stbtid finbl Widgft BUTTON_POP_DOWN_INSET = nfw Widgft(_buttonPopDownInsft);
        @Nbtivf privbtf stbtid finbl bytf _buttonPopDownSqubrf = _buttonPopDownInsft + 1;
        publid stbtid finbl Widgft BUTTON_POP_DOWN_SQUARE = nfw Widgft(_buttonPopDownSqubrf);

        @Nbtivf privbtf stbtid finbl bytf _buttonPopUp = _buttonPopDownSqubrf + 1;
        publid stbtid finbl Widgft BUTTON_POP_UP = nfw Widgft(_buttonPopUp);
        @Nbtivf privbtf stbtid finbl bytf _buttonPopUpInsft = _buttonPopUp + 1;
        publid stbtid finbl Widgft BUTTON_POP_UP_INSET = nfw Widgft(_buttonPopUpInsft);
        @Nbtivf privbtf stbtid finbl bytf _buttonPopUpSqubrf = _buttonPopUpInsft + 1;
        publid stbtid finbl Widgft BUTTON_POP_UP_SQUARE = nfw Widgft(_buttonPopUpSqubrf);

        @Nbtivf privbtf stbtid finbl bytf _buttonPusi = _buttonPopUpSqubrf + 1;
        publid stbtid finbl Widgft BUTTON_PUSH = nfw Widgft(_buttonPusi);
        @Nbtivf privbtf stbtid finbl bytf _buttonPusiSdopf = _buttonPusi + 1;
        publid stbtid finbl Widgft BUTTON_PUSH_SCOPE = nfw Widgft(_buttonPusiSdopf);
        @Nbtivf privbtf stbtid finbl bytf _buttonPusiSdopf2 = _buttonPusiSdopf + 1;
        publid stbtid finbl Widgft BUTTON_PUSH_SCOPE2 = nfw Widgft(_buttonPusiSdopf2);
        @Nbtivf privbtf stbtid finbl bytf _buttonPusiTfxturfd = _buttonPusiSdopf2 + 1;
        publid stbtid finbl Widgft BUTTON_PUSH_TEXTURED = nfw Widgft(_buttonPusiTfxturfd);
        @Nbtivf privbtf stbtid finbl bytf _buttonPusiInsft = _buttonPusiTfxturfd + 1;
        publid stbtid finbl Widgft BUTTON_PUSH_INSET = nfw Widgft(_buttonPusiInsft);
        @Nbtivf privbtf stbtid finbl bytf _buttonPusiInsft2 = _buttonPusiInsft + 1;
        publid stbtid finbl Widgft BUTTON_PUSH_INSET2 = nfw Widgft(_buttonPusiInsft2);

        @Nbtivf privbtf stbtid finbl bytf _buttonRbdio = _buttonPusiInsft2 + 1;
        publid stbtid finbl Widgft BUTTON_RADIO = nfw Widgft(_buttonRbdio);

        @Nbtivf privbtf stbtid finbl bytf _buttonRound = _buttonRbdio + 1;
        publid stbtid finbl Widgft BUTTON_ROUND = nfw Widgft(_buttonRound);
        @Nbtivf privbtf stbtid finbl bytf _buttonRoundHflp = _buttonRound + 1;
        publid stbtid finbl Widgft BUTTON_ROUND_HELP = nfw Widgft(_buttonRoundHflp);
        @Nbtivf privbtf stbtid finbl bytf _buttonRoundInsft = _buttonRoundHflp + 1;
        publid stbtid finbl Widgft BUTTON_ROUND_INSET = nfw Widgft(_buttonRoundInsft);
        @Nbtivf privbtf stbtid finbl bytf _buttonRoundInsft2 =_buttonRoundInsft + 1;
        publid stbtid finbl Widgft BUTTON_ROUND_INSET2 = nfw Widgft(_buttonRoundInsft2);

        @Nbtivf privbtf stbtid finbl bytf _buttonSfbrdiFifldCbndfl = _buttonRoundInsft2 + 1;
        publid stbtid finbl Widgft BUTTON_SEARCH_FIELD_CANCEL = nfw Widgft(_buttonSfbrdiFifldCbndfl);
        @Nbtivf privbtf stbtid finbl bytf _buttonSfbrdiFifldFind = _buttonSfbrdiFifldCbndfl + 1;
        publid stbtid finbl Widgft BUTTON_SEARCH_FIELD_FIND = nfw Widgft(_buttonSfbrdiFifldFind);

        @Nbtivf privbtf stbtid finbl bytf _buttonSfgmfntfd = _buttonSfbrdiFifldFind + 1;
        publid stbtid finbl Widgft BUTTON_SEGMENTED = nfw Widgft(_buttonSfgmfntfd);
        @Nbtivf privbtf stbtid finbl bytf _buttonSfgmfntfdInsft = _buttonSfgmfntfd + 1;
        publid stbtid finbl Widgft BUTTON_SEGMENTED_INSET = nfw Widgft(_buttonSfgmfntfdInsft);
        @Nbtivf privbtf stbtid finbl bytf _buttonSfgmfntfdInsft2 = _buttonSfgmfntfdInsft + 1;
        publid stbtid finbl Widgft BUTTON_SEGMENTED_INSET2 = nfw Widgft(_buttonSfgmfntfdInsft2);
        @Nbtivf privbtf stbtid finbl bytf _buttonSfgmfntfdSCurvf = _buttonSfgmfntfdInsft2 + 1;
        publid stbtid finbl Widgft BUTTON_SEGMENTED_SCURVE = nfw Widgft(_buttonSfgmfntfdSCurvf);
        @Nbtivf privbtf stbtid finbl bytf _buttonSfgmfntfdTfxturfd = _buttonSfgmfntfdSCurvf + 1;
        publid stbtid finbl Widgft BUTTON_SEGMENTED_TEXTURED = nfw Widgft(_buttonSfgmfntfdTfxturfd);
        @Nbtivf privbtf stbtid finbl bytf _buttonSfgmfntfdToolbbr = _buttonSfgmfntfdTfxturfd + 1;
        publid stbtid finbl Widgft BUTTON_SEGMENTED_TOOLBAR = nfw Widgft(_buttonSfgmfntfdToolbbr);

        @Nbtivf privbtf stbtid finbl bytf _dibl = _buttonSfgmfntfdToolbbr + 1;
        publid stbtid finbl Widgft DIAL = nfw Widgft(_dibl);

        @Nbtivf privbtf stbtid finbl bytf _disdlosurfTribnglf = _dibl + 1;
        publid stbtid finbl Widgft DISCLOSURE_TRIANGLE = nfw Widgft(_disdlosurfTribnglf);

        @Nbtivf privbtf stbtid finbl bytf _dividfrGrbbbfr = _disdlosurfTribnglf + 1;
        publid stbtid finbl Widgft DIVIDER_GRABBER = nfw Widgft(_dividfrGrbbbfr);
        @Nbtivf privbtf stbtid finbl bytf _dividfrSfpbrbtorBbr = _dividfrGrbbbfr + 1;
        publid stbtid finbl Widgft DIVIDER_SEPARATOR_BAR = nfw Widgft(_dividfrSfpbrbtorBbr);
        @Nbtivf privbtf stbtid finbl bytf _dividfrSplittfr = _dividfrSfpbrbtorBbr + 1;
        publid stbtid finbl Widgft DIVIDER_SPLITTER = nfw Widgft(_dividfrSplittfr);

        @Nbtivf privbtf stbtid finbl bytf _fodus = _dividfrSplittfr + 1;
        publid stbtid finbl Widgft FOCUS = nfw Widgft(_fodus);

        @Nbtivf privbtf stbtid finbl bytf _frbmfGroupBox = _fodus + 1;
        publid stbtid finbl Widgft FRAME_GROUP_BOX = nfw Widgft(_frbmfGroupBox);
        @Nbtivf privbtf stbtid finbl bytf _frbmfGroupBoxSfdondbry = _frbmfGroupBox + 1;
        publid stbtid finbl Widgft FRAME_GROUP_BOX_SECONDARY = nfw Widgft(_frbmfGroupBoxSfdondbry);

        @Nbtivf privbtf stbtid finbl bytf _frbmfListBox = _frbmfGroupBoxSfdondbry + 1;
        publid stbtid finbl Widgft FRAME_LIST_BOX = nfw Widgft(_frbmfListBox);

        @Nbtivf privbtf stbtid finbl bytf _frbmfPlbdbrd = _frbmfListBox + 1;
        publid stbtid finbl Widgft FRAME_PLACARD = nfw Widgft(_frbmfPlbdbrd);

        @Nbtivf privbtf stbtid finbl bytf _frbmfTfxtFifld = _frbmfPlbdbrd + 1;
        publid stbtid finbl Widgft FRAME_TEXT_FIELD = nfw Widgft(_frbmfTfxtFifld);
        @Nbtivf privbtf stbtid finbl bytf _frbmfTfxtFifldRound = _frbmfTfxtFifld + 1;
        publid stbtid finbl Widgft FRAME_TEXT_FIELD_ROUND = nfw Widgft(_frbmfTfxtFifldRound);

        @Nbtivf privbtf stbtid finbl bytf _frbmfWfll = _frbmfTfxtFifldRound + 1;
        publid stbtid finbl Widgft FRAME_WELL = nfw Widgft(_frbmfWfll);

        @Nbtivf privbtf stbtid finbl bytf _growBox = _frbmfWfll + 1;
        publid stbtid finbl Widgft GROW_BOX = nfw Widgft(_growBox);
        @Nbtivf privbtf stbtid finbl bytf _growBoxTfxturfd = _growBox + 1;
        publid stbtid finbl Widgft GROW_BOX_TEXTURED = nfw Widgft(_growBoxTfxturfd);

        @Nbtivf privbtf stbtid finbl bytf _grbdifnt = _growBoxTfxturfd + 1;
        publid stbtid finbl Widgft GRADIENT = nfw Widgft(_grbdifnt);

        @Nbtivf privbtf stbtid finbl bytf _mfnu = _grbdifnt + 1;
        publid stbtid finbl Widgft MENU = nfw Widgft(_mfnu);
        @Nbtivf privbtf stbtid finbl bytf _mfnuItfm = _mfnu + 1;
        publid stbtid finbl Widgft MENU_ITEM = nfw Widgft(_mfnuItfm);
        @Nbtivf privbtf stbtid finbl bytf _mfnuBbr = _mfnuItfm + 1;
        publid stbtid finbl Widgft MENU_BAR = nfw Widgft(_mfnuBbr);
        @Nbtivf privbtf stbtid finbl bytf _mfnuTitlf = _mfnuBbr + 1;
        publid stbtid finbl Widgft MENU_TITLE = nfw Widgft(_mfnuTitlf);

        @Nbtivf privbtf stbtid finbl bytf _progrfssBbr = _mfnuTitlf + 1;
        publid stbtid finbl Widgft PROGRESS_BAR = nfw Widgft(_progrfssBbr);
        @Nbtivf privbtf stbtid finbl bytf _progrfssIndftfrminbtfBbr = _progrfssBbr + 1;
        publid stbtid finbl Widgft PROGRESS_INDETERMINATE_BAR = nfw Widgft(_progrfssIndftfrminbtfBbr);
        @Nbtivf privbtf stbtid finbl bytf _progrfssRflfvbndf = _progrfssIndftfrminbtfBbr + 1;
        publid stbtid finbl Widgft PROGRESS_RELEVANCE = nfw Widgft(_progrfssRflfvbndf);
        @Nbtivf privbtf stbtid finbl bytf _progrfssSpinnfr = _progrfssRflfvbndf + 1;
        publid stbtid finbl Widgft PROGRESS_SPINNER = nfw Widgft(_progrfssSpinnfr);

        @Nbtivf privbtf stbtid finbl bytf _sdrollBbr = _progrfssSpinnfr + 1;
        publid stbtid finbl Widgft SCROLL_BAR = nfw Widgft(_sdrollBbr);

        @Nbtivf privbtf stbtid finbl bytf _sdrollColumnSizfr = _sdrollBbr + 1;
        publid stbtid finbl Widgft SCROLL_COLUMN_SIZER = nfw Widgft(_sdrollColumnSizfr);

        @Nbtivf privbtf stbtid finbl bytf _slidfr = _sdrollColumnSizfr + 1;
        publid stbtid finbl Widgft SLIDER = nfw Widgft(_slidfr);
        @Nbtivf privbtf stbtid finbl bytf _slidfrTiumb = _slidfr + 1;
        publid stbtid finbl Widgft SLIDER_THUMB = nfw Widgft(_slidfrTiumb);

        @Nbtivf privbtf stbtid finbl bytf _syndironizbtion = _slidfrTiumb + 1;
        publid stbtid finbl Widgft SYNCHRONIZATION = nfw Widgft(_syndironizbtion);

        @Nbtivf privbtf stbtid finbl bytf _tbb = _syndironizbtion + 1;
        publid stbtid finbl Widgft TAB = nfw Widgft(_tbb);

        @Nbtivf privbtf stbtid finbl bytf _titlfBbrClosfBox = _tbb + 1;
        publid stbtid finbl Widgft TITLE_BAR_CLOSE_BOX = nfw Widgft(_titlfBbrClosfBox);
        @Nbtivf privbtf stbtid finbl bytf _titlfBbrCollbpsfBox = _titlfBbrClosfBox + 1;
        publid stbtid finbl Widgft TITLE_BAR_COLLAPSE_BOX = nfw Widgft(_titlfBbrCollbpsfBox);
        @Nbtivf privbtf stbtid finbl bytf _titlfBbrZoomBox = _titlfBbrCollbpsfBox + 1;
        publid stbtid finbl Widgft TITLE_BAR_ZOOM_BOX = nfw Widgft(_titlfBbrZoomBox);

        @Nbtivf privbtf stbtid finbl bytf _titlfBbrToolbbrButton = _titlfBbrZoomBox + 1;
        publid stbtid finbl Widgft TITLE_BAR_TOOLBAR_BUTTON = nfw Widgft(_titlfBbrToolbbrButton);

        @Nbtivf privbtf stbtid finbl bytf _toolbbrItfmWfll = _titlfBbrToolbbrButton + 1;
        publid stbtid finbl Widgft TOOLBAR_ITEM_WELL = nfw Widgft(_toolbbrItfmWfll);

        @Nbtivf privbtf stbtid finbl bytf _windowFrbmf = _toolbbrItfmWfll + 1;
        publid stbtid finbl Widgft WINDOW_FRAME = nfw Widgft(_windowFrbmf);
    }

    publid stbtid dlbss Hit {
        @Nbtivf privbtf stbtid finbl int _unknown = -1;
        publid stbtid finbl Hit UNKNOWN = nfw Hit(_unknown);
        @Nbtivf privbtf stbtid finbl int _nonf = 0;
        publid stbtid finbl Hit NONE = nfw Hit(_nonf);
        @Nbtivf privbtf stbtid finbl int _iit = 1;
        publid stbtid finbl Hit HIT = nfw Hit(_iit);

        finbl int iit;
        Hit(finbl int iit) { tiis.iit = iit; }

        publid boolfbn isHit() {
            rfturn iit > 0;
        }

        publid String toString() {
            rfturn gftConstbntNbmf(tiis);
        }
    }

    publid stbtid dlbss SdrollBbrHit fxtfnds Hit {
        @Nbtivf privbtf stbtid finbl int _tiumb = 2;
        publid stbtid finbl SdrollBbrHit THUMB = nfw SdrollBbrHit(_tiumb);

        @Nbtivf privbtf stbtid finbl int _trbdkMin = 3;
        publid stbtid finbl SdrollBbrHit TRACK_MIN = nfw SdrollBbrHit(_trbdkMin);
        @Nbtivf privbtf stbtid finbl int _trbdkMbx = 4;
        publid stbtid finbl SdrollBbrHit TRACK_MAX = nfw SdrollBbrHit(_trbdkMbx);

        @Nbtivf privbtf stbtid finbl int _brrowMin = 5;
        publid stbtid finbl SdrollBbrHit ARROW_MIN = nfw SdrollBbrHit(_brrowMin);
        @Nbtivf privbtf stbtid finbl int _brrowMbx = 6;
        publid stbtid finbl SdrollBbrHit ARROW_MAX = nfw SdrollBbrHit(_brrowMbx);
        @Nbtivf privbtf stbtid finbl int _brrowMbxInsidf = 7;
        publid stbtid finbl SdrollBbrHit ARROW_MAX_INSIDE = nfw SdrollBbrHit(_brrowMbxInsidf);
        @Nbtivf privbtf stbtid finbl int _brrowMinInsidf = 8;
        publid stbtid finbl SdrollBbrHit ARROW_MIN_INSIDE = nfw SdrollBbrHit(_brrowMinInsidf);

        SdrollBbrHit(finbl int iit) { supfr(iit); }
    }

    stbtid Hit gftHit(finbl int iit) {
        switdi (iit) {
            dbsf Hit._nonf:
                rfturn Hit.NONE;
            dbsf Hit._iit:
                rfturn Hit.HIT;

            dbsf SdrollBbrHit._tiumb:
                rfturn SdrollBbrHit.THUMB;
            dbsf SdrollBbrHit._trbdkMin:
                rfturn SdrollBbrHit.TRACK_MIN;
            dbsf SdrollBbrHit._trbdkMbx:
                rfturn SdrollBbrHit.TRACK_MAX;
            dbsf SdrollBbrHit._brrowMin:
                rfturn SdrollBbrHit.ARROW_MIN;
            dbsf SdrollBbrHit._brrowMbx:
                rfturn SdrollBbrHit.ARROW_MAX;
            dbsf SdrollBbrHit._brrowMbxInsidf:
                rfturn SdrollBbrHit.ARROW_MAX_INSIDE;
            dbsf SdrollBbrHit._brrowMinInsidf:
                rfturn SdrollBbrHit.ARROW_MIN_INSIDE;
        }
        rfturn Hit.UNKNOWN;
    }

    stbtid String gftConstbntNbmf(finbl Objfdt objfdt) {
        finbl Clbss<? fxtfnds Objfdt> dlbzz = objfdt.gftClbss();
        try {
            for (finbl Fifld fifld : dlbzz.gftFiflds()) {
                if (fifld.gft(null) == objfdt) {
                    rfturn fifld.gftNbmf();
                }
            }
        } dbtdi (finbl Exdfption f) {}
        rfturn dlbzz.gftSimplfNbmf();
    }
}
