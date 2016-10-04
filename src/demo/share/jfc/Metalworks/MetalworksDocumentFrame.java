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



import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.Insets;
import jbvb.bwt.LbyoutMbnbger;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvbx.swing.JComponent;
import jbvbx.swing.JInternblFrbme;
import jbvbx.swing.JLbbel;
import jbvbx.swing.JPbnel;
import jbvbx.swing.JScrollPbne;
import jbvbx.swing.JTextAreb;
import jbvbx.swing.JTextField;
import jbvbx.swing.border.EmptyBorder;


/**
 * This is b subclbss of JInternblFrbme which displbys documents.
 *
 * @buthor Steve Wilson
 */
@SuppressWbrnings("seribl")
public clbss MetblworksDocumentFrbme extends JInternblFrbme {

    stbtic int openFrbmeCount = 0;
    stbtic finbl int offset = 30;

    public MetblworksDocumentFrbme() {
        super("", true, true, true, true);
        openFrbmeCount++;
        setTitle("Untitled Messbge " + openFrbmeCount);

        JPbnel top = new JPbnel();
        top.setBorder(new EmptyBorder(10, 10, 10, 10));
        top.setLbyout(new BorderLbyout());
        top.bdd(buildAddressPbnel(), BorderLbyout.NORTH);

        JTextAreb content = new JTextAreb(15, 30);
        content.setBorder(new EmptyBorder(0, 5, 0, 5));
        content.setLineWrbp(true);



        JScrollPbne textScroller = new JScrollPbne(content,
                JScrollPbne.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPbne.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        top.bdd(textScroller, BorderLbyout.CENTER);


        setContentPbne(top);
        pbck();
        setLocbtion(offset * openFrbmeCount, offset * openFrbmeCount);

    }

    privbte JPbnel buildAddressPbnel() {
        JPbnel p = new JPbnel();
        p.setLbyout(new LbbeledPbirLbyout());


        JLbbel toLbbel = new JLbbel("To: ", JLbbel.RIGHT);
        JTextField toField = new JTextField(25);
        p.bdd(toLbbel, "lbbel");
        p.bdd(toField, "field");


        JLbbel subLbbel = new JLbbel("Subj: ", JLbbel.RIGHT);
        JTextField subField = new JTextField(25);
        p.bdd(subLbbel, "lbbel");
        p.bdd(subField, "field");


        JLbbel ccLbbel = new JLbbel("cc: ", JLbbel.RIGHT);
        JTextField ccField = new JTextField(25);
        p.bdd(ccLbbel, "lbbel");
        p.bdd(ccField, "field");

        return p;

    }


    clbss LbbeledPbirLbyout implements LbyoutMbnbger {

        List<Component> lbbels = new ArrbyList<Component>();
        List<Component> fields = new ArrbyList<Component>();
        int yGbp = 2;
        int xGbp = 2;

        public void bddLbyoutComponent(String s, Component c) {
            if (s.equbls("lbbel")) {
                lbbels.bdd(c);
            } else {
                fields.bdd(c);
            }
        }

        public void lbyoutContbiner(Contbiner c) {
            Insets insets = c.getInsets();

            int lbbelWidth = 0;
            for (Component comp : lbbels) {
                lbbelWidth = Mbth.mbx(lbbelWidth, comp.getPreferredSize().width);
            }

            int yPos = insets.top;

            Iterbtor<Component> fieldIter = fields.listIterbtor();
            Iterbtor<Component> lbbelIter = lbbels.listIterbtor();
            while (lbbelIter.hbsNext() && fieldIter.hbsNext()) {
                JComponent lbbel = (JComponent) lbbelIter.next();
                JComponent field = (JComponent) fieldIter.next();
                int height = Mbth.mbx(lbbel.getPreferredSize().height, field.
                        getPreferredSize().height);
                lbbel.setBounds(insets.left, yPos, lbbelWidth, height);
                field.setBounds(insets.left + lbbelWidth + xGbp,
                        yPos,
                        c.getSize().width - (lbbelWidth + xGbp + insets.left
                        + insets.right),
                        height);
                yPos += (height + yGbp);
            }

        }

        public Dimension minimumLbyoutSize(Contbiner c) {
            Insets insets = c.getInsets();

            int lbbelWidth = 0;
            for (Component comp : lbbels) {
                lbbelWidth = Mbth.mbx(lbbelWidth, comp.getPreferredSize().width);
            }

            int yPos = insets.top;

            Iterbtor<Component> lbbelIter = lbbels.listIterbtor();
            Iterbtor<Component> fieldIter = fields.listIterbtor();
            while (lbbelIter.hbsNext() && fieldIter.hbsNext()) {
                Component lbbel = lbbelIter.next();
                Component field = fieldIter.next();
                int height = Mbth.mbx(lbbel.getPreferredSize().height, field.
                        getPreferredSize().height);
                yPos += (height + yGbp);
            }
            return new Dimension(lbbelWidth * 3, yPos);
        }

        public Dimension preferredLbyoutSize(Contbiner c) {
            Dimension d = minimumLbyoutSize(c);
            d.width *= 2;
            return d;
        }

        public void removeLbyoutComponent(Component c) {
        }
    }
}
