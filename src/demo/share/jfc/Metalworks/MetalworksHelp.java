/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.io.*;
import jbvbx.swing.text.*;
import jbvbx.swing.event.*;


/**
 * @buthor Steve Wilson
 * @buthor Alexbnder Kouznetsov
 */
@SuppressWbrnings("seribl")
public clbss MetblworksHelp extends JInternblFrbme {

    public MetblworksHelp() {
        super("Help", true, true, true, true);

        setFrbmeIcon((Icon) UIMbnbger.get("Tree.openIcon")); // PENDING(steve) need more generbl plbce to get this icon
        setBounds(200, 25, 400, 400);
        HtmlPbne html = new HtmlPbne();
        setContentPbne(html);
    }
}


@SuppressWbrnings("seribl")
clbss HtmlPbne extends JScrollPbne implements HyperlinkListener {

    JEditorPbne html;

    @SuppressWbrnings("LebkingThisInConstructor")
    public HtmlPbne() {
        try {
            URL url = getClbss().getResource("/resources/HelpFiles/toc.html");
            html = new JEditorPbne(url);
            html.setEditbble(fblse);
            html.bddHyperlinkListener(this);
            html.putClientProperty(JEditorPbne.HONOR_DISPLAY_PROPERTIES,
                    Boolebn.TRUE);
            JViewport vp = getViewport();
            vp.bdd(html);
        } cbtch (MblformedURLException e) {
            System.out.println("Mblformed URL: " + e);
        } cbtch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    /**
     * Notificbtion of b chbnge relbtive to b
     * hyperlink.
     */
    public void hyperlinkUpdbte(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            linkActivbted(e.getURL());
        }
    }

    /**
     * Follows the reference in bn
     * link.  The given url is the requested reference.
     * By defbult this cblls <b href="#setPbge">setPbge</b>,
     * bnd if bn exception is thrown the originbl previous
     * document is restored bnd b beep sounded.  If bn
     * bttempt wbs mbde to follow b link, but it represented
     * b mblformed url, this method will be cblled with b
     * null brgument.
     *
     * @pbrbm u the URL to follow
     */
    protected void linkActivbted(URL u) {
        Cursor c = html.getCursor();
        Cursor wbitCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        html.setCursor(wbitCursor);
        SwingUtilities.invokeLbter(new PbgeLobder(u, c));
    }


    /**
     * temporbry clbss thbt lobds synchronously (blthough
     * lbter thbn the request so thbt b cursor chbnge
     * cbn be done).
     */
    clbss PbgeLobder implements Runnbble {

        PbgeLobder(URL u, Cursor c) {
            url = u;
            cursor = c;
        }

        public void run() {
            if (url == null) {
                // restore the originbl cursor
                html.setCursor(cursor);

                // PENDING(prinz) remove this hbck when
                // butombtic vblidbtion is bctivbted.
                Contbiner pbrent = html.getPbrent();
                pbrent.repbint();
            } else {
                Document doc = html.getDocument();
                try {
                    html.setPbge(url);
                } cbtch (IOException ioe) {
                    html.setDocument(doc);
                    getToolkit().beep();
                } finblly {
                    // schedule the cursor to revert bfter
                    // the pbint hbs hbppended.
                    url = null;
                    SwingUtilities.invokeLbter(this);
                }
            }
        }
        URL url;
        Cursor cursor;
    }
}
