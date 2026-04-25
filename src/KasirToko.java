/**
 * Tugas Personal ke-2 (Week 7)
 * Topik: Stack & Queue - Sistem Kasir Toko
 *
 * Nama: Charys Sonnia Indiriyana
 * NIM: 2902728251
 * Kelas: Computer Science
 */

import java.util.Scanner;

// Class Node untuk pelanggan
class NodePelanggan {
    String nomorAntrian;
    String namaPelanggan;
    double totalBelanja;
    NodePelanggan next;

    NodePelanggan(String nomorAntrian, String namaPelanggan, double totalBelanja) {
        this.nomorAntrian = nomorAntrian;
        this.namaPelanggan = namaPelanggan;
        this.totalBelanja = totalBelanja;
        this.next = null;
    }
}

// CLASS QUEUE (FIFO) untuk antrian pelanggan
class QueueAntrian {
    NodePelanggan front;
    NodePelanggan rear;
    int jumlah = 0;

    // Enqueue - tambah antrian di belakang
    void enqueue(String nomorAntrian, String namaPelanggan, double totalBelanja) {
        NodePelanggan newNode = new NodePelanggan(nomorAntrian, namaPelanggan, totalBelanja);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        jumlah++;
        System.out.println("✓ Data pelanggan ditambahkan ke antrian!");
    }

    // Dequeue - hapus dari depan (layani pelanggan)
    NodePelanggan dequeue() {
        if (front == null) {
            System.out.println("✗ Antrian kosong!");
            return null;
        }
        NodePelanggan temp = front;
        front = front.next;
        if (front == null) rear = null;
        jumlah--;
        return temp;
    }

    // Tampilkan semua antrian
    void tampilkanAntrian() {
        if (front == null) {
            System.out.println("Antrian kosong.");
            return;
        }
        System.out.println("\n=== ANTRIAN PELANGGAN ===");
        NodePelanggan current = front;
        int no = 1;
        while (current != null) {
            System.out.println(no + ". " + current.nomorAntrian + " - " +
                    current.namaPelanggan + " (Rp" + current.totalBelanja + ")");
            current = current.next;
            no++;
        }
        System.out.println("Total antrian: " + jumlah);
    }

    boolean isEmpty() {
        return front == null;
    }
}

// CLASS STACK (LIFO) untuk riwayat transaksi
class StackRiwayat {
    NodePelanggan top;
    int jumlah = 0;

    // Push - simpan transaksi ke stack
    void push(NodePelanggan pelanggan) {
        if (pelanggan == null) return;
        NodePelanggan newNode = new NodePelanggan(pelanggan.nomorAntrian,
                pelanggan.namaPelanggan,
                pelanggan.totalBelanja);
        newNode.next = top;
        top = newNode;
        jumlah++;
        System.out.println("✓ Transaksi disimpan ke riwayat.");
    }

    // Tampilkan riwayat dari terbaru ke lama (LIFO)
    void tampilkanRiwayat() {
        if (top == null) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }
        System.out.println("\n=== RIWAYAT TRANSAKSI (Terbaru ke Lama) ===");
        NodePelanggan current = top;
        int no = 1;
        while (current != null) {
            System.out.println(no + ". " + current.nomorAntrian + " - " +
                    current.namaPelanggan + " (Rp" + current.totalBelanja + ")");
            current = current.next;
            no++;
        }
        System.out.println("Total transaksi: " + jumlah);
    }
}

// Class Main
public class KasirToko {
    public static void main(String[] args) {
        QueueAntrian queue = new QueueAntrian();
        StackRiwayat stack = new StackRiwayat();
        Scanner sc = new Scanner(System.in);
        int pilih;

        do {
            System.out.println("\n=== SISTEM KASIR TOKO ===");
            System.out.println("1. Tambah Antrian");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Antrian");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilih = sc.nextInt(); sc.nextLine();

            switch (pilih) {
                case 1:
                    System.out.print("Masukkan Nomor Antrian: ");
                    String nomor = sc.nextLine();
                    System.out.print("Masukkan Nama Pelanggan: ");
                    String nama = sc.nextLine();
                    System.out.print("Masukkan Total Belanja: ");
                    double total = sc.nextDouble(); sc.nextLine();
                    queue.enqueue(nomor, nama, total);
                    break;

                case 2:
                    NodePelanggan dilayani = queue.dequeue();
                    if (dilayani != null) {
                        System.out.println("\n=== MELAYANI PELANGGAN ===");
                        System.out.println("Nomor Antrian : " + dilayani.nomorAntrian);
                        System.out.println("Nama Pelanggan: " + dilayani.namaPelanggan);
                        System.out.println("Total Belanja : Rp" + dilayani.totalBelanja);
                        stack.push(dilayani);
                    }
                    break;

                case 3:
                    queue.tampilkanAntrian();
                    break;

                case 4:
                    stack.tampilkanRiwayat();
                    break;

                case 5:
                    System.out.println("Keluar dari program...");
                    break;

                default:
                    System.out.println("Pilihan salah!");
            }
        } while (pilih != 5);

        sc.close();
    }
}