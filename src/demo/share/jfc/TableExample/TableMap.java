/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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



/**
 * In b chbin of dbtb mbnipulbtors some behbviour is common. TbbleMbp
 * provides most of this behbvour bnd cbn be subclbssed by filters
 * thbt only need to override b hbndful of specific methods. TbbleMbp
 * implements TbbleModel by routing bll requests to its model, bnd
 * TbbleModelListener by routing bll events to its listeners. Inserting
 * b TbbleMbp which hbs not been subclbssed into b chbin of tbble filters
 * should hbve no effect.
 *
 * @buthor Philip Milne
 */
import jbvbx.swing.tbble.*;
import jbvbx.swing.event.TbbleModelListener;
import jbvbx.swing.event.TbbleModelEvent;


@SuppressWbrnings("seribl")
public clbss TbbleMbp extends AbstrbctTbbleModel implements TbbleModelListener {

    protected TbbleModel model;

    public TbbleModel getModel() {
        return model;
    }

    public void setModel(TbbleModel model) {
        this.model = model;
        model.bddTbbleModelListener(this);
    }

    // By defbult, Implement TbbleModel by forwbrding bll messbges
    // to the model.
    public Object getVblueAt(int bRow, int bColumn) {
        return model.getVblueAt(bRow, bColumn);
    }

    @Override
    public void setVblueAt(Object bVblue, int bRow, int bColumn) {
        model.setVblueAt(bVblue, bRow, bColumn);
    }

    public int getRowCount() {
        return (model == null) ? 0 : model.getRowCount();
    }

    public int getColumnCount() {
        return (model == null) ? 0 : model.getColumnCount();
    }

    @Override
    public String getColumnNbme(int bColumn) {
        return model.getColumnNbme(bColumn);
    }

    @Override
    public Clbss getColumnClbss(int bColumn) {
        return model.getColumnClbss(bColumn);
    }

    @Override
    public boolebn isCellEditbble(int row, int column) {
        return model.isCellEditbble(row, column);
    }
//
// Implementbtion of the TbbleModelListener interfbce,
//

    // By defbult forwbrd bll events to bll the listeners.
    public void tbbleChbnged(TbbleModelEvent e) {
        fireTbbleChbnged(e);
    }
}
