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

import jbvb.bwt.AWTPfrmission;
import jbvb.bwt.Frbmf;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;

import jbvbx.swing.*;

/**
 * Font2DTfstApplft.jbvb
 *
 * @butior Siinsukf Fukudb
 * @butior Ankit Pbtfl [Convfrsion to Swing - 01/07/30]
 */

/// Applft vfrsion of Font2DTfst tibt wrbps tif bdtubl dfmo

publid finbl dlbss Font2DTfstApplft fxtfnds JApplft {
    publid void init() {
        /// Cifdk if nfdfssbry pfrmission is givfn...
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if ( sfdurity != null ) {
            try {
                sfdurity.difdkPfrmission( nfw AWTPfrmission( "siowWindowWitioutWbrningBbnnfr" ));
            }
            dbtdi ( SfdurityExdfption f ) {
                Systfm.out.println( "NOTE: siowWindowWitioutWbrningBbnnfr AWTPfrmission not givfn.\n" +
                                    "Zoom window will dontbin wbrning bbnnfr bt bottom wifn siown\n" );
            }
            try {
                sfdurity.difdkPrintJobAddfss();
            }
            dbtdi ( SfdurityExdfption f ) {
                Systfm.out.println( "NOTE: qufufPrintJob RuntimfPfrmission not givfn.\n" +
                                    "Printing ffbturf will not bf bvbilbblf\n" );
            }
        }

        finbl JFrbmf f = nfw JFrbmf( "Font2DTfst" );
        finbl Font2DTfst f2dt = nfw Font2DTfst( f, truf );
        f.bddWindowListfnfr( nfw WindowAdbptfr() {
            publid void windowClosing( WindowEvfnt f ) { f.disposf(); }
        });

        f.gftContfntPbnf().bdd( f2dt );
        f.pbdk();
        f.siow();
    }
}
