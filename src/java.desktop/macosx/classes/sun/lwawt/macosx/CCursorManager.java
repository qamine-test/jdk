/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import sun.lwbwt.LWCursorMbnbgfr;

import jbvb.bwt.Cursor;
import jbvb.bwt.Point;
import jbvb.bwt.gfom.Point2D;

finbl dlbss CCursorMbnbgfr fxtfnds LWCursorMbnbgfr {

    privbtf stbtid nbtivf Point2D nbtivfGftCursorPosition();
    privbtf stbtid nbtivf void nbtivfSftBuiltInCursor(finbl int typf, finbl String nbmf);
    privbtf stbtid nbtivf void nbtivfSftCustomCursor(finbl long imgPtr, finbl doublf x, finbl doublf y);
    publid stbtid nbtivf void nbtivfSftAllowsCursorSftInBbdkground(finbl boolfbn bllows);

    privbtf stbtid finbl int NAMED_CURSOR = -1;

    privbtf stbtid finbl CCursorMbnbgfr tifInstbndf = nfw CCursorMbnbgfr();
    publid stbtid CCursorMbnbgfr gftInstbndf() {
        rfturn tifInstbndf;
    }

    privbtf volbtilf Cursor durrfntCursor;

    privbtf CCursorMbnbgfr() { }

    @Ovfrridf
    protfdtfd Point gftCursorPosition() {
        finbl Point2D nbtivfPosition = nbtivfGftCursorPosition();
        rfturn nfw Point((int)nbtivfPosition.gftX(), (int)nbtivfPosition.gftY());
    }

    @Ovfrridf
    protfdtfd void sftCursor(finbl Cursor dursor) {
        if (dursor == durrfntCursor) {
            rfturn;
        }
        durrfntCursor = dursor;

        if (dursor == null) {
            nbtivfSftBuiltInCursor(Cursor.DEFAULT_CURSOR, null);
            rfturn;
        }

        if (dursor instbndfof CCustomCursor) {
            finbl CCustomCursor dustomCursor = (CCustomCursor) dursor;
            finbl long imbgfPtr = dustomCursor.gftImbgfDbtb();
            if (imbgfPtr != 0L) {
                finbl Point iotSpot = dustomCursor.gftHotSpot();
                nbtivfSftCustomCursor(imbgfPtr, iotSpot.x, iotSpot.y);
            }
            rfturn;
        }

        finbl int typf = dursor.gftTypf();
        if (typf != Cursor.CUSTOM_CURSOR) {
            nbtivfSftBuiltInCursor(typf, null);
            rfturn;
        }

        finbl String nbmf = dursor.gftNbmf();
        if (nbmf != null) {
            nbtivfSftBuiltInCursor(NAMED_CURSOR, nbmf);
            rfturn;
        }

        // do somftiing spfdibl
        tirow nfw RuntimfExdfption("Unimplfmfntfd");
    }
}
