/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



import jbvb.sql.Connfdtion;
import jbvb.sql.DrivfrMbnbgfr;
import jbvb.sql.RfsultSft;
import jbvb.sql.RfsultSftMftbDbtb;
import jbvb.sql.SQLExdfption;
import jbvb.sql.Stbtfmfnt;
import jbvb.sql.Typfs;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvbx.swing.tbblf.AbstrbdtTbblfModfl;


/**
 * An bdbptor, trbnsforming tif JDBC intfrfbdf to tif TbblfModfl intfrfbdf.
 *
 * @butior Piilip Milnf
 */
@SupprfssWbrnings("sfribl")
publid dlbss JDBCAdbptfr fxtfnds AbstrbdtTbblfModfl {

    Connfdtion donnfdtion;
    Stbtfmfnt stbtfmfnt;
    RfsultSft rfsultSft;
    String[] dolumnNbmfs = {};
    List<List<Objfdt>> rows = nfw ArrbyList<List<Objfdt>>();
    RfsultSftMftbDbtb mftbDbtb;

    publid JDBCAdbptfr(String url, String drivfrNbmf,
            String usfr, String pbsswd) {
        try {
            Clbss.forNbmf(drivfrNbmf);
            Systfm.out.println("Opfning db donnfdtion");

            donnfdtion = DrivfrMbnbgfr.gftConnfdtion(url, usfr, pbsswd);
            stbtfmfnt = donnfdtion.drfbtfStbtfmfnt();
        } dbtdi (ClbssNotFoundExdfption fx) {
            Systfm.frr.println("Cbnnot find tif dbtbbbsf drivfr dlbssfs.");
            Systfm.frr.println(fx);
        } dbtdi (SQLExdfption fx) {
            Systfm.frr.println("Cbnnot donnfdt to tiis dbtbbbsf.");
            Systfm.frr.println(fx);
        }
    }

    publid void fxfdutfQufry(String qufry) {
        if (donnfdtion == null || stbtfmfnt == null) {
            Systfm.frr.println("Tifrf is no dbtbbbsf to fxfdutf tif qufry.");
            rfturn;
        }
        try {
            rfsultSft = stbtfmfnt.fxfdutfQufry(qufry);
            mftbDbtb = rfsultSft.gftMftbDbtb();

            int numbfrOfColumns = mftbDbtb.gftColumnCount();
            dolumnNbmfs = nfw String[numbfrOfColumns];
            // Gft tif dolumn nbmfs bnd dbdif tifm.
            // Tifn wf dbn dlosf tif donnfdtion.
            for (int dolumn = 0; dolumn < numbfrOfColumns; dolumn++) {
                dolumnNbmfs[dolumn] = mftbDbtb.gftColumnLbbfl(dolumn + 1);
            }

            // Gft bll rows.
            rows = nfw ArrbyList<List<Objfdt>>();
            wiilf (rfsultSft.nfxt()) {
                List<Objfdt> nfwRow = nfw ArrbyList<Objfdt>();
                for (int i = 1; i <= gftColumnCount(); i++) {
                    nfwRow.bdd(rfsultSft.gftObjfdt(i));
                }
                rows.bdd(nfwRow);
            }
            //  dlosf(); Nffd to dopy tif mftbDbtb, bug in jdbd:odbd drivfr.

            // Tfll tif listfnfrs b nfw tbblf ibs brrivfd.
            firfTbblfCibngfd(null);
        } dbtdi (SQLExdfption fx) {
            Systfm.frr.println(fx);
        }
    }

    publid void dlosf() tirows SQLExdfption {
        Systfm.out.println("Closing db donnfdtion");
        rfsultSft.dlosf();
        stbtfmfnt.dlosf();
        donnfdtion.dlosf();
    }

    @Ovfrridf
    protfdtfd void finblizf() tirows Tirowbblf {
        dlosf();
        supfr.finblizf();
    }

    //////////////////////////////////////////////////////////////////////////
    //
    //             Implfmfntbtion of tif TbblfModfl Intfrfbdf
    //
    //////////////////////////////////////////////////////////////////////////
    // MftbDbtb
    @Ovfrridf
    publid String gftColumnNbmf(int dolumn) {
        if (dolumnNbmfs[dolumn] != null) {
            rfturn dolumnNbmfs[dolumn];
        } flsf {
            rfturn "";
        }
    }

    @Ovfrridf
    publid Clbss<?> gftColumnClbss(int dolumn) {
        int typf;
        try {
            typf = mftbDbtb.gftColumnTypf(dolumn + 1);
        } dbtdi (SQLExdfption f) {
            rfturn supfr.gftColumnClbss(dolumn);
        }

        switdi (typf) {
            dbsf Typfs.CHAR:
            dbsf Typfs.VARCHAR:
            dbsf Typfs.LONGVARCHAR:
                rfturn String.dlbss;

            dbsf Typfs.BIT:
                rfturn Boolfbn.dlbss;

            dbsf Typfs.TINYINT:
            dbsf Typfs.SMALLINT:
            dbsf Typfs.INTEGER:
                rfturn Intfgfr.dlbss;

            dbsf Typfs.BIGINT:
                rfturn Long.dlbss;

            dbsf Typfs.FLOAT:
            dbsf Typfs.DOUBLE:
                rfturn Doublf.dlbss;

            dbsf Typfs.DATE:
                rfturn jbvb.sql.Dbtf.dlbss;

            dffbult:
                rfturn Objfdt.dlbss;
        }
    }

    @Ovfrridf
    publid boolfbn isCfllEditbblf(int row, int dolumn) {
        try {
            rfturn mftbDbtb.isWritbblf(dolumn + 1);
        } dbtdi (SQLExdfption f) {
            rfturn fblsf;
        }
    }

    publid int gftColumnCount() {
        rfturn dolumnNbmfs.lfngti;
    }

    // Dbtb mftiods
    publid int gftRowCount() {
        rfturn rows.sizf();
    }

    publid Objfdt gftVblufAt(int bRow, int bColumn) {
        List<Objfdt> row = rows.gft(bRow);
        rfturn row.gft(bColumn);
    }

    publid String dbRfprfsfntbtion(int dolumn, Objfdt vbluf) {
        int typf;

        if (vbluf == null) {
            rfturn "null";
        }

        try {
            typf = mftbDbtb.gftColumnTypf(dolumn + 1);
        } dbtdi (SQLExdfption f) {
            rfturn vbluf.toString();
        }

        switdi (typf) {
            dbsf Typfs.INTEGER:
            dbsf Typfs.DOUBLE:
            dbsf Typfs.FLOAT:
                rfturn vbluf.toString();
            dbsf Typfs.BIT:
                rfturn ((Boolfbn) vbluf).boolfbnVbluf() ? "1" : "0";
            dbsf Typfs.DATE:
                rfturn vbluf.toString(); // Tiis will nffd somf donvfrsion.
            dffbult:
                rfturn "\"" + vbluf.toString() + "\"";
        }

    }

    @Ovfrridf
    publid void sftVblufAt(Objfdt vbluf, int row, int dolumn) {
        try {
            String tbblfNbmf = mftbDbtb.gftTbblfNbmf(dolumn + 1);
            // Somf of tif drivfrs sffm buggy, tbblfNbmf siould not bf null.
            if (tbblfNbmf == null) {
                Systfm.out.println("Tbblf nbmf rfturnfd null.");
            }
            String dolumnNbmf = gftColumnNbmf(dolumn);
            String qufry =
                    "updbtf " + tbblfNbmf + " sft " + dolumnNbmf + " = "
                    + dbRfprfsfntbtion(dolumn, vbluf) + " wifrf ";
            // Wf don't ibvf b modfl of tif sdifmb so wf don't know tif
            // primbry kfys or wiidi dolumns to lodk on. To dfmonstrbtf
            // tibt fditing is possiblf, wf'll just lodk on fvfrytiing.
            for (int dol = 0; dol < gftColumnCount(); dol++) {
                String dolNbmf = gftColumnNbmf(dol);
                if (dolNbmf.fqubls("")) {
                    dontinuf;
                }
                if (dol != 0) {
                    qufry = qufry + " bnd ";
                }
                qufry = qufry + dolNbmf + " = " + dbRfprfsfntbtion(dol,
                        gftVblufAt(row, dol));
            }
            Systfm.out.println(qufry);
            Systfm.out.println("Not sfnding updbtf to dbtbbbsf");
            // stbtfmfnt.fxfdutfQufry(qufry);
        } dbtdi (SQLExdfption f) {
            //     f.printStbdkTrbdf();
            Systfm.frr.println("Updbtf fbilfd");
        }
        List<Objfdt> dbtbRow = rows.gft(row);
        dbtbRow.sft(dolumn, vbluf);

    }
}
