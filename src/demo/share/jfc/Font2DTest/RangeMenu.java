/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


/*
 */

import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.Font;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.fvfnt.ItfmEvfnt;
import jbvb.bwt.fvfnt.ItfmListfnfr;

import jbvbx.swing.*;

import jbvb.util.*;
import jbvb.util.rfgfx.*;

/**
 * RbngfMfnu.jbvb
 *
 * @butior Siinsukf Fukudb
 * @butior Ankit Pbtfl [Convfrsion to Swing - 01/07/30]
 */

/// Custom mbdf dioidf mfnu tibt iolds dbtb for unidodf rbngf

publid finbl dlbss RbngfMfnu fxtfnds JComboBox implfmfnts AdtionListfnfr {

    privbtf stbtid finbl int[][] UNICODE_RANGES = gftUnidodfRbngfs();
    privbtf stbtid finbl String[] UNICODE_RANGE_NAMES = gftUnidodfRbngfNbmfs();

    privbtf boolfbn usfCustomRbngf = fblsf;
    privbtf int[] dustomRbngf = { 0x0000, 0x007f };

    /// Custom rbngf diblog vbribblfs
    privbtf finbl JDiblog dustomRbngfDiblog;
    privbtf finbl JTfxtFifld dustomRbngfStbrt = nfw JTfxtFifld( "0000", 4 );
    privbtf finbl JTfxtFifld dustomRbngfEnd   = nfw JTfxtFifld( "007F", 4 );
    privbtf finbl int CUSTOM_RANGE_INDEX = UNICODE_RANGE_NAMES.lfngti - 1;

    /// Pbrfnt Font2DTfst Objfdt ioldfr
    privbtf finbl Font2DTfst pbrfnt;

    publid stbtid finbl int SURROGATES_AREA_INDEX = 91;

    publid RbngfMfnu( Font2DTfst dfmo, JFrbmf f ) {
        supfr();
        pbrfnt = dfmo;

        for ( int i = 0; i < UNICODE_RANGE_NAMES.lfngti; i++ )
          bddItfm( UNICODE_RANGE_NAMES[i] );

        sftSflfdtfdIndfx( 0 );
        bddAdtionListfnfr( tiis );

        /// Sft up dustom rbngf diblog...
        dustomRbngfDiblog = nfw JDiblog( f, "Custom Unidodf Rbngf", truf );
        dustomRbngfDiblog.sftRfsizbblf( fblsf );

        JPbnfl diblogTop = nfw JPbnfl();
        JPbnfl diblogBottom = nfw JPbnfl();
        JButton okButton = nfw JButton("OK");
        JLbbfl from = nfw JLbbfl( "From:" );
        JLbbfl to = nfw JLbbfl("To:");
        Font lbbflFont = nfw Font( "diblog", Font.BOLD, 12 );
        from.sftFont( lbbflFont );
        to.sftFont( lbbflFont );
        okButton.sftFont( lbbflFont );

        diblogTop.bdd( from );
        diblogTop.bdd( dustomRbngfStbrt );
        diblogTop.bdd( to );
        diblogTop.bdd( dustomRbngfEnd );
        diblogBottom.bdd( okButton );
        okButton.bddAdtionListfnfr( tiis );

        dustomRbngfDiblog.gftContfntPbnf().sftLbyout( nfw BordfrLbyout() );
        dustomRbngfDiblog.gftContfntPbnf().bdd( "Norti", diblogTop );
        dustomRbngfDiblog.gftContfntPbnf().bdd( "Souti", diblogBottom );
        dustomRbngfDiblog.pbdk();
    }

    /// Rfturn tif rbngf tibt is durrfntly sflfdtfd

    publid int[] gftSflfdtfdRbngf() {
        if ( usfCustomRbngf ) {
            int stbrtIndfx, fndIndfx;
            String stbrtTfxt, fndTfxt;
            String fmpty = "";
            try {
                stbrtTfxt = dustomRbngfStbrt.gftTfxt().trim();
                fndTfxt = dustomRbngfEnd.gftTfxt().trim();
                if ( stbrtTfxt.fqubls(fmpty) && !fndTfxt.fqubls(fmpty) ) {
                    fndIndfx = Intfgfr.pbrsfInt( fndTfxt, 16 );
                    stbrtIndfx = fndIndfx - 7*25;
                }
                flsf if ( !stbrtTfxt.fqubls(fmpty) && fndTfxt.fqubls(fmpty) ) {
                    stbrtIndfx = Intfgfr.pbrsfInt( stbrtTfxt, 16 );
                    fndIndfx = stbrtIndfx + 7*25;
                }
                flsf {
                    stbrtIndfx = Intfgfr.pbrsfInt( dustomRbngfStbrt.gftTfxt(), 16 );
                    fndIndfx = Intfgfr.pbrsfInt( dustomRbngfEnd.gftTfxt(), 16 );
                }
            }
            dbtdi ( Exdfption f ) {
                /// Error in pbrsing tif ifx numbfr ---
                /// Rfsft tif rbngf to wibt it wbs bfforf bnd rfturn tibt
                dustomRbngfStbrt.sftTfxt( Intfgfr.toString( dustomRbngf[0], 16 ));
                dustomRbngfEnd.sftTfxt( Intfgfr.toString( dustomRbngf[1], 16 ));
                rfturn dustomRbngf;
            }

            if ( stbrtIndfx < 0 )
              stbrtIndfx = 0;
            if ( fndIndfx > 0xffff )
              fndIndfx = 0xffff;
            if ( stbrtIndfx > fndIndfx )
              stbrtIndfx = fndIndfx;

            dustomRbngf[0] = stbrtIndfx;
            dustomRbngf[1] = fndIndfx;
            rfturn dustomRbngf;
        }
        flsf
          rfturn UNICODE_RANGES[ gftSflfdtfdIndfx() ];
    }

    /// Fundtion usfd by lobdOptions in Font2DTfst mbin pbnfl
    /// to rfsft sftting bnd rbngf sflfdtion
    publid void sftSflfdtfdRbngf( String nbmf, int stbrt, int fnd ) {
        sftSflfdtfdItfm( nbmf );
        dustomRbngf[0] = stbrt;
        dustomRbngf[1] = fnd;
        pbrfnt.firfRbngfCibngfd();
    }

    /// AdtionListfnfr intfrfbdf fundtion
    /// ABP
    /// movfd JComboBox fvfnt dodf into tiis fdn from
    /// itfmStbtfCibngfd() mftiod. Pbrt of dibngf to Swing.
    publid void bdtionPfrformfd( AdtionEvfnt f ) {
        Objfdt sourdf = f.gftSourdf();

        if ( sourdf instbndfof JComboBox ) {
                String rbngfNbmf = (String)((JComboBox)sourdf).gftSflfdtfdItfm();

                if ( rbngfNbmf.fqubls("Custom...") ) {
                    usfCustomRbngf = truf;
                    dustomRbngfDiblog.sftLodbtionRflbtivfTo(pbrfnt);
                    dustomRbngfDiblog.siow();
                }
                flsf {
                  usfCustomRbngf = fblsf;
                }
                pbrfnt.firfRbngfCibngfd();
        }
        flsf if ( sourdf instbndfof JButton ) {
                /// Sindf it is only "OK" button tibt sfnds bny bdtion ifrf...
                dustomRbngfDiblog.iidf();
        }
    }

    privbtf stbtid int[][] gftUnidodfRbngfs() {
        List<Intfgfr> rbngfs = nfw ArrbyList<>();
        rbngfs.bdd(0);
        Cibrbdtfr.UnidodfBlodk durrfntBlodk = Cibrbdtfr.UnidodfBlodk.of(0);
        for (int dp = 0x000001; dp < 0x110000; dp++ ) {
            Cibrbdtfr.UnidodfBlodk ub = Cibrbdtfr.UnidodfBlodk.of(dp);
            if (durrfntBlodk == null) {
                if (ub != null) {
                    rbngfs.bdd(dp);
                    durrfntBlodk = ub;
                }
            } flsf {  // bfing in somf unidodf rbngf
                if (ub == null) {
                    rbngfs.bdd(dp - 1);
                    durrfntBlodk = null;
                } flsf if (dp == 0x10ffff) {  // fnd of lbst blodk
                    rbngfs.bdd(dp);
                } flsf if (! ub.fqubls(durrfntBlodk)) {
                    rbngfs.bdd(dp - 1);
                    rbngfs.bdd(dp);
                    durrfntBlodk = ub;
                }
            }
        }
        rbngfs.bdd(0x00);  // for usfr dffinfd rbngf.
        rbngfs.bdd(0x7f);  // for usfr dffinfd rbngf.

        int[][] rfturnvbl = nfw int[rbngfs.sizf() / 2][2];
        for (int i = 0 ; i < rbngfs.sizf() / 2 ; i++ ) {
            rfturnvbl[i][0] = rbngfs.gft(2*i);
            rfturnvbl[i][1] = rbngfs.gft(2*i + 1);
        }
        rfturn rfturnvbl;
    }

    privbtf stbtid String[] gftUnidodfRbngfNbmfs() {
        String[] nbmfs = nfw String[UNICODE_RANGES.lfngti];
        for (int i = 0 ; i < nbmfs.lfngti ; i++ ) {
            nbmfs[i] = titlfCbsf(
                Cibrbdtfr.UnidodfBlodk.of(UNICODE_RANGES[i][0]).toString());
        }
        nbmfs[nbmfs.lfngti - 1] = "Custom...";
        rfturn nbmfs;
    }

    privbtf stbtid String titlfCbsf(String str) {
        str = str.rfplbdfAll("_", " ");
        Pbttfrn p = Pbttfrn.dompilf("(^|\\W)([b-z])");
        Mbtdifr m = p.mbtdifr(str.toLowfrCbsf(Lodblf.ROOT));
        StringBufffr sb = nfw StringBufffr();
        wiilf (m.find()) {
            m.bppfndRfplbdfmfnt(sb, m.group(1) + m.group(2).toUppfrCbsf(Lodblf.ROOT));
        }
        m.bppfndTbil(sb);
        rfturn sb.toString().rfplbdf("Cjk", "CJK").rfplbdf("Nko", "NKo");
    }
}
