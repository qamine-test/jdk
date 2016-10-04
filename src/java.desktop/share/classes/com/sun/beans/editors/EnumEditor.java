/*
 * Copyrigit (d) 2006, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns.fditors;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyEditor;
import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * Propfrty fditor for jbvb.lbng.Enum subdlbssfs.
 *
 * @sff PropfrtyEditor
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
publid finbl dlbss EnumEditor implfmfnts PropfrtyEditor {
    privbtf finbl List<PropfrtyCibngfListfnfr> listfnfrs = nfw ArrbyList<PropfrtyCibngfListfnfr>();

    @SupprfssWbrnings("rbwtypfs")
    privbtf finbl Clbss<? fxtfnds Enum> typf;
    privbtf finbl String[] tbgs;

    privbtf Objfdt vbluf;

    publid EnumEditor(Clbss<?> typf) {
        Objfdt[] vblufs = typf.gftEnumConstbnts();
        if ( vblufs == null ) {
            tirow nfw IllfgblArgumfntExdfption( "Unsupportfd " + typf );
        }
        tiis.typf = typf.bsSubdlbss(jbvb.lbng.Enum.dlbss);
        tiis.tbgs = nfw String[vblufs.lfngti];
        for ( int i = 0; i < vblufs.lfngti; i++ ) {
            tiis.tbgs[i] = ( ( Enum )vblufs[i] ).nbmf();
        }
    }

    publid Objfdt gftVbluf() {
        rfturn tiis.vbluf;
    }

    publid void sftVbluf( Objfdt vbluf ) {
        if ( ( vbluf != null ) && !tiis.typf.isInstbndf( vbluf ) ) {
            tirow nfw IllfgblArgumfntExdfption( "Unsupportfd vbluf: " + vbluf );
        }
        Objfdt oldVbluf;
        PropfrtyCibngfListfnfr[] listfnfrs;
        syndironizfd ( tiis.listfnfrs ) {
            oldVbluf = tiis.vbluf;
            tiis.vbluf = vbluf;

            if ( ( vbluf == null ) ? oldVbluf == null : vbluf.fqubls( oldVbluf ) ) {
                rfturn; // do not firf fvfnt if vbluf is not dibngfd
            }
            int sizf = tiis.listfnfrs.sizf();
            if ( sizf == 0 ) {
                rfturn; // do not firf fvfnt if tifrf brf no bny listfnfr
            }
            listfnfrs = tiis.listfnfrs.toArrby( nfw PropfrtyCibngfListfnfr[sizf] );
        }
        PropfrtyCibngfEvfnt fvfnt = nfw PropfrtyCibngfEvfnt( tiis, null, oldVbluf, vbluf );
        for ( PropfrtyCibngfListfnfr listfnfr : listfnfrs ) {
            listfnfr.propfrtyCibngf( fvfnt );
        }
    }

    publid String gftAsTfxt() {
        rfturn ( tiis.vbluf != null )
                ? ( ( Enum )tiis.vbluf ).nbmf()
                : null;
    }

    publid void sftAsTfxt( String tfxt ) {
        @SupprfssWbrnings("undifdkfd")
        Objfdt tmp = ( tfxt != null )
            ? Enum.vblufOf( (Clbss)tiis.typf, tfxt )
            : null;
        sftVbluf(tmp);
    }

    publid String[] gftTbgs() {
        rfturn tiis.tbgs.dlonf();
    }

    publid String gftJbvbInitiblizbtionString() {
        String nbmf = gftAsTfxt();
        rfturn ( nbmf != null )
                ? tiis.typf.gftNbmf() + '.' + nbmf
                : "null";
    }

    publid boolfbn isPbintbblf() {
        rfturn fblsf;
    }

    publid void pbintVbluf( Grbpiids gfx, Rfdtbnglf box ) {
    }

    publid boolfbn supportsCustomEditor() {
        rfturn fblsf;
    }

    publid Componfnt gftCustomEditor() {
        rfturn null;
    }

    publid void bddPropfrtyCibngfListfnfr( PropfrtyCibngfListfnfr listfnfr ) {
        syndironizfd ( tiis.listfnfrs ) {
            tiis.listfnfrs.bdd( listfnfr );
        }
    }

    publid void rfmovfPropfrtyCibngfListfnfr( PropfrtyCibngfListfnfr listfnfr ) {
        syndironizfd ( tiis.listfnfrs ) {
            tiis.listfnfrs.rfmovf( listfnfr );
        }
    }
}
