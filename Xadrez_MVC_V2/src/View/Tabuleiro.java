/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * tabuleiro.java
 *
 * Created on 03/03/2011, 18:28:20
 */
package View;

import Controler.TabuleiroController;
import Controler.Carregar;
import Model.ModelTabuleiro;
import Model.Peca;
import Controler.Salvar;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author felipelageduarte
 */
public class Tabuleiro extends javax.swing.JFrame implements Observer {

    
  private final Canvas canvas;  
  private Point mouseCoord;
  private final ModelTabuleiro model;
  private TabuleiroController controller;
  //private JPanel CanvasD;//atributo com a janela de desenho
  
  /** Creates new form tabuleiro
     * @param model */
  public Tabuleiro(ModelTabuleiro model,TabuleiroController controller) {
     this.controller = controller;
    initComponents();
    canvas = new Canvas();
    mouseCoord = new Point();
    canvas.registerObserver(this);
    this.model = model;
    canvas.registerObserver(model);
    
    Dimension area = new Dimension(jPCanvas.getWidth(), jPCanvas.getHeight());
    canvas.setPreferredSize(area);//set dimensao do painel de desenho
    jPCanvas.setLayout(new GridLayout(1, 1));
    jPCanvas.add(canvas);
    
    
  }
   
    public Point getMouseCoord() {
        return mouseCoord;
    }
    
    public JInternalFrame getErrorWindow(){
        return ErrorWindow;
    }
    
    public JInternalFrame getVictoryWindow(){
        return victoryWindow;
    }
    public void setMouseCoord(Point mouseCoord) {
        this.mouseCoord = mouseCoord;
    }
    
    public JLabel getGlobalTime() {
        return TempoGlobal;
    }
    
    public JLabel getErrorLabel() {
        return ErrorLabel;
    }
    
    public JLabel getTurnTime() {
        return TempoTurno;
    }
  
    public JLabel getClickLabel() {
        return clickLabel;
    }

    public JLabel getCoordenadaLabel() {
        return coordenadaLabel;
    }
    
    public JLabel getVictoryLabel() {
        return victoryLabel;
    }
    
    public JPanel getPromotionTab() {
        return PawnPromotion;
    }
    
    public JLabel getPecaLabel() {
        return pecaLabel;
    }
    
    public JLabel getMovLabel() {
        return movLabel;
    }
    
    public JLabel getScoreLabelBrancas() {
        return scoreLabelBrancas;
    }
    
    public JPanel getJPCanvas(){
        return jPCanvas;
    }
    
    public JPanel getJPpanel(){
        return jPanel1;
    }
    
    public JLabel getScoreLabelPretas() {
        return scoreLabelPretas;
    }
    
    public JLabel getTurnLabel() {
        return turnLabel;
    }
    
    public JToggleButton getQueen() {
        return jToggleButton1;
    }

    public void setClickLabel(JLabel clickLabel) {
        this.clickLabel = clickLabel;
    }

    public void setCoordenadaLabel(JLabel coordenadaLabel) {
        this.coordenadaLabel = coordenadaLabel;
    }

    
    public void addController(TabuleiroController controller){
        jPCanvas.addMouseListener(controller);
        jPCanvas.addMouseMotionListener(controller);
        
    }
  
 public void drawMouseQuadrante(Graphics2D g) {
        
        int width = canvas.getWidth()/8;
        int height = canvas.getHeight()/8;
        
        int qx = mouseCoord.x/width;
        int qy = mouseCoord.y/height;
        //System.out.println(qx + "   " + qy);
        
        int squareWidth = g.getClip().getBounds().width / 8;
        int squareHeight = g.getClip().getBounds().height / 8;
        
        squareWidth++; // gambiarra 1
        squareHeight++;  // gambiarra 2

        //System.out.println(squareWidth + "   " + squareHeight);
        
        g.setColor(Color.red);
        g.setStroke(new BasicStroke(2));
        g.drawRect(qx * squareWidth, qy * squareHeight, squareWidth, squareHeight);
        g.setColor(Color.BLACK);
    }   
    
 public void drawMovementSquare(Graphics2D g,Point[] movements) { // Pinta os quadrantes com movimentos válidos
        int width = canvas.getWidth()/8;
        int height = canvas.getHeight()/8;
        
        for(Point p : movements){
        if(p != null){
        int qx = p.x;
        int qy = p.y;
        //System.out.println(qx + "   " + qy);
        
        int squareWidth = g.getClip().getBounds().width / 8;
        int squareHeight = g.getClip().getBounds().height / 8;
        
        squareWidth++; // gambiarra 1
        squareHeight++;  // gambiarra 2

        //System.out.println(squareWidth + "   " + squareHeight);
        
        g.setColor(Color.GREEN);
        g.setStroke(new BasicStroke(2));
        g.drawRect(qx * squareWidth, qy * squareHeight, squareWidth, squareHeight);
        g.setColor(Color.BLACK);
        }
        }             
    }  
    
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPCanvas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        coordenadaLabel = new javax.swing.JLabel();
        clickLabel = new javax.swing.JLabel();
        pecaLabel = new javax.swing.JLabel();
        movLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scoreLabelBrancas = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        scoreLabelPretas = new javax.swing.JLabel();
        turnLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        PawnPromotion = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        TempoTurno = new javax.swing.JLabel();
        TempoGlobal = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        LoadButton = new javax.swing.JButton();
        TempoTurno1 = new javax.swing.JLabel();
        TempoTurno2 = new javax.swing.JLabel();
        ErrorWindow = new javax.swing.JInternalFrame();
        ErrorLabel = new javax.swing.JLabel();
        ErrorButton = new javax.swing.JButton();
        victoryWindow = new javax.swing.JInternalFrame();
        victoryLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0204 - Xadrez");
        setResizable(false);

        jPCanvas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPCanvas.setMaximumSize(new java.awt.Dimension(400, 400));
        jPCanvas.setMinimumSize(new java.awt.Dimension(400, 400));
        jPCanvas.setPreferredSize(new java.awt.Dimension(480, 480));

        javax.swing.GroupLayout jPCanvasLayout = new javax.swing.GroupLayout(jPCanvas);
        jPCanvas.setLayout(jPCanvasLayout);
        jPCanvasLayout.setHorizontalGroup(
            jPCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );
        jPCanvasLayout.setVerticalGroup(
            jPCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jLabel2.setText("Coordenada:");

        jLabel3.setText("click:");

        coordenadaLabel.setText(" ");

        clickLabel.setText(" ");

        pecaLabel.setText(" ");

        movLabel.setText(" ");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Pontuação Brancas:");
        jLabel1.setToolTipText("");

        scoreLabelBrancas.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        scoreLabelBrancas.setText("0");

        jLabel4.setText("Pontuação Pretas:");

        scoreLabelPretas.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        scoreLabelPretas.setText("0");

        turnLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        turnLabel.setText("XXXXXX");

        statusLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        statusLabel.setText("Turno das");

        PawnPromotion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jCheckBox1.setText("Dama");

        jCheckBox2.setText("Torre");

        jCheckBox3.setText("Bispo");

        jCheckBox4.setText("Cavalo");

        jLabel5.setText("Promover Peão");

        jToggleButton1.setText("botao");

        javax.swing.GroupLayout PawnPromotionLayout = new javax.swing.GroupLayout(PawnPromotion);
        PawnPromotion.setLayout(PawnPromotionLayout);
        PawnPromotionLayout.setHorizontalGroup(
            PawnPromotionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PawnPromotionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PawnPromotionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jToggleButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PawnPromotionLayout.setVerticalGroup(
            PawnPromotionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PawnPromotionLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addContainerGap())
        );

        TempoTurno.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        TempoTurno.setText("00:00");

        TempoGlobal.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        TempoGlobal.setText("00:00");

        SaveButton.setText("Salvar");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        LoadButton.setText("Carregar");
        LoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadButtonActionPerformed(evt);
            }
        });

        TempoTurno1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        TempoTurno1.setText("Global:");

        TempoTurno2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        TempoTurno2.setText("Turno:");

        ErrorWindow.setVisible(true);

        ErrorLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ErrorLabel.setText("ERROR");

        ErrorButton.setText("Fechar");
        ErrorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ErrorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ErrorWindowLayout = new javax.swing.GroupLayout(ErrorWindow.getContentPane());
        ErrorWindow.getContentPane().setLayout(ErrorWindowLayout);
        ErrorWindowLayout.setHorizontalGroup(
            ErrorWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ErrorWindowLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(ErrorWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ErrorLabel)
                    .addComponent(ErrorButton))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        ErrorWindowLayout.setVerticalGroup(
            ErrorWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ErrorWindowLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(ErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(ErrorButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(SaveButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LoadButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(TempoTurno1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TempoGlobal))
                                    .addComponent(statusLabel)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel4)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(scoreLabelBrancas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(scoreLabelPretas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(turnLabel))
                                            .addGap(13, 13, 13))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(TempoTurno2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(TempoTurno))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PawnPromotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(ErrorWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabelBrancas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreLabelPretas)
                .addGap(17, 17, 17)
                .addComponent(statusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(turnLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TempoTurno1)
                    .addComponent(TempoGlobal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TempoTurno)
                    .addComponent(TempoTurno2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PawnPromotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ErrorWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(LoadButton))
                .addContainerGap())
        );

        victoryWindow.setVisible(true);

        victoryLabel.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        victoryLabel.setText("Brancas");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 50)); // NOI18N
        jLabel7.setText("Vitória das");

        jButton1.setFont(new java.awt.Font("Arial", 0, 40)); // NOI18N
        jButton1.setText("Fechar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout victoryWindowLayout = new javax.swing.GroupLayout(victoryWindow.getContentPane());
        victoryWindow.getContentPane().setLayout(victoryWindowLayout);
        victoryWindowLayout.setHorizontalGroup(
            victoryWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, victoryWindowLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(victoryWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, victoryWindowLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, victoryWindowLayout.createSequentialGroup()
                        .addGroup(victoryWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(victoryLabel))
                        .addGap(81, 81, 81))))
        );
        victoryWindowLayout.setVerticalGroup(
            victoryWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, victoryWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(victoryLabel)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pecaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(39, 39, 39))
                            .addComponent(movLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(coordenadaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(clickLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(145, 145, 145)
                    .addComponent(victoryWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(347, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(coordenadaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(clickLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pecaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movLabel)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(137, 137, 137)
                    .addComponent(victoryWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(94, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        // TODO add your handling code here:
      
        Salvar save = new Salvar(model,controller,this);
       
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void LoadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadButtonActionPerformed
        // TODO add your handling code here:
       
        Carregar save = new Carregar(model,controller,this);
        getScoreLabelBrancas().setText(""+model.getScoreBrancas());
        getScoreLabelPretas().setText(""+model.getScorePretas());
        repaint();
        
    }//GEN-LAST:event_LoadButtonActionPerformed

    private void ErrorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ErrorButtonActionPerformed
        // TODO add your handling code here:
        ErrorWindow.setVisible(false);
    }//GEN-LAST:event_ErrorButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ErrorButton;
    private javax.swing.JLabel ErrorLabel;
    private javax.swing.JInternalFrame ErrorWindow;
    private javax.swing.JButton LoadButton;
    private javax.swing.JPanel PawnPromotion;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel TempoGlobal;
    private javax.swing.JLabel TempoTurno;
    private javax.swing.JLabel TempoTurno1;
    private javax.swing.JLabel TempoTurno2;
    private javax.swing.JLabel clickLabel;
    private javax.swing.JLabel coordenadaLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPCanvas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel movLabel;
    private javax.swing.JLabel pecaLabel;
    private javax.swing.JLabel scoreLabelBrancas;
    private javax.swing.JLabel scoreLabelPretas;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel turnLabel;
    private javax.swing.JLabel victoryLabel;
    private javax.swing.JInternalFrame victoryWindow;
    // End of variables declaration//GEN-END:variables
    private Point[] legalMove;
     public void SetLegalMoves(Point[] x, Peca y){ // x são os movimentos que a peça é capaz de fazer
        if(x == null){
            legalMove = null;
            return;
        }
        
        int i = 0;
        for(Point p : x){ // Conta a quantidade de movimentos que a peça é capaz de fazer
            i++;
        }
        legalMove = new Point[i];
        i=0;
        for(Point p : x){
            if(p!=null){                            
            Peca peca = model.findPeca(p.x, p.y);   
            if(peca!=null && peca.getCor()==y.getCor()){                        
              legalMove[i]=null;                                
            }else{
              legalMove[i] = x[i];
            }
            }else{
              legalMove[i]=null; 
            }
            i++;
        }
        model.SetActualLegalMove(legalMove);
        
    }


    @Override
    public void update(Observable o, Object arg) {
        drawMouseQuadrante((Graphics2D) arg);
        if(legalMove != null){
        drawMovementSquare((Graphics2D) arg,legalMove);
        }
    }
}

/*
class Canvas extends JPanel {
    @Override //sobrescrita do metodo paintComponent da classe JPanel
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    desenhaTabuleiro(g);
  }
  
  private void desenhaTabuleiro(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    // 64 é o numedo de quadrantes de um tabuleiro de xadrez
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {        
        if ((j+i) % 2 == 0) g2d.setColor(Color.black);
        else g2d.setColor(Color.white);
        g2d.fillRect(j * 50, i*50, 50, 50);
      }
    }
  }
  
}
*/
